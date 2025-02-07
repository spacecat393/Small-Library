package com.nali.list.gui.si.server;

import com.nali.list.entity.si.SIEArea;
import com.nali.list.gui.si.client.CSIEArea;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.CPageSI;
import com.nali.network.NetworkRegistry;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.system.bytes.ByteReader;
import net.minecraft.entity.player.EntityPlayerMP;

public class SSIEArea
{
	public static byte ID;

	public final static byte I_FETCH = 0;

	public final static byte I_PUT_PLAYER = 4;
	public final static byte I_PUT_OWNER = 8;
	public final static byte I_PUT_OTHER_TAMEABLE = 16;
	public final static byte I_PUT_OWNER_TAMEABLE = 32;
	public final static byte I_PUT_ALL_TAMEABLE = 64;
	public final static byte I_PUT_OBJECT = (byte)128;//-1

	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
	{
		ServerE s = ServerE.S_MAP.get(ByteReader.getLong(servermessage.data, 3));
		SIEArea siearea = (SIEArea)s.ms.si_map.get(SIEArea.ID);
		if (servermessage.data[2] != I_FETCH)
		{
			siearea.flag ^= servermessage.data[2];
//			servermessage.data[2] = I_FETCH;
		}

//		if (servermessage.data[2] == I_FETCH)
//		{
		byte[] byte_array = new byte[1 + 1 + 1];
		byte_array[0] = CPageSI.ID;
		byte_array[1] = CSIEArea.ID;
		byte_array[2] = siearea.flag;
		NetworkRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
//		}
	}
}
