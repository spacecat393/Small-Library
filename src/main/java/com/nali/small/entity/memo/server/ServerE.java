package com.nali.small.entity.memo.server;

import com.nali.data.IBothDaNe;
import com.nali.small.chunk.ChunkLoader;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.IBothE;
import com.nali.small.entity.memo.server.ai.MixAIE;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;

import java.util.Map;
import java.util.UUID;

import static com.nali.small.chunk.ChunkCallBack.CHUNK_MAP;

public abstract class ServerE<SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, A extends MixAIE<SD, BD, E, I, ?>> implements IBothE<SD, BD, E, I>
{
    public static Map<UUID, ServerE> S_MAP;
//    public static List<ServerE> S_LIST;

    public I i;
    public A a;

    public WorldServer worldserver;

//    public UUID owner_uuid;
//    public int id;
    public UUID uuid;
//    public byte[] work_byte_array;
    //    public byte[] server_state_byte_array;
//    public byte[] current_work_byte_array;
//    public byte[] current_server_work_byte_array;
    public int[] frame_int_array;
//    public boolean sus_init;
//    public SkinningEntities skinningentities;

//    public StatLe statle = new StatLe();
//    public ItemStack current_mouth_itemstack;
    public byte[] sync_byte_array;

    public ServerE(I i, A a)
    {
        this.i = i;
        this.a = a;
//        this.a = this.createA();
        this.worldserver = (WorldServer)i.getE().world;
//        int size = (int)Math.ceil(this.i.getAI().length / 8.0F);
//        this.work_byte_array = new byte[(int)Math.ceil(this.i.getAI().length / 8.0F)/*size*/];// /8
//        this.current_work_byte_array = new byte[size];
        this.frame_int_array = new int[i.getIntegerDataParameterArray().length];
        this.sync_byte_array = new byte[this.i.getBD().MaxSync()];
    }

    @Override
    public void onUpdate()
    {
        E e = this.i.getE();
        EntityDataManager entitydatamanager = e.getDataManager();
        DataParameter<Byte>[] byte_dataparameter_array = this.i.getByteDataParameterArray();
        DataParameter<Integer>[] integer_dataparameter = this.i.getIntegerDataParameterArray();

        for (int i = 0; i < byte_dataparameter_array.length; ++i)
        {
            this.sync_byte_array[i] = entitydatamanager.get(byte_dataparameter_array[i]);
        }

//        ItemStack mouth_itemstack = e.offset_itemstack_nonnulllist.get(0);
//        if (mouth_itemstack.isEmpty())
//        {
//            entitydatamanager.set(MOUTH_ITEMSTACK_DATAPARAMETER, ItemStack.EMPTY);
//        }
//        else
//        {
//            entitydatamanager.set(MOUTH_ITEMSTACK_DATAPARAMETER, mouth_itemstack);
//        }

        if (!CHUNK_MAP.containsKey(this))
        {
            ChunkLoader.updateChunk(this);
        }

//        System.arraycopy(this.main_work_byte_array, 0, this.current_work_byte_array, 0, this.current_work_byte_array.length);

        for (int i = 0; i < this.frame_int_array.length; ++i)
        {
            this.frame_int_array[i] = entitydatamanager.get(integer_dataparameter[i]);
        }

        this.updateServer();
        e.width = 0.5F;
        e.height = 0.5F;

        UUID uuid = e.getUniqueID();

        if (!uuid.equals(this.uuid))
        {
            if (this.uuid != null)
            {
                S_MAP.remove(this.uuid);
            }

            S_MAP.put(uuid, this);
            this.uuid = uuid;
        }

//        this.entitiesaimemory.update(this.skinningentities.isMove(), (this.main_work_byte_array[this.workbytes.SIT() / 8] >> this.workbytes.SIT() % 8 & 1) == 0);
        this.a.update();
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
//        if (this.owner_uuid != null)
//        {
//            byte[] byte_array = new byte[16];
//            ByteWriter.set(byte_array, this.owner_uuid, 0);
//            nbttagcompound.setByteArray("owner_uuid", byte_array);
//        }

//        this.entitiesaimemory.writeNBT(nbttagcompound);

//        if (this.main_work_byte_array == null)
//        {
//            this.main_work_byte_array = new byte[this.workbytes.MAX_WORKS()];
//            this.initWorkBytes();
//        }

//        if (!this.sus_init)
//        if ((this.a.state & 4) == 0)
//        {
//            this.main_work_byte_array[this.workbytes.LOCK_INVENTORY() / 8] ^= (byte)Math.pow(2, this.workbytes.LOCK_INVENTORY() % 8);
//            this.main_work_byte_array[this.workbytes.LOCK_DAMAGE() / 8] ^= (byte)Math.pow(2, this.workbytes.LOCK_DAMAGE() % 8);
//
//            if (this.workbytes.CARE_OWNER() != -1)
//            {
//                this.main_work_byte_array[this.workbytes.CARE_OWNER() / 8] ^= (byte)Math.pow(2, this.workbytes.CARE_OWNER() % 8);
//            }
//
//            nbttagcompound.setFloat("float_0", this.i.getBothData().Scale());
//            this.initWriteEntityToNBT(nbttagcompound);
////            nbttagcompound.setByteArray("work_bytes", this.main_work_byte_array);
//        }

//        nbttagcompound.setByteArray("work_bytes", this.main_work_byte_array);

//        nbttagcompound.setBoolean("sus_init", true);

        this.a.writeNBT(nbttagcompound);
//        this.statle.writeNBT(nbttagcompound);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
//        byte[] work_bytes = nbttagcompound.getByteArray("work_bytes");
//        if (work_bytes.length == this.main_work_byte_array.length)
//        {
//            this.main_work_byte_array = work_bytes;
//        }

//        if (nbttagcompound.hasKey("owner_uuid", 7))
//        {
//            this.owner_uuid = ByteReader.getUUID(nbttagcompound.getByteArray("owner_uuid"), 0);
//        }

//        this.entitiesaimemory.readNBT(nbttagcompound);
        this.a.readNBT(nbttagcompound);

//        this.sus_init = nbttagcompound.hasKey("sus_init");
//        if (!this.sus_init)
//        if ((this.a.state & 4) == 0)
//        {
//            this.main_work_byte_array[this.workbytes.LOCK_INVENTORY() / 8] ^= (byte)Math.pow(2, this.workbytes.LOCK_INVENTORY() % 8);
//            this.main_work_byte_array[this.workbytes.LOCK_DAMAGE() / 8] ^= (byte)Math.pow(2, this.workbytes.LOCK_DAMAGE() % 8);
//
//            if (this.workbytes.CARE_OWNER() != -1)
//            {
//                this.main_work_byte_array[this.workbytes.CARE_OWNER() / 8] ^= (byte)Math.pow(2, this.workbytes.CARE_OWNER() % 8);
//            }
//
//            this.i.getE().getDataManager().set(this.i.getFloatDataParameterArray()[0], this.i.getBothData().Scale());
//            this.initReadEntityFromNBT(nbttagcompound);
//
//            this.sus_init = true;
//        }

//        this.statle.readNBT(nbttagcompound);
    }

    @Override
    public void remove()
    {
        ChunkLoader.removeChunk(this.i);
        S_MAP.remove(this.uuid);
    }

//    public Entity getOwner()
//    {
//        if (this.owner_uuid == null)
//        {
//            return null;
//        }
//
//        return this.worldserver.getEntityFromUuid(this.owner_uuid);
//    }

    public Material getMaterial(BlockPos blockpos)
    {
        IBlockState temp_iblockstate = this.i.getE().world.getBlockState(blockpos);
        return temp_iblockstate.getMaterial();
    }

//    public boolean isWork(byte index)
//    {
//        for (byte i = this.workbytes.SIT(); i < index; ++i)
//        {
//            if ((this.current_work_byte_array[i / 8] >> i % 8 & 1) == 1)
//            {
//                return false;
//            }
//        }
//
//        return (this.current_work_byte_array[index / 8] >> index % 8 & 1) == 1;
//    }

//    public boolean isWorkBypass(byte index, byte[] bypass_byte_array)
//    {
//        for (byte i = this.workbytes.SIT(); i < index; ++i)
//        {
//            boolean on_bypass = false;
//            for (byte bypass : bypass_byte_array)
//            {
//                if (i == bypass)
//                {
//                    on_bypass = true;
//                    break;
//                }
//            }
//
//            if (!on_bypass && (this.current_work_byte_array[i / 8] >> i % 8 & 1) == 1)
//            {
//                return false;
//            }
//        }
//
//        return (this.current_work_byte_array[index / 8] >> index % 8 & 1) == 1;
//    }

//    public void initWriteEntityToNBT(NBTTagCompound nbttagcompound)
//    {
//        this.initWorkBytes();
//    }
//
//    public void initReadEntityFromNBT(NBTTagCompound nbttagcompound)
//    {
//        this.initWorkBytes();
//    }

    @Override
    public I getI()
    {
        return this.i;
    }

//    public abstract A createA();

    public abstract int[][] getFrame2DIntArray();

    public abstract void updateServer();
}
