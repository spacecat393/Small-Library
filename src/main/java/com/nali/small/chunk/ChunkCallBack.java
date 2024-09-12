package com.nali.small.chunk;

import com.nali.small.Small;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;

import java.util.*;

public class ChunkCallBack implements ForgeChunkManager.OrderedLoadingCallback
{
	public static ChunkCallBack I;
	public static Map<UUID, ChunkData> CHUNK_MAP;

	public static void set()
	{
		I = new ChunkCallBack();
		CHUNK_MAP = new HashMap();
		ForgeChunkManager.setForcedChunkLoadingCallback(Small.I, I);
	}

	@Override
	public List<ForgeChunkManager.Ticket> ticketsLoaded(List<ForgeChunkManager.Ticket> tickets, World world, int maxTicketCount)
	{
		// Return ms list of tickets to load
		List<ForgeChunkManager.Ticket> ticketsToLoad = new ArrayList();

		// Add your logic to determine which tickets to load
		// You can process the tickets parameter to make decisions

		// For example, load the first maxTicketCount tickets
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
//		// Handle forced chunk loading tickets being loaded
//		for (ForgeChunkManager.Ticket ticket : tickets)
//		{
//			// Process each ticket as needed
//		}
	}
}
