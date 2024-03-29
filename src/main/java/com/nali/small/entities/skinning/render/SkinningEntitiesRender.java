package com.nali.small.entities.skinning.render;

import com.nali.render.SkinningRender;
import com.nali.small.entities.memory.ClientEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL20;

import java.awt.*;

import static com.nali.small.entities.skinning.render.SkinningEntitiesRenderMath.generateRainbowColor;
import static com.nali.small.entities.skinning.render.SkinningEntitiesRenderMath.interpolateRotation;
import static com.nali.system.opengl.memory.OpenGLCurrentMemory.*;

@SideOnly(Side.CLIENT)
public abstract class SkinningEntitiesRender<T extends SkinningEntities> extends Render<T>
{
    public SkinningEntitiesRender(RenderManager rendermanager)
    {
        super(rendermanager);
    }

    @Override
    public boolean shouldRender(T skinningentities, ICamera camera, double camX, double camY, double camZ)
    {
        ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)skinningentities.bothentitiesmemory;
        SkinningRender skinningrender = (SkinningRender)cliententitiesmemory.objectrender;
        skinningrender.entitiesrendermemory.should_render = super.shouldRender(skinningentities, camera, camX, camY, camZ);
        return skinningrender.entitiesrendermemory.should_render;
    }

    @Override
    public void doRender(T skinningentities, double ox, double oy, double oz, float entityYaw, float partialTicks)
    {
        ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)skinningentities.bothentitiesmemory;
        SkinningRender skinningrender = (SkinningRender)cliententitiesmemory.objectrender;

        this.renderLayer(skinningentities, ox, oy, oz, partialTicks);

        GL11.glPushMatrix();

        this.updateData(skinningentities, partialTicks);

        GL11.glTranslated(ox, oy, oz);

        this.renderHitBox(skinningentities);

        GL11.glScalef(skinningrender.entitiesrendermemory.scale, skinningrender.entitiesrendermemory.scale, skinningrender.entitiesrendermemory.scale);
        this.shadowOpaque *= skinningrender.entitiesrendermemory.scale;
        this.shadowSize *= skinningrender.entitiesrendermemory.scale;

        boolean invisible = skinningentities.isInvisible() || skinningentities.isInvisibleToPlayer(Minecraft.getMinecraft().player);
        if (invisible)
        {
            OPENGL_FIXED_PIPE_FLOATBUFFER.limit(16);
            GL11.glGetFloat(GL11.GL_CURRENT_COLOR, OPENGL_FIXED_PIPE_FLOATBUFFER);
            GL_CURRENT_COLOR[0] = OPENGL_FIXED_PIPE_FLOATBUFFER.get(0);
            GL_CURRENT_COLOR[1] = OPENGL_FIXED_PIPE_FLOATBUFFER.get(1);
            GL_CURRENT_COLOR[2] = OPENGL_FIXED_PIPE_FLOATBUFFER.get(2);
            GL_CURRENT_COLOR[3] = OPENGL_FIXED_PIPE_FLOATBUFFER.get(3);
            GL11.glColor4f(GL_CURRENT_COLOR[0], GL_CURRENT_COLOR[1], GL_CURRENT_COLOR[2], 0.25F);
        }
        skinningrender.objectworlddraw.renderWorld(/*ox, oy, oz*/);
        if (invisible)
        {
            GL11.glColor4f(GL_CURRENT_COLOR[0], GL_CURRENT_COLOR[1], GL_CURRENT_COLOR[2], GL_CURRENT_COLOR[3]);
        }

        GL11.glPopMatrix();

//        this.renderLayer(skinningentities, ox, oy, oz, partialTicks);

        super.doRender(skinningentities, ox, oy, oz, entityYaw, partialTicks);
    }

    public void updateData(T skinningentities, float partialTicks)
    {
        ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)skinningentities.bothentitiesmemory;
        SkinningRender skinningrender = (SkinningRender)cliententitiesmemory.objectrender;

        skinningrender.entitiesrendermemory.body_rot = (float)Math.toRadians(interpolateRotation(skinningentities.prevRenderYawOffset, skinningentities.renderYawOffset, partialTicks));
        skinningrender.entitiesrendermemory.head_rot = (float)Math.toRadians(interpolateRotation(skinningentities.prevRotationYaw, skinningentities.rotationYaw, partialTicks));
        skinningrender.entitiesrendermemory.net_head_yaw = skinningrender.entitiesrendermemory.head_rot - skinningrender.entitiesrendermemory.body_rot;
        skinningrender.entitiesrendermemory.head_pitch = (float)Math.toRadians(skinningentities.prevRotationPitch + (skinningentities.rotationPitch - skinningentities.prevRotationPitch) * partialTicks);
//        skinningrender.timeline = partialTicks;

        skinningrender.initSkinning();

        if (!cliententitiesmemory.fake)
        {
            this.multiplyAnimation(skinningentities);
        }

        skinningrender.setSkinning();
    }

    public void renderLayer(T skinningentities, double ox, double oy, double oz, float partialTicks)
    {
        ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)skinningentities.bothentitiesmemory;
        SkinningRender skinningrender = (SkinningRender)cliententitiesmemory.objectrender;
        GL11.glPushMatrix();

        GL11.glTranslated(ox, oy, oz);
        GL11.glScalef(skinningrender.entitiesrendermemory.scale, skinningrender.entitiesrendermemory.scale, skinningrender.entitiesrendermemory.scale);
        GL11.glTranslated(-ox, -oy, -oz);
        cliententitiesmemory.itemlayerrender.x = (float)ox;
        cliententitiesmemory.itemlayerrender.y = (float)oy;
        cliententitiesmemory.itemlayerrender.z = (float)oz;
        cliententitiesmemory.itemlayerrender.layer(partialTicks);
        cliententitiesmemory.arrowlayerrender.layer(this, (float)ox, (float)oy, (float)oz, partialTicks);

        GL11.glPopMatrix();
    }

    public void renderHitBox(T skinningentities)
    {
        if (this.renderManager.isDebugBoundingBox() && !skinningentities.isInvisible() && !Minecraft.getMinecraft().isReducedDebug())
        {
//            ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)skinningentities.bothentitiesmemory;
            GL11.glPushMatrix();

            GL_TEXTURE_2D = GL11.glIsEnabled(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_TEXTURE_2D);

            GL_BLEND = GL11.glIsEnabled(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_BLEND);

            GL11.glGetInteger(GL20.GL_BLEND_EQUATION_RGB, OPENGL_INTBUFFER);
            GL_BLEND_EQUATION_RGB = OPENGL_INTBUFFER.get(0);
            GL11.glGetInteger(GL20.GL_BLEND_EQUATION_ALPHA, OPENGL_INTBUFFER);
            GL_BLEND_EQUATION_ALPHA = OPENGL_INTBUFFER.get(0);
            GL20.glBlendEquationSeparate(GL14.GL_FUNC_ADD, GL14.GL_FUNC_ADD);

            GL11.glGetInteger(GL14.GL_BLEND_SRC_RGB, OPENGL_INTBUFFER);
            GL_BLEND_SRC_RGB = OPENGL_INTBUFFER.get(0);
            GL11.glGetInteger(GL14.GL_BLEND_SRC_ALPHA, OPENGL_INTBUFFER);
            GL_BLEND_SRC_ALPHA = OPENGL_INTBUFFER.get(0);
            GL11.glGetInteger(GL14.GL_BLEND_DST_RGB, OPENGL_INTBUFFER);
            GL_BLEND_DST_RGB = OPENGL_INTBUFFER.get(0);
            GL11.glGetInteger(GL14.GL_BLEND_DST_ALPHA, OPENGL_INTBUFFER);
            GL_BLEND_DST_ALPHA = OPENGL_INTBUFFER.get(0);
            GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);

            GL11.glTranslated(-skinningentities.posX, -skinningentities.posY, -skinningentities.posZ);
            Color color = generateRainbowColor();
            float r = color.getRed() / 255.0F, g = color.getGreen() / 255.0F, b = color.getBlue() / 255.0F;

            OPENGL_FIXED_PIPE_FLOATBUFFER.limit(16);
            GL11.glGetFloat(GL11.GL_LINE_WIDTH, OPENGL_FIXED_PIPE_FLOATBUFFER);
            GL_LINE_WIDTH = OPENGL_FIXED_PIPE_FLOATBUFFER.get(0);
            GL11.glLineWidth(5.0F);

            AxisAlignedBB[] axisalignedbb_array = new AxisAlignedBB[]
            {
                skinningentities.getHeadAxisAlignedBB(),
                skinningentities.getMouthAxisAlignedBB()
            };
            for (AxisAlignedBB axisalignedbb : axisalignedbb_array)
            {
                RenderGlobal.drawSelectionBoundingBox(axisalignedbb, r, g, b, 1.0F);
            }

            if (GL_BLEND)
            {
                GL11.glEnable(GL11.GL_BLEND);
            }
            else
            {
                GL11.glDisable(GL11.GL_BLEND);
            }

            GL20.glBlendEquationSeparate(GL_BLEND_EQUATION_RGB, GL_BLEND_EQUATION_ALPHA);
            GL14.glBlendFuncSeparate(GL_BLEND_SRC_RGB, GL_BLEND_DST_RGB, GL_BLEND_SRC_ALPHA, GL_BLEND_DST_ALPHA);

            GL11.glLineWidth(GL_LINE_WIDTH);

            if (GL_TEXTURE_2D)
            {
                GL11.glEnable(GL11.GL_TEXTURE_2D);
            }
            else
            {
                GL11.glDisable(GL11.GL_TEXTURE_2D);
            }

            GL11.glPopMatrix();
        }
    }

    public abstract void multiplyAnimation(T skinningentities);

//    @Override
//    public void renderMultipass(T skinningentities, double ox, double oy, double oz, float entityYaw, float partialTicks)
//    {
//        super.renderMultipass(skinningentities, ox, oy, oz, entityYaw, partialTicks);
//    }
//
//    @Override
//    public boolean isMultipass()
//    {
//        return true;
//    }
}
