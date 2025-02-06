//package com.nali.list.network.method.client;
//
//import com.nali.list.network.message.ClientMessage;
//import com.nali.small.entity.IMixE;
//import com.nali.small.entity.memo.client.ClientE;
//import com.nali.system.bytes.ByteReader;
//import net.minecraft.client.Minecraft;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityList;
//import net.minecraft.world.World;
//
//import java.lang.reflect.InvocationTargetException;
//
//import static com.nali.Nali.warn;
//import static com.nali.small.entity.memo.client.ClientE.C_MAP;
//
//public class CSToC
//{
//	public static byte ID;
//	public static byte STATE = 2;//render ready_init client_reinit
//
//	public static void run(ClientMessage clientmessage)
//	{
//		if ((STATE & 2+4) == 2)
//		{
//			STATE &= 255-2;
//
//	//		ClientE.UUID_MAP.clear();
//			C_MAP.clear();
//
//			for (int i = 1; i < clientmessage.data.length; i += 4)
//			{
//	//			UUID uuid = ByteReader.getUUID(clientmessage.data, i);
//	//			i += 16;
//				long id_key = ByteReader.getLong(clientmessage.data, i);
//				i += 8;
//
//				int e_id = ByteReader.getInt(clientmessage.data, i);
//	//			int list_id = ByteReader.getInt(clientmessage.data, i);
//	//			i += 4;
//
//				World world = Minecraft.getMinecraft().player.world;
//
//				ClientE cliente;
//				Entity entity = world.getEntityByID((int)id_key);
//
//				if (entity instanceof IMixE)
//				{
//					IMixE imixe = (IMixE)entity;
//					cliente = (ClientE)imixe.getB();
//				}
//				else
//				{
//					try
//					{
//						cliente = (ClientE)EntityList.getClassFromID(e_id).getMethod("getC").invoke(null);
//						cliente.fake = true;
//	//					cliente.uuid = uuid;
//	//					cliente.onReadNBT();
//					}
//					catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
//					{
//						warn(e);
//						continue;
//					}
//				}
//
//				cliente.key = id_key;
//				cliente.e_id = e_id;
//
//	//			ClientE.UUID_MAP.put(list_id, uuid);
//
//	//			C_MAP.put(uuid, cliente);
//				C_MAP.put(id_key, cliente);
//			}
//
//			STATE |= 1;
//		}
//	}
//}
