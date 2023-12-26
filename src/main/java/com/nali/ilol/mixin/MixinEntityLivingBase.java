package com.nali.ilol.mixin;

import com.nali.list.capabilitiesserializations.IlolSakuraSerializations;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase
{
    @Shadow
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return null;
    }

    @Inject(method = "onDeath", at = @At(value = "HEAD"))
    private void onDeath(DamageSource cause, CallbackInfo ci)
    {
        Entity entity = cause.getTrueSource();

        if (entity instanceof EntityPlayerMP && (this.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD || this.getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD))
        {
            EntityPlayerMP entityplayermp = (EntityPlayerMP)entity;
            entityplayermp.getCapability(IlolSakuraSerializations.ILOLSAKURATYPES_CAPABILITY, null).add(1);
        }
    }
}
