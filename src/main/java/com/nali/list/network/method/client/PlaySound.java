package com.nali.list.network.method.client;

import com.nali.list.network.message.ClientMessage;
import com.nali.small.entity.memo.client.ClientLe;
import com.nali.small.entity.EntityLeInv;
import com.nali.system.bytes.BytesReader;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class PlaySound
{
    public static byte ID;

    public static void run(ClientMessage clientmessage)
    {
        Entity entity = Minecraft.getMinecraft().world.getEntityByID(BytesReader.getInt(clientmessage.data, 1));

        if (entity instanceof EntityLeInv)
        {
            ((ClientLe)((EntityLeInv)entity).bothentitiesmemory).soundrender.play(BytesReader.getInt(clientmessage.data, 1 + 4));
        }
    }
}
