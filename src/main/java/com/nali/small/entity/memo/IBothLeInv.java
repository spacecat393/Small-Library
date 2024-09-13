package com.nali.small.entity.memo;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.inv.InvLe;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public interface IBothLeInv<IE extends InvLe, SD, BD extends IBothDaNe, E extends EntityLivingBase, I extends IMixE<SD, BD, E>> extends IBothLe<SD, BD, E, I>, IBothNInv<IE>
{
	void damageArmor(float damage);

	default Iterable<ItemStack> getHeldEquipment()
	{
		return this.getIE().hands_itemstack_nonnulllist;
	}

	default Iterable<ItemStack> getArmorInventoryList()
	{
		return this.getIE().armor_itemstack_nonnulllist;
	}

	default ItemStack getItemStackFromSlot(EntityEquipmentSlot entityequipmentslot)
	{
		IE ie = this.getIE();
		switch (entityequipmentslot.getSlotType())
		{
			case HAND:
			{
				return ie.hands_itemstack_nonnulllist.get(entityequipmentslot.getIndex());
			}
			case ARMOR:
			{
				return ie.armor_itemstack_nonnulllist.get(entityequipmentslot.getIndex());
			}
			default:
			{
				return ItemStack.EMPTY;
			}
		}
	}

	void setItemStackToSlot(EntityEquipmentSlot entityequipmentslot, ItemStack itemstack);//need check both side
//	@Override
//	default void writeEntityToNBT(NBTTagCompound nbttagcompound)
//	{
//		this.writeInvToNBT(nbttagcompound);
//	}
//
//	@Override
//	default void readEntityFromNBT(NBTTagCompound nbttagcompound)
//	{
//		this.readInvFromNBT(nbttagcompound);
//	}
}
