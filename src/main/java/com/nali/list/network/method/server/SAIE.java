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
        ServerE s = S_MAP.get(ByteReader.getUUID(servermessage.data, 1));
        if (s != null && (s.a.state & 8) == 8)
        {
            s.a.set(entityplayermp, servermessage.data);
            s.a.call(servermessage.data[17]);
            s.a.clear();
        }
    }
}
