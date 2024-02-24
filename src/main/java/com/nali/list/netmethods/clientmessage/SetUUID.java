package com.nali.list.netmethods.clientmessage;

import com.nali.list.messages.ClientMessage;
import com.nali.small.entities.memory.ClientEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.system.bytes.BytesReader;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class SetUUID
{
    public static byte ID = 5;

    public static void run(ClientMessage clientmessage)
    {
        Entity entity = Minecraft.getMinecraft().world.getEntityByID(BytesReader.getInt(clientmessage.data, 1 + 16));
        if (entity instanceof SkinningEntities)
        {
            ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)((SkinningEntities)entity).bothentitiesmemory;
            cliententitiesmemory.uuid = BytesReader.getUUID(clientmessage.data, 1);
        }
    }
}
