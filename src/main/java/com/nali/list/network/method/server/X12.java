//package com.nali.list.network.method.server;
//
//import com.nali.list.capability.serializable.SmallSakuraSerializable;
//import com.nali.list.capability.type.SmallSakuraType;
//import com.nali.list.item.SmallBox;
//import com.nali.list.network.message.ServerMessage;
//import com.nali.small.Small;
//import com.nali.small.config.MyConfig;
//import com.nali.small.entity.EntityRegistry;
//import com.nali.small.entity.memo.server.ServerE;
//import com.nali.small.entity.EntityLeInv;
//import net.minecraft.entity.player.EntityPlayerMP;
//import net.minecraft.init.Items;
//import net.minecraft.item.ItemStack;
//import net.minecraft.world.World;
//
//import java.lang.reflect.InvocationTargetException;
//import java.util.Random;
//
//import static com.nali.small.item.ItemRegistry.ITEM_ARRAY;
//
//public class X12
//{
//	public static byte ID;
//
//	public static byte GUARANTEE;
//	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
//	{
//		if (MyConfig.SERVER.player_rng)
//		{
//			Random random = entityplayermp.getRNG();
//			SmallSakuraType smallsakuratypes = entityplayermp.getCapability(SmallSakuraSerializable.SMALLSAKURATYPES_CAPABILITY, null);
//			byte value = smallsakuratypes.get();
//			if (value >= 12)
//			{
//				smallsakuratypes.set((byte)(value - 12));
//
//				if (GUARANTEE >= 10)
//				{
//					spawnX12(random, entityplayermp);
//					GUARANTEE = 0;
//				}
//				else
//				{
//					++GUARANTEE;
//				}
//
//				if (random.nextInt(7) == 0)
//				{
//					spawnX12(random, entityplayermp);
//				}
//				else
//				{
//					entityplayermp.dropItem(Items.STICK, 1);
//				}
//			}
//		}
//	}
//
//	public static void spawnX12(Random random, EntityPlayerMP entityplayermp)
//	{
//		try
//		{
//			ItemStack itemstack = new ItemStack(ITEM_ARRAY[SmallBox.ID]);
//			int id = random.nextInt(EntityRegistry.ENTITIES_CLASS_LIST.size());
//			EntityLeInv skinningentities = (EntityLeInv) EntityRegistry.ENTITIES_CLASS_LIST.get(id).getConstructor(World.class).newInstance(entityplayermp.world);
//			ServerE serverentitiesmemory = (ServerE)skinningentities.bothentitiesmemory;
//			serverentitiesmemory.owner_uuid = entityplayermp.getUniqueID();
//			SmallBox.putToBox(skinningentities, itemstack);
//			entityplayermp.entityDropItem(itemstack, 0.0F);
//		}
//		catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e)
//		{
//			Small.error(e);
//		}
//	}
//}
