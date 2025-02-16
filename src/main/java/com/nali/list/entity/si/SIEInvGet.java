package com.nali.list.entity.si;

import com.nali.da.IBothDaE;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.inv.InvE;
import com.nali.small.entity.memo.IBothEInv;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import com.nali.small.mixin.IMixinEntityXPOrb;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class SIEInvGet
<
	IE extends InvE,
	BD extends IBothDaE,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS> & IBothEInv<IE>,
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
//	public final static byte B_GHOST_INV = (byte)128;
	public byte state = B_CAN_TAKE_XP | B_CAN_TAKE_ITEM;//move_to | remote_xp remote_item can_take_xp can_take_item walk_to_xp walk_to_item
//	public boolean pickup;
//	public int item_time_out, xp_time_out;

	public SIEInvGet(S s)
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
							if ((this.state & B_CAN_TAKE_XP) == B_CAN_TAKE_XP)
							{
	//							Nali.LOGGER.info("XP TAKE");
								//!need le
								IE ie = this.s.getIE();
								ie.pick(e, entityxporb, 1);

								if (!xp_itemstack.isEmpty() && xp_itemstack.isItemDamaged())
								{
									float ratio = xp_itemstack.getItem().getXpRepairRatio(xp_itemstack);
									int i = Math.min(IMixinEntityXPOrb.GOroundAverage(entityxporb.xpValue * ratio), xp_itemstack.getItemDamage());
									entityxporb.xpValue -= IMixinEntityXPOrb.GOroundAverage(i / ratio);
									xp_itemstack.setItemDamage(xp_itemstack.getItemDamage() - i);
								}

								entityxporb.setDead();
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
						if ((this.state & B_CAN_TAKE_ITEM) == B_CAN_TAKE_ITEM)
						{
	//						Nali.LOGGER.info("ITEM TAKE");
							IE ie = this.s.getIE();
							for (byte i = 0; i < ie.size(); ++i)
							{
								ItemStack inv_itemstack = ie.get(i);

								int max_stack = inv_itemstack.getMaxStackSize();
								int e_count = itemstack.getCount();

								if (inv_itemstack.isEmpty())
								{
									ie.pick(e, entityitem, e_count);
									ie.set(i, itemstack);
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
								if (InvE.isSameItemSameTags(inv_itemstack, itemstack))
								{
									if (count <= 0)
									{
										ie.pick(e, entityitem, e_count);
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
	}

	@Override
	public void writeFile(SIData sidata)
	{
		sidata.byte_array[sidata.index++] = this.state;
	}

	@Override
	public void readFile(SIData sidata)
	{
		this.state = sidata.byte_array[sidata.index++];
	}

	@Override
	public int size()
	{
		return 1;
	}

	public ItemStack getXPItemStack(E e)
	{
		return ItemStack.EMPTY;
	}

//	public static int roundAverage(float value)
//	{
//		double floor = Math.floor(value);
//		return (int) floor + (Math.random() < value - floor ? 1 : 0);
//	}
}
