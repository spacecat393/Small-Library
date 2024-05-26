package com.nali.list.tilerender;

import com.nali.list.tiles.SmallTPBase;
import com.nali.small.render.IMixRender;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import org.lwjgl.opengl.GL11;

public class SmallTPBaseRender<T extends SmallTPBase> extends TileEntitySpecialRenderer<T>
{
    @Override
    public void render(T t, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
        ((IMixRender)t).getObjectRender().draw();
        GL11.glPopMatrix();
    }
}
