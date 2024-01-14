package com.nali.ilol.entities.object;

import com.nali.render.ObjectRender;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import static com.nali.ilol.entities.EntitiesMathHelper.interpolateRotation;

@SideOnly(Side.CLIENT)
public abstract class ObjectEntitiesRender<T extends ObjectEntities> extends Render<T>
{
    public ObjectEntitiesRender(RenderManager rendermanager)
    {
        super(rendermanager);
    }

    @Override
    public boolean shouldRender(T objectentities, ICamera camera, double camX, double camY, double camZ)
    {
        ObjectRender objectrender = (ObjectRender)objectentities.client_object;
        objectrender.should_render = super.shouldRender(objectentities, camera, camX, camY, camZ);
        return objectrender.should_render;
    }

    @Override
    public void doRender(T objectentities, double ox, double oy, double oz, float entityYaw, float partialTicks)
    {
        GL11.glPushMatrix();
        ObjectRender objectrender = (ObjectRender)objectentities.client_object;
        GL11.glTranslated(ox, oy, oz);
        GL11.glScalef(objectrender.scale, objectrender.scale, objectrender.scale);
        float scale = (objectrender.scale == 0 ? 1.0F : objectrender.scale);
        this.shadowOpaque *= scale;
        this.shadowSize *= scale;

        this.updateData(objectentities, partialTicks);
        objectrender.objectworlddraw.renderWorld();

        GL11.glPopMatrix();
        super.doRender(objectentities, ox, oy, oz, entityYaw, partialTicks);
    }

    public void updateData(T objectentities, float partialTicks)
    {
        ObjectRender objectrender = (ObjectRender)objectentities.client_object;

        objectrender.head_rot = (float)Math.toRadians(interpolateRotation(objectentities.rotationYaw, objectentities.prevRotationYaw, partialTicks));
        objectrender.head_pitch = (float)Math.toRadians(objectentities.prevRotationPitch + (objectentities.rotationPitch - objectentities.prevRotationPitch) * partialTicks);

        this.multiplyAnimation(objectentities);
    }

    public abstract void multiplyAnimation(T skinningentities);
}
