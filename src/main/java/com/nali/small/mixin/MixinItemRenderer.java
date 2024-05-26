package com.nali.small.mixin;

import com.nali.render.ObjectRender;
import com.nali.small.items.IMixItems;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public abstract class MixinItemRenderer
{
    @Inject(method = "renderItemInFirstPerson(Lnet/minecraft/client/entity/AbstractClientPlayer;FFLnet/minecraft/util/EnumHand;FLnet/minecraft/item/ItemStack;F)V", at = @At(value = "HEAD"))
    @Mutable
    private void nali_small_renderItemInFirstPerson(AbstractClientPlayer player, float p_187457_2_, float p_187457_3_, EnumHand hand, float p_187457_5_, ItemStack stack, float p_187457_7_, CallbackInfo ci)
    {
        if (stack.getItem() instanceof IMixItems)
        {
            BlockPos blockpos = new BlockPos(player.posX, player.posY + (double)player.getEyeHeight(), player.posZ);
            ObjectRender objectrender = ((IMixItems)stack.getItem()).getObjectRender();
            objectrender.lig_b = player.world.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, blockpos) / 16.0F;
            objectrender.lig_s = player.world.getLightFromNeighborsFor(EnumSkyBlock.SKY, blockpos) / 16.0F;

            if (objectrender.lig_b < 0.1875F)
            {
                objectrender.lig_b = 0.1875F;
            }
        }
    }

    @Inject(method = "renderItemSide", at = @At(value = "HEAD"))
    @Mutable
    private void nali_small_renderItemSide(EntityLivingBase entitylivingbaseIn, ItemStack heldStack, ItemCameraTransforms.TransformType transform, boolean leftHanded, CallbackInfo ci)
    {
        if (heldStack.getItem() instanceof IMixItems)
        {
            BlockPos blockpos = new BlockPos(entitylivingbaseIn.posX, entitylivingbaseIn.posY + (double)entitylivingbaseIn.getEyeHeight(), entitylivingbaseIn.posZ);
            ObjectRender objectrender = ((IMixItems)heldStack.getItem()).getObjectRender();
            objectrender.lig_b = entitylivingbaseIn.world.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, blockpos) / 16.0F;
            objectrender.lig_s = entitylivingbaseIn.world.getLightFromNeighborsFor(EnumSkyBlock.SKY, blockpos) / 16.0F;

            if (objectrender.lig_b < 0.1875F)
            {
                objectrender.lig_b = 0.1875F;
            }
        }
    }
}
