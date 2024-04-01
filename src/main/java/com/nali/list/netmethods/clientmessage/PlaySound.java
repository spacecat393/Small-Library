package com.nali.list.netmethods.clientmessage;

import com.nali.list.messages.ClientMessage;
import com.nali.small.entities.memory.client.ClientEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.system.bytes.BytesReader;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class PlaySound
{
    public static byte ID = 10;

    public static void run(ClientMessage clientmessage)
    {
        Entity entity = Minecraft.getMinecraft().world.getEntityByID(BytesReader.getInt(clientmessage.data, 1));

        if (entity instanceof SkinningEntities)
        {
            ((ClientEntitiesMemory)((SkinningEntities)entity).bothentitiesmemory).soundrender.play(BytesReader.getInt(clientmessage.data, 1 + 4));
        }
    }
}
