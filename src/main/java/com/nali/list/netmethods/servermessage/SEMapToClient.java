package com.nali.list.netmethods.servermessage;

import com.nali.list.messages.ClientMessage;
import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.networks.NetworksRegistry;
import com.nali.small.world.ChunkLoader;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SEMapToClient
{
    public static byte ID = 5;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        //                WorldServer worldserver = (WorldServer)entityplayermp.world;
        //                Map<UUID, Entity> entity_map = ((IMixinWorldServer)worldserver).entitiesByUuid();
        //                if (!entity_map.isEmpty())
        if (!SkinningEntities.SERVER_ENTITIES_MAP.isEmpty())
        {
            int index = 1;
            //                    Set<UUID> keys_set = new HashSet<>(entity_map.keySet());
            Set<UUID> keys_set = new HashSet<>(SkinningEntities.SERVER_ENTITIES_MAP.keySet());
            byte[] byte_array = new byte[keys_set.size() * 16 + 1 + keys_set.size() * 8];
            //                    byte_array[0] = 0;

            for (UUID uuid : keys_set)
            {
                SkinningEntities skinningentities = SkinningEntities.SERVER_ENTITIES_MAP.get(uuid);
//                            entityplayermp.connection.sendPacket(new SPacketSpawnObject(skinningentities, EntityList.getID(skinningentities.getClass())));
                //should check long with uuid
                ChunkLoader.updateChunk(skinningentities);
                //                        if (worldserver.getEntityFromUuid(uuid) instanceof SkinningEntities)
                //                        {
                BytesWriter.set(byte_array, uuid, index);
                index += 16;
                BytesWriter.set(byte_array, skinningentities.getEntityId(), index);
                index += 4;
                BytesWriter.set(byte_array, EntityList.getID(skinningentities.getClass()), index);
                index += 4;
                //                        }
            }

            NetworksRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
        }
    }
}
