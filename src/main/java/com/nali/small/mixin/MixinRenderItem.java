package com.nali.small.mixin;

import com.nali.render.ObjectRender;
import com.nali.small.items.MixItems;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderItem.class)
public abstract class MixinRenderItem
{
    @Inject(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V", at = @At("HEAD"), cancellable = true)
    private void renderItem(ItemStack stack, IBakedModel model, CallbackInfo ci)
    {
        if (stack.getItem() instanceof MixItems)
        {
            MixItems mixitems = ((MixItems)stack.getItem());
            mixitems.render();
            ObjectRender objectrender = mixitems.getObjectRender();
            objectrender.lig_b = 208.0F;
            objectrender.lig_s = 240.0F;
            ci.cancel();
        }
    }

//    @Inject(method = "renderItemIntoGUI", at = @At("HEAD"))
//    private void renderItemIntoGUI(ItemStack stack, int x, int y, CallbackInfo ci)
//    {
//        if (stack.getItem() instanceof MixItems)
//        {
//            ((MixItems)stack.getItem()).disableLightMap();
//        }
//    }
//
//    @Inject(method = "renderItemAndEffectIntoGUI(Lnet/minecraft/item/ItemStack;II)V", at = @At("HEAD"))
//    private void renderItemAndEffectIntoGUI(ItemStack stack, int xPosition, int yPosition, CallbackInfo ci)
//    {
//        if (stack.getItem() instanceof MixItems)
//        {
//            ((MixItems)stack.getItem()).disableLightMap();
//        }
//    }
//
//    @Inject(method = "renderItemOverlays", at = @At("HEAD"))
//    private void renderItemOverlayIntoGUI(FontRenderer fr, ItemStack stack, int xPosition, int yPosition, CallbackInfo ci)
//    {
//        if (stack.getItem() instanceof MixItems)
//        {
//            ((MixItems)stack.getItem()).disableLightMap();
//        }
//    }
}
