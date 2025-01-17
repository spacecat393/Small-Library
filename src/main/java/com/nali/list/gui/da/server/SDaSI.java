package com.nali.list.gui.da.server;

import com.nali.list.gui.da.client.CDaEntity;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.CPage;
import com.nali.network.NetworkRegistry;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.ArrayList;
import java.util.List;

public class SDaSI
{
	public static byte ID;

	public final static byte MAX_SIZE = 120;
	public final static byte I_MORE = 0;
	public final static byte I_LESS = 1;
	public final static byte I_FETCH = 2;

	//stop on array
	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
	{
		byte page = servermessage.data[3];
		ServerE s = ServerE.S_MAP.get(ByteReader.getLong(servermessage.data, 4));
		List<Byte> si_byte_lsit = new ArrayList<Byte>(s.ms.si_map.keySet());
		int si_size = si_byte_lsit.size();

		if (servermessage.data[2] == I_MORE)
		{
			if (((page + 1) * MAX_SIZE) < si_size)
			{
				++page;
				servermessage.data[2] = I_FETCH;
			}
		}
		else if (servermessage.data[2] == I_LESS)
		{
			byte new_page = (byte)(page - 1);
			if (new_page != -1)
			{
				if ((new_page * MAX_SIZE) < si_size)
				{
					--page;
					servermessage.data[2] = I_FETCH;
				}
			}
		}

		if (servermessage.data[2] == I_FETCH)
		{
			byte max_mix_page = (byte)Math.ceil(si_size / (float)MAX_SIZE);
			byte max_page;

			if (max_mix_page > 0)
			{
				max_mix_page -= 1;
			}

			if (page == max_mix_page)
			{
				byte left = (byte)(si_size % MAX_SIZE);
				if (left == 0 && si_size > 0)
				{
					max_page = MAX_SIZE;
				}
				else
				{
					max_page = left;
				}
			}
			else
			{
				max_page = MAX_SIZE;
			}

			short byte_array_index = 2;
			int new_page = page * MAX_SIZE;
			byte[] byte_array = new byte[1 + 1 + max_page * 4 + 1 + 1 + 1];
			byte_array[0] = CPage.ID;
			byte_array[1] = CDaEntity.ID;
//			if (list != null)
//			{
			for (int i = new_page; i < new_page + max_page; ++i)
			{
				ByteWriter.set(byte_array, si_byte_lsit.get(i), byte_array_index);
				byte_array_index += 4;
			}
//			}
			byte_array[byte_array_index++] = page;//page
			byte_array[byte_array_index++] = max_page;//max_page
			byte_array[byte_array_index] = max_mix_page;//max_mix_page
			NetworkRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
		}
	}
}
