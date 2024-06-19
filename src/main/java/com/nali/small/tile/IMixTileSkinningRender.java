package com.nali.small.tile;

import com.nali.data.client.SkinningClientData;
import com.nali.render.SkinningRender;
import com.nali.small.render.IMixRender;
import com.nali.system.opengl.memory.OpenGLAnimationMemory;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import static com.nali.system.ClientLoader.OBJECT_LIST;

@SideOnly(Side.CLIENT)
public interface IMixTileSkinningRender<T extends TileEntity> extends IMixTileObjectRender<T>
{
    @Override
    default void render(T t, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        GL11.glPushMatrix();
        this.translate(x, y, z);
        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
        this.scale();
        SkinningRender skinningrender = (SkinningRender)((IMixRender)t.getBlockType()).getObjectRender();
        OpenGLAnimationMemory openglanimationmemory = (OpenGLAnimationMemory) OBJECT_LIST.get(((SkinningClientData)skinningrender.clientdata).AnimationID());
        skinningrender.updateLightCoord(t.getWorld(), t.getPos().up());
        skinningrender.initSkinning(openglanimationmemory);
        this.updateFrame(skinningrender);
        skinningrender.setSkinning(openglanimationmemory);
        skinningrender.drawLater();
        GL11.glPopMatrix();
    }

    @Override
    default void translate(double x, double y, double z)
    {
        GL11.glTranslated(x + 0.5D, y, z + 0.5D);//0.5
    }

    @Override
    default void scale()
    {
        GL11.glScalef(0.5F, 0.5F, 0.5F);
    }

    void updateFrame(SkinningRender skinningrender);
}
