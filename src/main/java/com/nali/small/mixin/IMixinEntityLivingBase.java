package com.nali.small.mixin;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(EntityLivingBase.class)
public interface IMixinEntityLivingBase
{
    @Accessor("attackingPlayer")
    @Mutable
    EntityPlayer attackingPlayer();

    @Accessor("recentlyHit")
    @Mutable
    void recentlyHit(int i);

    @Accessor("idleTime")
    @Mutable
    void idleTime(int i);
    @Accessor("lastDamage")
    @Mutable
    float lastDamage();
    @Accessor("lastDamage")
    @Mutable
    void lastDamage(float f);
    @Accessor("lastDamageSource")
    @Mutable
    void lastDamageSource(DamageSource damagesource);
    @Accessor("lastDamageStamp")
    @Mutable
    void lastDamageStamp(long l);
    @Invoker("canBlockDamageSource")
    @Mutable
    boolean GOcanBlockDamageSource(DamageSource damagesource);
    @Invoker("damageShield")
    @Mutable
    void GOdamageShield(float damage);
    @Invoker("blockUsingShield")
    @Mutable
    void GOblockUsingShield(EntityLivingBase entitylivingbase);
    @Invoker("damageEntity")
    @Mutable
    void GOdamageEntity(DamageSource damagesource, float damageAmount);
    @Invoker("checkTotemDeathProtection")
    @Mutable
    boolean GOcheckTotemDeathProtection(DamageSource damagesource);
    @Invoker("getDeathSound")
    @Mutable
    SoundEvent GOgetDeathSound();
    @Invoker("getSoundVolume")
    @Mutable
    float GOgetSoundVolume();
    @Invoker("getSoundPitch")
    @Mutable
    float GOgetSoundPitch();
    @Invoker("playHurtSound")
    @Mutable
    void GOplayHurtSound(DamageSource damagesource);
}
