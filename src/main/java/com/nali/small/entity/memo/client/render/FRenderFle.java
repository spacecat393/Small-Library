package com.nali.small.entity.memo.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class FRenderFle<E extends EntityLivingBase> extends RenderLivingBase<E>
{
	public FRenderFle()
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
	public ResourceLocation getEntityTexture(E e)
	{
		return null;
	}

	@Override
	public float handleRotationFloat(E e, float partialTicks)
	{
		return super.handleRotationFloat(e, partialTicks);
	}
}
