package com.nali.small.mixin;

import com.nali.render.ObjectRender;
import com.nali.small.items.IMixItems;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderEntityItem.class)
public class MixinRenderEntityItem
{
    @Inject(method = "doRender(Lnet/minecraft/entity/item/EntityItem;DDDFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderItem;renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V", shift = At.Shift.BEFORE))
    @Mutable
    private void nali_small_doRender(EntityItem entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo ci)
    {
        ItemStack itemstack = entity.getItem();
        if (itemstack.getItem() instanceof IMixItems)
        {
            BlockPos blockpos = entity.getPosition();
            ObjectRender objectrender = ((IMixItems)itemstack.getItem()).getObjectRender();
            objectrender.lig_b = entity.world.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, blockpos) / 16.0F;
            objectrender.lig_s = entity.world.getLightFromNeighborsFor(EnumSkyBlock.SKY, blockpos) / 16.0F;

            if (objectrender.lig_b < 0.1875F)
            {
                objectrender.lig_b = 0.1875F;
            }
        }
    }

//    @Inject(method = "doRender(Lnet/minecraft/entity/item/EntityItem;DDDFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderItem;renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V", shift = At.Shift.AFTER))
//    private void afterRenderItem(EntityItem entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo ci)
//    {
//        ItemStack itemstack = entity.getItem();
//        if (itemstack.getItem() instanceof MixItems)
//        {
//        }
//    }
}
