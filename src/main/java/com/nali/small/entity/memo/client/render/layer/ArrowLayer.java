package com.nali.small.entity.memo.client.render.layer;

import com.nali.render.SkinningRender;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.client.ClientSle;
import com.nali.small.entity.memo.client.render.SkinningEntitiesRender;
import com.nali.system.opengl.memory.OpenGLCurrentMemory;
import com.nali.system.opengl.memory.OpenGLSkinningMemory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.nali.system.ClientLoader.OBJECT_LIST;

@SideOnly(Side.CLIENT)
public class ArrowLayer<R extends SkinningRender, E extends EntityLivingBase, I extends IMixLe<E>, C extends ClientSle<R, E, I>> extends LayerRender<R, E, I, C>
{
    public List<int[]> index_int_array_list = new ArrayList();
    public List<float[]> float_array_list = new ArrayList();

    public ArrowLayer(C c)
    {
        super(c);
    }

    public void layer(SkinningEntitiesRender skinningentitiesrender, float x, float y, float z, float partialTicks)
    {
        E e = this.c.i.getSelf();
        int i = e.getArrowCountInEntity();

        if (i > 0)
        {
            Entity entity = new EntityTippedArrow(e.world, e.posX, e.posY, e.posZ);

            SkinningRender skinningrender = this.c.getR();
            if (this.index_int_array_list.size() < i)
            {
                Random random = entity.world.rand;
                int start = skinningrender.clientdata.StartPart();
                int model_i = random.nextInt(skinningrender.clientdata.EndPart() - start) + start;
//                OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)skinningrender.dataloader.openglobjectmemory_array[model_i];
//                OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)skinningrender.dataloader.object_array[model_i];
                OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)OBJECT_LIST.get(model_i);
                this.index_int_array_list.add(new int[]{model_i, random.nextInt(openglskinningmemory.index_int_array.length)});
                this.float_array_list.add(new float[]{random.nextFloat(), random.nextFloat(), random.nextFloat()});
            }
            else if (this.index_int_array_list.size() > i)
            {
                this.index_int_array_list.subList(i, this.index_int_array_list.size()).clear();
                this.float_array_list.subList(i, this.float_array_list.size()).clear();
            }

            for (int j = 0; j < i; ++j)
            {
                int[] int_array = this.index_int_array_list.get(j);
                float[] float_array = this.float_array_list.get(j);
                GL11.glPushMatrix();
                skinningrender.apply3DSkinningVec4(skinningrender.get3DSkinning(x, y, z, 0, 0, 0, int_array[0], int_array[1]));

                float[] c_mat4 = skinningrender.getMat43DSkinning(int_array[0], int_array[1]);
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
            }
        }
    }
}
