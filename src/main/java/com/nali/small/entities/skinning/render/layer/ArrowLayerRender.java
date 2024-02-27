package com.nali.small.entities.skinning.render.layer;

import com.nali.render.SkinningRender;
import com.nali.small.entities.memory.ClientEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.render.SkinningEntitiesRender;
import com.nali.system.opengl.memory.OpenGLCurrentMemory;
import com.nali.system.opengl.memory.OpenGLSkinningMemory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.util.math.MathHelper;
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

    public void layer(SkinningEntitiesRender skinningentitiesrender, float x, float y, float z, float partialTicks)
    {
        int i = this.skinningentities.getArrowCountInEntity();

        if (i > 0)
        {
            Entity entity = new EntityTippedArrow(this.skinningentities.world, this.skinningentities.posX, this.skinningentities.posY, this.skinningentities.posZ);

            if (this.index_int_array_arraylist.size() < i)
            {
                ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)skinningentities.bothentitiesmemory;
                SkinningRender skinningrender = (SkinningRender)cliententitiesmemory.objectrender;
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

            ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)this.skinningentities.bothentitiesmemory;
            SkinningRender skinningrender = (SkinningRender)cliententitiesmemory.objectrender;
//            cliententitiesmemory.objectrender.takeDefault((OpenGLSkinningMemory)cliententitiesmemory.objectrender.memory_object_array[1]);
//            cliententitiesmemory.objectrender.setDefault((OpenGLSkinningMemory)cliententitiesmemory.objectrender.memory_object_array[1]);

            for (int j = 0; j < i; ++j)
            {
                int[] int_array = this.index_int_array_arraylist.get(j);
                float[] float_array = this.float_array_arraylist.get(j);
                GL11.glPushMatrix();
                skinningentitiesrender.apply3DSkinningVec4(skinningentitiesrender.get3DSkinning(skinningrender, x, y, z, 0, 0, 0, int_array[0], int_array[1]));

                float[] c_mat4 = skinningentitiesrender.getMat43DSkinning(skinningrender, int_array[0], int_array[1]);
                float[] mat4 = new float[]
                {
                    c_mat4[0], c_mat4[4], c_mat4[8], 0,
                    c_mat4[1], c_mat4[5], c_mat4[9], 0,
                    c_mat4[2], c_mat4[6], c_mat4[10], 0,
                    0, 0, 0, 1.0F
                };
                OpenGLCurrentMemory.setFloatBuffer(mat4);
                GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                GL11.glMultMatrix(OpenGLCurrentMemory.OPENGL_FLOATBUFFER);
                GL11.glScalef(0.5F * 0.5F, 0.5F * 0.5F, 0.5F * 0.5F);
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
//                cliententitiesmemory.objectrender.setDefault((OpenGLSkinningMemory)cliententitiesmemory.objectrender.memory_object_array[1]);
            }
        }
    }
}
