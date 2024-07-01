package com.nali.small.tile;

import com.nali.small.mix.memo.IBothB;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public abstract class RenderT<T extends TileEntity> extends TileEntitySpecialRenderer<T>
{
    @Override
    public void render(T te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        ((IBothB)te.getBlockType()).render(te, x, y, z, partialTicks, destroyStage, alpha);
    }
}
