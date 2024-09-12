package com.nali.small.mixin;

import com.nali.small.mix.IMixN;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public abstract class MixinItemRenderer
{
	@Inject(method = "renderItemInFirstPerson(Lnet/minecraft/client/entity/AbstractClientPlayer;FFLnet/minecraft/util/EnumHand;FLnet/minecraft/item/ItemStack;F)V", at = @At(value = "HEAD"))
	private void nali_small_renderItemInFirstPerson(AbstractClientPlayer player, float p_187457_2_, float p_187457_3_, EnumHand hand, float p_187457_5_, ItemStack stack, float p_187457_7_, CallbackInfo ci)
	{
		Item item = stack.getItem();
		if (item instanceof IMixN)
		{
			((IMixN)item).updateLight(player.world, new BlockPos(player.posX, player.posY + (double)player.getEyeHeight(), player.posZ));
		}
	}

	@Inject(method = "renderItemSide", at = @At(value = "HEAD"))
	private void nali_small_renderItemSide(EntityLivingBase entitylivingbaseIn, ItemStack heldStack, ItemCameraTransforms.TransformType transform, boolean leftHanded, CallbackInfo ci)
	{
		Item item = heldStack.getItem();
		if (item instanceof IMixN)
		{
			((IMixN)item).updateLight(entitylivingbaseIn.world, new BlockPos(entitylivingbaseIn.posX, entitylivingbaseIn.posY + (double)entitylivingbaseIn.getEyeHeight(), entitylivingbaseIn.posZ));
		}
	}
}
