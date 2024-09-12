//package com.nali.list.network.method.server;
//
//import com.nali.list.network.message.ServerMessage;
//import com.nali.small.entity.EntityLeInv;
//import com.nali.system.bytes.BytesReader;
//import net.minecraft.entity.player.EntityPlayerMP;
//
//import static com.nali.list.network.handler.ServerHandler.canPass;
//import static com.nali.small.entity.memo.server.ServerE.ENTITIES_MAP;
//
//public class SSetXYZ
//{
//	public static byte ID;
//
//	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
//	{
//		EntityLeInv skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
//		if (skinningentities != null && canPass(skinningentities, entityplayermp))
//		{
//			skinningentities.setPositionAndUpdate(BytesReader.getFloat(servermessage.data, 1 + 16), BytesReader.getFloat(servermessage.data, 1 + 16 + 4), BytesReader.getFloat(servermessage.data, 1 + 16 + 4 + 4));
//		}
//	}
//}
