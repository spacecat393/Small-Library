package com.nali.small.chunk;

import com.nali.small.Small;
import com.nali.small.entities.skinning.SkinningEntities;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ChunkCallBack implements ForgeChunkManager.OrderedLoadingCallback
{
    public static ChunkCallBack I;
    public static Map<SkinningEntities, ChunkData> CHUNK_MAP;

    public static void set()
    {
        I = new ChunkCallBack();
        CHUNK_MAP = new HashMap();
        ForgeChunkManager.setForcedChunkLoadingCallback(Small.I, I);
    }

    @Override
    public List<ForgeChunkManager.Ticket> ticketsLoaded(List<ForgeChunkManager.Ticket> tickets, World world, int maxTicketCount)
    {
        // Return a list of tickets to load
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
//        // Handle forced chunk loading tickets being loaded
//        for (ForgeChunkManager.Ticket ticket : tickets)
//        {
//            // Process each ticket as needed
//        }
    }
}
