package com.nali.list.netmethods.clientmessage;

import com.nali.list.messages.ClientMessage;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.system.bytes.BytesReader;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

import java.util.UUID;

public class SetWorkBytes
{
    public static int ID = 6;

    public static void run(ClientMessage clientmessage)
    {
        int id = BytesReader.getInt(clientmessage.data, 1);
        Entity entity = Minecraft.getMinecraft().world.getEntityByID(id);
        if (!(entity instanceof SkinningEntities))
        {
            UUID uuid = SkinningEntities.FAKE_CLIENT_ENTITIES_MAP.get(id);
            entity = SkinningEntities.CLIENT_ENTITIES_MAP.get(uuid);
        }

        if (entity instanceof SkinningEntities)
        {
            SkinningEntities skinningentities = (SkinningEntities)entity;
            System.arraycopy(clientmessage.data, 1 + 4, skinningentities.client_work_byte_array, 0, skinningentities.client_work_byte_array.length);
        }
    }
}
