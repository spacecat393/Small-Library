package com.nali.small.mixin;

import com.nali.list.capability.serializable.SmallSakuraSerializable;
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
    private void nali_small_onDeath(DamageSource cause, CallbackInfo ci)
    {
        Entity entity = cause.getTrueSource();

        if (entity instanceof EntityPlayerMP)
        {
            EntityPlayerMP entityplayermp = (EntityPlayerMP)entity;
            entityplayermp.getCapability(SmallSakuraSerializable.SMALLSAKURATYPES_CAPABILITY, null).add((byte)1);
        }
    }

    @Inject(method = "attackEntityFrom", at = @At(value = "HEAD"), cancellable = true)
    private void nali_small_attackEntityFrom(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        byte protect = this.getEntityData().getByte("Nali_protect");
        if (protect > 0)
        {
            this.getEntityData().setByte("Nali_protect", (byte)(protect - 1));
            cir.cancel();
        }
    }
}
