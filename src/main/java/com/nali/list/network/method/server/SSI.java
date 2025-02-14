package com.nali.list.network.method.server;

import com.nali.list.network.message.ServerMessage;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.system.bytes.ByteReader;
import net.minecraft.entity.player.EntityPlayerMP;

public class SSI
{
	public static byte ID;

	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
	{
//		ServerE s = S_MAP.get(ByteReader.getUUID(servermessage.data, 1));
//		ServerE s = S_MAP.get(ByteReader.getLong(servermessage.data, 1));
		MixSIE ms = MixSIE.MS_MAP.get(ByteReader.getLong(servermessage.data, 1));
//		if (s != null/* && (s.ms.state & 8) == 8*/)
		if (ms != null)
		{
//			s.ms.set(entityplayermp, servermessage.data);
//			s.ms.call(servermessage.data[1 + 16]);
			ms.call(entityplayermp, servermessage.data);
//			s.ms.clear();
		}
	}
}
