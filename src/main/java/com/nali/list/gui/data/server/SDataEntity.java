package com.nali.list.gui.data.server;

import com.nali.list.gui.data.client.CDataEntity;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.CPage;
import com.nali.network.NetworkRegistry;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.ArrayList;
import java.util.List;

public class SDataEntity
{
	public static byte ID;

	//stop on array
	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
	{
		byte page = servermessage.data[3];
		int s_map_size = ServerE.S_MAP.size();

		if (servermessage.data[2] == 0)//more
		{
			if (((page + 1) * 120) < s_map_size)
			{
				++page;
				servermessage.data[2] = 2;
			}
		}
		else if (servermessage.data[2] == 1)//less
		{
			byte new_page = (byte)(page - 1);
			if (new_page != -1)
			{
				if ((new_page * 120) < s_map_size)
				{
					--page;
					servermessage.data[2] = 2;
				}
			}
		}
		else if (servermessage.data[2] == 3)//rename
		{
			long key = ByteReader.getLong(servermessage.data, 4);
			String name = new String(servermessage.data, 4+8, servermessage.data.length - (4+8));
			ServerE.S_MAP.get(key).i.getE().setCustomNameTag(name);
			servermessage.data[2] = 2;
		}

		if (servermessage.data[2] == 2)//fetch
		{
			byte max_mix_page = (byte)Math.ceil(s_map_size / 120.0F);
			byte max_page;

			if (max_mix_page > 0)
			{
				max_mix_page -= 1;
			}

			if (page == max_mix_page)
			{
				byte left = (byte)(s_map_size % 120);
				if (left == 0 && s_map_size > 0)
				{
					max_page = 120;
				}
				else
				{
					max_page = left;
				}
			}
			else
			{
				max_page = 120;
			}

			short byte_array_index = 2;
			int new_page = page * 120;
			ArrayList<Long> id_long_arraylist = new ArrayList(ServerE.S_MAP.keySet());
			List<byte[]> name_byte_array_list = new ArrayList();
			int i_page = new_page + max_page;
			int name_max_size = 0;
			for (int i = new_page; i < i_page; ++i)
			{
				long id = id_long_arraylist.get(i);
				ServerE s = ServerE.S_MAP.get(id);
				Entity e = s.i.getE();
				byte[] name_byte_array = e.getName().getBytes();
				name_byte_array_list.add(name_byte_array);
				name_max_size += name_byte_array.length;
			}
			byte[] byte_array = new byte[1 + 1 + max_page * (8 + 4) + name_max_size + 1 + 1 + 1];
			byte_array[0] = CPage.ID;
			byte_array[1] = CDataEntity.ID;
			byte name_index = 0;
			for (int i = new_page; i < i_page; ++i)
			{
				long id = id_long_arraylist.get(i);
//				ServerE s = ServerE.S_MAP.get(id);
//				Entity e = s.i.getE();
				ByteWriter.set(byte_array, id, byte_array_index);
				byte_array_index += 8;
				byte[] name_byte_array = name_byte_array_list.get(name_index++);
				ByteWriter.set(byte_array, name_byte_array.length, byte_array_index);
				byte_array_index += 4;
				//data here
				System.arraycopy(name_byte_array, 0, byte_array, byte_array_index, name_byte_array.length);
				byte_array_index += name_byte_array.length;
			}
			byte_array[byte_array_index++] = page;//page
			byte_array[byte_array_index++] = max_page;//max_page
			byte_array[byte_array_index] = max_mix_page;//max_mix_page
			NetworkRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
		}
	}
}
