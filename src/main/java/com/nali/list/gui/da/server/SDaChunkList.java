package com.nali.list.gui.da.server;

import com.nali.list.gui.da.client.CDaChunkList;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.CPage;
import com.nali.network.NetworkRegistry;
import com.nali.small.chunk.ChunkCallBack;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.ChunkPos;
import net.minecraftforge.common.ForgeChunkManager;

public class SDaChunkList
{
	public static byte ID;

	public final static byte MAX_SIZE = 118;
	public final static byte I_MORE = 0;
	public final static byte I_LESS = 1;
	public final static byte I_FETCH = 2;
	public final static byte I_DELETE = 3;
	public final static byte I_DELETE_ALL = 4;

	//stop on array
	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
	{
		int page = ByteReader.getInt(servermessage.data, 3);
		int chunk_list_size = ChunkCallBack.CHUNK_LIST.size();

		if (servermessage.data[2] == I_MORE)
		{
			if (((page + 1) * MAX_SIZE) < chunk_list_size)
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
				if ((new_page * MAX_SIZE) < chunk_list_size)
				{
					--page;
					servermessage.data[2] = I_FETCH;
				}
			}
		}
		else if (servermessage.data[2] == I_DELETE)
		{
			int index = servermessage.data[3+4] + page * MAX_SIZE;
			ForgeChunkManager.releaseTicket(ChunkCallBack.CHUNK_LIST.get(index).ticket);
			ChunkCallBack.CHUNK_LIST.remove(index);
			servermessage.data[2] = I_FETCH;
			--chunk_list_size;
		}
		else if (servermessage.data[2] == I_DELETE_ALL)
		{
			ChunkCallBack.CHUNK_LIST.clear();
			servermessage.data[2] = I_FETCH;
			chunk_list_size = 0;
		}

		if (servermessage.data[2] == I_FETCH)
		{
			int max_mix_page = (int)Math.ceil(chunk_list_size / (float)MAX_SIZE);
			//data type
			byte max_page;

			if (max_mix_page > 0)
			{
				max_mix_page -= 1;
			}

			if (page == max_mix_page)
			{
				byte left = (byte)(chunk_list_size % MAX_SIZE);
				if (left == 0 && chunk_list_size > 0)
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

			byte[] byte_array = new byte[1 + 1 + max_page * 2 * 4 + 4 + 1 + 4];
			byte_array[0] = CPage.ID;
			byte_array[1] = CDaChunkList.ID;
			short byte_array_index = 2;
			int new_page = page * MAX_SIZE;
			for (int i = new_page; i < new_page + max_page; ++i)
			{
				ChunkPos chunkpos = ChunkCallBack.CHUNK_LIST.get(i).chunkpos;
				ByteWriter.set(byte_array, chunkpos.x, byte_array_index);
				byte_array_index += 4;
				ByteWriter.set(byte_array, chunkpos.z, byte_array_index);
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
