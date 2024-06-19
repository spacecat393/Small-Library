package com.nali.small.chunk;

import com.nali.small.Small;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;

import static com.nali.small.chunk.ChunkCallBack.CHUNK_MAP;

public class ChunkLoader
{
    public static void updateChunk(ServerE servere)
    {
        if (servere != null && CHUNK_MAP != null)
        {
            Entity entity = servere.i.getE();
            ChunkPos chunkpos = new ChunkPos(entity.getPosition());
            World world = entity.world;

            if (CHUNK_MAP.containsKey(entity))
            {
                ChunkData chunkdata = CHUNK_MAP.get(entity);

                if (!world.equals(chunkdata.world) || !chunkpos.equals(chunkdata.chunkpos))
                {
    //                ForgeChunkManager.unforceChunk(chunkdata.ticket, chunkdata.chunkpos);
                    ForgeChunkManager.releaseTicket(chunkdata.ticket);
                    chunkdata.world = world;
                    chunkdata.chunkpos = chunkpos;
                    chunkdata.ticket = ForgeChunkManager.requestTicket(Small.I, chunkdata.world, ForgeChunkManager.Type.ENTITY);
                    chunkdata.ticket.bindEntity(entity);
                    ForgeChunkManager.forceChunk(chunkdata.ticket, chunkdata.chunkpos);
                }
            }
            else
            {
                ChunkData chunkdata = new ChunkData();
                chunkdata.world = world;
                chunkdata.chunkpos = chunkpos;
                chunkdata.ticket = ForgeChunkManager.requestTicket(Small.I, chunkdata.world, ForgeChunkManager.Type.ENTITY);

                if (chunkdata.ticket != null)
                {
                    chunkdata.ticket.bindEntity(entity);
                    ForgeChunkManager.forceChunk(chunkdata.ticket, chunkdata.chunkpos);
                    CHUNK_MAP.put(servere, chunkdata);
                }
            }
        }
    }

    public static void removeChunk(IMixE imixentity)
    {
        if (imixentity != null && CHUNK_MAP != null && CHUNK_MAP.containsKey(imixentity))
        {
            ChunkData chunkdata = CHUNK_MAP.get(imixentity);
//            ForgeChunkManager.unforceChunk(chunkdata.ticket, chunkdata.chunkpos);
            ForgeChunkManager.releaseTicket(chunkdata.ticket);
            CHUNK_MAP.remove(imixentity);
        }
    }
}
