package com.nali.small.entity.memo.client.render;

import com.nali.small.entity.IMixE;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class FRenderSeInv<E extends Entity & IMixE> extends FRenderE<E>
{
    public FRenderSeInv(RenderManager rendermanager)
    {
        super(rendermanager);
    }

    @Override
    public boolean shouldRender(E e, ICamera camera, double camX, double camY, double camZ)
    {
        boolean result = super.shouldRender(e, camera, camX, camY, camZ);
        e.getB().setShouldRender(result);
        return result;
    }

    @Override
    public void doRender(E e, double ox, double oy, double oz, float entityYaw, float partialTicks)
    {
        e.getB().doRender(this, ox, oy, oz, partialTicks);
        super.doRender(e, ox, oy, oz, entityYaw, partialTicks);
    }

//    @Override
//    public void renderMultipass(T t, double ox, double oy, double oz, float entityYaw, float partialTicks)
//    {
//        super.renderMultipass(t, ox, oy, oz, entityYaw, partialTicks);
//    }
//
//    @Override
//    public boolean isMultipass()
//    {
//        return true;
//    }
}
