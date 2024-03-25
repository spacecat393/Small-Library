package com.nali.small.mixin;

import com.nali.list.capabilitiesserializations.SmallSakuraSerializations;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase extends Entity
{
    public MixinEntityLivingBase(World worldIn)
    {
        super(worldIn);
    }

    @Inject(method = "onDeath", at = @At(value = "HEAD"))
    @Mutable
    private void onDeath(DamageSource cause, CallbackInfo ci)
    {
        Entity entity = cause.getTrueSource();

        if (entity instanceof EntityPlayerMP)
        {
            EntityPlayerMP entityplayermp = (EntityPlayerMP)entity;
            entityplayermp.getCapability(SmallSakuraSerializations.SMALLSAKURATYPES_CAPABILITY, null).add(1);
        }
    }

    @Inject(method = "attackEntityFrom", at = @At(value = "HEAD"), cancellable = true)
    @Mutable
    private void attackEntityFrom(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        byte protect = this.getEntityData().getByte("protect_nali");
        if (protect > 0)
        {
            this.getEntityData().setByte("protect_nali", (byte)(protect - 1));
            cir.cancel();
        }
    }
}
