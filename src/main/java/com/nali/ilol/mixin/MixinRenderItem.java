package com.nali.ilol.mixin;

import com.nali.data.ObjectData;
import com.nali.ilol.items.MixItems;
import com.nali.system.DataLoader;
import com.nali.system.opengl.drawing.OpenGLObjectDrawing;
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
            ((MixItems)stack.getItem()).render();
            ci.cancel();
        }
    }

    private void render(ObjectData objectdata)
    {
        objectdata.multiplyAnimation();

        for (DataLoader.SCREEN_INDEX = 0; DataLoader.SCREEN_INDEX < objectdata.model_address_object_array.length; ++DataLoader.SCREEN_INDEX)
        {
            if (objectdata.model_boolean_array[DataLoader.SCREEN_INDEX])
            {
                OpenGLObjectDrawing.startScreenObjectGL(objectdata);
            }
        }
    }
}
