package com.nali.small.entity;

import com.nali.small.entity.memo.IBothLeInv;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public abstract class EntityLeInv extends EntityLe
{
    public static DataParameter<ItemStack> MOUTH_ITEMSTACK_DATAPARAMETER = EntityDataManager.createKey(EntityLeInv.class, DataSerializers.ITEM_STACK);
    public IBothLeInv ibothleinv;

    public EntityLeInv(World world)
    {
        super(world);
        this.Einit(this, world);
    }

    @Override
    public void entityInit()
    {
        super.entityInit();
        this.dataManager.register(MOUTH_ITEMSTACK_DATAPARAMETER, ItemStack.EMPTY);
    }

    @Override
    public void damageArmor(float damage)
    {
        damage = damage / 4.0F;

        if (damage < 1.0F)
        {
            damage = 1.0F;
        }

        Inventory inventory = this.getB().getInventory();
        for (int i = 0; i < inventory.armor_itemstack_nonnulllist.size(); ++i)
        {
            ItemStack itemstack = inventory.armor_itemstack_nonnulllist.get(i);

            if (itemstack.getItem() instanceof ItemArmor)
            {
                itemstack.damageItem((int)damage, this);
            }
        }
    }

    @Override
    public Iterable<ItemStack> getHeldEquipment()
    {
        return this.getB().getInventory().hands_itemstack_nonnulllist;
    }

    @Override
    public Iterable<ItemStack> getArmorInventoryList()
    {
        return this.getB().getInventory().armor_itemstack_nonnulllist;
    }

    @Override
    public ItemStack getItemStackFromSlot(EntityEquipmentSlot entityequipmentslot)
    {
        Inventory inventory = this.getB().getInventory();
        switch (entityequipmentslot.getSlotType())
        {
            case HAND:
            {
                return inventory.hands_itemstack_nonnulllist.get(entityequipmentslot.getIndex());
            }
            case ARMOR:
            {
                return inventory.armor_itemstack_nonnulllist.get(entityequipmentslot.getIndex());
            }
            default:
            {
                return ItemStack.EMPTY;
            }
        }
    }

    @Override
    public void setItemStackToSlot(EntityEquipmentSlot entityequipmentslot, ItemStack itemstack)
    {
        this.playEquipSound(itemstack);
        Inventory inventory = this.getB().getInventory();
        switch (entityequipmentslot.getSlotType())
        {
            case HAND:
            {
                inventory.hands_itemstack_nonnulllist.set(entityequipmentslot.getIndex(), itemstack);
                break;
            }
            case ARMOR:
            {
                inventory.armor_itemstack_nonnulllist.set(entityequipmentslot.getIndex(), itemstack);
                break;
            }
            default:
            {
                break;
            }
        }
    }

    @Override
    public Entity getE()
    {
        return this;
    }

    @Override
    public IBothLeInv getB()
    {
        return ibothleinv;
    }
}