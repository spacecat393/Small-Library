package com.nali.ilol.mixin;

import com.nali.ilol.items.MixItems;
import com.nali.render.ObjectRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.entity.EntityLivingBase;
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
    private void renderItemInFirstPerson(AbstractClientPlayer player, float p_187457_2_, float p_187457_3_, EnumHand hand, float p_187457_5_, ItemStack stack, float p_187457_7_, CallbackInfo ci)
    {
        if (stack.getItem() instanceof MixItems)
        {
            int lig_coord = Minecraft.getMinecraft().world.getCombinedLight(new BlockPos(player.posX, player.posY + (double)player.getEyeHeight(), player.posZ), 0);
            ObjectRender objectrender = ((MixItems)stack.getItem()).objectrender;
            objectrender.lig_b = lig_coord & 0xFFFF;
            objectrender.lig_s = (lig_coord >> 16) & 0xFFFF;
        }
    }

    @Inject(method = "renderItemSide", at = @At(value = "HEAD"))
    private void renderItemSide(EntityLivingBase entitylivingbaseIn, ItemStack heldStack, ItemCameraTransforms.TransformType transform, boolean leftHanded, CallbackInfo ci)
    {
        if (heldStack.getItem() instanceof MixItems)
        {
            int lig_coord = Minecraft.getMinecraft().world.getCombinedLight(new BlockPos(entitylivingbaseIn.posX, entitylivingbaseIn.posY + (double)entitylivingbaseIn.getEyeHeight(), entitylivingbaseIn.posZ), 0);
            ObjectRender objectrender = ((MixItems)heldStack.getItem()).objectrender;
            objectrender.lig_b = lig_coord & 0xFFFF;
            objectrender.lig_s = (lig_coord >> 16) & 0xFFFF;
        }
    }
}
