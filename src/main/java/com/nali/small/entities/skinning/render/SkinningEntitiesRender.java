package com.nali.small.entities.skinning.render;

import com.nali.math.M4x4;
import com.nali.math.Quaternion;
import com.nali.render.SkinningRender;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.system.opengl.memory.OpenGLSkinningMemory;
import com.nali.system.opengl.memory.OpenGLSkinningShaderMemory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static com.nali.small.entities.EntitiesMath.interpolateRotation;
import static com.nali.small.entities.skinning.render.SkinningEntitiesRenderMath.generateRainbowColor;
import static com.nali.small.entities.skinning.render.SkinningEntitiesRenderMath.multiplyVec4Mat4;

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
        SkinningRender skinningrender = (SkinningRender)skinningentities.client_object;
        skinningrender.should_render = super.shouldRender(skinningentities, camera, camX, camY, camZ);
        return skinningrender.should_render;
    }

    @Override
    public void doRender(T skinningentities, double ox, double oy, double oz, float entityYaw, float partialTicks)
    {
        GL11.glPushMatrix();

        SkinningRender skinningrender = (SkinningRender)skinningentities.client_object;
        GL11.glTranslated(ox, oy, oz);

        //
        if (this.renderManager.isDebugBoundingBox() && !skinningentities.isInvisible() && !Minecraft.getMinecraft().isReducedDebug())
        {
            GlStateManager.pushMatrix();
            GlStateManager.disableTexture2D();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glTranslated(-skinningentities.posX, -skinningentities.posY, -skinningentities.posZ);
            Color color = generateRainbowColor();
            float r = color.getRed() / 255.0F, g = color.getGreen() / 255.0F, b = color.getBlue() / 255.0F;
            GlStateManager.glLineWidth(5.0F);

            AxisAlignedBB[] axisalignedbb_array = new AxisAlignedBB[]
            {
                skinningentities.getHeadAxisAlignedBB(),
                skinningentities.getMouthAxisAlignedBB()
            };
            for (AxisAlignedBB axisalignedbb : axisalignedbb_array)
            {
                RenderGlobal.drawSelectionBoundingBox(axisalignedbb, r, g, b, 1.0F);
            }

            GlStateManager.glLineWidth(1.0F);
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
        //
        GL11.glScalef(skinningrender.scale, skinningrender.scale, skinningrender.scale);
        float scale = (skinningrender.scale == 0 ? 1.0F : skinningrender.scale);
        this.shadowOpaque *= scale;
        this.shadowSize *= scale;

        this.updateData(skinningentities, partialTicks);

        skinningentities.itemlayerrender.layer(this, partialTicks);
        skinningentities.arrowlayerrender.layer(this, partialTicks);

        if (!(this.renderManager.isDebugBoundingBox() && !skinningentities.isInvisible() && !Minecraft.getMinecraft().isReducedDebug()))
        {
            skinningrender.objectworlddraw.renderWorld();
        }

        GL11.glPopMatrix();
        super.doRender(skinningentities, ox, oy, oz, entityYaw, partialTicks);
    }

    public void updateData(T skinningentities, float partialTicks)
    {
        SkinningRender skinningrender = (SkinningRender)skinningentities.client_object;

        skinningrender.body_rot = (float)Math.toRadians(interpolateRotation(skinningentities.prevRenderYawOffset, skinningentities.renderYawOffset, partialTicks));
        skinningrender.head_rot = (float)Math.toRadians(interpolateRotation(skinningentities.prevRotationYaw, skinningentities.rotationYaw, partialTicks));
        skinningrender.net_head_yaw = skinningrender.head_rot - skinningrender.body_rot;
        skinningrender.head_pitch = (float)Math.toRadians(skinningentities.prevRotationPitch + (skinningentities.rotationPitch - skinningentities.prevRotationPitch) * partialTicks);

        skinningrender.initSkinning();

        if (!skinningentities.fake)
        {
            this.multiplyAnimation(skinningentities);
        }

        skinningrender.setSkinning();

//        GL11.glColor4f(1.0F, 0.0F, 0.0F, 1.0F);
//        GL11.glPointSize(10.0F);
//        for (int i = 0; i < skinningrender.memory_object_array.length; ++i)
//        {
//            OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)skinningrender.memory_object_array[i];
//            for (int v = 0; v < openglskinningmemory.index_int_array.length; ++v)
//            {
//                doModel(skinningentities, i, v, () ->
//                {
//                    GL11.glBegin(GL11.GL_POINTS);
//                    GL11.glVertex3f(0.0F, 0.0F, 0.0F);
//                    GL11.glEnd();
//                });
//            }
//        }
    }

    public abstract void multiplyAnimation(T skinningentities);

    public Vec3d doModel(T skinningentities, int i, int v)
    {
        SkinningRender skinningrender = (SkinningRender)skinningentities.client_object;
        OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)skinningrender.memory_object_array[i];

        int vi = openglskinningmemory.index_int_array[v] * 3;

        byte max_joints = openglskinningmemory.max_joints;
        float[] main_vec4_float_array = new float[4];
        float[] temp_vec4_float_array = new float[4];

        for (int j = 0; j < max_joints; ++j)
        {
            int ji = openglskinningmemory.index_int_array[v] * max_joints + j;
            int joints = (int)openglskinningmemory.joints_float_array[ji];

            if (joints != -1)
            {
                temp_vec4_float_array[0] = openglskinningmemory.vertices_float_array[vi];
                temp_vec4_float_array[1] = openglskinningmemory.vertices_float_array[vi + 1];
                temp_vec4_float_array[2] = openglskinningmemory.vertices_float_array[vi + 2];
                temp_vec4_float_array[3] = 1.0F;

                OpenGLSkinningShaderMemory openglskinningshadermemory = (OpenGLSkinningShaderMemory)openglskinningmemory.shader;

                for (int b = 0; b < openglskinningshadermemory.back_bones_2d_int_array[joints].length; ++b)
                {
                    int index = openglskinningshadermemory.back_bones_2d_int_array[joints][b] * 16;
                    float[] bindpose_mat4 = new float[16], skinning_mat4 = new float[16];
                    System.arraycopy(openglskinningshadermemory.bind_poses_float_array, index, bindpose_mat4, 0, 16);
                    System.arraycopy(skinningrender.skinning_float_array, index, skinning_mat4, 0, 16);

                    M4x4.inverse(bindpose_mat4, 0);
                    temp_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, bindpose_mat4);

                    temp_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, skinning_mat4);

                    M4x4.inverse(bindpose_mat4, 0);
                    temp_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, bindpose_mat4);
                }

                float weights = openglskinningmemory.weights_float_array[ji];

                temp_vec4_float_array[0] *= weights;
                temp_vec4_float_array[1] *= weights;
                temp_vec4_float_array[2] *= weights;
                temp_vec4_float_array[3] *= weights;

                main_vec4_float_array[0] += temp_vec4_float_array[0];
                main_vec4_float_array[1] += temp_vec4_float_array[1];
                main_vec4_float_array[2] += temp_vec4_float_array[2];
                main_vec4_float_array[3] += temp_vec4_float_array[3];
            }
        }

        main_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, new Quaternion(-1.571F, 0.0F, 0.0F).getM4x4().mat);

        Vec3d vec3d = new Vec3d(main_vec4_float_array[0] / main_vec4_float_array[3], main_vec4_float_array[1] / main_vec4_float_array[3], main_vec4_float_array[2] / main_vec4_float_array[3]);

        return vec3d;
    }
}
