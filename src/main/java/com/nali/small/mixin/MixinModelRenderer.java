//package com.nali.small.mixin;
//
//import com.nali.small.entities.skinning.render.layer.ItemLayerRender;
//import net.minecraft.client.model.ModelRenderer;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//@Mixin(ModelRenderer.class)
//public abstract class MixinModelRenderer
//{
//    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;translate(FFF)V", shift = At.Shift.BEFORE, ordinal = 0))
//    private void renderB(float scale, CallbackInfo ci)
//    {
//        if (ItemLayerRender.ARMOR)
//        {
////            GL11.glTranslatef(0.0F, 0.4F, 0.0F);
////            GL11.glScalef(-0.08F, -0.08F, -0.08F);
////            GL11.glRotatef(ItemLayerRender.Y, 0.0F, 1.0F, 0.0F);
////            GL11.glRotatef(ItemLayerRender.P, 1.0F, 0.0F, 0.0F);
//        }
//    }
//
//    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;translate(FFF)V", shift = At.Shift.BEFORE, ordinal = 4))
//    private void renderE(float scale, CallbackInfo ci)
//    {
//        if (ItemLayerRender.ARMOR)
//        {
////            GL11.glScalef(0.08F, 0.08F, 0.08F);
////            GL11.glTranslatef(0.0F, -0.4F, 0.0F);
////            GL11.glRotatef(-ItemLayerRender.P, 1.0F, 0.0F, 0.0F);
////            GL11.glRotatef(-ItemLayerRender.Y, 0.0F, 1.0F, 0.0F);
//        }
//    }
//}
