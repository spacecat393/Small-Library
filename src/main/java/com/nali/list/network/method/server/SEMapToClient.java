//package com.nali.list.network.method.server;
//
//import com.nali.list.network.message.ClientMessage;
//import com.nali.list.network.message.ServerMessage;
//import com.nali.list.network.method.client.CSetSEMap;
//import com.nali.network.NetworkRegistry;
//import com.nali.networks.NetworksRegistry;
//import com.nali.small.chunk.ChunkLoader;
//import com.nali.small.entity.EntityLeInv;
//import com.nali.system.bytes.ByteWriter;
//import net.minecraft.entity.EntityList;
//import net.minecraft.entity.player.EntityPlayerMP;
//
//import java.util.UUID;
//
//import static com.nali.small.entity.memo.server.ServerE.ENTITIES_MAP;
//
//public class SEMapToClient
//{
//    public static byte ID;
//
//    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
//    {
//        //                WorldServer worldserver = (WorldServer)entityplayermp.world;
//        //                Map<UUID, Entity> entity_map = ((IMixinWorldServer)worldserver).entitiesByUuid();
//        //                if (!entity_map.isEmpty())
//        if (ENTITIES_MAP.isEmpty())
//        {
//            NetworkRegistry.I.sendTo(new ClientMessage(new byte[]{CSetSEMap.ID}), entityplayermp);
//        }
//        else
//        {
//            int index = 1;
//            //                    Set<UUID> keys_set = new HashSet<>(entity_map.keySet());
////            Set<UUID> keys_set = new HashSet(ENTITIES_MAP.keySet());
//            byte[] byte_array = new byte[keys_set.size() * 16 + 1 + keys_set.size() * 8];
//            byte_array[0] = CSetSEMap.ID;
//
//            for (UUID uuid : keys_set)
//            {
//                EntityLeInv skinningentities = ENTITIES_MAP.get(uuid);
////                            entityplayermp.connection.sendPacket(new SPacketSpawnObject(skinningentities, EntityList.getID(skinningentities.getClass())));
//                //should check long with uuid
//                ChunkLoader.updateChunk(skinningentities);
//                //                        if (worldserver.getEntityFromUuid(uuid) instanceof SkinningEntities)
//                //                        {
//                ByteWriter.set(byte_array, uuid, index);
//                index += 16;
//                ByteWriter.set(byte_array, skinningentities.getEntityId(), index);
//                index += 4;
//                ByteWriter.set(byte_array, EntityList.getID(skinningentities.getClass()), index);
//                index += 4;
//                //                        }
//            }
//
//            NetworkRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
//        }
//    }
//}
