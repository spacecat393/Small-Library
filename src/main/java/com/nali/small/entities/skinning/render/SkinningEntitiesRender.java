package com.nali.small.entities.skinning.render;

import com.nali.math.M4x4;
import com.nali.math.Quaternion;
import com.nali.render.SkinningRender;
import com.nali.small.entities.memory.ClientEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.system.opengl.memory.OpenGLSkinningMemory;
import com.nali.system.opengl.memory.OpenGLSkinningShaderMemory;
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

import static com.nali.small.entities.skinning.render.SkinningEntitiesRenderMath.*;
import static com.nali.system.opengl.memory.OpenGLCurrentMemory.*;
import static com.nali.system.opengl.memory.OpenGLCurrentMemory.OPENGL_INTBUFFER;

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

        GL11.glPushMatrix();

        this.updateData(skinningentities, partialTicks);

//        if (this.renderManager.isDebugBoundingBox() && !skinningentities.isInvisible() && !Minecraft.getMinecraft().isReducedDebug())
//        {
//            for (int i = 0; i < skinningrender.memory_object_array.length; ++i)
//            {
//                OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)skinningrender.memory_object_array[i];
//                for (int v = 0; v < openglskinningmemory.index_int_array.length; ++v)
//                {
//                    GL11.glPushMatrix();
//                    this.apply3DSkinningVec4(this.get3DSkinning(skinningentities, (float)ox, (float)oy, (float)oz, i, v));
//                    GL11.glScalef(0.01F, 0.01F, 0.01F);
//                    Minecraft.getMinecraft().getItemRenderer().renderItemSide(skinningentities, SmallBox.I.getDefaultInstance(), ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, false);
//                    GL11.glPopMatrix();
//                }
//            }
//        }

//        if (!(this.renderManager.isDebugBoundingBox() && !skinningentities.isInvisible() && !Minecraft.getMinecraft().isReducedDebug()))
//        {
        GL11.glTranslated(ox, oy, oz);

        //
        if (this.renderManager.isDebugBoundingBox() && !skinningentities.isInvisible() && !Minecraft.getMinecraft().isReducedDebug())
        {
            GL11.glPushMatrix();
//            GlStateManager.disableTexture2D();
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
//            GlStateManager.glLineWidth(5.0F);

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

//            GlStateManager.glLineWidth(1.0F);
//            GlStateManager.enableTexture2D();
            GL11.glPopMatrix();
        }
        //

        GL11.glScalef(skinningrender.entitiesrendermemory.scale, skinningrender.entitiesrendermemory.scale, skinningrender.entitiesrendermemory.scale);
//        float scale = (skinningrender.scale == 0 ? 1.0F : skinningrender.scale);
//        this.shadowOpaque *= scale;
//        this.shadowSize *= scale;
        this.shadowOpaque *= skinningrender.entitiesrendermemory.scale;
        this.shadowSize *= skinningrender.entitiesrendermemory.scale;

//        GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT);
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
        skinningrender.objectworlddraw.renderWorld();
//        GL11.glPopAttrib();
        if (invisible)
        {
            GL11.glColor4f(GL_CURRENT_COLOR[0], GL_CURRENT_COLOR[1], GL_CURRENT_COLOR[2], GL_CURRENT_COLOR[3]);
        }
//        }

        GL11.glPopMatrix();

        GL11.glPushMatrix();

        GL11.glTranslated(ox, oy, oz);
        GL11.glScalef(skinningrender.entitiesrendermemory.scale, skinningrender.entitiesrendermemory.scale, skinningrender.entitiesrendermemory.scale);
        GL11.glTranslated(-ox, -oy, -oz);
        cliententitiesmemory.itemlayerrender.x = (float)ox;
        cliententitiesmemory.itemlayerrender.y = (float)oy;
        cliententitiesmemory.itemlayerrender.z = (float)oz;
        cliententitiesmemory.itemlayerrender.layer(this, partialTicks);
        cliententitiesmemory.arrowlayerrender.layer(this, (float)ox, (float)oy, (float)oz, partialTicks);

        GL11.glPopMatrix();

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
        skinningrender.timeline = partialTicks;

        skinningrender.initSkinning();

        if (!cliententitiesmemory.fake)
        {
            this.multiplyAnimation(skinningentities);
        }

        skinningrender.setSkinning();
    }

    public abstract void multiplyAnimation(T skinningentities);

    public float[] get3DSkinning(SkinningRender skinningrender, float x, float y, float z, float x0, float y0, float z0, int i, int v)
    {
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
                temp_vec4_float_array[0] = openglskinningmemory.vertices_float_array[vi] + x0;
                temp_vec4_float_array[1] = openglskinningmemory.vertices_float_array[vi + 1] + y0;
                temp_vec4_float_array[2] = openglskinningmemory.vertices_float_array[vi + 2] + z0;
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

//        main_vec4_float_array = multiplyVec4Mat4(main_vec4_float_array, new float[]
//        {
//            skinningrender.scale, 0.0F, 0.0F, 0.0F,
//            0.0F, skinningrender.scale, 0.0F, 0.0F,
//            0.0F, 0.0F, skinningrender.scale, 0.0F,
//            0.0F, 0.0F, 0.0F, 1.0F,
//        });
        main_vec4_float_array = multiplyVec4Mat4(main_vec4_float_array, new Quaternion(-1.571F, 0.0F, 0.0F).getM4x4().mat);
        main_vec4_float_array[0] += x;
        main_vec4_float_array[1] += y;
        main_vec4_float_array[2] += z;
//        main_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, new Quaternion(-1.571F, 0.0F, 0.0F).getM4x4().mat);

        return main_vec4_float_array;
    }

    public float[] getMat43DSkinning(SkinningRender skinningrender, int i, int v)
    {
        OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)skinningrender.memory_object_array[i];

        byte max_joints = openglskinningmemory.max_joints;
        float[] mat4_float_array = new float[16];
        System.arraycopy(M4x4.IDENTITY, 0, mat4_float_array, 0, 16);

//        for (int j = 0; j < 1; ++j)
//        {
        int ji = openglskinningmemory.index_int_array[v] * max_joints;// + j;
        int joints = (int)openglskinningmemory.joints_float_array[ji];

        if (joints != -1)
        {
            OpenGLSkinningShaderMemory openglskinningshadermemory = (OpenGLSkinningShaderMemory)openglskinningmemory.shader;

            for (int b = 0; b < openglskinningshadermemory.back_bones_2d_int_array[joints].length; ++b)
            {
                M4x4.multiply(skinningrender.skinning_float_array, mat4_float_array, openglskinningshadermemory.back_bones_2d_int_array[joints][b] * 16, 0);
            }
        }
//        }

//        M4x4.multiply(new Quaternion(-1.571F, 0.0F, 0.0F).getM4x4().mat, mat4_float_array, 0, 0);
        return mat4_float_array;
    }

    public void apply3DSkinningVec4(float[] vec4)
    {
//        if (vec4[3] == 0.0F)
//        {
//            vec4[3] = 1.0F;
//        }

//        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
        GL11.glTranslatef(vec4[0] / vec4[3], vec4[1] / vec4[3], vec4[2] / vec4[3]);
//        GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
//        float[] mat4 = new float[]
//        {
//            1, 0, 0, 0,
//            0, 1, 0, 0,
//            0, 0, 1, 0,
//            vec4[0], vec4[1], vec4[2], vec4[3]
//        };
//        OpenGLCurrentMemory.setFloatBuffer(mat4);
//        GL11.glMultMatrix(OpenGLCurrentMemory.OPENGL_FLOATBUFFER);
    }
}
