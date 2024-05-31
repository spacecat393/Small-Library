package com.nali.list.tilerender;

import com.nali.data.client.SkinningClientData;
import com.nali.list.tiles.SmallTPBase;
import com.nali.render.SkinningRender;
import com.nali.small.render.IMixRender;
import com.nali.system.opengl.memory.OpenGLAnimationMemory;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import static com.nali.system.ClientLoader.OBJECT_LIST;

@SideOnly(Side.CLIENT)
public class SmallTPBaseRender<T extends SmallTPBase> extends TileEntitySpecialRenderer<T>
{
    @Override
    public void render(T t, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y, z + 0.5D);//0.5
        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        SkinningRender skinningrender = (SkinningRender)((IMixRender)t.getBlockType()).getObjectRender();
        OpenGLAnimationMemory openglanimationmemory = (OpenGLAnimationMemory) OBJECT_LIST.get(((SkinningClientData)skinningrender.clientdata).AnimationID());
        skinningrender.updateLightCoord(t.getWorld(), t.getPos().up());
        skinningrender.initSkinning(openglanimationmemory);
        if (skinningrender.frame_int_array[0] < 78)
        {
            ++skinningrender.frame_int_array[0];
        }
        else
        {
            skinningrender.frame_int_array[0] = 0;
        }
        skinningrender.setSkinning(openglanimationmemory);
        skinningrender.drawLater();
        GL11.glPopMatrix();
    }
}
