package com.nali.small.tile;

import com.nali.render.ObjectRender;
import com.nali.small.block.memo.client.ClientB;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public interface IMixTileObjectRender<T extends TileEntity>
{
    default void render(T t, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        GL11.glPushMatrix();
        this.translate(x, y, z);
        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
        this.scale();
        ObjectRender objectrender = ((ClientB)t.getBlockType()).getObjectRender();
        objectrender.drawLater();
        GL11.glPopMatrix();
    }

    default void translate(double x, double y, double z)
    {
        GL11.glTranslated(x + 0.5D, y + 0.001D, z + 0.5D);
    }

    default void scale()
    {
        GL11.glScalef(0.499F, 0.499F, 0.499F);
    }
}
