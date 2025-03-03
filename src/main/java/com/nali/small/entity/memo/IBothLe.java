package com.nali.small.entity.memo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public interface IBothLe
<
	E extends EntityLivingBase
> extends IBothE<E>
{
	@Override
	default boolean isMove(E e)
	{
		return IBothE.super.isMove(e) && e.getHealth() > 0.0F;
	}

	default boolean isZeroMove(E e)
	{
		return e.getHealth() <= 0.0F;
	}

//	//both
//	WorkEBodyYaw getWorkEBodyYaw();

	//server
	boolean attackEntityAsMob(Entity entity);
	boolean attackEntityFrom(DamageSource damagesource, float amount);
	void getHurtSound(DamageSource damagesource);
	void getDeathSound();

	//server-inv
	void damageArmor(float damage);
	Iterable<ItemStack> getHeldEquipment();
	Iterable<ItemStack> getArmorInventoryList();
	ItemStack getItemStackFromSlot(EntityEquipmentSlot entityequipmentslot);
	void setItemStackToSlot(EntityEquipmentSlot entityequipmentslot, ItemStack itemstack);
}
