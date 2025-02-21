//package com.nali.small.entity.inv;
//
//import com.nali.Nali;
//import com.nali.system.bytes.ByteReader;
//import com.nali.system.bytes.ByteWriter;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityTracker;
//import net.minecraft.entity.item.EntityItem;
//import net.minecraft.entity.item.EntityXPOrb;
//import net.minecraft.entity.projectile.EntityArrow;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.network.play.server.SPacketCollectItem;
//import net.minecraft.world.WorldServer;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//
//public class InvE
//{
//	public final static byte BS_LOCK = 1;
//	public static byte ST;
//
//	public ItemStack[] itemstack_array;
//
//	public void pick(Entity e, Entity entity, int quantity)
//	{
//		EntityTracker entitytracker = ((WorldServer)entity.world).getEntityTracker();
//
//		if (entity instanceof EntityItem || entity instanceof EntityArrow || entity instanceof EntityXPOrb)
//		{
//			entitytracker.sendToTracking(entity, new SPacketCollectItem(entity.getEntityId(), e.getEntityId(), quantity));
//		}
//	}
//
//	public ItemStack get(int index)
//	{
//		return this.itemstack_array[index];
//	}
//
//	public int size()
//	{
//		return this.itemstack_array.length;
//	}
//
//	public void set(int index, ItemStack itemstack)
//	{
//		this.itemstack_array[index] = itemstack;
//	}
//
//	public static boolean add(File world_file, int inv, ItemStack itemstack)
//	{
//		if (itemstack.isEmpty())
//		{
//			return false;
//		}
//
//		if ((ST & BS_LOCK) == 0)
//		{
//			ST |= BS_LOCK;
//
//			File inv_file = new File(world_file, "nali/inv/" + inv);
//
//			int item_id = Item.getIdFromItem(itemstack.getItem());
//			File inv_n_file = new File(inv_file, "" + item_id);
//
//			long count = itemstack.getCount();
//			byte[] count_byte_array = new byte[Long.BYTES];
//
//			if (inv_n_file.exists())
//			{
//				try
//				{
//					byte[] byte_array = Files.readAllBytes(inv_n_file.toPath());
//					count += ByteReader.getLong(byte_array, 0);
//					if ((count & (1L << 63)) != 0)
//					{
//						ST &= 255 - BS_LOCK;
//						return false;
//					}
//				}
//				catch (Exception e)
//				{
//					Nali.warn(e);
//					inv_n_file.delete();
//				}
//			}
//			try
//			{
//				ByteWriter.set(count_byte_array, count, 0);
//				Files.write(inv_n_file.toPath(), count_byte_array);
//			}
//			catch (IOException e)
//			{
//				Nali.warn(e);
//			}
//
//			NBTTagCompound nbttagcompound = itemstack.getTagCompound();
//			if (nbttagcompound != null)
//			{
//				byte[] nbt_byte_array = ByteWriter.serializeNBT(nbttagcompound);
//
//				File nbt_n_file = new File(inv_file, "nbt/" + item_id);
//				nbt_n_file.mkdir();
//				try
//				{
//					long file_index = 0;
//					File nbt_i_file = new File(nbt_n_file, "" + file_index);
//					while (nbt_i_file.exists())
//					{
//						++file_index;
//						nbt_i_file = new File(nbt_n_file, "" + file_index);
//					}
//
//					Files.write(nbt_i_file.toPath(), nbt_byte_array);
//				}
//				catch (IOException e)
//				{
//					Nali.warn(e);
//				}
//			}
//
//			ST &= 255 - BS_LOCK;
//			return true;
//		}
//
//		return false;
//	}
//
//	public static boolean isSameItemSameTags(ItemStack itemstack_a, ItemStack itemstack_b)
//	{
//		return itemstack_a.getItem() == itemstack_b.getItem() && ItemStack.areItemStackTagsEqual(itemstack_a, itemstack_b);
//	}
//}
