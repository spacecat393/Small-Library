package com.nali.list.gui.data.server;

import com.nali.list.gui.data.client.CDataChunkList;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.CPage;
import com.nali.network.NetworkRegistry;
import com.nali.small.chunk.ChunkCallBack;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.ChunkPos;
import net.minecraftforge.common.ForgeChunkManager;

public class SDataChunkList
{
	public static byte ID;

	//stop on array
	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
	{
		byte page = servermessage.data[3];
		int chunk_list_size = ChunkCallBack.CHUNK_LIST.size();

		if (servermessage.data[2] == 0)//more
		{
			if (((page + 1) * 120) < chunk_list_size)
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
				if ((new_page * 120) < chunk_list_size)
				{
					--page;
					servermessage.data[2] = 2;
				}
			}
		}
		else if (servermessage.data[2] == 3)//delete
		{
			short index = (short)(servermessage.data[4] + page * 120);
			ForgeChunkManager.releaseTicket(ChunkCallBack.CHUNK_LIST.get(index).ticket);
			ChunkCallBack.CHUNK_LIST.remove(index);
//			byte[] byte_array = new byte[1 + 1];
//			byte_array[0] = CPage.ID;
//			byte_array[1] = CDataChunkList.ID;
//			NetworkRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
			servermessage.data[2] = 2;
			--chunk_list_size;
		}

		if (servermessage.data[2] == 2)//fetch
		{
			byte max_mix_page = (byte)Math.ceil(chunk_list_size / 120.0F);
			byte max_page;

			if (max_mix_page > 0)
			{
				max_mix_page -= 1;
			}

			if (page == max_mix_page)
			{
				byte left = (byte)(chunk_list_size % 120);
				if (left == 0 && chunk_list_size > 0)
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

			byte[] byte_array = new byte[1 + 1 + max_page * 2 * 4 + 1 + 1 + 1];
			byte_array[0] = CPage.ID;
			byte_array[1] = CDataChunkList.ID;
			short byte_array_index = 2;
			int new_page = page * 120;
			for (int i = new_page; i < new_page + max_page; ++i)
			{
				ChunkPos chunkpos = ChunkCallBack.CHUNK_LIST.get(i).chunkpos;
				ByteWriter.set(byte_array, chunkpos.x, byte_array_index);
				byte_array_index += 4;
				ByteWriter.set(byte_array, chunkpos.z, byte_array_index);
				byte_array_index += 4;
			}
			byte_array[byte_array_index++] = page;//page
			byte_array[byte_array_index++] = max_page;//max_page
			byte_array[byte_array_index] = max_mix_page;//max_mix_page
			NetworkRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
		}
	}
}
