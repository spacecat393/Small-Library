package com.nali.list.network.method.server;

import com.nali.list.network.message.ServerMessage;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.system.bytes.ByteReader;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.small.entity.memo.server.ServerE.S_MAP;

public class SAIE
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        ServerE servere = S_MAP.get(ByteReader.getUUID(servermessage.data, 1));
        if (servere != null && (servere.a.state & 8) == 8)
        {
            servere.a.set(entityplayermp, servermessage.data);
            servere.a.call(servermessage.data[17]);
            servere.a.clear();
        }
    }
}
