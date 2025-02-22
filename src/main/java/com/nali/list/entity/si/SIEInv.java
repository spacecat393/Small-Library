package com.nali.list.entity.si;

import com.nali.Nali;
import com.nali.da.IBothDaE;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import com.nali.small.mixin.IMixinEntityXPOrb;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketCollectItem;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class SIEInv
<
	BD extends IBothDaE,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
> extends SI<BD, E, I, S, MS>
{
	public static byte ID;

	public SIEArea<BD, E, I, S, MS> siearea;
	public SIELocation<BD, E, I, S, MS> sielocation;
	public SIEFindMove<BD, E, I, S, MS> siefindmove;

	public final static byte B_MOVE_TO = 1;

	public final static byte B_REMOTE_XP = 2;
	public final static byte B_REMOTE_ITEM = 4;
	public final static byte B_CAN_TAKE_XP = 8;
	public final static byte B_CAN_TAKE_ITEM = 16;
	public final static byte B_WALK_TO_XP = 32;
	public final static byte B_WALK_TO_ITEM = 64;

//	public final static short B_LOCK = 128;
//	public final static byte B_GHOST_INV = (byte)128;
	public byte state = B_CAN_TAKE_XP | B_CAN_TAKE_ITEM;//move_to | remote_xp remote_item can_take_xp can_take_item walk_to_xp walk_to_item
//	public boolean pickup;
//	public int item_time_out, xp_time_out;
	public int xp;

	//s0-net
	public EntityPlayerMP entityplayermp;
	public final static byte N_CHECK = 0;
//	public final static byte N_ADD = 0;
	public final static byte N_DELETE = 1;
//	public final static byte N_MOVE = 2;
	public byte net = -1;
	public int net_slot = -1;
	//e0-net

	//s0-inv
	public final static byte BS_LOCK = 1;
	public static byte ST;

	public ItemStack[] itemstack_array;

	public void invPick(Entity e, Entity entity, int quantity)
	{
		EntityTracker entitytracker = ((WorldServer)entity.world).getEntityTracker();

		if (entity instanceof EntityItem || entity instanceof EntityArrow || entity instanceof EntityXPOrb)
		{
			entitytracker.sendToTracking(entity, new SPacketCollectItem(entity.getEntityId(), e.getEntityId(), quantity));
		}
	}

	public ItemStack invGet(int index)
	{
		return this.itemstack_array[index];
	}

	public int invSize()
	{
		return this.itemstack_array.length;
	}

	public void invSet(int index, ItemStack itemstack)
	{
		this.itemstack_array[index] = itemstack;
	}

	public static boolean invAdd(File world_file, int inv, ItemStack itemstack)
	{
		if (itemstack.isEmpty())
		{
			return false;
		}

//		if ((ST & BS_LOCK) == 0)
//		{
//			ST |= BS_LOCK;
		File inv_file = new File(world_file, "nali/inv/" + inv);

		int item_id = Item.getIdFromItem(itemstack.getItem());
		File inv_n_file = new File(inv_file, "" + item_id);

		long count = itemstack.getCount();
		byte[] count_byte_array = new byte[Long.BYTES];

		if (inv_n_file.exists())
		{
			try
			{
				byte[] byte_array = Files.readAllBytes(inv_n_file.toPath());
				count += ByteReader.getLong(byte_array, 0);
				if ((count & (1L << 63)) != 0)
				{
					ST &= 255 - BS_LOCK;
					return false;
				}
			}
			catch (Exception e)
			{
				Nali.warn(e);
				inv_n_file.delete();
			}
		}
		try
		{
			ByteWriter.set(count_byte_array, count, 0);
			Files.write(inv_n_file.toPath(), count_byte_array);
		}
		catch (IOException e)
		{
			Nali.warn(e);
		}

		NBTTagCompound nbttagcompound = itemstack.getTagCompound();
		if (nbttagcompound != null)
		{
			byte[] nbt_byte_array = ByteWriter.serializeNBT(nbttagcompound);

			File nbt_n_file = new File(inv_file, "nbt/" + item_id);
			nbt_n_file.mkdir();
			try
			{
				long file_index = 0;
				File nbt_i_file = new File(nbt_n_file, "" + file_index);
				while (nbt_i_file.exists())
				{
					++file_index;
					nbt_i_file = new File(nbt_n_file, "" + file_index);
				}

				Files.write(nbt_i_file.toPath(), nbt_byte_array);
			}
			catch (IOException e)
			{
				Nali.warn(e);
			}
		}
//			ST &= 255 - BS_LOCK;
		return true;
//		}
//		return false;
	}

	public static boolean isSameItemSameTags(ItemStack itemstack_a, ItemStack itemstack_b)
	{
		return itemstack_a.getItem() == itemstack_b.getItem() && ItemStack.areItemStackTagsEqual(itemstack_a, itemstack_b);
	}
	//e0-inv

	public SIEInv(S s)
	{
		super(s);
	}

	@Override
	public void init()
	{
		this.siearea = (SIEArea<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEArea.ID);
		this.sielocation = (SIELocation<BD, E, I, S, MS>)this.s.ms.si_map.get(SIELocation.ID);
		this.siefindmove = (SIEFindMove<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEFindMove.ID);
	}

	@Override
	public void call(EntityPlayerMP entityplayermp, byte[] byte_array)
	{
	}

//	public void set()
//	{
//		byte[] byte_array = this.s.ms.byte_array;
//		float id = ByteReader.getFloat(byte_array, 1 + 8 + 1 + 1);
//		float f = ByteReader.getFloat(byte_array, 1 + 8 + 1 + 1 + 4);
//
////		SmallSakuraType smallsakuratypes = this.s.ms.entityplayermp.getCapability(SmallSakuraSerializable.SMALLSAKURATYPES_CAPABILITY, null);
////		byte value = smallsakuratypes.get();
//		UUID player_uuid = this.s.ms.entityplayermp.getUniqueID();
//		byte value = PlayerData.SAKURA_MAP.getOrDefault(player_uuid, (byte)0);
//
//		if (id >= 2)
//		{
//			if (id == 2.1F)
//			{
//				if (f == 1)
//				{
//					if (value >= 1)
//					{
////						smallsakuratypes.set((byte)(value - 1));
//						PlayerData.SAKURA_MAP.put(player_uuid, (byte)(value - 1));
//						this.flag |= 4;
//					}
//				}
//				else
//				{
//					this.flag &= 255-4;
//				}
//			}
//			else if (id == 2.2F)
//			{
//				if (f == 1)
//				{
//					if (value >= 1)
//					{
////						smallsakuratypes.set((byte)(value - 1));
//						PlayerData.SAKURA_MAP.put(player_uuid, (byte)(value - 1));
//						this.flag |= 64;
//					}
//				}
//				else
//				{
//					this.flag &= 255-64;
//				}
//			}
//			else if (id == 2.3F)
//			{
//				if (f == 1)
//				{
//					if (value >= 1)
//					{
////						smallsakuratypes.set((byte)(value - 1));
//						PlayerData.SAKURA_MAP.put(player_uuid, (byte)(value - 1));
//						this.flag |= 16;
//					}
//				}
//				else
//				{
//					this.flag &= 255-16;
//				}
//			}
//		}
//		else if (id >= 1)
//		{
//			if (id == 1.1F)
//			{
//				if (f == 1)
//				{
//					if (value >= 1)
//					{
////						smallsakuratypes.set((byte)(value - 1));
//						PlayerData.SAKURA_MAP.put(player_uuid, (byte)(value - 1));
//						this.flag |= 2;
//					}
//				}
//				else
//				{
//					this.flag &= 255-2;
//				}
//			}
//			else if (id == 1.2F)
//			{
//				if (f == 1)
//				{
//					if (value >= 1)
//					{
////						smallsakuratypes.set((byte)(value - 1));
//						PlayerData.SAKURA_MAP.put(player_uuid, (byte)(value - 1));
//						this.flag |= 32;
//					}
//				}
//				else
//				{
//					this.flag &= 255-32;
//				}
//			}
//			else if (id == 1.3F)
//			{
//				if (f == 1)
//				{
//					if (value >= 1)
//					{
////						smallsakuratypes.set((byte)(value - 1));
//						PlayerData.SAKURA_MAP.put(player_uuid, (byte)(value - 1));
//						this.flag |= 8;
//					}
//				}
//				else
//				{
//					this.flag &= 255-8;
//				}
//			}
//		}
//
//		this.fetch();
//	}
//
//	public void fetch()
//	{
//		byte[] byte_array = new byte[1 + 1];
//		byte_array[0] = CSetGetItem.ID;
//		byte_array[1] = this.flag;
//		NetworkRegistry.I.sendTo(new ClientMessage(byte_array), this.s.ms.entityplayermp);
//	}

	//support lock inv in e only
	@Override
	public void onUpdate()
	{
		E e = this.s.i.getE();
		if (this.s.isMove(e))
		{
			boolean xp = !this.siearea.xp_entity_list.isEmpty();
			EntityXPOrb to_entityxporb = null;
			ItemStack xp_itemstack = null;
			boolean should_get_xp = false;
			if (xp)
			{
				to_entityxporb = this.siearea.xp_entity_list.get(this.siearea.xp_entity_list.size() - 1);
				//!need le
//				xp_itemstack = EnchantmentHelper.getEnchantedItem(Enchantments.MENDING, e);
				xp_itemstack = this.getXPItemStack(e);
				should_get_xp = !xp_itemstack.isEmpty()/* && itemstack.isItemStackDamageable()*/ && xp_itemstack.getItemDamage() > 0/* && EnchantmentHelper.getEnchantmentLevel(Enchantments.MENDING, itemstack) > 0*/;
			}

			boolean item = !this.siearea.item_entity_list.isEmpty();

//			if (this.s.isWork(this.s.bytele.GET_ITEM()))
			if ((this.s.ms.flag & MixSIE.B_MAIN_WORK) == MixSIE.B_MAIN_WORK)
			{
	//			if ((this.s.main_work_byte_array[this.s.bytele.MINE() / 8] >> this.s.bytele.MINE() % 8 & 1) == 1 && this.s.entitiesaimemory.skinningentitiesmine.blockpos != null)
	//			{
	//				this.s.entitiesaimemory.skinningentitiesmine.breakWork();
	//			}

				if (xp && should_get_xp)
				{
					if ((this.state & B_WALK_TO_XP) == B_WALK_TO_XP && !e.getEntityBoundingBox().intersects(to_entityxporb.getEntityBoundingBox())/*!isTooClose(e, to_entityxporb, 0)*/)
					{
						if (this.sielocation.in(to_entityxporb))
						{
	//						Nali.LOGGER.info("XP START");
							this.state |= B_MOVE_TO;
//							this.siefindmove.setBreakGoal(to_entityxporb.posX, to_entityxporb.posY, to_entityxporb.posZ);
							this.siefindmove.setGoal(to_entityxporb.posX, to_entityxporb.posY, to_entityxporb.posZ);
						}
					}
				}
				else if (item)
				{
					EntityItem to_entityitem = this.siearea.item_entity_list.get(this.siearea.item_entity_list.size() - 1);
					if ((this.state & B_WALK_TO_ITEM) == B_WALK_TO_ITEM && !e.getEntityBoundingBox().intersects(to_entityitem.getEntityBoundingBox())/*!isTooClose(e, to_entityitem, 0)*/)
					{
						if (this.sielocation.in(to_entityitem))
						{
	//						Nali.LOGGER.info("ITEM START");
							this.state |= B_MOVE_TO;
//							this.siefindmove.setBreakGoal(to_entityitem.posX, to_entityitem.posY, to_entityitem.posZ);
							this.siefindmove.setGoal(to_entityitem.posX, to_entityitem.posY, to_entityitem.posZ);
						}
					}
				}
			}
	//		else
	//		{
	//			this.s.current_work_byte_array[this.s.bytele.GET_ITEM() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.GET_ITEM() % 8));
	//		}

			if (xp)
			{
				for (EntityXPOrb entityxporb : this.siearea.xp_entity_list)
				{
					if (should_get_xp)
					{
						if ((this.state & B_REMOTE_XP) == B_REMOTE_XP || e.getEntityBoundingBox().intersects(entityxporb.getEntityBoundingBox())/*isTooClose(e, entityxporb, 0)*/)
						{
							if ((this.state & B_MOVE_TO) == B_MOVE_TO)
							{
	//							Nali.LOGGER.info("XP END");
								this.siefindmove.endGoal();
								this.state &= 255 - B_MOVE_TO;
	//							this.s.current_work_byte_array[this.s.bytele.GET_ITEM() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.GET_ITEM() % 8));
							}
	//						Nali.LOGGER.info("XP STEP");
							if ((this.state & B_CAN_TAKE_XP/* + B_LOCK*/) == B_CAN_TAKE_XP)
							{
//								this.state |= B_LOCK;
	//							Nali.LOGGER.info("XP TAKE");
								//!need le
								this.invPick(e, entityxporb, 1);

								if (!xp_itemstack.isEmpty() && xp_itemstack.isItemDamaged())
								{
									float ratio = xp_itemstack.getItem().getXpRepairRatio(xp_itemstack);
									int i = Math.min(IMixinEntityXPOrb.GOroundAverage(entityxporb.xpValue * ratio), xp_itemstack.getItemDamage());
									entityxporb.xpValue -= IMixinEntityXPOrb.GOroundAverage(i / ratio);
									xp_itemstack.setItemDamage(xp_itemstack.getItemDamage() - i);
								}

								entityxporb.setDead();
//								this.state &= 255 - B_LOCK;
							}
						}
					}
				}
			}

			if (item)
			{
				for (EntityItem entityitem : this.siearea.item_entity_list)
				{
					ItemStack itemstack = entityitem.getItem();

					if ((this.state & B_REMOTE_ITEM) == B_REMOTE_ITEM || e.getEntityBoundingBox().intersects(entityitem.getEntityBoundingBox())/*isTooClose(e, entityitem, 0)*/)
					{
						if ((this.state & B_MOVE_TO) == B_MOVE_TO)
						{
	//						Nali.LOGGER.info("ITEM END");
							this.siefindmove.endGoal();
							this.state &= 255 - B_MOVE_TO;
	//						this.s.current_work_byte_array[this.s.bytele.GET_ITEM() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.GET_ITEM() % 8));
						}
	//					Nali.LOGGER.info("ITEM STEP");
						if ((this.state & B_CAN_TAKE_ITEM/* + B_LOCK*/) == B_CAN_TAKE_ITEM)
						{
//							this.state |= B_LOCK;

	//						Nali.LOGGER.info("ITEM TAKE");
							for (int i = 0; i < this.invSize(); ++i)
							{
								ItemStack inv_itemstack = this.invGet(i);

								int max_stack = inv_itemstack.getMaxStackSize();
								int e_count = itemstack.getCount();

								if (inv_itemstack.isEmpty())
								{
									this.invPick(e, entityitem, e_count);
									this.invSet(i, itemstack);
									entityitem.setDead();

//									if (i >= 27-3*3+2+4 && i <= 27-3*3+2+4+3*3)
//									{
//										Container container = ((MixinInventoryCrafting)inventory.inventorycrafting).eventHandler();
//										if (container != null)
//										{
//											container.onCraftMatrixChanged(inventory.inventorycrafting);
//										}
//									}

									break;
								}

								int max_count = inv_itemstack.getCount() + e_count;
								int count = max_count - max_stack;
								if (isSameItemSameTags(inv_itemstack, itemstack))
								{
									if (count <= 0)
									{
										this.invPick(e, entityitem, e_count);
										inv_itemstack.setCount(max_count);
										entityitem.setDead();

										break;
									}
									else
									{
										inv_itemstack.setCount(max_stack);
										itemstack.setCount(count);
										entityitem.setItem(itemstack);
									}
								}
							}

//							this.state &= 255 - B_LOCK;
						}
					}
				}
			}

	//		if ((this.flag & 1) == 1)
	//		{
	//			this.siefindmove.endGoal();
	//			this.flag &= 255-1;
	//			this.s.current_work_byte_array[this.s.bytele.GET_ITEM() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.GET_ITEM() % 8));
	//		}
//			if ((this.flag & 1) == 0)
//			{
//				this.s.current_work_byte_array[this.s.bytele.GET_ITEM() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.GET_ITEM() % 8));
//			}
		}

//		if ((this.state & B_LOCK) == 0)
//		{
//		this.state |= B_LOCK;

		if (this.net == N_CHECK)
		{
			ItemStack inv_itemstack = this.invGet(this.net_slot);
			if (inv_itemstack.isEmpty())
			{
				ItemStack player_itemstack = this.entityplayermp.getHeldItem(this.entityplayermp.getActiveHand());
				if (!player_itemstack.isEmpty())
				{
					int max_stack = inv_itemstack.getMaxStackSize();
					int e_count = player_itemstack.getCount();

					if (inv_itemstack.isEmpty())
					{
						this.invSet(this.net_slot, player_itemstack.copy());
						player_itemstack.setCount(0);
					}
					else
					{
						int max_count = inv_itemstack.getCount() + e_count;
						int count = max_count - max_stack;
						if (SIEInv.isSameItemSameTags(inv_itemstack, player_itemstack))
						{
							if (count <= 0)
							{
								inv_itemstack.setCount(max_count);
								player_itemstack.setCount(0);
							}
							else
							{
								inv_itemstack.setCount(max_stack);
								player_itemstack.setCount(count);
							}
						}
					}
				}
			}
			else
			{
				if (!this.entityplayermp.inventory.addItemStackToInventory(inv_itemstack))
				{
					World world = this.entityplayermp.world;
					world.spawnEntity(new EntityItem(world, this.entityplayermp.posX, this.entityplayermp.posY, this.entityplayermp.posZ, inv_itemstack));
				}
			}
			this.clearNet();
		}
		else if (this.net == N_DELETE)
		{
			for (int i = 0; i < this.invSize(); ++i)
			{
				this.invSet(i, ItemStack.EMPTY);
			}
			this.clearNet();
		}
//		ItemStack itemstack;
//		switch (this.net)
//		{
//			case N_ADD:
//				itemstack = this.entityplayermp.getHeldItem(this.entityplayermp.getActiveHand());
//				if (!itemstack.isEmpty())
//				{
//					for (int i = 0; i < this.invSize(); ++i)
//					{
//						ItemStack inv_itemstack = this.invGet(i);
//
//						int max_stack = inv_itemstack.getMaxStackSize();
//						int e_count = itemstack.getCount();
//
//						if (inv_itemstack.isEmpty())
//						{
//							this.invSet(i, itemstack);
//							itemstack.setCount(0);
//							break;
//						}
//
//						int max_count = inv_itemstack.getCount() + e_count;
//						int count = max_count - max_stack;
//						if (SIEInv.isSameItemSameTags(inv_itemstack, itemstack))
//						{
//							if (count <= 0)
//							{
//								inv_itemstack.setCount(max_count);
//								itemstack.setCount(0);
//								break;
//							}
//							else
//							{
//								inv_itemstack.setCount(max_stack);
//								itemstack.setCount(count);
//							}
//						}
//					}
//				}
//				this.clearNet();
//				break;
//			case N_DELETE:
//				for (int i = 0; i < this.invSize(); ++i)
//				{
//					this.invSet(i, ItemStack.EMPTY);
//				}
//				this.clearNet();
//				break;
//			case N_MOVE:
//				itemstack = this.invGet(this.net_slot);
//				if (!this.entityplayermp.inventory.addItemStackToInventory(itemstack))
//				{
//					World world = this.entityplayermp.world;
//					world.spawnEntity(new EntityItem(world, this.entityplayermp.posX, this.entityplayermp.posY, this.entityplayermp.posZ, itemstack));
//				}
//				this.clearNet();
//				break;
//		}
////		this.state &= 255 - B_LOCK;
////		}
	}

	@Override
	public void writeFile(SIData sidata)
	{
		sidata.byte_array[sidata.index++] = this.state;

		ByteWriter.set(sidata.byte_array, this.byteSize(), sidata.index);
		sidata.index += 4;

		for (int i = 0; i < this.invSize(); ++i)
		{
			ItemStack itemstack = this.invGet(i);
			if (!itemstack.isEmpty())
			{
				NBTTagCompound nbttagcompound = new NBTTagCompound();
				itemstack.writeToNBT(nbttagcompound);
				byte[] byte_array = ByteWriter.serializeNBT(nbttagcompound);
				int byte_array_length = byte_array.length;

				ByteWriter.set(sidata.byte_array, byte_array_length, sidata.index);
				sidata.index += 4;

				System.arraycopy(byte_array, 0, sidata.byte_array, sidata.index, byte_array_length);
				sidata.index += byte_array_length;
			}
		}
	}

	@Override
	public void readFile(SIData sidata)
	{
		this.state = sidata.byte_array[sidata.index++];

		int byte_size = ByteReader.getInt(sidata.byte_array, sidata.index);
		sidata.index += 4;

		for (int i = 0; i < byte_size; ++i)
		{
			ItemStack itemstack = this.invGet(i);
			if (!itemstack.isEmpty())
			{
				int byte_array_length = ByteReader.getInt(sidata.byte_array, sidata.index);
				sidata.index += 4;

				byte[] byte_array = new byte[byte_array_length];
				System.arraycopy(sidata.byte_array, sidata.index, byte_array, 0, byte_array_length);
				NBTTagCompound nbttagcompound = ByteReader.deserializeNBT(byte_array);
				this.invSet(i, new ItemStack(nbttagcompound));
				sidata.index += byte_array_length;
			}
		}
	}

	@Override
	public int size()
	{
		return 1 + 4 + this.byteSize();
	}

	public ItemStack getXPItemStack(E e)
	{
		return ItemStack.EMPTY;
	}

	public int byteSize()
	{
		int size = 0;
		for (int i = 0; i < this.invSize(); ++i)
		{
			ItemStack itemstack = this.invGet(i);
			if (!itemstack.isEmpty())
			{
				NBTTagCompound nbttagcompound = new NBTTagCompound();
				itemstack.writeToNBT(nbttagcompound);
				size += 4 + ByteWriter.serializeNBT(nbttagcompound).length;
			}
		}
		return size;
	}

	public void clearNet()
	{
		this.entityplayermp = null;
		this.net = -1;
		this.net_slot = -1;
	}

//	public static int roundAverage(float value)
//	{
//		double floor = Math.floor(value);
//		return (int) floor + (Math.random() < value - floor ? 1 : 0);
//	}
}
