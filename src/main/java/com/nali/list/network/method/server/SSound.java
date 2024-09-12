package com.nali.list.network.method.server;

import com.nali.list.entity.ci.CIESound;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.CCI;
import com.nali.network.NetworkRegistry;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.player.EntityPlayerMP;

public class SSound
{
	public static byte ID;

	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
	{
		byte[] byte_array = new byte[1 + 4 + 1 + 4];
		byte_array[0] = CCI.ID;
		ByteWriter.set(byte_array, ByteReader.getInt(servermessage.data, 1), 1);
		byte_array[1 + 4] = CIESound.ID;
		ByteWriter.set(byte_array, ByteReader.getInt(servermessage.data, 1 + 4), 1 + 4 + 1);
		NetworkRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
	}
}
