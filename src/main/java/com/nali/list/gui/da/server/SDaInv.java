package com.nali.list.gui.da.server;

import com.nali.Nali;
import com.nali.list.gui.da.client.CDaInv;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.CPage;
import com.nali.network.NetworkRegistry;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.player.EntityPlayerMP;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class SDaInv
{
	public static byte ID;
	public static byte STATE;//delete/add

	public final static byte MAX_SIZE = 117;
	public final static byte I_MORE = 0;
	public final static byte I_LESS = 1;
	public final static byte I_FETCH = 2;
	public final static byte I_DELETE = 3;
	public final static byte I_ADD = 4;

	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
	{
		File inv_file = new File(entityplayermp.world.getSaveHandler().getWorldDirectory(), "nali/inv");
		inv_file.mkdir();
//		File[] inv_file_array = inv_file.listFiles();
		long max_inv_file = 0;
		try (Stream<Path> path_stream = Files.list(inv_file.toPath()))
		{
			max_inv_file = path_stream.count();
		}
		catch (IOException e)
		{
			Nali.warn(e);
		}
//		String[] inv_string_array = null;

		int page = ByteReader.getInt(servermessage.data, 3);

		if (servermessage.data[2] == I_MORE)
		{
			if (((long)(page + 1) * MAX_SIZE) < max_inv_file)
			{
				++page;
				servermessage.data[2] = I_FETCH;
			}
		}
		else if (servermessage.data[2] == I_LESS)
		{
			int new_page = page - 1;
			if (new_page != -1)
			{
				if (((long)new_page * MAX_SIZE) < max_inv_file)
				{
					--page;
					servermessage.data[2] = I_FETCH;
				}
			}
		}
		else if (servermessage.data[2] == I_DELETE && (STATE & 1) == 0)
		{
			STATE |= 1;
			int index = servermessage.data[4] + page * MAX_SIZE;
//			PlayerData.INV_SHORT_LIST.remove(rg);

//			File inv_i_file = new File(inv_file, "" + index);
			File inv_i_file = inv_file.listFiles()[index];
			File nbt_file = new File(inv_i_file, "nbt");
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
//			nbt_file.delete();

//			File nbt_size_file = new File(inv_i_file, "nbt_size");
//			File[] nbt_size_n_file_array = nbt_size_file.listFiles();
//			if (nbt_size_n_file_array != null)
//			{
//				for (File nbt_size_n_file : nbt_size_n_file_array)
//				{
//					File[] nbt_size_i_file_array = nbt_size_n_file.listFiles();
//					if (nbt_size_i_file_array != null)
//					{
//						for (File nbt_size_i_file : nbt_size_i_file_array)
//						{
//							nbt_size_i_file.delete();
//						}
//					}
//					nbt_size_n_file.delete();
//				}
//			}

			for (File file : inv_i_file.listFiles())
			{
				file.delete();
			}
			inv_i_file.delete();

			servermessage.data[2] = I_FETCH;
			--max_inv_file;
//			inv_string_array = inv_file.list();
//			else
//			{
//				inv_string_array = new String[];
//				for ()
//				{
//					inv_string_array[]
//				}
//			}
			STATE &= 255-1;
		}
		else if (servermessage.data[2] == I_ADD && (STATE & 1) == 0)
		{
			STATE |= 1;
//			PlayerData.INV_SHORT_LIST.add((short)inv_short_list_size);

			int file_index = 0;
			File file = new File(inv_file, "" + file_index);
			while (!file.mkdir())
			{
				++file_index;
				file = new File(inv_file, "" + file_index);
			}
			new File(file, "nbt").mkdir();
//			new File(file, "nbt_size").mkdir();

			servermessage.data[2] = I_FETCH;
			++max_inv_file;
//			inv_string_array = inv_file.list();
			STATE &= 255-1;
		}
		String[] inv_string_array = inv_file.list();

		if (servermessage.data[2] == I_FETCH)
		{
			int max_mix_page = (int)Math.ceil(max_inv_file / (float)MAX_SIZE);
			byte max_page;

			if (max_mix_page > 0)
			{
				max_mix_page -= 1;
			}

			if (page == max_mix_page)
			{
				byte left = (byte)(max_inv_file % MAX_SIZE);
				if (left == 0 && max_inv_file > 0)
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

			byte[] byte_array = new byte[1 + 1 + max_page * 4 + 4 + 1 + 4];
			byte_array[0] = CPage.ID;
			byte_array[1] = CDaInv.ID;
			short byte_array_index = 2;
			int new_page = page * MAX_SIZE;
			for (int i = new_page; i < new_page + max_page; ++i)
			{
				ByteWriter.set(byte_array, Integer.parseInt(inv_string_array[i]), byte_array_index);
				byte_array_index += 4;
			}
			ByteWriter.set(byte_array, page, byte_array_index);
			byte_array_index += 4;
			byte_array[byte_array_index++] = max_page;//max_page
			ByteWriter.set(byte_array, max_mix_page, byte_array_index);
			NetworkRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
		}
	}
}
