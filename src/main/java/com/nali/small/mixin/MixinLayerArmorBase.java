//package com.nali.small.mixin;
//
//import com.nali.small.entities.skinning.SkinningEntities;
//import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.inventory.EntityEquipmentSlot;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//@Mixin(LayerArmorBase.class)
//public class MixinLayerArmorBase
//{
//	@Inject(method = "renderArmorLayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemArmor;hasOverlay(Lnet/minecraft/item/ItemStack;)Z", shift = At.Shift.BEFORE), cancellable = true)
//	private void renderArmorLayer(EntityLivingBase entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale, EntityEquipmentSlot slotIn, CallbackInfo ci)
//	{
//		if (entityLivingBaseIn instanceof SkinningEntities)
//		{
////			GL11.glScalef(0.08F, -0.08F, -0.08F);
////			GL11.glRotatef(entityLivingBaseIn.getEntityData().getFloat("y_nali"), 0.0F, 1.0F, 0.0F);
////			GL11.glRotatef(entityLivingBaseIn.getEntityData().getFloat("p_nali"), 1.0F, 0.0F, 0.0F);
////			GL11.glTranslatef(0.0F, 0.4F, 0.0F);
////			GL11.glScalef(-0.0625F, -0.0625F, 0.0625F);
////			Minecraft.getMinecraft().getItemRenderer().renderItemSide(entityLivingBaseIn, SmallBox.I.getDefaultInstance(), ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, false);
////			ci.cancel();
//		}
//	}
//
////	@Inject(method = "renderArmorLayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V", shift = At.Shift.BEFORE, ordinal = 1))
////	private void renderArmorLayer2(EntityLivingBase entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale, EntityEquipmentSlot slotIn, CallbackInfo ci)
////	{
////	}
//}
