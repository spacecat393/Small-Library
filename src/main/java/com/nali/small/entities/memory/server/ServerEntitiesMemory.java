package com.nali.small.entities.memory.server;

import com.nali.data.BothData;
import com.nali.small.entities.bytes.WorkBytes;
import com.nali.small.entities.memory.BothEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import net.minecraft.item.ItemStack;

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
    public ItemStack current_mouth_itemstack;
    public byte[] sync_byte_array;

    public ServerEntitiesMemory(SkinningEntities skinningentities, BothData bothdata, WorkBytes workbytes)
    {
        super(skinningentities, bothdata, workbytes);
        this.entitiesaimemory = new EntitiesAIMemory(skinningentities);
        this.main_work_byte_array = new byte[workbytes.MAX_WORKS()];
        this.current_work_byte_array = new byte[workbytes.MAX_WORKS()];
        this.frame_int_array = new int[skinningentities.getIntegerDataParameterArray().length];
        this.sync_byte_array = new byte[bothdata.MaxSync()];
    }
}
