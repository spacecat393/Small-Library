package com.nali.small.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class EntityLe extends EntityLivingBase implements IMixLe
{
    public EntityLe(World worldIn)
    {
        super(worldIn);
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
    public float getSoundVolume()
    {
        return super.getSoundVolume();
    }

    @Override
    public float getSoundPitch()
    {
        return super.getSoundPitch();
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
    public EnumHandSide getPrimaryHand()
    {
        return EnumHandSide.RIGHT;
    }

    @Override
    public double getYOffset()
    {
        return 0.3D;
    }

    @Override
    public boolean isEntityAlive()
    {
        return !this.isDead;
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
    public void heal(float value)
    {
        value = net.minecraftforge.event.ForgeEventFactory.onLivingHeal(this, value);
        if (value <= 0) return;
        float health = this.getHealth();
        this.setHealth(health + value);
    }

    @Override
    public void onDeath(DamageSource damagesource){}

    @Override
    public void onDeathUpdate(){}
}
