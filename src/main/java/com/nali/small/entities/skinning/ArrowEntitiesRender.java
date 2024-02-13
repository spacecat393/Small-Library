package com.nali.small.entities.skinning;

import com.nali.render.SkinningRender;
import com.nali.system.opengl.memory.OpenGLSkinningMemory;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class ArrowEntitiesRender
{
    public static void layer(SkinningEntitiesRender skinningentitiesrender, SkinningEntities skinningentities, float partialTicks)
    {
        int i = skinningentities.getArrowCountInEntity();

        if (i > 0)
        {
            Entity entity = new EntityTippedArrow(skinningentities.world, skinningentities.posX, skinningentities.posY, skinningentities.posZ);
            Random random = entity.world.rand;

            for (int j = 0; j < i; ++j)
            {
                SkinningRender skinningrender = (SkinningRender)skinningentities.client_object;
                int model_i = random.nextInt(skinningrender.memory_object_array.length);

                OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)skinningrender.memory_object_array[model_i];
                skinningentitiesrender.doModel(skinningentities, model_i, openglskinningmemory.index_int_array[random.nextInt(openglskinningmemory.index_int_array.length)], () ->
                {
                    RenderHelper.disableStandardItemLighting();
                    float f = random.nextFloat();
                    float f1 = random.nextFloat();
                    float f2 = random.nextFloat();
                    f = f * 2.0F - 1.0F;
                    f1 = f1 * 2.0F - 1.0F;
                    f2 = f2 * 2.0F - 1.0F;
                    f = f * -1.0F;
                    f1 = f1 * -1.0F;
                    f2 = f2 * -1.0F;
                    float f6 = MathHelper.sqrt(f * f + f2 * f2);
                    entity.rotationYaw = (float)(Math.atan2(f, f2) * (180D / Math.PI));
                    entity.rotationPitch = (float)(Math.atan2(f1, f6) * (180D / Math.PI));
                    entity.prevRotationYaw = entity.rotationYaw;
                    entity.prevRotationPitch = entity.rotationPitch;
                    skinningentitiesrender.getRenderManager().renderEntity(entity, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks, false);
                    RenderHelper.enableStandardItemLighting();
                });
            }
        }
    }
}
