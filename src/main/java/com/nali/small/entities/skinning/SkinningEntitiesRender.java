package com.nali.small.entities.skinning;

import com.nali.math.M4x4;
import com.nali.render.SkinningRender;
import com.nali.system.opengl.memory.OpenGLCurrentMemory;
import com.nali.system.opengl.memory.OpenGLSkinningMemory;
import com.nali.system.opengl.memory.OpenGLSkinningShaderMemory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static com.nali.small.entities.EntitiesMathHelper.generateRainbowColor;
import static com.nali.small.entities.EntitiesMathHelper.interpolateRotation;

@SideOnly(Side.CLIENT)
public abstract class SkinningEntitiesRender<T extends SkinningEntities> extends Render<T>
{
//    static
//    {
//        supportEntities();
//    }

    public SkinningEntitiesRender(RenderManager rendermanager)
    {
        super(rendermanager);
//        new LayerBipedArmor(this);
//        new LayerHeldItem(this);
//        new LayerArrow(this);
//        new LayerDeadmau5Head(this);
//        new LayerCustomHead(this.getMainModel().bipedHead);
//        new LayerElytra(this);
//        new LayerEntityOnShoulder(renderManager);
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

//        GL11.glPointSize(100.0F);
//        GL11.glBegin(GL11.GL_POINTS);
//        GL11.glColor3f(1.0F, 0.0F, 0.0F);
//        GL11.glVertex4d(0, 0.1, 0, 1.0);
//        GL11.glEnd();

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

        this.renderHeldItem(skinningentities, skinningentities.getHeldItemMainhand(), partialTicks);
        skinningrender.objectworlddraw.renderWorld();

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
    }

    public void renderHeldItem(T skinningentities, ItemStack itemstack, float partialTicks/*, int hand_index, ItemCameraTransforms.TransformType transformtype*/)
    {
        if (!itemstack.isEmpty())
        {
            SkinningRender skinningrender = (SkinningRender)skinningentities.client_object;

            GL11.glColor4f(1.0F, 0.0F, 0.0F, 1.0F);
            GL11.glPushMatrix();

//            OpenGLCurrentMemory.GL_MATRIX_MODE = GL11.glGetInteger(GL11.GL_MATRIX_MODE);
//            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glPointSize(10.0F);

            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            for (int i = 1; i < skinningrender.memory_object_array.length; ++i)
            {
                OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)skinningrender.memory_object_array[i];
//                float[] main_vec4 = new float[4];
                for (int v = 0; v < openglskinningmemory.index_int_array.length; ++v)
                {
                    GL11.glPushMatrix();

                    int vi = openglskinningmemory.index_int_array[v] * 3;

                    byte max_joints = openglskinningmemory.max_joints;
                    float[] main_vec4_float_array = new float[4];
                    float[] temp_vec4_float_array = new float[4];

                    for (int j = 0; j < max_joints; ++j)
                    {
//                        float[] vec4 = new float[4];
                        int ji = openglskinningmemory.index_int_array[v] * max_joints + j;
                        int joints = (int)openglskinningmemory.joints_float_array[ji];

                        if (joints != -1)
                        {
//                            GL11.glPushMatrix();

                            OpenGLSkinningShaderMemory openglskinningshadermemory = (OpenGLSkinningShaderMemory)openglskinningmemory.shader;
                            temp_vec4_float_array[0] = openglskinningmemory.vertices_float_array[vi];
                            temp_vec4_float_array[1] = openglskinningmemory.vertices_float_array[vi + 1];
                            temp_vec4_float_array[2] = openglskinningmemory.vertices_float_array[vi + 2];
                            temp_vec4_float_array[3] = 1.0F;

//                            GL11.glTranslatef(openglskinningmemory.vertices_float_array[vi], openglskinningmemory.vertices_float_array[vi + 1], openglskinningmemory.vertices_float_array[vi + 2]);
//                            vec4[0] += openglskinningmemory.vertices_float_array[vi];
//                            vec4[1] += openglskinningmemory.vertices_float_array[vi + 1];
//                            vec4[2] += openglskinningmemory.vertices_float_array[vi + 2];
//                            vec4[3] += 1.0F;
//                            float[] t_mat4 = new float[]
//                            {
//                                1.0F, 0.0F, 0.0F, 0.0F,
//                                0.0F, 1.0F, 0.0F, 0.0F,
//                                0.0F, 0.0F, 1.0F, 0.0F,
//                                openglskinningmemory.vertices_float_array[vi], openglskinningmemory.vertices_float_array[vi + 1], openglskinningmemory.vertices_float_array[vi + 2], 1.0F
//                            };
//                            toVec4(vec4, t_mat4);
//                            OpenGLCurrentMemory.setFloatBuffer(t_mat4);
//                            GL11.glMultMatrix(OpenGLCurrentMemory.OPENGL_FLOATBUFFER);

                            for (int b = 0; b < openglskinningshadermemory.back_bones_2d_int_array[joints].length; ++b)
                            {
                                int index = openglskinningshadermemory.back_bones_2d_int_array[joints][b] * 16;
                                float[] bindpose_mat4 = new float[16], skinning_mat4 = new float[16];
                                System.arraycopy(openglskinningshadermemory.bind_poses_float_array, index, bindpose_mat4, 0, 16);
                                System.arraycopy(skinningrender.skinning_float_array, index, skinning_mat4, 0, 16);

//                                skinning_mat4[12] += temp_vec4_float_array[0];
//                                skinning_mat4[13] += temp_vec4_float_array[1];
//                                skinning_mat4[14] += temp_vec4_float_array[2];
//                                skinning_mat4[12] += openglskinningmemory.vertices_float_array[vi];
//                                skinning_mat4[13] += openglskinningmemory.vertices_float_array[vi + 1];
//                                skinning_mat4[14] += openglskinningmemory.vertices_float_array[vi + 2];
//                                skinning_mat4[15] = 1.0F;

//                                setMat4(bindpose_mat4);
//                                toVec4(vec4, bindpose_mat4);
//                                OpenGLCurrentMemory.setFloatBuffer(bindpose_mat4);
//                                GL11.glMultMatrix(OpenGLCurrentMemory.OPENGL_FLOATBUFFER);

//                                M4x4.inverse(skinning_mat4, 0);
//                                setMat4(skinning_mat4);
//                                toVec4(vec4, skinning_mat4);
//                                OpenGLCurrentMemory.setFloatBuffer(skinning_mat4);
//                                GL11.glMultMatrix(OpenGLCurrentMemory.OPENGL_FLOATBUFFER);

//                                setMat4(bindpose_mat4);
//                                M4x4.inverse(bindpose_mat4, 0);
//                                setMat4(bindpose_mat4);
//                                toVec4(vec4, bindpose_mat4);
//                                OpenGLCurrentMemory.setFloatBuffer(bindpose_mat4);
//                                GL11.glMultMatrix(OpenGLCurrentMemory.OPENGL_FLOATBUFFER);
//                                M4x4.inverse(skinning_mat4, 0);
//                                M4x4.multiply(bindpose_mat4, skinning_mat4, 0, 0);
//                                M4x4.inverse(bindpose_mat4, 0);
//                                M4x4.multiply(bindpose_mat4, skinning_mat4, 0, 0);
                                M4x4.inverse(bindpose_mat4, 0);
                                temp_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, bindpose_mat4);
//                                M4x4.inverse(skinning_mat4, 0);
//                                M4x4.multiply(bindpose_mat4, skinning_mat4, 0, 0);
                                temp_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, skinning_mat4);
                                M4x4.inverse(bindpose_mat4, 0);
                                temp_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, bindpose_mat4);
                            }

                            float weights = openglskinningmemory.weights_float_array[ji];
//                            GL11.glScalef(weights, weights, weights);
//                            GL11.glVertex3f(-vec4[0], -vec4[1], -vec4[2]);
//                            float[] w_mat4 = new float[]
//                            {
//                                1.0F, 0.0F, 0.0F, 0.0F,
//                                0.0F, 1.0F, 0.0F, 0.0F,
//                                0.0F, 0.0F, 1.0F, 0.0F,
//                                -vec4[0], -vec4[1], -vec4[2], vec4[3],
//                            };
//                            OpenGLCurrentMemory.setFloatBuffer(w_mat4);
//                            GL11.glMultMatrix(OpenGLCurrentMemory.OPENGL_FLOATBUFFER);
                            temp_vec4_float_array[0] *= weights;
                            temp_vec4_float_array[1] *= weights;
                            temp_vec4_float_array[2] *= weights;
                            temp_vec4_float_array[3] *= weights;

                            main_vec4_float_array[0] += temp_vec4_float_array[0];
                            main_vec4_float_array[1] += temp_vec4_float_array[1];
                            main_vec4_float_array[2] += temp_vec4_float_array[2];
                            main_vec4_float_array[3] += temp_vec4_float_array[3];

//                            GL11.glPopMatrix();
                        }
                    }

//                    if (main_vec4_float_array[3] != 1.0F)
//                    {
//                        main_vec4_float_array[0] /= main_vec4_float_array[3];
//                        main_vec4_float_array[1] /= main_vec4_float_array[3];
//                        main_vec4_float_array[2] /= main_vec4_float_array[3];
////                        main_vec4_float_array[3] /= main_vec4_float_array[3];
//                    }
//                    GL11.glTranslatef(main_vec4_float_array[0], main_vec4_float_array[1], main_vec4_float_array[2]);

                    float[] final_mat4 = new float[]
                    {
                        1.0F, 0.0F, 0.0F, 0.0F,
                        0.0F, 1.0F, 0.0F, 0.0F,
                        0.0F, 0.0F, 1.0F, 0.0F,
                        main_vec4_float_array[0], main_vec4_float_array[1], main_vec4_float_array[2], main_vec4_float_array[3]
                    };
                    OpenGLCurrentMemory.setFloatBuffer(final_mat4);
                    GL11.glMultMatrix(OpenGLCurrentMemory.OPENGL_FLOATBUFFER);

                    GL11.glBegin(GL11.GL_POINTS);
                    GL11.glVertex3f(0.0F, 0.0F, 0.0F);
                    GL11.glEnd();

//                    main_vec4[0] += vec4[0];
//                    main_vec4[1] += vec4[1];
//                    main_vec4[2] += vec4[2];
//                    main_vec4[3] += vec4[3];

//                    float[] t_mat4 = new float[]
//                    {
//                        1.0F, 0.0F, 0.0F, 0.0F,
//                        0.0F, 1.0F, 0.0F, 0.0F,
//                        0.0F, 0.0F, 1.0F, 0.0F,
//                        -vec4[0], -vec4[1], -vec4[2], -vec4[3]
//                    };
//                    toVec4(vec4, t_mat4);
//                    OpenGLCurrentMemory.setFloatBuffer(t_mat4);
//                    GL11.glMultMatrix(OpenGLCurrentMemory.OPENGL_FLOATBUFFER);
//                    if (vec4[3] != 1.0F)
//                    {
//                        vec4[0] /= vec4[3];
//                        vec4[1] /= vec4[3];
//                        vec4[2] /= vec4[3];
//                    }
//                    GL11.glTranslatef(-vec4[0], -vec4[1], -vec4[2]);

//                    Minecraft.getMinecraft().getItemRenderer().renderItemSide(skinningentities, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, false);
                    GL11.glPopMatrix();
                }
            }
//            GL11.glMatrixMode(OpenGLCurrentMemory.GL_MATRIX_MODE);
            GL11.glPopMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    public abstract void multiplyAnimation(T skinningentities);
//    public abstract int leftHand();
//    public abstract int rightHand();

//    public static float[] multiplyVec4Mat4(float[] vec4, float[] mat4)
//    {
//        float[] result = new float[4];
//        for (int i = 0; i < 4; i++)
//        {
//            float sum = 0.0F;
//            for (int j = 0; j < 4; j++)
//            {
//                sum += vec4[j] * mat4[i + j * 4];
//            }
//            result[i] = sum;
//        }
//        return result;
//    }

    public static float[] multiplyVec4Mat4(float[] vec4, float[] mat4)
    {
        float[] result = new float[4];
        for (int i = 0; i < 4; i++)
        {
            float sum = 0.0F;
            for (int j = 0; j < 4; j++)
            {
                sum += vec4[j] * mat4[i * 4 + j];
            }
            result[i] = sum;
        }
        return result;
    }

//     public static float[] multiplyVec4Mat4(float[] vec4, float[] mat4, int mat4_index)
//     {
//         float[] out_vec4 = new float[4];
//         out_vec4[0] = vec4[0] * mat4[mat4_index] + vec4[1] * mat4[mat4_index + 1] + vec4[2] * mat4[mat4_index + 2] + vec4[3] * mat4[mat4_index + 12];
//         out_vec4[1] = vec4[0] * mat4[mat4_index + 4] + vec4[1] * mat4[mat4_index + 5] + vec4[2] * mat4[mat4_index + 6] + vec4[3] * mat4[mat4_index + 13];
//         out_vec4[2] = vec4[0] * mat4[mat4_index + 8] + vec4[1] * mat4[mat4_index + 9] + vec4[2] * mat4[mat4_index + 10] + vec4[3] * mat4[mat4_index + 14];
//         out_vec4[3] = vec4[0] * mat4[mat4_index + 3] + vec4[1] * mat4[mat4_index + 7] + vec4[2] * mat4[mat4_index + 11] + vec4[3] * mat4[mat4_index + 15];
//
//         if (out_vec4[3] != 1.0F)
//         {
//             out_vec4[0] /= out_vec4[3];
//             out_vec4[1] /= out_vec4[3];
//             out_vec4[2] /= out_vec4[3];
//             out_vec4[3] /= out_vec4[3];
//         }
//         return out_vec4;
//     }

//    public static float[] multiplyVec4Mat4(float[] vec4, float[] mat4, int mat4_index)
//    {
//        float[] out_vec4 = new float[4];
//        out_vec4[0] = vec4[0] * mat4[mat4_index] + vec4[1] * mat4[mat4_index + 4] + vec4[2] * mat4[mat4_index + 8] + vec4[3] * mat4[mat4_index + 3];
//        out_vec4[1] = vec4[0] * mat4[mat4_index + 1] + vec4[1] * mat4[mat4_index + 5] + vec4[2] * mat4[mat4_index + 9] + vec4[3] * mat4[mat4_index + 7];
//        out_vec4[2] = vec4[0] * mat4[mat4_index + 2] + vec4[1] * mat4[mat4_index + 6] + vec4[2] * mat4[mat4_index + 10] + vec4[3] * mat4[mat4_index + 11];
//        out_vec4[3] = vec4[0] * mat4[mat4_index + 12] + vec4[1] * mat4[mat4_index + 13] + vec4[2] * mat4[mat4_index + 14] + vec4[3] * mat4[mat4_index + 15];
//
//        if (out_vec4[3] != 1.0F)
//        {
//            out_vec4[0] /= out_vec4[3];
//            out_vec4[1] /= out_vec4[3];
//            out_vec4[2] /= out_vec4[3];
//            out_vec4[3] /= out_vec4[3];
//        }
//        return out_vec4;
//    }

//    public static void setMat4(float[] mat4)
//    {
//        float f3 = mat4[3], f7 = mat4[7], f11 = mat4[11];
//
//        mat4[3] = mat4[12];
//        mat4[7] = mat4[13];
//        mat4[11] = mat4[14];
//
//        mat4[12] = f3;
//        mat4[13] = f7;
//        mat4[14] = f11;
//    }
//    public static void toVec4(float[] vec4, float[] mat4)
//    {
//        vec4[0] = mat4[3];
//        vec4[1] = mat4[7];
//        vec4[2] = mat4[11];
//        vec4[3] = mat4[15];
//    }
//
//    public static void toVec4(float[] vec4, float[] mat4)
//    {
//        vec4[0] = mat4[12];
//        vec4[1] = mat4[13];
//        vec4[2] = mat4[14];
//        vec4[3] = mat4[15];
//    }
}
