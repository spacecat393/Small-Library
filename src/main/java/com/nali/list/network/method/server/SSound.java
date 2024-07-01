package com.nali.list.network.method.server;

import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.CSound;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.player.EntityPlayerMP;

public class SSound
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        byte[] byte_array = new byte[1 + 4];
        byte_array[0] = CSound.ID;
        ByteWriter.set(byte_array, ByteReader.getInt(servermessage.data, 1), 1);
    }
}
