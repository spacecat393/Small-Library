package com.nali.small.entity.memo.client.render.mix;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaO;
import com.nali.da.IBothDaS;
import com.nali.render.RenderS;
import com.nali.small.draw.DrawDa;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.IMixES;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.ci.MixCIE;
import com.nali.small.entity.memo.client.render.FRenderE;
import com.nali.small.render.IRenderS;
import com.nali.system.BothLoader;
import com.nali.system.opengl.memo.MemoF2;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public abstract class MixRenderSe
<
	BD extends IBothDaE & IBothDaO & IBothDaS,
	R extends RenderS<BD> & IRenderS<BD, R>,
	E extends Entity,
	I extends IMixE<BD, E> & IMixES,
	MC extends MixCIE<BD, R, E, I, MB, ?, C>,
	MB extends MixBoxE<BD, R, E, I, MC, ?, C>,
	C extends ClientE<BD, R, E, I, MC, MB, ?>
> extends MixRenderE<BD, R, E, I, MC, MB, C>
{
//	public float
//		head_rot,
//		head_pitch;

	public MixRenderSe(C c)
	{
		super(c);
	}

	@Override
	public void doRender(FRenderE<E> rendere, double ox, double oy, double oz, float partial_ticks)
	{
//		Nali.LOGGER.info("START");

		R r = this.c.r;
		E e = this.c.i.getE();

		GL11.glPushMatrix();

//		this.updateData(/*rendere, */partial_ticks);
		this.updateSkinning(partial_ticks);

		GL11.glTranslated(ox, oy, oz);

//		this.renderHitBox(rendere);

		GL11.glScalef(r.scale, r.scale, r.scale);
		rendere.setShadowOpaque(this.shadow_opaque * r.scale);
		rendere.setShadowSize(this.shadow_size * r.scale);

//		boolean invisible = e.isInvisible() || e.isInvisibleToPlayer(Minecraft.getMinecraft().player);
//		if (invisible)
//		{
//			OPENGL_FIXED_PIPE_FLOATBUFFER.limit(16);
//			GL11.glGetFloat(GL11.GL_CURRENT_COLOR, OPENGL_FIXED_PIPE_FLOATBUFFER);
//			GL_CURRENT_COLOR[0] = OPENGL_FIXED_PIPE_FLOATBUFFER.get(0);
//			GL_CURRENT_COLOR[1] = OPENGL_FIXED_PIPE_FLOATBUFFER.get(1);
//			GL_CURRENT_COLOR[2] = OPENGL_FIXED_PIPE_FLOATBUFFER.get(2);
//			GL_CURRENT_COLOR[3] = OPENGL_FIXED_PIPE_FLOATBUFFER.get(3);
//			GL11.glColor4f(GL_CURRENT_COLOR[0], GL_CURRENT_COLOR[1], GL_CURRENT_COLOR[2], 0.25F);
//		}
		GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
		r.updateLight(e.world, e.getPosition());
//		r.draw(/*ox, oy, oz*/);
		DrawDa drawda = new DrawDa();
		r.startDrawLater(this.c.i.getBD(), this.c.r, drawda);
//		if (/*e.isInvisible() || */e.isInvisibleToPlayer(Minecraft.getMinecraft().player))
//		{
////			if (drawda.color_v4_float[3] == 0)
////			{
////				drawda.color_v4_float[3] = 0.75F;
////			}
////			else
////			{
//			drawda.color_v4_float[3] *= 0.75F;
////			}
//		}
		r.endDrawLater(drawda);
//		if (invisible)
//		{
//			GL11.glColor4f(GL_CURRENT_COLOR[0], GL_CURRENT_COLOR[1], GL_CURRENT_COLOR[2], GL_CURRENT_COLOR[3]);
//		}

		GL11.glPopMatrix();
//		this.renderLayer(t, ox, oy, oz, partial_ticks);
	}

//	public void updateData(/*RenderE<E> rendere, */float partial_ticks)
//	{
//		E e = this.c.i.getE();
//		this.head_rot = (float)Math.toRadians(interpolateRotation(e.prevRotationYaw, e.rotationYaw, partial_ticks));
//		this.head_pitch = (float)Math.toRadians(e.prevRotationPitch + (e.rotationPitch - e.prevRotationPitch) * partial_ticks);
////		r.timeline = partial_ticks;
//
////		OpenGLAnimationMemory openglanimationmemory = r.dataloader.openglanimationmemory_list.get(((SkinningClientData)r.clientdata).AnimationID());
////		OpenGLAnimationMemory openglanimationmemory = (OpenGLAnimationMemory)r.dataloader.object_array[((SkinningClientData)r.clientdata).AnimationID()];
////		MemoAnimation memoanimation = r.rst.memoanimation_list.get(r.rc.AnimationID());
//	}

	public void updateSkinning(float partial_ticks)
	{
		R r = this.c.r;
		BD bd = this.c.i.getBD();
		MemoF2 f2 = BothLoader.F2_LIST.get(bd.S_FrameID());
		f2.initS(bd, r.skinning_float_array/*memoanimation*/);

//		if (!this.c.fake)
//		{
		this.c.i.mulFrame(r.skinning_float_array, r.key_short_array, partial_ticks);
//		}

		f2.setS(bd, r.skinning_float_array, r.key_short_array/*memoanimation*/);
	}

//	public void renderHitBox(FRenderE<E> rendere)
//	{
//	}

//	public abstract void multiplyAnimation(/*RenderE<E> rendere*/);
}
