package com.nali.small.entity.memo.client.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class FRenderE<E extends Entity> extends Render<E>
{
	public FRenderE(RenderManager renderManager)
	{
		super(renderManager);
	}

	@Override
	public ResourceLocation getEntityTexture(E e)
	{
		return null;
	}

//	public float getShadowOpaque()
//	{
//		return this.shadowOpaque;
//	}
//
//	public float getShadowSize()
//	{
//		return this.shadowSize;
//	}

	public void setShadowOpaque(float f)
	{
		this.shadowOpaque = f;
	}

	public void setShadowSize(float f)
	{
		this.shadowSize = f;
	}
}
