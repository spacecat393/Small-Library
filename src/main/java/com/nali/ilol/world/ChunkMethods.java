package com.nali.ilol.world;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkProviderServer;

import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

public class ChunkMethods
{
    //change uuid to object to keep more chunk
    //remove all when entity in box or by player
    //try remove after and take a way to bypass
    public static Map<UUID, int[]> CHUNK_MAP = new WeakHashMap<>();
    //net.minecraft.gametest.framework.StructureUtils;
    public static void force(UUID uuid, WorldServer serverlevel, ChunkPos chunkpos)
    {
        ChunkProviderServer chunkproviderserver = serverlevel.getChunkProvider();

        //try load one?
        for (int i = -1; i < 4; ++i)
        {
            for (int j = -1; j < 4; ++j)
            {
                int k = chunkpos.x + i;
                int l = chunkpos.z + j;
                chunkproviderserver.loadChunk(k, l);

                if (CHUNK_MAP.containsKey(uuid))
                {
                    int[] int_array = CHUNK_MAP.get(uuid);

                    if (chunkpos.x != int_array[0] || chunkpos.z != int_array[1])
                    {
                        chunkproviderserver.loadChunk(int_array[0], int_array[1]);
                        CHUNK_MAP.put(uuid, new int[]{chunkpos.x, chunkpos.z});
                    }
                }
            }
        }
    }

    public static void free(UUID uuid, WorldServer serverlevel)
    {
        if (CHUNK_MAP.containsKey(uuid))
        {
            int[] int_array = CHUNK_MAP.get(uuid);
            serverlevel.getChunkProvider().queueUnload(new Chunk(serverlevel, int_array[0], int_array[1]));
            CHUNK_MAP.remove(uuid);
        }
    }
}
