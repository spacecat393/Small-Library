//package com.nali.list.network.method.server;
//
//import com.nali.list.entity.si.SILeEat;
//import com.nali.list.network.message.ClientMessage;
//import com.nali.list.network.message.ServerMessage;
//import com.nali.list.network.method.client.CEToC;
//import com.nali.network.NetworkRegistry;
//import com.nali.small.entity.memo.server.ServerE;
//import com.nali.system.bytes.ByteReader;
//import com.nali.system.bytes.ByteWriter;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.player.EntityPlayerMP;
//
//import java.util.UUID;
//
//import static com.nali.small.entity.memo.server.ServerE.S_MAP;
//
//public class SEToC
//{
//	public static byte ID;
//
//	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
//	{
//		UUID uuid = ByteReader.getUUID(servermessage.data, 1);
//		ServerE s = S_MAP.get(uuid);
//		if (s != null/* && (s.ms.state & 8) == 8*/)
//		{
////			ChunkLoader.updateChunk(s);
//			Entity e = s.i.getE();
//			byte[] name_byte_array = e.getName().getBytes();
//			byte[] byte_array = new byte[1 + 8 + 4 + 4 + 4 + 4 + name_byte_array.length/* + 4 * s.frame_int_array.length*/ + 1 + 4];
//			byte_array[0] = CEToC.ID;
//
//			ByteWriter.set(byte_array, uuid, 1);
////			int dimension = e.dimension;
////			Nali.LOGGER.info("D " + dimension);
////			if (dimension == -1)
////			{
////				try
////				{
////					byte[] file_byte_array = Files.readAllBytes(new File(e.world.getSaveHandler().getWorldDirectory() + "/nali/entity/" + e.getUniqueID()).toPath());
////					dimension = ByteReader.getInt(file_byte_array, 4 + 8);
////				}
////				catch (IOException ex)
////				{
////					warn(e);
////				}
////			}
////			Nali.LOGGER.info("D " + dimension);
////			ByteWriter.set(byte_array, dimension, 1 + 16);
////			ByteWriter.set(byte_array, e.dimension, 1 + 16);
//			// 1 + 8 as 4
////			ByteWriter.set(byte_array, e.world.provider.getDimension(), 1 + 16);
//			ByteWriter.set(byte_array, (float)e.posX, 1 + 8 + 4);
//			ByteWriter.set(byte_array, (float)e.posY, 1 + 8 + 4 + 4);
//			ByteWriter.set(byte_array, (float)e.posZ, 1 + 8 + 4 + 4 + 4);
//			System.arraycopy(name_byte_array, 0, byte_array, 1 + 8 + 4 + 4 + 4 + 4, name_byte_array.length);
//
////			for (int i = 0; i < s.frame_int_array.length; ++i)
////			{
////				ByteWriter.set(byte_array, s.frame_int_array[i], 1 + 16 + 4 + 4 + 4 + 4 + name_byte_array.length + 4 * i);
////			}
//
//			byte_array[1 + 8 + 4 + 4 + 4 + 4 + name_byte_array.length/* + 4 * s.frame_int_array.length*/] = ((SILeEat)s.ms.si_map.get(SILeEat.ID)).state;
//			ByteWriter.set(byte_array, ((EntityLivingBase)e).getHealth(), 1 + 8 + 4 + 4 + 4 + 4 + name_byte_array.length/* + 4 * s.frame_int_array.length*/ + 1);
//
//			NetworkRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
//		}
//	}
//}
