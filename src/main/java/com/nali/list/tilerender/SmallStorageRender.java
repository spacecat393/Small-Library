package com.nali.list.tilerender;

import com.nali.list.tiles.SmallStorage;
import com.nali.render.ObjectRender;
import com.nali.small.render.IMixRender;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class SmallStorageRender<T extends SmallStorage> extends TileEntitySpecialRenderer<T>
{
    @Override
    public void render(T t, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y + 0.001D, z + 0.5D);
        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(0.499F, 0.499F, 0.499F);
        ObjectRender objectrender = ((IMixRender)t.getBlockType()).getObjectRender();
        objectrender.drawLater();
        GL11.glPopMatrix();
    }
}
