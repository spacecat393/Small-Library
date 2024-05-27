package com.nali.list.tilerender;

import com.nali.data.client.SkinningClientData;
import com.nali.list.tiles.SmallTPBase;
import com.nali.small.render.IMixRender;
import com.nali.small.render.SkinningBlocksRender;
import com.nali.system.opengl.memory.OpenGLAnimationMemory;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import org.lwjgl.opengl.GL11;

import static com.nali.system.ClientLoader.OBJECT_LIST;

public class SmallTPBaseRender<T extends SmallTPBase> extends TileEntitySpecialRenderer<T>
{
    @Override
    public void render(T t, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y, z + 0.5D);//0.5
        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        SkinningBlocksRender skinningtilesrender = (SkinningBlocksRender)((IMixRender)t.getBlockType()).getObjectRender();
        OpenGLAnimationMemory openglanimationmemory = (OpenGLAnimationMemory) OBJECT_LIST.get(((SkinningClientData)skinningtilesrender.clientdata).AnimationID());
        skinningtilesrender.updateLightCoord(t.getWorld(), t.getPos().up());
        skinningtilesrender.initSkinning(openglanimationmemory);
        if (skinningtilesrender.frame_int_array[0] < 78)
        {
            ++skinningtilesrender.frame_int_array[0];
        }
        else
        {
            skinningtilesrender.frame_int_array[0] = 0;
        }
        skinningtilesrender.setSkinning(openglanimationmemory);
        skinningtilesrender.drawLater();
        GL11.glPopMatrix();
    }
}
