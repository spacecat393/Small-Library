package com.nali.small.entity.memo.client.render.mix;

import com.nali.da.IBothDaNe;
import com.nali.da.IBothDaSn;
import com.nali.da.client.IClientDaS;
import com.nali.render.RenderS;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.ci.MixCIE;
import com.nali.small.entity.memo.client.render.FRenderE;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import static com.nali.small.entity.memo.client.render.FRenderSeMath.interpolateRotation;
import static com.nali.system.opengl.memo.client.MemoC.GL_CURRENT_COLOR;
import static com.nali.system.opengl.memo.client.MemoC.OPENGL_FIXED_PIPE_FLOATBUFFER;

@SideOnly(Side.CLIENT)
public abstract class MixRenderSe<RC extends IClientDaS, R extends RenderS<BD, RC>, SD, BD extends IBothDaNe & IBothDaSn, E extends Entity, I extends IMixE<SD, BD, E>, MC extends MixCIE<RC, R, SD, BD, E, I, MB, ?, C>, MB extends MixBoxE<RC, R, SD, BD, E, I, MC, ?, C>, C extends ClientE<RC, R, SD, BD, E, I, MC, MB, ?>> extends MixRenderE<RC, R, SD, BD, E, I, MC, MB, C>
{
	public float
	head_rot,
	head_pitch;

	public MixRenderSe(C c)
	{
		super(c);
	}

	@Override
	public void doRender(FRenderE<E> rendere, double ox, double oy, double oz, float partialTicks)
	{
//		Nali.LOGGER.info("START");

		R r = this.c.r;
		E e = this.c.i.getE();

		GL11.glPushMatrix();

		this.updateData(/*rendere, */partialTicks);
		this.updateSkinning();

		GL11.glTranslated(ox, oy, oz);

		this.renderHitBox(rendere);

		GL11.glScalef(r.scale, r.scale, r.scale);
		rendere.setShadowOpaque(this.shadow_opaque * r.scale);
		rendere.setShadowSize(this.shadow_size * r.scale);

		boolean invisible = e.isInvisible() || e.isInvisibleToPlayer(Minecraft.getMinecraft().player);
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
		GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
		r.updateLightCoord(e.world, e.getPosition());
//		r.draw(/*ox, oy, oz*/);
		r.drawLater(/*ox, oy, oz*/);
		if (invisible)
		{
			GL11.glColor4f(GL_CURRENT_COLOR[0], GL_CURRENT_COLOR[1], GL_CURRENT_COLOR[2], GL_CURRENT_COLOR[3]);
		}

		GL11.glPopMatrix();
//		this.renderLayer(t, ox, oy, oz, partialTicks);
	}

	public void updateData(/*RenderE<E> rendere, */float partialTicks)
	{
		E e = this.c.i.getE();
		this.head_rot = (float)Math.toRadians(interpolateRotation(e.prevRotationYaw, e.rotationYaw, partialTicks));
		this.head_pitch = (float)Math.toRadians(e.prevRotationPitch + (e.rotationPitch - e.prevRotationPitch) * partialTicks);
//		r.timeline = partialTicks;

//		OpenGLAnimationMemory openglanimationmemory = r.dataloader.openglanimationmemory_list.get(((SkinningClientData)r.clientdata).AnimationID());
//		OpenGLAnimationMemory openglanimationmemory = (OpenGLAnimationMemory)r.dataloader.object_array[((SkinningClientData)r.clientdata).AnimationID()];
//		MemoAnimation memoanimation = r.rst.memoanimation_list.get(r.rc.AnimationID());
	}

	public void updateSkinning()
	{
		R r = this.c.r;
		r.initSkinning(/*memoanimation*/);

//		if (!this.c.fake)
//		{
		this.multiplyAnimation(/*rendere*/);
//		}

		r.setSkinning(/*memoanimation*/);
	}

	public void renderHitBox(FRenderE<E> rendere)
	{
	}

	public abstract void multiplyAnimation(/*RenderE<E> rendere*/);
}
