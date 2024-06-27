package com.nali.small.entity.memo.client.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class RenderE<E extends Entity> extends Render<E>
{
    public RenderE(RenderManager renderManager)
    {
        super(renderManager);
    }

    public void mShadowOpaque(float f)
    {
        this.shadowOpaque = f;
    }

    public void mShadowSize(float f)
    {
        this.shadowSize = f;
    }
}
