package com.nali.list.block.tile.render;

import com.nali.list.block.tile.SmallTPBase;
import com.nali.render.SkinningRender;
import com.nali.small.tile.IMixTileSkinningRender;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SmallTPBaseRender<T extends SmallTPBase> extends TileEntitySpecialRenderer<T> implements IMixTileSkinningRender<T>
{
    @Override
    public void render(T te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        IMixTileSkinningRender.super.render(te, x, y, z, partialTicks, destroyStage, alpha);
    }

    @Override
    public void updateFrame(SkinningRender skinningrender)
    {
        if (skinningrender.frame_int_array[0] < 78)
        {
            ++skinningrender.frame_int_array[0];
        }
        else
        {
            skinningrender.frame_int_array[0] = 0;
        }
    }
}
