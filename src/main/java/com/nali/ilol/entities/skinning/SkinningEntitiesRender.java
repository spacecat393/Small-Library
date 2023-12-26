package com.nali.ilol.entities.skinning;

import com.nali.math.M4x4;
import com.nali.math.MixMath;
import com.nali.render.SkinningRender;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
        SkinningRender skinningrender = (SkinningRender)skinningentities.client_object;
        float scale = (skinningrender.scale == 0 ? 1.0F : skinningrender.scale);
        this.shadowOpaque *= scale;
        this.shadowSize *= scale;

        this.updateData(skinningentities, partialTicks);

        skinningrender.objectworlddraw.renderWorld(ox, oy, oz, scale);

        super.doRender(skinningentities, ox, oy, oz, entityYaw, partialTicks);
    }

    public void updateData(T skinningentities, float partialTicks)
    {
        SkinningRender skinningrender = (SkinningRender)skinningentities.client_object;

        skinningrender.body_rot = (float)Math.toRadians(MixMath.interpolateRotation(skinningentities.prevRenderYawOffset, skinningentities.renderYawOffset, partialTicks));
        skinningrender.head_rot = (float)Math.toRadians(MixMath.interpolateRotation(skinningentities.prevRotationYawHead, skinningentities.rotationYawHead, partialTicks));
        skinningrender.net_head_yaw = skinningrender.head_rot - skinningrender.body_rot;
        skinningrender.head_pitch = (float)Math.toRadians(skinningentities.prevRotationPitch + (skinningentities.rotationPitch - skinningentities.prevRotationPitch) * partialTicks);

        int max_bones = skinningrender.openglanimationmemory.bones;

        //

        int max_key = skinningrender.openglanimationmemory.length;

        for (int i = 0; i < max_bones; ++i)
        {
            System.arraycopy(M4x4.IDENTITY, 0, skinningrender.skinning_float_array, i * 16, 16);
        }

        this.multiplyAnimation(skinningentities);

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

            for (int f = 1; f < skinningrender.frame_int_array.length; ++f)
            {
                if (skinningrender.frame_boolean_array[f - 1])
                {
                    M4x4.multiply(skinningrender.openglanimationmemory.transforms_float_array, skinningrender.skinning_float_array, (skinningrender.frame_int_array[f] + max_key * i) * 16, i * 16);
                }
            }
        }
    }

    public abstract void multiplyAnimation(T skinningentities);
}
