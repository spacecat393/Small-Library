package com.nali.small.mixin;

import com.nali.small.draw.Draw;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class MixinEntityRenderer
{
	//for tr check far camera then check block around model to camera
	//later
//	@Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderBlockLayer(Lnet/minecraft/util/BlockRenderLayer;DILnet/minecraft/entity/Entity;)I", shift = At.Shift.AFTER, ordinal = 3))
//	private void nali_small_renderWorldPassB(int pass, float partialTicks, long finishTimeNano, CallbackInfo ci)
//	{
//
//	}

	@Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderBlockLayer(Lnet/minecraft/util/BlockRenderLayer;DILnet/minecraft/entity/Entity;)I", shift = At.Shift.AFTER, ordinal = 2))
	private void nali_small_renderWorldPass(int pass, float partialTicks, long finishTimeNano, CallbackInfo ci)
	{
		Draw.run();
//		RenderO.take();
//
//		if (!Draw.MODEL_MAP.isEmpty())
//		{
//			Draw.draw(Draw.MODEL_MAP);
//		}
//
//		//model texture
////		if (!E_GLOW_MAP.isEmpty())
////		{
////			draw(E_GLOW_MAP);
////		}
//
//		Draw.clear();
//		RenderO.free();
	}

//	@Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderBlockLayer(Lnet/minecraft/util/BlockRenderLayer;DILnet/minecraft/entity/Entity;)I", shift = At.Shift.BEFORE, ordinal = 3))
//	private void nali_renderWorldPass1(int pass, float partialTicks, long finishTimeNano, CallbackInfo ci)
//	{
//		Draw.run();
/// /		RenderO.take();
/// /		if (!Draw.TRANSLUCENT_MAP.isEmpty())
/// /		{
/// ///			GL11.glDepthMask(false);
/// /			Draw.draw(Draw.TRANSLUCENT_MAP);
/// /		}
/// /		RenderO.free();
/// /		Draw.clear();
//	}

//	@Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderBlockLayer(Lnet/minecraft/util/BlockRenderLayer;DILnet/minecraft/entity/Entity;)I", shift = At.Shift.BEFORE, ordinal = 0))
//	private void nali_renderWorldPass1(int pass, float partialTicks, long finishTimeNano, CallbackInfo ci)
//	{
//	}
}
