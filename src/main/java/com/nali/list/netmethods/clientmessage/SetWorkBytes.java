package com.nali.list.netmethods.clientmessage;

import com.nali.list.messages.ClientMessage;
import com.nali.small.entities.memory.ClientEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.system.bytes.BytesReader;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

import java.util.UUID;

import static com.nali.small.entities.memory.ClientEntitiesMemory.ENTITIES_MAP;
import static com.nali.small.entities.memory.ClientEntitiesMemory.FAKE_ENTITIES_MAP;

public class SetWorkBytes
{
    public static byte ID = 6;

    public static void run(ClientMessage clientmessage)
    {
        int id = BytesReader.getInt(clientmessage.data, 1);
        Entity entity = Minecraft.getMinecraft().world.getEntityByID(id);
        if (!(entity instanceof SkinningEntities))
        {
            UUID uuid = FAKE_ENTITIES_MAP.get(id);
            entity = ENTITIES_MAP.get(uuid);
        }

        if (entity instanceof SkinningEntities)
        {
            SkinningEntities skinningentities = (SkinningEntities)entity;
            ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)((SkinningEntities)entity).bothentitiesmemory;
            System.arraycopy(clientmessage.data, 1 + 4, cliententitiesmemory.work_byte_array, 0, cliententitiesmemory.work_byte_array.length);
        }
    }
}
