package com.nali.list.gui.da.server;

import com.nali.list.gui.da.client.CDaChunkMap;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.CPageDa;
import com.nali.network.NetworkRegistry;
import com.nali.small.chunk.ChunkCallBack;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.ChunkPos;
import net.minecraftforge.common.ForgeChunkManager;

import java.util.ArrayList;

public class SDaChunkMap
{
	public static byte ID;

	public final static byte MAX_SIZE = 118;
	public final static byte B_MORE = 0;
	public final static byte B_LESS = 1;
	public final static byte B_FETCH = 2;
	public final static byte B_DELETE = 3;
	public final static byte B_DELETE_ALL = 4;

	//stop on array
	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
	{
		int page = ByteReader.getInt(servermessage.data, 3);
		int chunk_map_size = ChunkCallBack.CHUNK_MAP.size();

		switch (servermessage.data[2])
		{
			case B_MORE:
				if (((page + 1) * MAX_SIZE) < chunk_map_size)
				{
					++page;
					servermessage.data[2] = B_FETCH;
				}
				break;
			case B_LESS:
				int new_page = page - 1;
				if (new_page != -1)
				{
					if ((new_page * MAX_SIZE) < chunk_map_size)
					{
						--page;
						servermessage.data[2] = B_FETCH;
					}
				}
				break;
			case B_DELETE:
				long id = ByteReader.getLong(servermessage.data, 3+4);
				ForgeChunkManager.releaseTicket(ChunkCallBack.CHUNK_MAP.get(id).ticket);
				ChunkCallBack.CHUNK_MAP.remove(id);
				servermessage.data[2] = B_FETCH;
				--chunk_map_size;
				break;
			case B_DELETE_ALL:
				ChunkCallBack.CHUNK_MAP.clear();
				servermessage.data[2] = B_FETCH;
				chunk_map_size = 0;
		}

		if (servermessage.data[2] == B_FETCH)
		{
			int max_mix_page = (int)Math.ceil(chunk_map_size / (float)MAX_SIZE);
			byte max_page;

			if (max_mix_page > 0)
			{
				max_mix_page -= 1;
			}

			if (page == max_mix_page)
			{
				byte left = (byte)(chunk_map_size % MAX_SIZE);
				if (left == 0 && chunk_map_size > 0)
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

			byte[] byte_array = new byte[1 + 1 + max_page * (2 * 4 + 8) + 4 + 1 + 4];
			byte_array[0] = CPageDa.ID;
			byte_array[1] = CDaChunkMap.ID;
			short byte_array_index = 2;
			int new_page = page * MAX_SIZE;
			ArrayList<Long> id_long_arraylist = new ArrayList(ChunkCallBack.CHUNK_MAP.keySet());
			for (int i = new_page; i < new_page + max_page; ++i)
			{
				long id = id_long_arraylist.get(i);
				ChunkPos chunkpos = ChunkCallBack.CHUNK_MAP.get(id).chunkpos;
				ByteWriter.set(byte_array, id, byte_array_index);
				byte_array_index += 8;
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
