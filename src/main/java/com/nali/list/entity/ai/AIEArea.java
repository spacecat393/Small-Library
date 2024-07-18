package com.nali.list.entity.ai;

import com.nali.Nali;
import com.nali.da.IBothDaNe;
import com.nali.list.capability.serializable.SmallSakuraSerializable;
import com.nali.list.capability.type.SmallSakuraType;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.method.client.CSetArea;
import com.nali.list.network.method.client.CSetTarget;
import com.nali.list.network.method.client.CSetTroublemaker;
import com.nali.network.NetworkRegistry;
import com.nali.small.entity.EntityRegistry;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.AI;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.small.mixin.IMixinWorldServer;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import java.util.*;

public class AIEArea<SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends AI<SD, BD, E, I, S, A>
{
    public static byte ID;

    public AIEOwner<SD, BD, E, I, S, A> aieowner;
    public Map<UUID, Entity> entity_map;
    public Collection<Entity> entity_collection;

    public List<EntityXPOrb> xp_entity_list = new ArrayList();
    public List<EntityItem> item_entity_list = new ArrayList();
    public List<Entity> all_entity_list = new ArrayList();//target
    public List<Entity> out_entity_list = new ArrayList();//not_target

    public List<Integer> troublemaker_list = new ArrayList();
    public List<Integer> target_list = new ArrayList();

//    public byte state;
    public byte flag;//check_tameable is_tameable | put_player put_owner put_other_tameable put_owner_tameable put_all_tameable put_object

    public AIEArea(S s)
    {
        super(s);
    }

    @Override
    public void init()
    {
        this.aieowner = (AIEOwner<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AIEOwner.ID);
        this.entity_map = ((IMixinWorldServer)this.s.i.getE().world).entitiesByUuid();
    }

    @Override
    public void call()
    {
        switch (this.s.a.byte_array[18])
        {
            case 0:
                this.addTarget();
                break;
            case 1:
                this.clearTarget();
                break;
            case 2:
                this.addTroublemaker();
                break;
            case 3:
                this.clearTroublemaker();
                break;
            case 4:
                this.fetch();
                break;
            default:
                Nali.I.warn("BIT_FLIP");
        }
    }

    public void addTarget()
    {
        byte[] byte_array = this.s.a.byte_array;
        for (int index = 1 + 16 + 1 + 1; index < byte_array.length; index += 4)
        {
            int id = ByteReader.getInt(byte_array, index);
//                            int id = Integer.parseInt(new_string);

            if (id >= EntityRegistry.ENTITY_KEY_ARRAY.length)
            {
                continue;
            }

            boolean result = true;
            for (int i : this.target_list)
            {
                if (i == id)
                {
                    result = false;
                    break;
                }
            }

            if (result)
            {
                this.target_list.add(id);
            }
        }
    }

    public void removeTarget()
    {
//                        String string = new String(servermessage.data, 1 + 16, servermessage.data.length - (1 + 16));
//                        String[] string_array = string.split(" ");

//                        for (String new_string : string_array)
        byte[] byte_array = this.s.a.byte_array;
        for (int x = 1 + 16 + 1 + 1; x < byte_array.length; x += 4)
        {
            int id = ByteReader.getInt(byte_array, x);
//                            int id = Integer.parseInt(new_string);

            int index = 0;
            for (int i : this.target_list)
            {
                if (i == id)
                {
                    this.target_list.remove(index);
                    break;
                }
                ++index;
            }
        }
    }

    public void clearTarget()
    {
        this.target_list.clear();
    }

    public void addTroublemaker()
    {
        byte[] byte_array = this.s.a.byte_array;
//                        String string = new String(servermessage.data, 1 + 16, servermessage.data.length - (1 + 16));
//                        String[] string_array = string.split(" ");

//                        for (String new_string : string_array)
        for (int index = 1 + 16 + 1 + 1; index < byte_array.length; index += 4)
        {
            int id = ByteReader.getInt(byte_array, index);
//                            int id = Integer.parseInt(new_string);

            if (id >= EntityRegistry.ENTITY_KEY_ARRAY.length)
            {
                continue;
            }

            boolean result = true;
            for (int i : this.troublemaker_list)
            {
                if (i == id)
                {
                    result = false;
                    break;
                }
            }

            if (result)
            {
                this.troublemaker_list.add(id);
            }
        }
    }

    public void removeTroublemaker()
    {
        //                        String string = new String(servermessage.data, 1 + 16, servermessage.data.length - (1 + 16));
//                        String[] string_array = string.split(" ");

//                        for (String new_string : string_array)
        byte[] byte_array = this.s.a.byte_array;
        for (int x = 1 + 16 + 1 + 1; x < byte_array.length; x += 4)
        {
            int id = ByteReader.getInt(byte_array, x);
//                            int id = Integer.parseInt(new_string);

            int index = 0;
            for (int i : this.troublemaker_list)
            {
                if (i == id)
                {
                    this.troublemaker_list.remove(index);
                    break;
                }
                ++index;
            }
        }
    }

    public void clearTroublemaker()
    {
        this.troublemaker_list.clear();
    }

    public void set()
    {
        byte[] byte_array = this.s.a.byte_array;
        float id = ByteReader.getFloat(byte_array, 1 + 16 + 1 + 1);
        float x = ByteReader.getFloat(byte_array, 1 + 16 + 1 + 1 + 4);

        SmallSakuraType smallsakuratypes = this.s.a.entityplayermp.getCapability(SmallSakuraSerializable.SMALLSAKURATYPES_CAPABILITY, null);
        byte value = smallsakuratypes.get();

        if (id == 0.1F)
        {
            if (x == 1)
            {
                if (value >= 1)
                {
                    smallsakuratypes.set((byte)(value - 1));
                    this.flag |= 4;
                }
            }
            else
            {
                this.flag &= 255 - 4;
            }
        }
        else if (id == 0.2F)
        {
            if (x == 1)
            {
                if (value >= 1)
                {
                    smallsakuratypes.set((byte)(value - 1));
                    this.flag |= 8;
                }
            }
            else
            {
                this.flag &= 255 - 8;
            }
        }
        else if (id == 0.3F)
        {
            if (x == 1)
            {
                if (value >= 1)
                {
                    smallsakuratypes.set((byte)(value - 1));
                    this.flag |= 128;
                }
            }
            else
            {
                this.flag &= 255 - 128;
            }
        }
        else if (id == 1.1F)
        {
            if (x == 1)
            {
                if (value >= 1)
                {
                    smallsakuratypes.set((byte)(value - 1));
                    this.flag |= 16;
                }
            }
            else
            {
                this.flag &= 255 - 16;
            }
        }
        else if (id == 1.2F)
        {
            if (x == 1)
            {
                if (value >= 1)
                {
                    smallsakuratypes.set((byte)(value - 1));
                    this.flag |= 32;
                }
            }
            else
            {
                this.flag &= 255 - 32;
            }
        }
        else if (id == 1.3F)
        {
            if (x == 1)
            {
                if (value >= 1)
                {
                    smallsakuratypes.set((byte)(value - 1));
                    this.flag |= 64;
                }
            }
            else
            {
                this.flag &= 255 - 64;
            }
        }

        this.fetch();
    }

    public void fetch()
    {
        byte[] byte_array = new byte[1 + 1];
        byte_array[0] = CSetArea.ID;
        byte_array[1] = this.flag;
        NetworkRegistry.I.sendTo(new ClientMessage(byte_array), this.s.a.entityplayermp);
    }

    public void fetchTarget()
    {
        int size = this.target_list.size() * 4;
        byte[] byte_array = new byte[1 + size];
        byte_array[0] = CSetTarget.ID;
        int index = 0;
        for (int i = 1; i < size; i += 4)
        {
            ByteWriter.set(byte_array, this.target_list.get(index++), i);
        }

        NetworkRegistry.I.sendTo(new ClientMessage(byte_array), this.s.a.entityplayermp);
    }

    public void fetchTroublemaker()
    {
        int size = this.troublemaker_list.size() * 4;
        byte[] byte_array = new byte[1 + size];
        byte_array[0] = CSetTroublemaker.ID;
        int index = 0;
        for (int i = 1; i < size; i += 4)
        {
            ByteWriter.set(byte_array, this.troublemaker_list.get(index++), i);
        }

        NetworkRegistry.I.sendTo(new ClientMessage(byte_array), this.s.a.entityplayermp);
    }

    @Override
    public void onUpdate()
    {
        if (this.s.isMove())
        {
            this.xp_entity_list.clear();
            this.item_entity_list.clear();
            this.all_entity_list.clear();
            this.out_entity_list.clear();

            this.entity_collection = new ArrayList(this.entity_map.values());

//            for (Entity entity : new ArrayList<>(this.entity_map.values()))
            for (Entity entity : this.entity_collection)
            {
                if (!entity.isEntityAlive())
                {
                    continue;
                }

                if (entity instanceof EntityItem)
                {
                    this.item_entity_list.add((EntityItem)entity);
                }

                if (entity instanceof EntityXPOrb)
                {
                    this.xp_entity_list.add((EntityXPOrb)entity);
                }

                if (this.isTarget(entity))
                {
                    this.all_entity_list.add(entity);
                }
                else
                {
                    this.out_entity_list.add(entity);
                }
            }
        }
    }

    @Override
    public void writeNBT(NBTTagCompound nbttagcompound)
    {
        NBTTagCompound new_nbttagcompound = new NBTTagCompound();
//        new_nbttagcompound.setByte("state", this.state);
        new_nbttagcompound.setByte("flag", this.flag);
        new_nbttagcompound.setIntArray("target", this.target_list.stream().mapToInt(Integer::intValue).toArray());
        new_nbttagcompound.setIntArray("troublemaker", this.troublemaker_list.stream().mapToInt(Integer::intValue).toArray());
        nbttagcompound.setTag("AIEArea", new_nbttagcompound);
    }

    @Override
    public void readNBT(NBTTagCompound nbttagcompound)
    {
        String key = "AIEArea";
        if (nbttagcompound.hasKey(key))
        {
            NBTTagCompound new_nbttagcompound = (NBTTagCompound)nbttagcompound.getTag(key);
//            this.state = new_nbttagcompound.getByte("state");
            this.flag = new_nbttagcompound.getByte("flag");

            int[] int_array = new_nbttagcompound.getIntArray("target");
            for (int x : int_array)
            {
                this.target_list.add(x);
            }

            int_array = new_nbttagcompound.getIntArray("troublemaker");
            for (int x : int_array)
            {
                this.troublemaker_list.add(x);
            }
        }
    }

    public boolean isTarget(Entity entity)
    {
        boolean result = this.target_list.isEmpty();

        if (result)
        {
            for (Class clasz : EntityRegistry.ENTITIES_CLASS_LIST)
            {
                if (entity.getClass().equals(clasz))
                {
                    return false;
                }
            }
        }
        else
        {
            for (int id : this.target_list)
            {
                if (id < EntityRegistry.ENTITY_KEY_ARRAY.length && entity.getClass().equals(EntityRegistry.ENTITY_KEY_ARRAY[id]))
                {
                    result = true;
                    break;
                }
            }
        }

        for (int id : this.troublemaker_list)
        {
            if (id < EntityRegistry.ENTITY_KEY_ARRAY.length && entity.getClass().equals(EntityRegistry.ENTITY_KEY_ARRAY[id]))
            {
                return false;
            }
        }

        if (result)
        {
            //check tameable
            if ((this.flag & 64) == 0)
            {
                if ((this.flag & 16) == 0)
                {
                    if (entity instanceof EntityTameable)
                    {
                        if (((EntityTameable)entity).getOwnerId() != null)
                        {
                            return false;
                        }
                        else
                        {
                            this.flag |= 1 + 2;
                        }
                    }
                }

                if ((this.flag & 32) == 0)
                {
                    UUID uuid = this.aieowner.uuid;
                    if (uuid != null)
                    {
                        byte flag = (byte)(this.flag & 1+2);
                        if (flag == 0)
                        {
                            if (entity instanceof EntityTameable && ((EntityTameable)entity).getOwnerId() == uuid)
                            {
                                this.flag &= 255-1;
                                return false;
                            }
                        }
                        else if (flag == 3 && ((EntityTameable)entity).getOwnerId() == uuid)
                        {
                            this.flag &= 255-(1+2);
                            return false;
                        }
                    }
                }
            }

            if ((this.flag & 4) == 0 && entity instanceof EntityPlayer)
            {
                return false;
            }

            if ((this.flag & 8) == 0 && entity.getUniqueID().equals(this.aieowner.uuid))
            {
                return false;
            }

            if ((this.flag & 128) == 0 && !(entity instanceof EntityLivingBase))
            {
                return false;
            }
        }

        return result;
    }
}
