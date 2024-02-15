package com.nali.small.entities.skinning.render.layer;

import com.nali.render.SkinningRender;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.render.SkinningEntitiesRender;
import com.nali.system.opengl.memory.OpenGLSkinningMemory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Random;

@SideOnly(Side.CLIENT)
public class ArrowLayerRender extends LayerRender
{
    public ArrayList<int[]> index_int_array_arraylist = new ArrayList<int[]>();
    public ArrayList<float[]> float_array_arraylist = new ArrayList<float[]>();

    public ArrowLayerRender(SkinningEntities skinningentities)
    {
        super(skinningentities);
    }

    public void layer(SkinningEntitiesRender skinningentitiesrender, float partialTicks)
    {
        int i = this.skinningentities.getArrowCountInEntity();

        if (i > 0)
        {
            Entity entity = new EntityTippedArrow(this.skinningentities.world, this.skinningentities.posX, this.skinningentities.posY, this.skinningentities.posZ);

            if (this.index_int_array_arraylist.size() < i)
            {
                SkinningRender skinningrender = (SkinningRender)this.skinningentities.client_object;
                Random random = entity.world.rand;
                int model_i = random.nextInt(skinningrender.memory_object_array.length);
                OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)skinningrender.memory_object_array[model_i];
                this.index_int_array_arraylist.add(new int[]{model_i, random.nextInt(openglskinningmemory.index_int_array.length)});
                this.float_array_arraylist.add(new float[]{random.nextFloat(), random.nextFloat(), random.nextFloat()});
            }
            else if (this.index_int_array_arraylist.size() > i)
            {
                this.index_int_array_arraylist.subList(i, this.index_int_array_arraylist.size()).clear();
                this.float_array_arraylist.subList(i, this.float_array_arraylist.size()).clear();
            }

            for (int j = 0; j < i; ++j)
            {
                int[] int_array = this.index_int_array_arraylist.get(j);
                float[] float_array = this.float_array_arraylist.get(j);
                Vec3d c_vec3d = skinningentitiesrender.doModel(this.skinningentities, int_array[0], int_array[1]);
                GL11.glPushMatrix();
                GL11.glTranslatef((float)c_vec3d.x, (float)c_vec3d.y, (float)c_vec3d.z);
                GL11.glScalef(0.5F, 0.5F, 0.5F);
//                    RenderHelper.disableStandardItemLighting();
                float f = float_array[0];
                float f1 = float_array[1];
                float f2 = float_array[2];
                f = f * 2.0F - 1.0F;
                f1 = f1 * 2.0F - 1.0F;
                f2 = f2 * 2.0F - 1.0F;
                f = f * -1.0F;
                f1 = f1 * -1.0F;
                f2 = f2 * -1.0F;
                float f6 = MathHelper.sqrt(f * f + f2 * f2);
                entity.rotationYaw = (float)(Math.atan2(f, f2) * (180D / Math.PI));
                entity.rotationPitch = (float)(Math.atan2(f1, f6) * (180D / Math.PI));
                entity.prevRotationYaw = entity.rotationYaw;
                entity.prevRotationPitch = entity.rotationPitch;
                skinningentitiesrender.getRenderManager().renderEntity(entity, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks, false);
//                    RenderHelper.enableStandardItemLighting();
                GL11.glPopMatrix();
            }
        }
    }
}
