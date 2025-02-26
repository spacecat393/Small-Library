package com.nali.small.entity.memo.client.render.layer;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaO;
import com.nali.da.IBothDaS;
import com.nali.render.RenderS;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.IMixESInv;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.ci.MixCIE;
import com.nali.small.entity.memo.client.render.FRenderE;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
import com.nali.system.BothLoader;
import com.nali.system.opengl.memo.MemoA2;
import com.nali.system.opengl.memo.MemoF2;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//not done yet
@SideOnly(Side.CLIENT)
public class LayerSleArrow
<
	BD extends IBothDaE & IBothDaO & IBothDaS,
	R extends RenderS<BD>,
	E extends EntityLivingBase,
	I extends IMixE<BD, E> & IMixESInv,
	MC extends MixCIE<BD, R, E, I, MB, MR, C>,
	MR extends MixRenderE<BD, R, E, I, MC, MB, C>,
	MB extends MixBoxE<BD, R, E, I, MC, MR, C>,
	C extends ClientE<BD, R, E, I, MC, MB, MR>
> extends LayerE<BD, R, E, I, MC, MR, MB, C>
{
	public static EntityTippedArrow ENTITYTIPPEDARROW = new EntityTippedArrow(null);
	public List<int[]> index_int_array_list = new ArrayList();
	public List<float[]> float_array_list = new ArrayList();

	public LayerSleArrow(C c)
	{
		super(c);
	}

	public void layer(FRenderE<E> rendere/*, float x, float y, float z*/, float partialTicks)
	{
		E e = this.c.i.getE();
		int i = e.getArrowCountInEntity();
//		int i = 5;

		if (i > 0)
		{
			ENTITYTIPPEDARROW.world = e.world;
//			Entity entity = new EntityTippedArrow(e.world, mr.x, mr.y, mr.z);

			R r = this.c.r;
			BD bd = this.c.i.getBD();

			MemoF2 bf2 = BothLoader.F2_LIST.get(bd.S_FrameID());
			for (int j = 0; j < i; ++j)
			{
				if (this.index_int_array_list.size() < i)
				{
					Random random = e.world.rand;
					int start = bd.O_StartPart();
					int model_i = random.nextInt(bd.O_EndPart() - start) + start;
//				OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)r.dataloader.openglobjectmemory_array[model_i];
//				OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)r.dataloader.object_array[model_i];
//				OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)OBJECT_LIST.get(model_i);
//				MemoG rg = this.c.r.rst.rg_list.get(model_i);
					MemoA2 ra2 = BothLoader.A2_MAP.get(model_i);
//				this.index_int_array_list.add(new int[]{model_i, random.nextInt(openglskinningmemory.key_data_index.length)});
					this.index_int_array_list.add(new int[]{model_i, random.nextInt(ra2.index_int_array.length)});
					this.float_array_list.add(new float[]{random.nextFloat(), random.nextFloat(), random.nextFloat()});
				}
				else if (this.index_int_array_list.size() > i)
				{
					this.index_int_array_list.subList(i, this.index_int_array_list.size()).clear();
					this.float_array_list.subList(i, this.float_array_list.size()).clear();
				}

				int[] int_array = this.index_int_array_list.get(j);
				float[] float_array = this.float_array_list.get(j);
				GL11.glPushMatrix();

//				MR mr = c.mr;
//				float[] sv4 = bf2.getSV4FloatArray(r.scale, r.skinning_float_array, (float)mr.x, (float)mr.y, (float)mr.z, 0, 0, 0, int_array[0], int_array[1]);
//				float[] sv4 = bf2.getSV4FloatArray(r.scale, r.skinning_float_array, 0, 0, 0, 0, 0, 0, int_array[0], int_array[1]);
				float[] sv4 = bf2.getSV4FloatArray(1.0F, r.skinning_float_array, 0, 0, 0, 0, 0, 0, int_array[0], int_array[1]);
				float sv4_3 = sv4[3];
				GL11.glTranslatef(sv4[0] / sv4_3, sv4[1] / sv4_3, sv4[2] / sv4_3);

//				float[] c_mat4 = bf2.getSM4X4FloatArray(r.skinning_float_array, int_array[0], int_array[1]);
//				float[] mat4 = new float[]
//				{
//					c_mat4[0], c_mat4[4], c_mat4[8], 0,
//					c_mat4[1], c_mat4[5], c_mat4[9], 0,
//					c_mat4[2], c_mat4[6], c_mat4[10], 0,
//					0, 0, 0, 1.0F
//				};
//				RenderO.FLOATBUFFER.clear();
//				RenderO.FLOATBUFFER.put(mat4);
//				RenderO.FLOATBUFFER.flip();
//				GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
//				GL11.glMultMatrix(RenderO.FLOATBUFFER);
//				GL11.glScalef(0.5F * 0.5F, 0.5F * 0.5F, 0.5F * 0.5F);
//					RenderHelper.disableStandardItemLighting();
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
				ENTITYTIPPEDARROW.rotationYaw = (float)(Math.atan2(f, f2) * (180D / Math.PI));
				ENTITYTIPPEDARROW.rotationPitch = (float)(Math.atan2(f1, f6) * (180D / Math.PI));
				ENTITYTIPPEDARROW.prevRotationYaw = ENTITYTIPPEDARROW.rotationYaw;
				ENTITYTIPPEDARROW.prevRotationPitch = ENTITYTIPPEDARROW.rotationPitch;
				rendere.getRenderManager().renderEntity(ENTITYTIPPEDARROW, 0, 0, 0, 0.0F, partialTicks, false);
//					RenderHelper.enableStandardItemLighting();
				GL11.glPopMatrix();
			}
		}
	}
}
