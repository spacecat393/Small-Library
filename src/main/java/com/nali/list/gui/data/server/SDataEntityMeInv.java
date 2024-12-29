package com.nali.list.gui.data.server;

import com.nali.list.gui.data.client.CDataChunkList;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.CPage;
import com.nali.network.NetworkRegistry;
import com.nali.small.entity.player.PlayerData;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.player.EntityPlayerMP;

import java.io.File;

public class SDataEntityMeInv
{
	public static byte ID;

	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
	{
		byte page = servermessage.data[3];
		int inv_short_list_size = PlayerData.INV_SHORT_LIST.size();

		if (servermessage.data[2] == 0)//more
		{
			if (((page + 1) * 119) < inv_short_list_size)
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
				if ((new_page * 119) < inv_short_list_size)
				{
					--page;
					servermessage.data[2] = 2;
				}
			}
		}
		else if (servermessage.data[2] == 3)//delete
		{
			short index = (short)(servermessage.data[4] + page * 119);
			PlayerData.INV_SHORT_LIST.remove(index);

			File inv_file = new File(entityplayermp.world.getSaveHandler().getWorldDirectory(), "nali/inv/" + index);
			File nbt_file = new File(inv_file, "nbt");
			File[] nbt_n_file_array = nbt_file.listFiles();
			if (nbt_n_file_array != null)
			{
				for (File nbt_n_file : nbt_n_file_array)
				{
					File[] nbt_i_file_array = nbt_n_file.listFiles();
					if (nbt_i_file_array != null)
					{
						for (File nbt_i_file : nbt_i_file_array)
						{
							nbt_i_file.delete();
						}
					}
					nbt_n_file.delete();
				}
			}
			for (File file : inv_file.listFiles())
			{
				file.delete();
			}
//			nbt_file.delete();
			inv_file.delete();

			servermessage.data[2] = 2;
			--inv_short_list_size;
		}
		else if (servermessage.data[2] == 4)//add
		{
			PlayerData.INV_SHORT_LIST.add((short)inv_short_list_size);

			File file = new File(entityplayermp.world.getSaveHandler().getWorldDirectory(), "nali/inv/" + inv_short_list_size);
			file.mkdir();
			new File(file, "nbt").mkdir();

			servermessage.data[2] = 2;
			++inv_short_list_size;
		}

		if (servermessage.data[2] == 2)//fetch
		{
			byte max_mix_page = (byte)Math.ceil(inv_short_list_size / 119.0F);
			byte max_page;

			if (max_mix_page > 0)
			{
				max_mix_page -= 1;
			}

			if (page == max_mix_page)
			{
				byte left = (byte)(inv_short_list_size % 119);
				if (left == 0 && inv_short_list_size > 0)
				{
					max_page = 119;
				}
				else
				{
					max_page = left;
				}
			}
			else
			{
				max_page = 119;
			}

			byte[] byte_array = new byte[1 + 1 + max_page * 2 * 4 + 1 + 1 + 1];
			byte_array[0] = CPage.ID;
			byte_array[1] = CDataChunkList.ID;
			short byte_array_index = 2;
			int new_page = page * 119;
			for (int i = new_page; i < new_page + max_page; ++i)
			{
				ByteWriter.set(byte_array, PlayerData.INV_SHORT_LIST.get(i), byte_array_index);
				byte_array_index += 2;
			}
			byte_array[byte_array_index++] = page;//page
			byte_array[byte_array_index++] = max_page;//max_page
			byte_array[byte_array_index] = max_mix_page;//max_mix_page
			NetworkRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
		}
	}
}
