//package com.nali.small.entity;
//
//import com.nali.small.entity.memo.IBothLeInv;
//import net.minecraft.entity.Entity;
//import net.minecraft.inventory.EntityEquipmentSlot;
//import net.minecraft.item.ItemStack;
//import net.minecraft.network.datasync.DataParameter;
//import net.minecraft.network.datasync.DataSerializers;
//import net.minecraft.network.datasync.EntityDataManager;
//import net.minecraft.world.World;
//
//public abstract class EntityLeInv extends EntityLe
//{
//	public static DataParameter<ItemStack> MOUTH_ITEMSTACK_DATAPARAMETER = EntityDataManager.createKey(EntityLeInv.class, DataSerializers.ITEM_STACK);
//
//	public EntityLeInv(World world)
//	{
//		super(world);
//	}
//
//	@Override
//	public void entityInit()
//	{
//		super.entityInit();
//		this.dataManager.register(MOUTH_ITEMSTACK_DATAPARAMETER, ItemStack.EMPTY);
//	}
//
//	@Override
//	public void damageArmor(float damage)
//	{
//		this.getB().damageArmor(damage);
//	}
//
//	@Override
//	public Iterable<ItemStack> getHeldEquipment()
//	{
//		return this.getB().getHeldEquipment();
//	}
//
//	@Override
//	public Iterable<ItemStack> getArmorInventoryList()
//	{
//		return this.getB().getArmorInventoryList();
//	}
//
//	@Override
//	public ItemStack getItemStackFromSlot(EntityEquipmentSlot entityequipmentslot)
//	{
//		return this.getB().getItemStackFromSlot(entityequipmentslot);
//	}
//
//	@Override
//	public void setItemStackToSlot(EntityEquipmentSlot entityequipmentslot, ItemStack itemstack)
//	{
//		this.getB().setItemStackToSlot(entityequipmentslot, itemstack);
//	}
//
//	@Override
//	public Entity getE()
//	{
//		return this;
//	}
//
//	@Override
//	public abstract IBothLeInv getB();
//}