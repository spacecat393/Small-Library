//package com.nali.list.network.method.server;
//
//import com.nali.list.network.handler.ServerHandler;
//import com.nali.list.network.message.ClientMessage;
//import com.nali.list.network.message.ServerMessage;
//import com.nali.list.network.method.client.PlaySound;
//import com.nali.list.network.method.client.SetWorkBytes;
//import com.nali.network.NetworkRegistry;
//import com.nali.networks.NetworksRegistry;
//import com.nali.small.entity.memo.server.ServerE;
//import com.nali.system.bytes.ByteReader;
//import com.nali.system.bytes.ByteWriter;
//import com.nali.system.bytes.BytesWriter;
//import net.minecraft.entity.player.EntityPlayerMP;
//
//import java.util.UUID;
//
//import static com.nali.small.entity.memo.server.ServerE.S_MAP;
//
//public class SetWorkByte
//{
//    public static byte ID;
//
//    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
//    {
//        UUID uuid = ByteReader.getUUID(servermessage.data, 1);
//        ServerE servere = S_MAP.get(uuid);
//
//        if (skinningentities != null && ServerHandler.canPass(skinningentities, entityplayermp))
//        {
////            int id = BytesReader.getInt(servermessage.data, 17);
//
//            byte id = servermessage.data[17];
//            byte index = (byte)(id / 8);
//            byte bit = (byte)(id % 8);
//
////            {
////                byte[] byte_array = new byte[1 + 4 + 4];
////                byte_array[0] = PlaySound.ID;
////                ByteWriter.set(byte_array, skinningentities.getEntityId(), 1);
////                if (((servere.main_work_byte_array[index] >> bit) & 1) == 1)
////                {
////                    servere.statentitiesmemory.stat &= 255-4;
////                    servere.statentitiesmemory.stat |= 2;
////
////                    ByteWriter.set(byte_array, skinningentities.bothentitiesmemory.sounds.SOFT_READY(), 1 + 4);
////                }
////                else
////                {
////                    servere.statentitiesmemory.stat |= 4;
////                    servere.statentitiesmemory.stat &= 255-2;
////
////                    ByteWriter.set(byte_array, skinningentities.bothentitiesmemory.sounds.HARD_READY(), 1 + 4);
////                }
////                NetworkRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
////            }
//
////            servere.a.aie_map.get(index).flip();
////            servere.main_work_byte_array[index] ^= (byte)Math.pow(2, bit);
//
////            byte[] byte_array = new byte[1 + 4 + servere.main_work_byte_array.length];
////            byte_array[0] = SetWorkBytes.ID;
////            ByteWriter.set(byte_array, skinningentities.getEntityId(), 1);
////            System.arraycopy(servere.main_work_byte_array, 0, byte_array, 1 + 4, servere.main_work_byte_array.length);
////            NetworkRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
//        }
//    }
//}
