package com.nali.small.entity;

import com.nali.small.SmallConfig;
import com.nali.small.entity.memo.IBothLe;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class EntityLe extends EntityLivingBase implements IMixE
{
	public float rotation_yaw_head;
	public float prev_rotation_yaw_head;

	public EntityLe(World world)
	{
		super(world);
		this.Einit(this, world);
	}

//	@Override
//	public boolean isGlowing()
//	{
//		this.getB().setGlowing(this.world.isRemote && this.getFlag(6));
//		return this.glowing;
//	}

	@Override
	public double getYOffset()
	{
		return 0.3D;
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
	public boolean canEntityBeSeen(Entity entity)
	{
		return this.world.rayTraceBlocks(new Vec3d(this.posX, this.posY, this.posZ), new Vec3d(entity.posX, entity.posY, entity.posZ), false, true, false) == null || super.canEntityBeSeen(entity);
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
	public void onDeath(DamageSource damagesource){}

	@Override
	public void onDeathUpdate(){}

	@Nullable
	@Override
	public SoundEvent getHurtSound(DamageSource damagesource)
	{
		this.getB().getHurtSound(damagesource);
		return super.getHurtSound(damagesource);
	}

	@Nullable
	@Override
	public SoundEvent getDeathSound()
	{
		this.getB().getDeathSound();
		return super.getDeathSound();
	}

	@Override
	public void entityInit()
	{
		super.entityInit();
		this.EentityInit();
	}

	//	@Override
//	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
//	{
//		super.writeEntityToNBT(nbttagcompound);
//		this.EwriteEntityToNBT(nbttagcompound);
//		this.getB().writeEntityToNBT(nbttagcompound);
//	}
//
	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		this.getB().onReadNBT();
//		super.readEntityFromNBT(nbttagcompound);
//		this.EreadEntityFromNBT(nbttagcompound);
//		this.getB().readEntityFromNBT(nbttagcompound);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readFromNBT(nbttagcompound);
		this.getB().readFile();
	}

//	@Override
//	public void setAIMoveSpeed(float speed)
//	{
//		super.setAIMoveSpeed(speed);
//		this.moveForward = speed;
//	}

	@Override
	public boolean attackEntityFrom(DamageSource damagesource, float amount)
	{
		if (!this.getB().attackEntityFrom(damagesource, amount))
		{
			return false;
		}
		return super.attackEntityFrom(damagesource, amount);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity)
	{
		return this.getB().attackEntityAsMob(entity);
	}

	@Override
	public float updateDistance(float f0, float f1)
	{
//		this.getB().getWorkEBodyYaw().run(this);
//		return f1;
		return 0;
	}

//	@Override
//	public boolean processInitialInteract(EntityPlayer entityplayer, EnumHand enumhand)
//	{
//		return this.getB().processInitialInteract(entityplayer, enumhand);
//	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		this.getB().onUpdate();
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
	public void playEquipSound(ItemStack itemstack)
	{
		super.playEquipSound(itemstack);
	}

	@Override
	public void collideWithEntity(Entity entityIn)
	{
	}

	@Override
	public void collideWithNearbyEntities()
	{
	}

	@Override
	public boolean canBeCollidedWith()
	{
//		return true;
		return !SmallConfig.NEED_EXTRA;
	}

//	@Override
//	public void onCollideWithPlayer(EntityPlayer entityIn)
//	{
//	}

	//	@Override
//	public IMixE getI()
//	{
//		return this;
//	}

//	@Override
//	public void setRotationYawHead(float rotation)
//	{
//	}

//	@Override
//	public void onLivingUpdate()
//	{
//	}

	@Override
	public void travel(float strafe, float vertical, float forward)
	{
	}

	public abstract IBothLe getB();
}
