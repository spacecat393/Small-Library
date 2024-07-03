package com.nali.small.entity;

import com.nali.small.entity.memo.IBothLeInv;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class EntityLeInv extends EntityLe implements IMixLe
{
    public static DataParameter<ItemStack> MOUTH_ITEMSTACK_DATAPARAMETER = EntityDataManager.createKey(EntityLeInv.class, DataSerializers.ITEM_STACK);
    public IBothLeInv ibothleinv;

    public EntityLeInv(World world)
    {
        super(world);
        this.Einit(this, world);
    }

    @Override
    public void damageArmor(float damage)
    {
        damage = damage / 4.0F;

        if (damage < 1.0F)
        {
            damage = 1.0F;
        }

        Inventory inventory = this.ibothleinv.getInventory();
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
    public void setAIMoveSpeed(float speed)
    {
        super.setAIMoveSpeed(speed);
        this.moveForward = speed;
    }

    @Override
    public void entityInit()
    {
        super.entityInit();
        this.EentityInit();
        this.dataManager.register(MOUTH_ITEMSTACK_DATAPARAMETER, ItemStack.EMPTY);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        this.EwriteEntityToNBT(nbttagcompound);
        this.ibothleinv.writeEntityToNBT(nbttagcompound);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        this.EreadEntityFromNBT(nbttagcompound);
        this.ibothleinv.readEntityFromNBT(nbttagcompound);
    }

    @Override
    public Iterable<ItemStack> getHeldEquipment()
    {
        return this.ibothleinv.getInventory().hands_itemstack_nonnulllist;
    }

    @Override
    public Iterable<ItemStack> getArmorInventoryList()
    {
        return this.ibothleinv.getInventory().armor_itemstack_nonnulllist;
    }

    @Override
    public ItemStack getItemStackFromSlot(EntityEquipmentSlot entityequipmentslot)
    {
        Inventory inventory = this.ibothleinv.getInventory();
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
        Inventory inventory = this.ibothleinv.getInventory();
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
    public boolean attackEntityFrom(DamageSource damagesource, float amount)
    {
        if (!this.ibothleinv.attackEntityFrom(damagesource, amount))
        {
            return false;
        }
        return super.attackEntityFrom(damagesource, amount);
    }

    @Override
    public boolean attackEntityAsMob(Entity entity)
    {
        return this.ibothleinv.attackEntityAsMob(entity);
    }

    @Override
    public float updateDistance(float f0, float f1)
    {
        this.ibothleinv.getWorkEBodyYaw().run();
        return f1;
    }

    @Override
    public boolean processInitialInteract(EntityPlayer entityplayer, EnumHand enumhand)
    {
        return this.ibothleinv.processInitialInteract(entityplayer, enumhand);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        this.ibothleinv.onUpdate();
    }

    @Nullable
    @Override
    public SoundEvent getHurtSound(DamageSource damagesource)
    {
        this.ibothleinv.getHurtSound(damagesource);
        return super.getHurtSound(damagesource);
    }

    @Nullable
    @Override
    public SoundEvent getDeathSound()
    {
        this.ibothleinv.getDeathSound();
        return super.getDeathSound();
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