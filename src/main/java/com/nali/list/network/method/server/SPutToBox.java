//package com.nali.list.network.method.server;
//
//import com.nali.list.item.SmallBox;
//import com.nali.list.network.message.ServerMessage;
//import com.nali.small.entity.EntityLeInv;
//import com.nali.system.bytes.BytesReader;
//import net.minecraft.entity.player.EntityPlayerMP;
//import net.minecraft.item.ItemStack;
//
//import static com.nali.list.network.handler.ServerHandler.canPass;
//import static com.nali.small.entity.memo.server.ServerE.ENTITIES_MAP;
//import static com.nali.small.item.ItemRegistry.ITEM_ARRAY;
//
//public class SPutToBox
//{
//	public static byte ID;
//
//	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
//	{
//		EntityLeInv skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
//		if (skinningentities != null && canPass(skinningentities, entityplayermp))
//		{
//			ItemStack itemstack = entityplayermp.getHeldItemMainhand();
//
//			if (itemstack.getItem() == ITEM_ARRAY[SmallBox.ID] && itemstack.getTagCompound() == null)
//			{
//				SmallBox.putToBox(skinningentities, itemstack);
//				entityplayermp.closeScreen();
//			}
//		}
//	}
//}