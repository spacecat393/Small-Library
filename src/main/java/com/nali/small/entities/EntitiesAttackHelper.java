package com.nali.small.entities;

import com.nali.small.mixin.IMixinEntity;
import com.nali.small.mixin.IMixinEntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;

public class EntitiesAttackHelper
{
    public static boolean doAttackEntityFrom(Entity target, Entity by_entity, DamageSource source, float amount)
    {
        EntityLivingBase entitylivingbase = null;
        if (target instanceof EntityLivingBase)
        {
            entitylivingbase = (EntityLivingBase)target;
        }

//        if (entitylivingbase != null)
//        {
//            if (!net.minecraftforge.common.ForgeHooks.onLivingAttack(entitylivingbase, source, amount)) return false;
//        }

        if (target.isEntityInvulnerable(source))
        {
            return false;
        }
        else if (target.world.isRemote)
        {
            return false;
        }
        else if (entitylivingbase != null)
        {
            ((IMixinEntityLivingBase)entitylivingbase).idleTime(0);

            if (entitylivingbase.getHealth() <= 0.0F)
            {
                return false;
            }
            else if (source.isFireDamage() && entitylivingbase.isPotionActive(MobEffects.FIRE_RESISTANCE))
            {
                return false;
            }
            else
            {
                if ((source == DamageSource.ANVIL || source == DamageSource.FALLING_BLOCK) && !entitylivingbase.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty())
                {
                    entitylivingbase.getItemStackFromSlot(EntityEquipmentSlot.HEAD).attemptDamageItem((int)(amount * 4.0F + entitylivingbase.getRNG().nextFloat() * amount * 2.0F), entitylivingbase.getRNG(), null);
                    amount *= 0.75F;
                }

                boolean flag = false;

                if (amount > 0.0F && ((IMixinEntityLivingBase)entitylivingbase).GOcanBlockDamageSource(source))
                {
                    ((IMixinEntityLivingBase)entitylivingbase).GOdamageShield(amount);
                    amount = 0.0F;

                    if (!source.isProjectile())
                    {
                        Entity entity = source.getImmediateSource();

                        if (entity instanceof EntityLivingBase)
                        {
                            ((IMixinEntityLivingBase)entitylivingbase).GOblockUsingShield((EntityLivingBase)entity);
                        }
                    }

                    flag = true;
                }

                entitylivingbase.limbSwingAmount = 1.5F;
                boolean flag1 = true;

                if ((float)target.hurtResistantTime > (float)entitylivingbase.maxHurtResistantTime / 2.0F)
                {
                    if (amount <= ((IMixinEntityLivingBase)entitylivingbase).lastDamage())
                    {
                        return false;
                    }

                    ((IMixinEntityLivingBase)entitylivingbase).GOdamageEntity(source, amount - ((IMixinEntityLivingBase)entitylivingbase).lastDamage());
                    ((IMixinEntityLivingBase)entitylivingbase).lastDamage(amount);
                    flag1 = false;
                }
                else
                {
                    ((IMixinEntityLivingBase)entitylivingbase).lastDamage(amount);
                    target.hurtResistantTime = entitylivingbase.maxHurtResistantTime;
                    ((IMixinEntityLivingBase)entitylivingbase).GOdamageEntity(source, amount);
                    entitylivingbase.maxHurtTime = 10;
                    entitylivingbase.hurtTime = entitylivingbase.maxHurtTime;
                }

                entitylivingbase.attackedAtYaw = 0.0F;
//                Entity by_entity = source.getTrueSource();

                if (by_entity != null)
                {
                    if (by_entity instanceof EntityLivingBase)
                    {
                        entitylivingbase.setRevengeTarget((EntityLivingBase)by_entity);
                    }

//                    if (by_entity instanceof EntityPlayer)
//                    {
//                        target.recentlyHit = 100;
//                        target.attackingPlayer = (EntityPlayer)by_entity;
//                    }
//                    else if (by_entity instanceof net.minecraft.entity.passive.EntityTameable)
//                    {
//                        net.minecraft.entity.passive.EntityTameable entitywolf = (net.minecraft.entity.passive.EntityTameable)by_entity;
//
//                        if (entitywolf.isTamed())
//                        {
//                            target.recentlyHit = 100;
//                            target.attackingPlayer = null;
//                        }
//                    }
                }

                if (flag1)
                {
                    if (flag)
                    {
                        target.world.setEntityState(target, (byte)29);
                    }
                    else if (source instanceof EntityDamageSource && ((EntityDamageSource)source).getIsThornsDamage())
                    {
                        target.world.setEntityState(target, (byte)33);
                    }
                    else
                    {
                        byte b0;

                        if (source == DamageSource.DROWN)
                        {
                            b0 = 36;
                        }
                        else if (source.isFireDamage())
                        {
                            b0 = 37;
                        }
                        else
                        {
                            b0 = 2;
                        }

                        target.world.setEntityState(target, b0);
                    }

                    if (source != DamageSource.DROWN && !flag)
                    {
                        ((IMixinEntity)target).GOmarkVelocityChanged();
                    }

                    if (by_entity != null)
                    {
                        double d1 = by_entity.posX - target.posX;
                        double d0;

                        for (d0 = by_entity.posZ - target.posZ; d1 * d1 + d0 * d0 < 1.0E-4D; d0 = (Math.random() - Math.random()) * 0.01D)
                        {
                            d1 = (Math.random() - Math.random()) * 0.01D;
                        }

                        entitylivingbase.attackedAtYaw = (float)(MathHelper.atan2(d0, d1) * (180D / Math.PI) - (double)target.rotationYaw);
                        entitylivingbase.knockBack(by_entity, 0.4F, d1, d0);
                    }
                    else
                    {
                        entitylivingbase.attackedAtYaw = (float)((int)(Math.random() * 2.0D) * 180);
                    }
                }

                if (entitylivingbase.getHealth() <= 0.0F)
                {
                    if (!((IMixinEntityLivingBase)entitylivingbase).GOcheckTotemDeathProtection(source))
                    {
                        SoundEvent soundevent = ((IMixinEntityLivingBase)entitylivingbase).GOgetDeathSound();

                        if (flag1 && soundevent != null)
                        {
                            target.playSound(soundevent, ((IMixinEntityLivingBase)entitylivingbase).GOgetSoundVolume(), ((IMixinEntityLivingBase)entitylivingbase).GOgetSoundPitch());
                        }

                        entitylivingbase.onDeath(source);
                    }
                }
                else if (flag1)
                {
                    ((IMixinEntityLivingBase)entitylivingbase).GOplayHurtSound(source);
                }

                boolean flag2 = !flag;

                if (flag2)
                {
                    ((IMixinEntityLivingBase)entitylivingbase).lastDamageSource(source);
                    ((IMixinEntityLivingBase)entitylivingbase).lastDamageStamp(target.world.getTotalWorldTime());
                }

//                if (target instanceof EntityPlayerMP)
//                {
//                    CriteriaTriggers.ENTITY_HURT_PLAYER.trigger((EntityPlayerMP)target, source, f, amount, flag);
//                }
//
//                if (by_entity instanceof EntityPlayerMP)
//                {
//                    CriteriaTriggers.PLAYER_HURT_ENTITY.trigger((EntityPlayerMP)by_entity, target, source, f, amount, flag);
//                }

                return flag2;
            }
        }
        else
        {
            target.setDead();
        }

        return false;
    }
}
