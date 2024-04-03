package com.nali.list.netmethods.clientmessage;

import com.nali.list.messages.ClientMessage;
import com.nali.system.bytes.BytesReader;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;

public class RenderFood
{
    public static byte ID;

    public static void run(ClientMessage clientmessage)
    {
        Minecraft.getMinecraft().world.spawnParticle(EnumParticleTypes.ITEM_CRACK, BytesReader.getFloat(clientmessage.data, 1), BytesReader.getFloat(clientmessage.data, 1 + 4), BytesReader.getFloat(clientmessage.data, 1 + 4 + 4), 0.0D, 0.0D, 0.0D, BytesReader.getInt(clientmessage.data, 1 + 4 + 4 + 4));
    }
}
