package com.nali.small.entities.memory.server;

import com.nali.data.BothData;
import com.nali.small.entities.bytes.WorkBytes;
import com.nali.small.entities.memory.BothEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.world.ChunkLoader;
import com.nali.system.bytes.BytesReader;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;

import java.util.Map;
import java.util.UUID;

public class ServerEntitiesMemory extends BothEntitiesMemory
{
    public static Map<UUID, SkinningEntities> ENTITIES_MAP;
    public UUID owner_uuid;
    public UUID uuid;
    public byte[] main_work_byte_array;
    //    public byte[] server_state_byte_array;
    public byte[] current_work_byte_array;
//    public byte[] current_server_work_byte_array;
    public int[] frame_int_array;
    public boolean sus_init;
    public SkinningEntities skinningentities;

    public EntitiesAIMemory entitiesaimemory;
    public StatEntitiesMemory statentitiesmemory = new StatEntitiesMemory();
    public ItemStack current_mouth_itemstack;
    public byte[] sync_byte_array;

    public ServerEntitiesMemory(SkinningEntities skinningentities, BothData bothdata, WorkBytes workbytes)
    {
        super(skinningentities, bothdata, workbytes);
        this.entitiesaimemory = new EntitiesAIMemory(skinningentities);
        this.main_work_byte_array = new byte[workbytes.MAX_WORKS()];// /8
        this.current_work_byte_array = new byte[workbytes.MAX_WORKS()];
        this.frame_int_array = new int[skinningentities.getIntegerDataParameterArray().length];
        this.sync_byte_array = new byte[bothdata.MaxSync()];
    }

    public void writeNBT(NBTTagCompound nbttagcompound)
    {
        if (this.owner_uuid != null)
        {
            byte[] byte_array = new byte[16];
            BytesWriter.set(byte_array, this.owner_uuid, 0);
            nbttagcompound.setByteArray("owner_uuid", byte_array);
        }

        this.entitiesaimemory.writeNBT(nbttagcompound);

        if (this.main_work_byte_array == null)
        {
            this.main_work_byte_array = new byte[this.workbytes.MAX_WORKS()];
            this.skinningentities.initWorkBytes();
        }

        if (!this.sus_init)
        {
            this.main_work_byte_array[this.workbytes.LOCK_INVENTORY() / 8] ^= (byte)Math.pow(2, this.workbytes.LOCK_INVENTORY() % 8);
            this.main_work_byte_array[this.workbytes.LOCK_DAMAGE() / 8] ^= (byte)Math.pow(2, this.workbytes.LOCK_DAMAGE() % 8);

            if (this.workbytes.CARE_OWNER() != -1)
            {
                this.main_work_byte_array[this.workbytes.CARE_OWNER() / 8] ^= (byte)Math.pow(2, this.workbytes.CARE_OWNER() % 8);
            }

            nbttagcompound.setFloat("float_0", this.bothdata.Scale());
            this.main_skinningentities.initWriteEntityToNBT(nbttagcompound);
            nbttagcompound.setByteArray("work_bytes", this.main_work_byte_array);
        }

        nbttagcompound.setByteArray("work_bytes", this.main_work_byte_array);

        nbttagcompound.setBoolean("sus_init", true);

        this.statentitiesmemory.writeNBT(nbttagcompound);
    }

    public void readNBT(NBTTagCompound nbttagcompound)
    {
        byte[] work_bytes = nbttagcompound.getByteArray("work_bytes");
        if (work_bytes.length == this.main_work_byte_array.length)
        {
            this.main_work_byte_array = work_bytes;
        }

        if (nbttagcompound.hasKey("owner_uuid", 7))
        {
            this.owner_uuid = BytesReader.getUUID(nbttagcompound.getByteArray("owner_uuid"), 0);
        }

        this.entitiesaimemory.readNBT(nbttagcompound);

        this.sus_init = nbttagcompound.hasKey("sus_init");
        if (!this.sus_init)
        {
            this.main_work_byte_array[this.workbytes.LOCK_INVENTORY() / 8] ^= (byte)Math.pow(2, this.workbytes.LOCK_INVENTORY() % 8);
            this.main_work_byte_array[this.workbytes.LOCK_DAMAGE() / 8] ^= (byte)Math.pow(2, this.workbytes.LOCK_DAMAGE() % 8);

            if (this.workbytes.CARE_OWNER() != -1)
            {
                this.main_work_byte_array[this.workbytes.CARE_OWNER() / 8] ^= (byte)Math.pow(2, this.workbytes.CARE_OWNER() % 8);
            }

            this.main_skinningentities.getDataManager().set(this.main_skinningentities.getFloatDataParameterArray()[0], this.bothdata.Scale());
            this.main_skinningentities.initReadEntityFromNBT(nbttagcompound);

            this.sus_init = true;
        }

        this.statentitiesmemory.readNBT(nbttagcompound);
    }

    public Entity getOwner()
    {
        if (this.owner_uuid == null)
        {
            return null;
        }

        return ((WorldServer)this.main_skinningentities.world).getEntityFromUuid(this.owner_uuid);
    }

    public Material getMaterial(BlockPos blockpos)
    {
        IBlockState temp_iblockstate = this.main_skinningentities.world.getBlockState(blockpos);
        return temp_iblockstate.getMaterial();
    }

    public boolean isWork(byte index)
    {
        for (byte i = this.workbytes.SIT(); i < index; ++i)
        {
            if ((this.current_work_byte_array[i / 8] >> i % 8 & 1) == 1)
            {
                return false;
            }
        }

        return (this.current_work_byte_array[index / 8] >> index % 8 & 1) == 1;
    }

    public boolean isWorkBypass(byte index, byte[] bypass_byte_array)
    {
        for (byte i = this.workbytes.SIT(); i < index; ++i)
        {
            boolean on_bypass = false;
            for (byte bypass : bypass_byte_array)
            {
                if (i == bypass)
                {
                    on_bypass = true;
                    break;
                }
            }

            if (!on_bypass && (this.current_work_byte_array[i / 8] >> i % 8 & 1) == 1)
            {
                return false;
            }
        }

        return (this.current_work_byte_array[index / 8] >> index % 8 & 1) == 1;
    }

    public static void removeFromMap(SkinningEntities skinningentities)
    {
        ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)skinningentities.bothentitiesmemory;
        ChunkLoader.removeChunk(skinningentities);
        ENTITIES_MAP.remove(serverentitiesmemory.uuid);
    }
}
