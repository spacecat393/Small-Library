package com.nali.list.network.method.client;

import com.nali.list.network.message.ClientMessage;
import com.nali.system.bytes.ByteReader;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;

public class CRenderFood
{
    public static byte ID;

    public static void run(ClientMessage clientmessage)
    {
        Minecraft.getMinecraft().world.spawnParticle(EnumParticleTypes.ITEM_CRACK, ByteReader.getFloat(clientmessage.data, 1), ByteReader.getFloat(clientmessage.data, 1 + 4), ByteReader.getFloat(clientmessage.data, 1 + 4 + 4), 0.0D, 0.0D, 0.0D, ByteReader.getInt(clientmessage.data, 1 + 4 + 4 + 4));
    }
}
