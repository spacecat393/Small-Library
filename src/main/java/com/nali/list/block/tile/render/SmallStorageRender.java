package com.nali.list.block.tile.render;

import com.nali.list.block.tile.SmallStorage;
import com.nali.small.tile.IMixTileObjectRender;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SmallStorageRender<T extends SmallStorage> extends TileEntitySpecialRenderer<T> implements IMixTileObjectRender<T>
{
    @Override
    public void render(T te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        IMixTileObjectRender.super.render(te, x, y, z, partialTicks, destroyStage, alpha);
    }
}
