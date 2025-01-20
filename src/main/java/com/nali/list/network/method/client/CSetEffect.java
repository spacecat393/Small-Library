//package com.nali.list.network.method.client;
//
//import com.nali.list.network.message.ClientMessage;
//import com.nali.small.entity.EntityLeInv;
//import com.nali.small.gui.MixGui;
//import com.nali.small.gui.features.messages.EffectsGUIFeatures;
//import com.nali.system.bytes.ByteReader;
//import net.minecraft.client.Minecraft;
//import net.minecraft.entity.Entity;
//
//import static com.nali.small.gui.features.messages.EffectsGUIFeatures.EFFECTS_INT_ARRAY;
//
//public class CSetEffect
//{
//	public static byte ID;
//
//	public static void run(ClientMessage clientmessage)
//	{
//		Entity entity = Minecraft.getMinecraft().world.getEntityByID(ByteReader.getInt(clientmessage.data, 1));
//
//		if (entity instanceof EntityLeInv)
//		{
//			EFFECTS_INT_ARRAY = new int[(clientmessage.data.length - 1 - 4) / 4];
//			int rg = 0;
//			for (int i = 1 + 4; i < clientmessage.data.length; i += 4)
//			{
//				EFFECTS_INT_ARRAY[rg++] = ByteReader.getInt(clientmessage.data, i);
//				i += 4;
//				EFFECTS_INT_ARRAY[rg++] = ByteReader.getInt(clientmessage.data, i);
//				i += 4;
//				EFFECTS_INT_ARRAY[rg++] = ByteReader.getInt(clientmessage.data, i);
//			}
//
//			MixGui.GUIFEATURESLOADER = new EffectsGUIFeatures(MixGui.I);
//		}
////				//sync looking
////				Entity entity = Minecraft.getMinecraft().world.getEntityByID(BytesReader.getInt(clientmessage.data, 1));
////
////				if (entity instanceof SkinningEntities)
////				{
////					SkinningEntities skinningentities = (SkinningEntities)entity;
////					skinningentities.rotationYaw = BytesReader.getFloat(clientmessage.data, 1 + 4);
////					skinningentities.rotationPitch = BytesReader.getFloat(clientmessage.data, 1 + 4 + 4);
////					skinningentities.rotationYawHead = BytesReader.getFloat(clientmessage.data, 1 + 4 + 4 + 4);
////					skinningentities.renderYawOffset = BytesReader.getFloat(clientmessage.data, 1 + 4 + 4 + 4 + 4);
////				}
//	}
//}
