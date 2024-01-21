package com.nali.small.world;

import com.nali.small.Small;
import com.nali.small.entities.skinning.SkinningEntities;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;

import static com.nali.small.world.ChunkCallBack.CHUNK_MAP;

public class ChunkLoader
{
    public static void updateChunk(SkinningEntities skinningentities)
    {
        if (skinningentities != null && CHUNK_MAP != null)
        {
            ChunkPos chunkpos = new ChunkPos(skinningentities.getPosition());
            World world = skinningentities.world;

            if (CHUNK_MAP.containsKey(skinningentities))
            {
                ChunkData chunkdata = CHUNK_MAP.get(skinningentities);

                if (!world.equals(chunkdata.world) || !chunkpos.equals(chunkdata.chunkpos))
                {
    //                ForgeChunkManager.unforceChunk(chunkdata.ticket, chunkdata.chunkpos);
                    ForgeChunkManager.releaseTicket(chunkdata.ticket);
                    chunkdata.world = world;
                    chunkdata.chunkpos = chunkpos;
                    chunkdata.ticket = ForgeChunkManager.requestTicket(Small.I, chunkdata.world, ForgeChunkManager.Type.ENTITY);
                    chunkdata.ticket.bindEntity(skinningentities);
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
                    chunkdata.ticket.bindEntity(skinningentities);
                    ForgeChunkManager.forceChunk(chunkdata.ticket, chunkdata.chunkpos);
                    CHUNK_MAP.put(skinningentities, chunkdata);
                }
            }
        }
    }

    public static void removeChunk(SkinningEntities skinningentities)
    {
        if (skinningentities != null && CHUNK_MAP != null && CHUNK_MAP.containsKey(skinningentities))
        {
            ChunkData chunkdata = CHUNK_MAP.get(skinningentities);
//            ForgeChunkManager.unforceChunk(chunkdata.ticket, chunkdata.chunkpos);
            ForgeChunkManager.releaseTicket(chunkdata.ticket);
            CHUNK_MAP.remove(skinningentities);
        }
    }
}
