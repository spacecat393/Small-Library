package com.nali.small.entity.memo.client.render.mix;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaO;
import com.nali.da.IBothDaS;
import com.nali.render.RenderS;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.IMixES;
import com.nali.small.entity.IMixESInv;
import com.nali.small.entity.memo.client.ClientLe;
import com.nali.small.entity.memo.client.box.mix.MixBoxSle;
import com.nali.small.entity.memo.client.ci.MixCIE;
import com.nali.small.entity.memo.client.render.FRenderE;
import com.nali.small.entity.memo.client.render.layer.LayerSleArrow;
import com.nali.small.render.IRenderS;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

//renew + layer
@SideOnly(Side.CLIENT)
public class MixRenderSle
<
	BD extends IBothDaE & IBothDaO & IBothDaS,
	R extends RenderS<BD> & IRenderS<BD, R>,
	E extends EntityLe,
	I extends IMixE<BD, E> & IMixES & IMixESInv,
	MC extends MixCIE<BD, R, E, I, MB, ?, C>,
	MB extends MixBoxSle<BD, R, E, I, MC, ?, C>,
	C extends ClientLe<BD, R, E, I, MC, MB, ?>
> extends MixRenderSe<BD, R, E, I, MC, MB, C>
{
	public LayerSleArrow<BD, R, E, I, MC, ?, MB, C> layerslearrow;
//	public LayerSleInvItem<R, BD, E, I, MC, ?, MB, C> layersleinvitem;

//	public float
//		body_rot,
//		net_head_yaw;

	public MixRenderSle(C c)
	{
		super(c);
		this.layerslearrow = new LayerSleArrow(c);
//		this.layersleinvitem = new LayerSleInvItem(c);
	}

//	@Override
//	public void updateData(float partial_ticks)
//	{
//		E e = this.c.i.getE();
//		this.body_rot = (float)Math.toRadians(interpolateRotation(e.prevRenderYawOffset, e.renderYawOffset, partial_ticks));
//		super.updateData(partial_ticks);
//		this.net_head_yaw = this.head_rot - this.body_rot;
//	}

	@Override
	public void doRender(FRenderE<E> rendere, double ox, double oy, double oz, float partial_ticks)
	{
		this.renderLayer(rendere/*, ox, oy, oz*/, partial_ticks);
		super.doRender(rendere, ox, oy, oz, partial_ticks);
	}

	public void renderLayer(FRenderE<E> rendere/*, double ox, double oy, double oz*/, float partial_ticks)
	{
		R r = this.c.r;
		GL11.glPushMatrix();

//		GL11.glTranslated(ox, oy, oz);
//		GL11.glScalef(r.scale, r.scale, r.scale);
//		GL11.glTranslated(-ox, -oy, -oz);
		RenderManager rendermanager = rendere.getRenderManager();
		double x = this.x - rendermanager.viewerPosX;
		double y = this.y - rendermanager.viewerPosY;
		double z = this.z - rendermanager.viewerPosZ;
		GL11.glTranslated(x, y, z);
		GL11.glScalef(r.scale, r.scale, r.scale);
		GL11.glTranslated(-x, -y, -z);
//		this.layersleinvitem.x = (float)ox;
//		this.layersleinvitem.y = (float)oy;
//		this.layersleinvitem.z = (float)oz;
//		this.layersleinvitem.layer(partial_ticks);
		this.layerslearrow.layer(rendere/*, (float)ox, (float)oy, (float)oz*/, partial_ticks);

		GL11.glPopMatrix();
	}

//	@Override
//	public void renderHitBox(FRenderE<E> rendere)
//	{
////		E e = this.c.i.getE();
////		if (rendere.getRenderManager().isDebugBoundingBox() && !e.isInvisible() && !Minecraft.getMinecraft().isReducedDebug())
////		{
////			GL11.glPushMatrix();
////
////			boolean gl_texture_2d = GL11.glIsEnabled(GL11.GL_TEXTURE_2D);
////			GL11.glDisable(GL11.GL_TEXTURE_2D);
////
////			boolean gl_blend = GL11.glIsEnabled(GL11.GL_BLEND);
////			GL11.glEnable(GL11.GL_BLEND);
////
////			GL11.glGetInteger(GL20.GL_BLEND_EQUATION_RGB, RenderO.INTBUFFER);
////			int gl_blend_equation_rgb = RenderO.INTBUFFER.get(0);
////			GL11.glGetInteger(GL20.GL_BLEND_EQUATION_ALPHA, OPENGL_INTBUFFER);
////			int gl_blend_equation_alpha = OPENGL_INTBUFFER.get(0);
////			GL20.glBlendEquationSeparate(GL14.GL_FUNC_ADD, GL14.GL_FUNC_ADD);
////
////			GL11.glGetInteger(GL14.GL_BLEND_SRC_RGB, OPENGL_INTBUFFER);
////			int gl_blend_src_rgb = OPENGL_INTBUFFER.get(0);
////			GL11.glGetInteger(GL14.GL_BLEND_SRC_ALPHA, OPENGL_INTBUFFER);
////			int gl_blend_src_alpha = OPENGL_INTBUFFER.get(0);
////			GL11.glGetInteger(GL14.GL_BLEND_DST_RGB, OPENGL_INTBUFFER);
////			int gl_blend_dst_rgb = OPENGL_INTBUFFER.get(0);
////			GL11.glGetInteger(GL14.GL_BLEND_DST_ALPHA, OPENGL_INTBUFFER);
////			int gl_blend_dst_alpha = OPENGL_INTBUFFER.get(0);
////			GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
////
////			GL11.glTranslated(-e.posX, -e.posY, -e.posZ);
////			Color color = generateRainbowColor();
////			float r = color.getRed() / 255.0F, g = color.getGreen() / 255.0F, b = color.getBlue() / 255.0F;
////
////			OPENGL_FIXED_PIPE_FLOATBUFFER.limit(16);
////			GL11.glGetFloat(GL11.GL_LINE_WIDTH, OPENGL_FIXED_PIPE_FLOATBUFFER);
////			GL_LINE_WIDTH = OPENGL_FIXED_PIPE_FLOATBUFFER.get(0);
////			GL11.glLineWidth(5.0F);
////
//////			AxisAlignedBB[] axisalignedbb_array = new AxisAlignedBB[]
//////			{
//////				t.getHeadAxisAlignedBB(),
//////				t.getMouthAxisAlignedBB()
//////			};
//////			for (AxisAlignedBB axisalignedbb : axisalignedbb_array)
//////			if (cliententitiesmemory.mixboxentitiesmemory.axisalignedbb_array != null)
//////			{
////			for (AxisAlignedBB axisalignedbb : this.c.mb.get()/*cliententitiesmemory.mixboxentitiesmemory.axisalignedbb_array*/)
////			{
////				RenderGlobal.drawSelectionBoundingBox(axisalignedbb, r, g, b, 1.0F);
////			}
//////			}
////
////			if (gl_blend)
////			{
////				GL11.glEnable(GL11.GL_BLEND);
////			}
////			else
////			{
////				GL11.glDisable(GL11.GL_BLEND);
////			}
////
////			GL20.glBlendEquationSeparate(gl_blend_equation_rgb, gl_blend_equation_alpha);
////			GL14.glBlendFuncSeparate(gl_blend_src_rgb, gl_blend_dst_rgb, gl_blend_src_alpha, gl_blend_dst_alpha);
////
////			GL11.glLineWidth(GL_LINE_WIDTH);
////
////			if (gl_texture_2d)
////			{
////				GL11.glEnable(GL11.GL_TEXTURE_2D);
////			}
////			else
////			{
////				GL11.glDisable(GL11.GL_TEXTURE_2D);
////			}
////
////			GL11.glPopMatrix();
////		}
//	}
}
