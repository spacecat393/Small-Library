package com.nali.small.mixin;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(EntityLivingBase.class)
public interface IMixinEntityLivingBase
{
    @Accessor("attackingPlayer")
    EntityPlayer attackingPlayer();

    @Accessor("idleTime")
    void idleTime(int i);
    @Accessor("lastDamage")
    float lastDamage();
    @Accessor("lastDamage")
    void lastDamage(float f);
    @Accessor("lastDamageSource")
    void lastDamageSource(DamageSource damagesource);
    @Accessor("lastDamageStamp")
    void lastDamageStamp(long l);
    @Invoker("canBlockDamageSource")
    boolean GOcanBlockDamageSource(DamageSource damagesource);
    @Invoker("damageShield")
    void GOdamageShield(float damage);
    @Invoker("blockUsingShield")
    void GOblockUsingShield(EntityLivingBase entitylivingbase);
    @Invoker("damageEntity")
    void GOdamageEntity(DamageSource damagesource, float damageAmount);
    @Invoker("checkTotemDeathProtection")
    boolean GOcheckTotemDeathProtection(DamageSource damagesource);
    @Invoker("getDeathSound")
    SoundEvent GOgetDeathSound();
    @Invoker("getSoundVolume")
    float GOgetSoundVolume();
    @Invoker("getSoundPitch")
    float GOgetSoundPitch();
    @Invoker("playHurtSound")
    void GOplayHurtSound(DamageSource damagesource);
}
