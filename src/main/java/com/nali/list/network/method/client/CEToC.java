//package com.nali.list.network.method.client;
//
//import com.nali.list.network.message.ClientMessage;
//import com.nali.small.entity.memo.client.ClientE;
//import com.nali.system.bytes.ByteReader;
//
//import static com.nali.small.entity.memo.client.ClientE.C_MAP;
//
//public class CEToC
//{
//	public static byte ID;
//
//	public static void run(ClientMessage clientmessage)
//	{
////		ClientE c = C_MAP.get(ByteReader.getUUID(clientmessage.data, 1));
////		if (c != null)
////		{
//////			c.i.getBD()
//////			c.mc.dimension = ByteReader.getInt(clientmessage.data, 1 + 16);
////			//1 + 8 as 4
////			c.x = ByteReader.getFloat(clientmessage.data, 1 + 8 + 4);
////			c.y = ByteReader.getFloat(clientmessage.data, 1 + 8 + 4 + 4);
////			c.z = ByteReader.getFloat(clientmessage.data, 1 + 8 + 4 + 4 + 4);
////			String string = new String(clientmessage.data, 1 + 8 + 4 + 4 + 4 + 4, clientmessage.data.length - (1 + 8 + 4 + 4 + 4 + 4)/* - frame*/ - 1 - 4);
////			c.name_string = string;
////			c.hp = ByteReader.getInt(clientmessage.data, 1 + 8 + 4 + 4 + 4 + 4 + string.getBytes().length/* + frame*/ + 1);
//////			PageArmy.BYTE |= 2;
////			PageArmy.BYTE |= 1;
////		}
//	}
//}
