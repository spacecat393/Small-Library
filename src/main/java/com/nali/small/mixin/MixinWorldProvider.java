package com.nali.small.mixin;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WorldProvider.class)
public abstract class MixinWorldProvider
{
    @Inject(method = "getRespawnDimension", at = @At(value = "HEAD"), cancellable = true, remap = false)
    private void getRespawnDimension(EntityPlayerMP player, CallbackInfoReturnable<Integer> cir)
    {
        if (player.getEntityData().hasKey("revive_nali"))
        {
            cir.setReturnValue(player.dimension);
        }
    }
}
