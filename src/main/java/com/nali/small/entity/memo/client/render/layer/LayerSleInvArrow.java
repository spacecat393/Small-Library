//package com.nali.small.entity.memo.client.render.layer;
//
//import com.nali.da.IBothDaNe;
//import com.nali.da.IBothDaSn;
//import com.nali.da.client.IClientDaS;
//import com.nali.render.RenderS;
//import com.nali.small.entity.IMixE;
//import com.nali.small.entity.IMixESoundDa;
//import com.nali.small.entity.memo.client.ClientLe;
//import com.nali.small.entity.memo.client.IClientERsInv;
//import com.nali.small.entity.memo.client.box.mix.MixBoxSleInv;
//import com.nali.small.entity.memo.client.ci.MixCIE;
//import com.nali.small.entity.memo.client.render.FRenderE;
//import com.nali.small.entity.memo.client.render.mix.MixRenderSe;
//import com.nali.sound.ISoundDaLe;
//import com.nali.system.opengl.memo.client.MemoA2;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.projectile.EntityTippedArrow;
//import net.minecraft.util.math.MathHelper;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import org.lwjgl.opengl.GL11;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//import static com.nali.system.ClientLoader.A2_MAP;
//
//@SideOnly(Side.CLIENT)
//public class LayerSleInvArrow
//<
//	RC extends IClientDaS,
//	R extends RenderS<BD, RC>,
//	SD extends ISoundDaLe,
//	BD extends IBothDaNe & IBothDaSn,
//	E extends EntityLivingBase,
//	I extends IMixE<BD, E> & IMixESoundDa<SD>,
//	MC extends MixCIE<RC, R, BD, E, I, MB, MR, C>,
//	MR extends MixRenderSe<RC, R, BD, E, I, MC, MB, C>,
//	MB extends MixBoxSleInv<RC, R, SD, BD, E, I, MC, MR, C>,
//	C extends ClientLe<RC, R, SD, BD, E, I, MC, MB, MR> & IClientERsInv
//> extends LayerE<RC, R, BD, E, I, MC, MR, MB, C>
//{
//	public List<int[]> index_int_array_list = new ArrayList();
//	public List<float[]> float_array_list = new ArrayList();
//
//	public LayerSleInvArrow(C c)
//	{
//		super(c);
//	}
//
//	public void layer(FRenderE<E> rendere, float x, float y, float z, float partialTicks)
//	{
//		E e = this.c.i.getE();
//		int i = e.getArrowCountInEntity();
//
//		if (i > 0)
//		{
//			Entity entity = new EntityTippedArrow(e.world, e.posX, e.posY, e.posZ);
//
//			R r = this.c.r;
//			if (this.index_int_array_list.size() < i)
//			{
//				Random random = entity.world.rand;
//				int start = r.rc.StartPart();
//				int model_i = random.nextInt(r.rc.EndPart() - start) + start;
////				OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)r.dataloader.openglobjectmemory_array[model_i];
////				OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)r.dataloader.object_array[model_i];
////				OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)OBJECT_LIST.get(model_i);
////				MemoG rg = this.c.r.rst.rg_list.get(model_i);
//				MemoA2 ra2 = A2_MAP.get(model_i);
////				this.index_int_array_list.add(new int[]{model_i, random.nextInt(openglskinningmemory.key_data_index.length)});
//				this.index_int_array_list.add(new int[]{model_i, random.nextInt(ra2.key_data_index.length)});
//				this.float_array_list.add(new float[]{random.nextFloat(), random.nextFloat(), random.nextFloat()});
//			}
//			else if (this.index_int_array_list.size() > i)
//			{
//				this.index_int_array_list.subList(i, this.index_int_array_list.size()).clear();
//				this.float_array_list.subList(i, this.float_array_list.size()).clear();
//			}
//
//			for (int j = 0; j < i; ++j)
//			{
//				int[] int_array = this.index_int_array_list.get(j);
//				float[] float_array = this.float_array_list.get(j);
//				GL11.glPushMatrix();
//				r.apply3DSkinningVec4(r.get3DSkinning(x, y, z, 0, 0, 0, int_array[0], int_array[1]));
//
//				float[] c_mat4 = r.getMat43DSkinning(int_array[0], int_array[1]);
//				float[] mat4 = new float[]
//				{
//					c_mat4[0], c_mat4[4], c_mat4[8], 0,
//					c_mat4[1], c_mat4[5], c_mat4[9], 0,
//					c_mat4[2], c_mat4[6], c_mat4[10], 0,
//					0, 0, 0, 1.0F
//				};
//				MemoC.setFloatBuffer(mat4);
//				GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
//				GL11.glMultMatrix(MemoC.OPENGL_FLOATBUFFER);
//				GL11.glScalef(0.5F * 0.5F, 0.5F * 0.5F, 0.5F * 0.5F);
////					RenderHelper.disableStandardItemLighting();
//				float f = float_array[0];
//				float f1 = float_array[1];
//				float f2 = float_array[2];
//				f = f * 2.0F - 1.0F;
//				f1 = f1 * 2.0F - 1.0F;
//				f2 = f2 * 2.0F - 1.0F;
//				f = f * -1.0F;
//				f1 = f1 * -1.0F;
//				f2 = f2 * -1.0F;
//				float f6 = MathHelper.sqrt(f * f + f2 * f2);
//				entity.rotationYaw = (float)(Math.atan2(f, f2) * (180D / Math.PI));
//				entity.rotationPitch = (float)(Math.atan2(f1, f6) * (180D / Math.PI));
//				entity.prevRotationYaw = entity.rotationYaw;
//				entity.prevRotationPitch = entity.rotationPitch;
//				rendere.getRenderManager().renderEntity(entity, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks, false);
////					RenderHelper.enableStandardItemLighting();
//				GL11.glPopMatrix();
//			}
//		}
//	}
//}
