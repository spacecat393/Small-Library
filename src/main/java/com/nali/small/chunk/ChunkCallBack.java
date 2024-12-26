package com.nali.small.chunk;

import com.nali.small.Small;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;

import java.util.*;

public class ChunkCallBack implements ForgeChunkManager.OrderedLoadingCallback
{
	public static ChunkCallBack I;
//	public static Map<UUID, ChunkData> CHUNK_MAP;
	public static Map<Long, ChunkData> CHUNK_MAP;
	public static List<ChunkData> CHUNK_LIST;//need to clear later

	public static void set()
	{
		I = new ChunkCallBack();
		CHUNK_MAP = new HashMap();
		CHUNK_LIST = new ArrayList();
		ForgeChunkManager.setForcedChunkLoadingCallback(Small.I, I);
	}

	@Override
	public List<ForgeChunkManager.Ticket> ticketsLoaded(List<ForgeChunkManager.Ticket> tickets, World world, int maxTicketCount)
	{
		List<ForgeChunkManager.Ticket> ticketsToLoad = new ArrayList();

		int count = Math.min(maxTicketCount, tickets.size());
		for (int i = 0; i < count; i++)
		{
			ticketsToLoad.add(tickets.get(i));
		}

		return ticketsToLoad;
	}

	@Override
	public void ticketsLoaded(List<ForgeChunkManager.Ticket> tickets, World world)
	{
	}
}
