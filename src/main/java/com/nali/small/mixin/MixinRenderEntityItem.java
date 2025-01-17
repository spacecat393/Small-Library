package com.nali.small.mixin;

import com.nali.small.mix.IMixN;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderEntityItem.class)
public class MixinRenderEntityItem
{
	@Inject(method = "doRender(Lnet/minecraft/entity/item/EntityItem;DDDFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderItem;renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V", shift = At.Shift.BEFORE))
	private void nali_small_doRender(EntityItem entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo ci)
	{
		Item item = entity.getItem().getItem();
		if (item instanceof IMixN)
		{
			((IMixN)item).updateLight(entity.world, entity.getPosition());
		}
	}

//	@Inject(method = "doRender(Lnet/minecraft/entity/item/EntityItem;DDDFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderItem;renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V", shift = At.Shift.AFTER))
//	private void afterRenderItem(EntityItem entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo ci)
//	{
//		ItemStack itemstack = entity.getItemStack();
//		if (itemstack.getItemStack() instanceof MixItems)
//		{
//		}
//	}
}
