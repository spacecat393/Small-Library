package com.nali.list.gui.si.server;

import com.mojang.authlib.GameProfile;
import com.nali.list.entity.si.SIEOwner;
import com.nali.list.gui.si.client.CSIEOwner;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.CPageSI;
import com.nali.network.NetworkRegistry;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.system.bytes.ByteReader;
import net.minecraft.entity.player.EntityPlayerMP;

public class SSIEOwner
{
	public static byte ID;

	public final static byte B_FETCH = 0;
	public final static byte B_SET = 1;
	public final static byte B_DELETE = 2;

	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
	{
		ServerE s = ServerE.S_MAP.get(ByteReader.getLong(servermessage.data, 3));
		SIEOwner sieowner = (SIEOwner)s.ms.si_map.get(SIEOwner.ID);
		if (servermessage.data[2] == B_SET)
		{
			sieowner.uuid = entityplayermp.getUniqueID();
			servermessage.data[2] = B_FETCH;
		}
		else if (servermessage.data[2] == B_DELETE)
		{
			sieowner.uuid = null;
			servermessage.data[2] = B_FETCH;
		}

		if (servermessage.data[2] == B_FETCH)
		{
			GameProfile gameprofile = entityplayermp.server.getPlayerProfileCache().getProfileByUUID(sieowner.uuid);
			byte[] name_byte_array;
			if (gameprofile == null)
			{
				name_byte_array = "NULL".getBytes();
			}
			else
			{
				name_byte_array = gameprofile.getName().getBytes();
			}
			byte name_byte_array_length = (byte)name_byte_array.length;
			byte[] byte_array = new byte[1 + 1 + name_byte_array_length];
			byte_array[0] = CPageSI.ID;
			byte_array[1] = CSIEOwner.ID;
			System.arraycopy(name_byte_array, 0, byte_array, 2, name_byte_array_length);
			NetworkRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
		}
	}
}
