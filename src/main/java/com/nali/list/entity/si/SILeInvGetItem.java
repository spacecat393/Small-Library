package com.nali.list.entity.si;

import com.nali.da.IBothDaNe;
import com.nali.list.capability.serializable.SmallSakuraSerializable;
import com.nali.list.capability.type.SmallSakuraType;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.method.client.CSetGetItem;
import com.nali.network.NetworkRegistry;
import com.nali.small.entity.EntityLeInv;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.IMixESoundDa;
import com.nali.small.entity.inv.InvLe;
import com.nali.small.entity.memo.server.ServerLeInv;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.sound.ISoundDaLe;
import com.nali.system.bytes.ByteReader;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;

import static com.nali.small.entity.EntityMath.isInArea;

public class SILeInvGetItem
<
	IE extends InvLe,
	SD extends ISoundDaLe,
	BD extends IBothDaNe,
	E extends EntityLeInv,
	I extends IMixE<BD, E> & IMixESoundDa<SD>,
	S extends ServerLeInv<IE, SD, BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
> extends SI<BD, E, I, S, MS>
{
	public static byte ID;

	public SIEArea<BD, E, I, S, MS> siearea;
	public SILeSetLocation<SD, BD, E, I, S, MS> silesetlocation;
	public SILeFindMove<SD, BD, E, I, S, MS> silefindmove;

	public byte flag;//move_to | remote_xp remote_item can_take_xp can_take_item walk_to_xp walk_to_item
//	public boolean pickup;
//	public int item_time_out, xp_time_out;

	public SILeInvGetItem(S s)
	{
		super(s);
	}

	@Override
	public void init()
	{
		this.siearea = (SIEArea<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEArea.ID);
		this.silesetlocation = (SILeSetLocation<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILeSetLocation.ID);
		this.silefindmove = (SILeFindMove<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILeFindMove.ID);
	}

	@Override
	public void call()
	{

	}

	public void set()
	{
		byte[] byte_array = this.s.ms.byte_array;
		float id = ByteReader.getFloat(byte_array, 1 + 8 + 1 + 1);
		float f = ByteReader.getFloat(byte_array, 1 + 8 + 1 + 1 + 4);

		SmallSakuraType smallsakuratypes = this.s.ms.entityplayermp.getCapability(SmallSakuraSerializable.SMALLSAKURATYPES_CAPABILITY, null);
		byte value = smallsakuratypes.get();

		if (id >= 2)
		{
			if (id == 2.1F)
			{
				if (f == 1)
				{
					if (value >= 1)
					{
						smallsakuratypes.set((byte)(value - 1));
						this.flag |= 4;
					}
				}
				else
				{
					this.flag &= 255-4;
				}
			}
			else if (id == 2.2F)
			{
				if (f == 1)
				{
					if (value >= 1)
					{
						smallsakuratypes.set((byte)(value - 1));
						this.flag |= 64;
					}
				}
				else
				{
					this.flag &= 255-64;
				}
			}
			else if (id == 2.3F)
			{
				if (f == 1)
				{
					if (value >= 1)
					{
						smallsakuratypes.set((byte)(value - 1));
						this.flag |= 16;
					}
				}
				else
				{
					this.flag &= 255-16;
				}
			}
		}
		else if (id >= 1)
		{
			if (id == 1.1F)
			{
				if (f == 1)
				{
					if (value >= 1)
					{
						smallsakuratypes.set((byte)(value - 1));
						this.flag |= 2;
					}
				}
				else
				{
					this.flag &= 255-2;
				}
			}
			else if (id == 1.2F)
			{
				if (f == 1)
				{
					if (value >= 1)
					{
						smallsakuratypes.set((byte)(value - 1));
						this.flag |= 32;
					}
				}
				else
				{
					this.flag &= 255-32;
				}
			}
			else if (id == 1.3F)
			{
				if (f == 1)
				{
					if (value >= 1)
					{
						smallsakuratypes.set((byte)(value - 1));
						this.flag |= 8;
					}
				}
				else
				{
					this.flag &= 255-8;
				}
			}
		}

		this.fetch();
	}

	public void fetch()
	{
		byte[] byte_array = new byte[1 + 1];
		byte_array[0] = CSetGetItem.ID;
		byte_array[1] = this.flag;
		NetworkRegistry.I.sendTo(new ClientMessage(byte_array), this.s.ms.entityplayermp);
	}

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
				xp_itemstack = EnchantmentHelper.getEnchantedItem(Enchantments.MENDING, e);
				should_get_xp = !xp_itemstack.isEmpty()/* && itemstack.isItemStackDamageable()*/ && xp_itemstack.getItemDamage() > 0/* && EnchantmentHelper.getEnchantmentLevel(Enchantments.MENDING, itemstack) > 0*/;
			}

			boolean item = !this.siearea.item_entity_list.isEmpty();

//			if (this.s.isWork(this.s.bytele.GET_ITEM()))
			if ((this.s.ms.state & 1) == 1)
			{
	//			if ((this.s.main_work_byte_array[this.s.bytele.MINE() / 8] >> this.s.bytele.MINE() % 8 & 1) == 1 && this.s.entitiesaimemory.skinningentitiesmine.blockpos != null)
	//			{
	//				this.s.entitiesaimemory.skinningentitiesmine.breakWork();
	//			}

				if (xp && should_get_xp)
				{
					if ((this.flag & 32) == 32 && !e.getEntityBoundingBox().intersects(to_entityxporb.getEntityBoundingBox())/*!isTooClose(e, to_entityxporb, 0)*/)
					{
						if (this.silesetlocation.far == 0 || this.silesetlocation.blockpos == null || isInArea(to_entityxporb, this.silesetlocation.blockpos, this.silesetlocation.far))
						{
	//						Nali.LOGGER.info("XP START");
							this.flag |= 1;
//							this.silefindmove.setBreakGoal(to_entityxporb.posX, to_entityxporb.posY, to_entityxporb.posZ);
							this.silefindmove.setGoal(to_entityxporb.posX, to_entityxporb.posY, to_entityxporb.posZ);
						}
					}
				}
				else if (item)
				{
					EntityItem to_entityitem = this.siearea.item_entity_list.get(this.siearea.item_entity_list.size() - 1);
					if ((this.flag & 64) == 64 && !e.getEntityBoundingBox().intersects(to_entityitem.getEntityBoundingBox())/*!isTooClose(e, to_entityitem, 0)*/)
					{
						if (this.silesetlocation.far == 0 || this.silesetlocation.blockpos == null || isInArea(to_entityitem, this.silesetlocation.blockpos, this.silesetlocation.far))
						{
	//						Nali.LOGGER.info("ITEM START");
							this.flag |= 1;
//							this.silefindmove.setBreakGoal(to_entityitem.posX, to_entityitem.posY, to_entityitem.posZ);
							this.silefindmove.setGoal(to_entityitem.posX, to_entityitem.posY, to_entityitem.posZ);
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
						if ((this.flag & 2) == 2 || e.getEntityBoundingBox().intersects(entityxporb.getEntityBoundingBox())/*isTooClose(e, entityxporb, 0)*/)
						{
							if ((this.flag & 1) == 1)
							{
	//							Nali.LOGGER.info("XP END");
								this.silefindmove.endGoal();
								this.flag &= 255-1;
	//							this.s.current_work_byte_array[this.s.bytele.GET_ITEM() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.GET_ITEM() % 8));
							}
	//						Nali.LOGGER.info("XP STEP");
							if ((this.flag & 8) == 8)
							{
	//							Nali.LOGGER.info("XP TAKE");
								e.onItemPickup(entityxporb, 1);

								if (!xp_itemstack.isEmpty() && xp_itemstack.isItemDamaged())
								{
									float ratio = xp_itemstack.getItem().getXpRepairRatio(xp_itemstack);
									int i = Math.min(roundAverage(entityxporb.xpValue * ratio), xp_itemstack.getItemDamage());
									entityxporb.xpValue -= roundAverage(i / ratio);
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

					if ((this.flag & 4) == 4 || e.getEntityBoundingBox().intersects(entityitem.getEntityBoundingBox())/*isTooClose(e, entityitem, 0)*/)
					{
						if ((this.flag & 1) == 1)
						{
	//						Nali.LOGGER.info("ITEM END");
							this.silefindmove.endGoal();
							this.flag &= 255-1;
	//						this.s.current_work_byte_array[this.s.bytele.GET_ITEM() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.GET_ITEM() % 8));
						}
	//					Nali.LOGGER.info("ITEM STEP");
//						if ((this.flag & 16) == 16)
//						{
//	//						Nali.LOGGER.info("ITEM TAKE");
//							IE ie = this.s.getIE();
//							for (byte i = 0; i < inventory.getSizeInventory(); ++i)
//							{
//								ItemStack inv_itemstack = inventory.getStackInSlot(i);
//
//								int max_stack = inv_itemstack.getMaxStackSize();
//								int e_count = itemstack.getCount();
//
//								if (inv_itemstack.isEmpty())
//								{
//									e.onItemPickup(entityitem, e_count);
//									inventory.setInventorySlotContents(i, itemstack);
//									entityitem.setDead();
//
//									if (i >= 27-3*3+2+4 && i <= 27-3*3+2+4+3*3)
//									{
//										Container container = ((MixinInventoryCrafting)inventory.inventorycrafting).eventHandler();
//										if (container != null)
//										{
//											container.onCraftMatrixChanged(inventory.inventorycrafting);
//										}
//									}
//
//									break;
//								}
//
//								int max_count = inv_itemstack.getCount() + e_count;
//								int count = max_count - max_stack;
//								if (isSameItemSameTags(inv_itemstack, itemstack))
//								{
//									if (count <= 0)
//									{
//										e.onItemPickup(entityitem, e_count);
//										inv_itemstack.setCount(max_count);
//										entityitem.setDead();
//
//										break;
//									}
//									else
//									{
//										inv_itemstack.setCount(max_stack);
//										itemstack.setCount(count);
//										entityitem.setItem(itemstack);
//									}
//								}
//							}
//						}
					}
				}
			}

	//		if ((this.flag & 1) == 1)
	//		{
	//			this.silefindmove.endGoal();
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
		sidata.byte_array[sidata.index++] = this.flag;
	}

	@Override
	public void readFile(SIData sidata)
	{
		this.flag = sidata.byte_array[sidata.index++];
	}

	@Override
	public int size()
	{
		return 1;
	}

	public static boolean isSameItemSameTags(ItemStack itemstack_a, ItemStack itemstack_b)
	{
		return itemstack_a.getItem() == itemstack_b.getItem() && ItemStack.areItemStackTagsEqual(itemstack_a, itemstack_b);
	}

	public static int roundAverage(float value)
	{
		double floor = Math.floor(value);
		return (int) floor + (Math.random() < value - floor ? 1 : 0);
	}
}
