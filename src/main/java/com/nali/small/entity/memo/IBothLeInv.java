package com.nali.small.entity.memo;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.IMixE;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public interface IBothLeInv<SD, BD extends IBothDaNe, E extends EntityLivingBase, I extends IMixE<SD, BD, E>> extends IBothLe<SD, BD, E, I>/*, IBothInv*/
{
	void damageArmor(float damage);
	Iterable<ItemStack> getHeldEquipment();
	Iterable<ItemStack> getArmorInventoryList();
	ItemStack getItemStackFromSlot(EntityEquipmentSlot entityequipmentslot);
	void setItemStackToSlot(EntityEquipmentSlot entityequipmentslot, ItemStack itemstack);
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
