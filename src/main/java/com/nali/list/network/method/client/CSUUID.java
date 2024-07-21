package com.nali.list.network.method.client;

import com.nali.list.network.message.ClientMessage;
import com.nali.small.entity.IMixE;
import com.nali.system.bytes.ByteReader;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class CSUUID
{
    public static byte ID;

    public static void run(ClientMessage clientmessage)
    {
        Entity entity = Minecraft.getMinecraft().world.getEntityByID(ByteReader.getInt(clientmessage.data, 1 + 16));
        if (entity instanceof IMixE)
        {
            ((IMixE)entity).getB().setUUID(ByteReader.getUUID(clientmessage.data, 1));
        }
    }
}
