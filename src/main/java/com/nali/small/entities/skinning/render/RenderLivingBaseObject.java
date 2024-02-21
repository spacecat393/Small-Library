package com.nali.small.entities.skinning.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderLivingBaseObject<T extends EntityLivingBase> extends RenderLivingBase<T>
{
    public RenderLivingBaseObject()
    {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelBase()
        {
            @Override
            public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
            {
                super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            }
        }, 0.0F);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(T entity)
    {
        return null;
    }

    @Override
    public float handleRotationFloat(T livingBase, float partialTicks)
    {
        return super.handleRotationFloat(livingBase, partialTicks);
    }
}
