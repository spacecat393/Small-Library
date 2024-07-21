package com.nali.small.chunk;

import com.nali.Nali;
import com.nali.small.Small;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

import static com.nali.small.chunk.ChunkCallBack.CHUNK_MAP;

public class ChunkLoader
{
    public static void updateChunk(ServerE servere)
    {
        if (servere != null && CHUNK_MAP != null)
        {
            Entity e = servere.i.getE();
            UUID uuid = e.getUniqueID();
            ChunkPos chunkpos = new ChunkPos(e.getPosition());
            World world = e.world;

            if (CHUNK_MAP.containsKey(uuid))
            {
                ChunkData chunkdata = CHUNK_MAP.get(uuid);

                if (!world.equals(chunkdata.world) || !chunkpos.equals(chunkdata.chunkpos))
                {
    //                ForgeChunkManager.unforceChunk(chunkdata.ticket, chunkdata.chunkpos);
                    ForgeChunkManager.releaseTicket(chunkdata.ticket);
                    chunkdata.world = world;
                    chunkdata.chunkpos = chunkpos;
                    chunkdata.ticket = ForgeChunkManager.requestTicket(Small.I, chunkdata.world, ForgeChunkManager.Type.ENTITY);
                    chunkdata.ticket.bindEntity(e);
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
                    chunkdata.ticket.bindEntity(e);
                    ForgeChunkManager.forceChunk(chunkdata.ticket, chunkdata.chunkpos);
                    CHUNK_MAP.put(uuid, chunkdata);
                }
            }

            byte[] byte_array = new byte[4 + 8];
            ByteWriter.set(byte_array, world.getWorldType().getId(), 0);
            ByteWriter.set(byte_array, e.getPosition().toLong(), 4);
            try
            {
                Files.write(new File(world.getSaveHandler().getWorldDirectory() + "/nali/entity/" + uuid).toPath(), byte_array);
            }
            catch (IOException ex)
            {
                Nali.I.error(ex);
            }
        }
    }

    public static void removeChunk(UUID uuid)
    {
        if (CHUNK_MAP != null && CHUNK_MAP.containsKey(uuid))
        {
            ChunkData chunkdata = CHUNK_MAP.get(uuid);
//            ForgeChunkManager.unforceChunk(chunkdata.ticket, chunkdata.chunkpos);
            ForgeChunkManager.releaseTicket(chunkdata.ticket);
            CHUNK_MAP.remove(uuid);
        }
    }
}
