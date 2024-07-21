package com.nali.list.network.method.server;

import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.CEToC;
import com.nali.network.NetworkRegistry;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.UUID;

import static com.nali.small.entity.memo.server.ServerE.S_MAP;

public class SEToC
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        UUID uuid = ByteReader.getUUID(servermessage.data, 1);
        ServerE s = S_MAP.get(uuid);
        if (s != null && (s.a.state & 8) == 8)
        {
//            ChunkLoader.updateChunk(s);
            Entity e = s.i.getE();

            byte[] byte_array = new byte[1 + 16 + 4 + 4 + 4 + 4];
            byte_array[0] = CEToC.ID;

            ByteWriter.set(byte_array, uuid, 1);
            ByteWriter.set(byte_array, e.dimension, 1 + 16);
            ByteWriter.set(byte_array, (float)e.posX, 1 + 16 + 4);
            ByteWriter.set(byte_array, (float)e.posY, 1 + 16 + 4 + 4);
            ByteWriter.set(byte_array, (float)e.posZ, 1 + 16 + 4 + 4 + 4);

            NetworkRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
        }
    }
}
