package com.nali.small.mixin;

import com.nali.render.RenderO;
import com.nali.small.Small;
import com.nali.small.draw.Draw;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL20;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer
{
	@Shadow @Final private Minecraft mc;

	@Shadow public abstract void disableLightmap();

//	private static byte BIT;//mix tr

	private static float PARTIALTICKS;
	private static Frustum FRUSTUM;

	@Redirect(method = "renderWorldPass", at = @At(value = "NEW", target = "()Lnet/minecraft/client/renderer/culling/Frustum;"))
	private Frustum nali_small_renderWorldPassIC()
	{
		FRUSTUM = new Frustum();
		return FRUSTUM;
	}

	@Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderBlockLayer(Lnet/minecraft/util/BlockRenderLayer;DILnet/minecraft/entity/Entity;)I", shift = At.Shift.AFTER, ordinal = 2))
	private void nali_small_renderWorldPassA(int pass, float partialTicks, long finishTimeNano, CallbackInfo ci)
	{
		PARTIALTICKS = partialTicks;
//		RenderO.take();
//		GL20.glBlendEquationSeparate(GL14.GL_FUNC_ADD, GL14.GL_FUNC_ADD);
//		GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
//
//		if (!Draw.MODEL_MAP.isEmpty())
//		{
//			Draw.draw(Draw.MODEL_MAP);
//		}
//
//		if ((BIT & 1) == 0)
//		{
//			if (!Draw.TRANSLUCENT_MAP.isEmpty())
//			{
////			draw(TRANSLUCENT_MAP);
//				Draw.drawT();
//			}
//
////			Draw.run();
//		}
//
//		RenderO.free();
//		Draw.clear();
//
////		RenderO.take();
////
////		if (!Draw.MODEL_MAP.isEmpty())
////		{
////			Draw.draw(Draw.MODEL_MAP);
////		}
////
////		//model texture
//////		if (!E_GLOW_MAP.isEmpty())
//////		{
//////			draw(E_GLOW_MAP);
//////		}
////
////		Draw.clear();
////		RenderO.free();
	}

	@Redirect(method = "renderWorldPass", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/EntityRenderer;debugView:Z", ordinal = 0))
	private boolean nali_small_renderWorldPass0(EntityRenderer instance)
	{
//		ICamera icamera = new Frustum();
		if ((Small.FLAG & 1) == 0)
		{
			ForgeHooksClient.setRenderPass(0);
			Entity entity = this.mc.getRenderViewEntity();
			RenderGlobal renderglobal = this.mc.renderGlobal;
			GlStateManager.matrixMode(5888);
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			RenderHelper.enableStandardItemLighting();
			renderglobal.renderEntities(entity, FRUSTUM, PARTIALTICKS);
			RenderHelper.disableStandardItemLighting();
			this.disableLightmap();

			RenderO.take();
			GL20.glBlendEquationSeparate(GL14.GL_FUNC_ADD, GL14.GL_FUNC_ADD);
			GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);

			if (!Draw.MODEL_MAP.isEmpty())
			{
				Draw.draw(Draw.MODEL_MAP);
			}

			if ((Small.FLAG & 2) == 0)
			{
				if (!Draw.TRANSLUCENT_MAP.isEmpty())
				{
					Draw.draw(Draw.TRANSLUCENT_MAP);
		//			Draw.drawT();
				}

	//			Draw.run();
			}

			RenderO.free();
			Draw.clear();
	//			Display.update();
		}

		return true;
	}

	//use only 0
	@Redirect(method = "renderWorldPass", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/EntityRenderer;debugView:Z", ordinal = 2))
	private boolean nali_small_renderWorldPass1(EntityRenderer instance)
	{
		return true;
	}

	//for tr check far camera then check block around model to camera
	//later
	@Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderBlockLayer(Lnet/minecraft/util/BlockRenderLayer;DILnet/minecraft/entity/Entity;)I", shift = At.Shift.AFTER, ordinal = 3))
	private void nali_small_renderWorldPassB(int pass, float partialTicks, long finishTimeNano, CallbackInfo ci)
	{
		if ((Small.FLAG & 1) == 1)
		{
			ForgeHooksClient.setRenderPass(0);
			Entity entity = this.mc.getRenderViewEntity();
			RenderGlobal renderglobal = this.mc.renderGlobal;
//			GlStateManager.matrixMode(5888);
//			GlStateManager.popMatrix();
//			GlStateManager.pushMatrix();
			RenderHelper.enableStandardItemLighting();
			renderglobal.renderEntities(entity, FRUSTUM, PARTIALTICKS);
			GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			RenderHelper.disableStandardItemLighting();
//			this.disableLightmap();
//			ForgeHooksClient.setRenderPass(-1);

			RenderO.take();
			GL20.glBlendEquationSeparate(GL14.GL_FUNC_ADD, GL14.GL_FUNC_ADD);
			GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);

			if (!Draw.MODEL_MAP.isEmpty())
			{
				Draw.draw(Draw.MODEL_MAP);
			}

			if ((Small.FLAG & 2) == 0)
			{
				if (!Draw.TRANSLUCENT_MAP.isEmpty())
				{
					Draw.draw(Draw.TRANSLUCENT_MAP);
//					Draw.drawT();
				}
			}

			RenderO.free();
			Draw.clear();
//			Draw.run();
//			Display.update();
		}

//		Draw.clear();
//		Small.FLAG ^= 1/* | 2*/;
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
