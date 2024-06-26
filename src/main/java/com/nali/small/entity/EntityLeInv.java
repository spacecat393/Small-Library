package com.nali.small.entity;

import com.nali.small.entity.memo.INNeInv;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class EntityLeInv extends EntityLivingBase implements IMixLe
{
    public static DataParameter<ItemStack> MOUTH_ITEMSTACK_DATAPARAMETER = EntityDataManager.createKey(EntityLeInv.class, DataSerializers.ITEM_STACK);
        public INNeInv ibothleinv;

    public EntityLeInv(World world)
    {
        super(world);
        this.Einit(this, world);
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
    public double getYOffset()
    {
        return 0.3D;
    }

    @Override
    public boolean isPushedByWater()
    {
        return false;
    }

    @Override
    public boolean isMovementBlocked()
    {
        return false;
    }

    @Override
    public boolean isEntityAlive()
    {
        return !this.isDead;
    }

    @Override
    public EnumHandSide getPrimaryHand()
    {
        return EnumHandSide.RIGHT;
    }

    @Override
    public Vec3d getLook(float partialTicks)
    {
        if (partialTicks == 1.0F)
        {
            return this.getVectorForRotation(this.rotationPitch, this.rotationYaw);
        }
        else
        {
            float f = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * partialTicks;
            float f1 = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * partialTicks;
            return this.getVectorForRotation(f, f1);
        }
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
    public void damageShield(float damage)
    {
        if (damage >= 3.0F && this.activeItemStack.getItem().isShield(this.activeItemStack, this))
        {
            int i = 1 + MathHelper.floor(damage);
            this.activeItemStack.damageItem(i, this);

            if (this.activeItemStack.isEmpty())
            {
                EnumHand enumhand = this.getActiveHand();

                if (enumhand == EnumHand.MAIN_HAND)
                {
                    this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStack.EMPTY);
                }
                else
                {
                    this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, ItemStack.EMPTY);
                }

                this.activeItemStack = ItemStack.EMPTY;
                this.playSound(SoundEvents.ITEM_SHIELD_BREAK, 0.8F, 0.8F + this.world.rand.nextFloat() * 0.4F);
            }
        }
    }

    @Override
    public boolean canEntityBeSeen(Entity entity)
    {
        return this.world.rayTraceBlocks(new Vec3d(this.posX, this.posY, this.posZ), new Vec3d(entity.posX, entity.posY, entity.posZ), false, true, false) == null || super.canEntityBeSeen(entity);
    }

    @Override
    public BlockPos getPosition()
    {
        return new BlockPos(this.posX, this.posY, this.posZ);
    }

    @Override
    public void heal(float value)
    {
        value = net.minecraftforge.event.ForgeEventFactory.onLivingHeal(this, value);
        if (value <= 0) return;
        float health = this.getHealth();
        this.setHealth(health + value);
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
    public void setAIMoveSpeed(float speed)
    {
        super.setAIMoveSpeed(speed);
        this.moveForward = speed;
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
    public void onDeath(DamageSource damagesource){}

    @Override
    public void onDeathUpdate(){}
}