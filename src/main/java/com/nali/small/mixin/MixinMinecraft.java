//package com.nali.small.mixin;
//
//import net.minecraft.client.Minecraft;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Mutable;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import static com.nali.small.key.KeyTick.FUNCTION;
//
//@Mixin(Minecraft.class)
//public abstract class MixinMinecraft
//{
//    @Inject(method = "runTickKeyboard", at = @At("HEAD"))
//    private void runTickKeyboard(CallbackInfo ci)
//    {
//        FUNCTION.apply(null);
//    }
//
//    @Inject(method = "dispatchKeypresses", at = @At("HEAD"))
//    private void dispatchKeypresses(CallbackInfo ci)
//    {
//        FUNCTION.apply(null);
//    }
//}
