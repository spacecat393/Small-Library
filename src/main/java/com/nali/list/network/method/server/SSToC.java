package com.nali.list.network.method.server;

import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.CSToC;
import com.nali.network.NetworkRegistry;
import com.nali.small.chunk.ChunkLoader;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.HashSet;
import java.util.Set;

import static com.nali.small.entity.memo.server.ServerE.S_MAP;

public class SSToC
{
	public static byte ID;

	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
	{
		//				WorldServer worldserver = (WorldServer)entityplayermp.world;
		//				Map<UUID, Entity> entity_map = ((IMixinWorldServer)worldserver).entitiesByUuid();
		//				if (!entity_map.isEmpty())
		if (S_MAP.isEmpty())
		{
			NetworkRegistry.I.sendTo(new ClientMessage(new byte[]{CSToC.ID}), entityplayermp);
		}
		else
		{
			int index = 1;
			//					Set<UUID> keys_set = new HashSet<>(entity_map.keySet());
//			Set<UUID> keys_set = new HashSet(S_MAP.keySet());
			Set<Long> keys_set = new HashSet(S_MAP.keySet());
			int size = keys_set.size();
			byte[] byte_array = new byte[1 + size * 8/*16*/ + size * (4+4/*+4*/)];
			byte_array[0] = CSToC.ID;

//			for (UUID uuid : keys_set)
			for (long l : keys_set)
			{
				ServerE s = S_MAP.get(l);
//							entityplayermp.connection.sendPacket(new SPacketSpawnObject(skinningentities, EntityList.getID(skinningentities.getClass())));
				if (s != null)
				{
					ChunkLoader.updateChunk(s);
					//						if (worldserver.getEntityFromUuid(uuid) instanceof SkinningEntities)
					//						{
					Entity e = s.i.getE();
//					ByteWriter.set(byte_array, uuid, index);
//					index += 16;
					ByteWriter.set(byte_array, (long)e.world.provider.getDimension() << 32 | e.getEntityId(), index);
					index += 8;
					ByteWriter.set(byte_array, e.getEntityId(), index);
					index += 4;
//					ByteWriter.set(byte_array, e.dimension, index);
//					index += 4;
					ByteWriter.set(byte_array, EntityList.getID(e.getClass()), index);
					index += 4;
					//						}
				}
			}

			NetworkRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
		}
	}
}
