package com.nali.small.mixin;

import com.nali.small.items.MixItems;
import com.nali.render.ObjectRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderEntityItem.class)
public class MixinRenderEntityItem
{
    @Inject(method = "doRender(Lnet/minecraft/entity/item/EntityItem;DDDFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderItem;renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V", shift = At.Shift.BEFORE))
    private void beforeRenderItem(EntityItem entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo ci)
    {
        ItemStack itemstack = entity.getItem();
        if (itemstack.getItem() instanceof MixItems)
        {
            int lig_coord = Minecraft.getMinecraft().world.getCombinedLight(entity.getPosition(), 0);
            ObjectRender objectrender = ((MixItems)itemstack.getItem()).getObjectRender();
            objectrender.lig_b = lig_coord & 0xFFFF;
            objectrender.lig_s = (lig_coord >> 16) & 0xFFFF;
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
