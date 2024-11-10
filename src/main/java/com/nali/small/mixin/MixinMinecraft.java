//package com.nali.small.mixin;
//
//import com.nali.small.mix.memo.client.ClientB;
//import net.minecraft.client.Minecraft;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//@Mixin(Minecraft.class)
//public abstract class MixinMinecraft
//{
//	@Inject(method = "runGameLoop", at = @At("TAIL"))
//	private void nali_small_runGameLoop(CallbackInfo ci)
//	{
//		ClientB.N_LIST.clear();
//	}
////	@Inject(method = "runTickKeyboard", at = @At("HEAD"))
////	private void runTickKeyboard(CallbackInfo ci)
////	{
////		FUNCTION.apply(null);
////	}
////
////	@Inject(method = "dispatchKeypresses", at = @At("HEAD"))
////	private void dispatchKeypresses(CallbackInfo ci)
////	{
////		FUNCTION.apply(null);
////	}
//}
