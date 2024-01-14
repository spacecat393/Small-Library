package com.nali.ilol.entities.skinning;

import com.nali.math.M4x4;
import com.nali.render.SkinningRender;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import static com.nali.ilol.entities.EntitiesMathHelper.interpolateRotation;

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
        GL11.glScalef(skinningrender.scale, skinningrender.scale, skinningrender.scale);
        float scale = (skinningrender.scale == 0 ? 1.0F : skinningrender.scale);
        this.shadowOpaque *= scale;
        this.shadowSize *= scale;

        this.updateData(skinningentities, partialTicks);

        skinningrender.objectworlddraw.renderWorld();

        GL11.glPopMatrix();
        super.doRender(skinningentities, ox, oy, oz, entityYaw, partialTicks);
    }

    public void updateData(T skinningentities, float partialTicks)
    {
        SkinningRender skinningrender = (SkinningRender)skinningentities.client_object;

        skinningrender.body_rot = (float)Math.toRadians(interpolateRotation(skinningentities.prevRenderYawOffset, skinningentities.renderYawOffset, partialTicks));
        skinningrender.head_rot = (float)Math.toRadians(interpolateRotation(skinningentities.prevRotationYawHead, skinningentities.rotationYawHead, partialTicks));
        skinningrender.net_head_yaw = skinningrender.head_rot - skinningrender.body_rot;
        skinningrender.head_pitch = (float)Math.toRadians(skinningentities.prevRotationPitch + (skinningentities.rotationPitch - skinningentities.prevRotationPitch) * partialTicks);

        int max_bones = skinningrender.openglanimationmemory.bones;

        //

        int max_key = skinningrender.openglanimationmemory.length;

        for (int i = 0; i < max_bones; ++i)
        {
            System.arraycopy(M4x4.IDENTITY, 0, skinningrender.skinning_float_array, i * 16, 16);
//            System.arraycopy(M4x4.IDENTITY, 0, skinningrender.inverse_skinning_float_array, i * 16, 16);
        }

        if (!skinningentities.fake)
        {
            this.multiplyAnimation(skinningentities);
        }

//        for (int z = 0; z < max_bones; ++z)
//        {
//            if (skinningrender.openglanimationmemory.idlebones_byte_array[z] != 0)
//            {
//                ((M4x4)FREE_SKINNING_OBJECT_ARRAY[6]).multiply(skinningrender.skinning_float_array, z * 16);
//            }
//        }

        for (int i = 0; i < max_bones; ++i)
        {
            M4x4.multiply(skinningrender.openglanimationmemory.transforms_float_array, skinningrender.skinning_float_array, (skinningrender.frame_int_array[0] + max_key * i) * 16, i * 16);
//            M4x4.multiply(skinningrender.openglanimationmemory.transforms_float_array, skinningrender.inverse_skinning_float_array, (skinningrender.frame_int_array[0] + max_key * i) * 16, i * 16);

//            {
//                M4x4 m4x4 = new M4x4();
//                M4x4.multiply(skinningrender.openglanimationmemory.transforms_float_array, m4x4.mat, (skinningrender.frame_int_array[0] + max_key * i) * 16, 0);
////                m4x4.inverse();
//                M4x4.multiply(m4x4.mat, skinningrender.inverse_skinning_float_array, 0, i * 16);
//            }

            for (int f = 1; f < skinningrender.frame_int_array.length; ++f)
            {
                if (skinningrender.frame_boolean_array[f - 1])
                {
                    M4x4.multiply(skinningrender.openglanimationmemory.transforms_float_array, skinningrender.skinning_float_array, (skinningrender.frame_int_array[f] + max_key * i) * 16, i * 16);
//                    M4x4.multiply(skinningrender.openglanimationmemory.transforms_float_array, skinningrender.inverse_skinning_float_array, (skinningrender.frame_int_array[f] + max_key * i) * 16, i * 16);

//                    {
//                        M4x4 m4x4 = new M4x4();
//                        M4x4.multiply(skinningrender.openglanimationmemory.transforms_float_array, m4x4.mat, (skinningrender.frame_int_array[f] + max_key * i) * 16, 0);
////                        m4x4.inverse();
//                        M4x4.multiply(m4x4.mat, skinningrender.inverse_skinning_float_array, 0, i * 16);
//                    }
                }
            }
            M4x4.inverse(skinningrender.skinning_float_array, i * 16);
        }
    }

//    public void renderHeldItem(SkinningEntities skinningentities, ItemStack itemstack, int hand_index, ItemCameraTransforms.TransformType transformtype)
//    {
//        GL11.glPushMatrix();
////        GL11.glTranslatef(0.0F, 0.75F, 0.0F);
////        GL11.glScalef(0.5F, 0.5F, 0.5F);
////        GL11.glTranslatef((float)(flag ? -1 : 1) / 16.0F, 0.125F, -0.625F);
//
//        if (!itemstack.isEmpty())
//        {
//            SkinningRender skinningrender = (SkinningRender)skinningentities.client_object;
//            OpenGLAnimationMemory openglanimationmemory = skinningrender.openglanimationmemory;
//            int[] bones_int_array = openglanimationmemory.bones_2d_int_array[hand_index];
//
//            boolean flag = transformtype == ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND;
////            GL11.glScalef(skinningrender.scale, skinningrender.scale, skinningrender.scale);
//
////            GL11.glRotated(-Math.toDegrees(skinningrender.body_rot), 0.0F, 1.0F, 0.0F);
//
//            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
////            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
//
////            GL11.glPushMatrix();
//
////            M4x4 m4x4 = new M4x4();
////            M4x4 inv_bp_m4x4 = new M4x4();
////            float m03 = 0;
////            float m13 = 0;
////            float m23 = 0;
//
//                for (int i = bones_int_array.length - 1; i > -1; --i)
////                for (int i = 0; i < bones_int_array.length; ++i)
//                {
//                    M4x4 m4x4 = new M4x4();
//                    M4x4 inv_bp_m4x4 = new M4x4();
////                    GL11.glPushMatrix();
//                    int index = bones_int_array[i] * 16;
//
//                    //create new visual bones
//                    System.arraycopy(openglanimationmemory.visual_bones_float_array, index, inv_bp_m4x4.mat, 0, 16);
//                    M4x4.multiply(inv_bp_m4x4.mat, m4x4.mat, 0, 0);
//                    M4x4.multiply(skinningrender.skinning_float_array, inv_bp_m4x4.mat, index, 0);
//                    inv_bp_m4x4.inverse();
//                    M4x4.multiply(inv_bp_m4x4.mat, m4x4.mat, 0, 0);
////                    System.arraycopy(openglanimationmemory.bind_poses_float_array, index, m4x4.mat, 0, 16);
////                    M4x4.multiply(openglanimationmemory.bind_poses_float_array, m4x4.mat, index, 0);
////                    M4x4.multiply(skinningrender.inverse_skinning_float_array, m4x4.mat, index, 0);
//
////                    float m03 = openglanimationmemory.bind_poses_float_array[index + 3];
////                    float m13 = openglanimationmemory.bind_poses_float_array[index + 7];
////                    float m23 = openglanimationmemory.bind_poses_float_array[index + 11];
//
////                    float m03 = m4x4.mat[3];
////                    float m13 = m4x4.mat[7];
////                    float m23 = m4x4.mat[11];
//
////                    m4x4.mat[3] = m4x4.mat[12];
////                    m4x4.mat[7] = m4x4.mat[13];
////                    m4x4.mat[11] = m4x4.mat[14];
//////
////                    m4x4.mat[12] = m03;
////                    m4x4.mat[13] = -m13;
////                    m4x4.mat[14] = -m23;
//
////                    m03 += m4x4.mat[3];
////                    m13 += m4x4.mat[7];
////                    m23 += m4x4.mat[11];
//
////                    float[] t_mat4 = new float[]
////                    {
////                        1.0F, 0.0F, 0.0F, 0.0F,
////                        0.0F, 1.0F, 0.0F, 0.0F,
////                        0.0F, 0.0F, 1.0F, 0.0F,
////                        m4x4.mat[3], m4x4.mat[7], m4x4.mat[11], 1.0F
////                    };
////                    OpenGLCurrentMemory.setFloatBuffer(t_mat4);
////                    GL11.glMultMatrix(OpenGLCurrentMemory.OPENGL_FLOATBUFFER);
////
////                    float[] r_mat4 = new float[]
////                    {
////                        m4x4.mat[0], m4x4.mat[4], m4x4.mat[8], 0.0F,
////                        m4x4.mat[1], m4x4.mat[5], m4x4.mat[9], 0.0F,
////                        m4x4.mat[2], m4x4.mat[6], m4x4.mat[10], 0.0F,
////                        0.0F, 0.0F, 0.0F, 1.0F
////                    };
////                    OpenGLCurrentMemory.setFloatBuffer(r_mat4);
////                    GL11.glMultMatrix(OpenGLCurrentMemory.OPENGL_FLOATBUFFER);
////                    GL11.glPushMatrix();
////                    m4x4.mat = new float[]
////                    {
////                        m4x4.mat[0], m4x4.mat[4], m4x4.mat[8], m4x4.mat[12],
////                        m4x4.mat[1], m4x4.mat[5], m4x4.mat[9], m4x4.mat[13],
////                        m4x4.mat[2], m4x4.mat[6], m4x4.mat[10], m4x4.mat[14],
////                        0.0F, 0.0F, 0.0F, m4x4.mat[15]
////                    };
//
////                    OpenGLCurrentMemory.setFloatBuffer(m4x4.mat);
////                    GL11.glTranslatef(m03, m13, m23);
////                    GL11.glMultMatrix(OpenGLCurrentMemory.OPENGL_FLOATBUFFER);
////                    GL11.glTranslatef(m03, m13, m23);
//
////                    System.arraycopy(openglanimationmemory.bind_poses_float_array, index, m4x4.mat, 0, 16);
////                    M4x4.multiply(skinningrender.inverse_skinning_float_array, m4x4.mat, index, 0);
////                    m4x4.inverse();
////                    M4x4.multiply(openglanimationmemory.bind_poses_float_array, m4x4.mat, index, 0);
////                    M4x4.multiply(openglanimationmemory.bind_poses_float_array, inv_bp_m4x4.mat, index, 0);
////                    inv_bp_m4x4.inverse();
//
////                    M4x4.multiply(inv_bp_m4x4.mat, m4x4.mat, 0, 0);
//
////                    GL11.glTranslatef(-m03, -m13, -m23);
////                    m03 = m4x4.mat[3];
////                    m13 = m4x4.mat[7];
////                    m23 = m4x4.mat[11];
//
////                    m4x4.mat[3] = m4x4.mat[12];
////                    m4x4.mat[7] = m4x4.mat[13];
////                    m4x4.mat[11] = m4x4.mat[14];
////
////                    m4x4.mat[12] = m03;
////                    m4x4.mat[13] = m13;
////                    m4x4.mat[14] = m23;
//
//                    m4x4.mat = new float[]
//                    {
//                        m4x4.mat[0], m4x4.mat[4], m4x4.mat[8], m4x4.mat[12],
//                        m4x4.mat[1], m4x4.mat[5], m4x4.mat[9], m4x4.mat[13],
//                        m4x4.mat[2], m4x4.mat[6], m4x4.mat[10], m4x4.mat[14],
//                        m4x4.mat[3], m4x4.mat[7], m4x4.mat[11], m4x4.mat[15]
//                    };
////                    GL11.glScalef(0.85F, 0.85F, 1.85F);
//                    OpenGLCurrentMemory.setFloatBuffer(m4x4.mat);
//                    GL11.glMultMatrix(OpenGLCurrentMemory.OPENGL_FLOATBUFFER);
////
////                    GL11.glTranslatef(m03, m13, m23);
////                    GL11.glPushMatrix();
////                    GL11.glTranslatef(-m03, -m13, -m23);
////                    GL11.glTranslatef(m03, m13, m23);
////                    GL11.glTranslatef(m03, m13, m23);
////                    GL11.glTranslatef(-m03, -m13, -m23);
//
////                    m4x4.mat = new float[]
////                    {
////                        m4x4.mat[0], m4x4.mat[4], m4x4.mat[8], m4x4.mat[12],
////                        m4x4.mat[1], m4x4.mat[5], m4x4.mat[9], m4x4.mat[13],
////                        m4x4.mat[2], m4x4.mat[6], m4x4.mat[10], m4x4.mat[14],
////                        m4x4.mat[3], m4x4.mat[7], m4x4.mat[11], m4x4.mat[15]
////                    };
//                }
//
//                GL11.glPushMatrix();
////                for (int i = bones_int_array.length - 1; i > -1; --i)
////                {
////                    GL11.glScalef(1.15F, 1.15F, 1.15F);
////                }
//
//                GL11.glScalef(skinningrender.scale / 3.0F, skinningrender.scale / 3.0F, skinningrender.scale / 3.0F);
//
//                Minecraft.getMinecraft().getItemRenderer().renderItemSide(skinningentities, itemstack, transformtype, flag);
//                GL11.glPopMatrix();
//
////                for (int i : bones_int_array)
////                {
////                    GL11.glPopMatrix();
////                }
//
////                GL11.glMultMatrix(OpenGLCurrentMemory.OPENGL_FLOATBUFFER);
////
////                m4x4.mat[3] = m4x4.mat[12];
////                m4x4.mat[7] = m4x4.mat[13];
////                m4x4.mat[11] = m4x4.mat[14];
////
////                m4x4.mat[12] = m03;
////                m4x4.mat[13] = m13;
////                m4x4.mat[14] = m23;
//
////            GL11.glTranslatef(m03, m13, m23);
//
//        }
//        GL11.glPopMatrix();
//    }

    public abstract void multiplyAnimation(T skinningentities);
//    public abstract int leftHand();
//    public abstract int rightHand();
}
