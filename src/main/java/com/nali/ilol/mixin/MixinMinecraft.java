package com.nali.ilol.mixin;

import com.nali.ilol.key.KeyTick;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft
{
    @Inject(method = "runTickKeyboard", at = @At("HEAD"))
    private void runTickKeyboard(CallbackInfo ci)
    {
        KeyTick.run(Keyboard.getEventKey());
    }

    @Inject(method = "dispatchKeypresses", at = @At("HEAD"))
    private void dispatchKeypresses(CallbackInfo ci)
    {
        KeyTick.run(Keyboard.getEventKey());
    }
}
