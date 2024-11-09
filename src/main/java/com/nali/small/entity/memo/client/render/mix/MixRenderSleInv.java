package com.nali.small.entity.memo.client.render.mix;

import com.nali.da.IBothDaNe;
import com.nali.da.IBothDaSn;
import com.nali.da.client.IClientDaS;
import com.nali.render.RenderS;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.inv.InvLe;
import com.nali.small.entity.memo.IBothEInv;
import com.nali.small.entity.memo.client.ClientLe;
import com.nali.small.entity.memo.client.IClientERsInv;
import com.nali.small.entity.memo.client.box.mix.MixBoxSleInv;
import com.nali.small.entity.memo.client.ci.MixCIE;
import com.nali.small.entity.memo.client.render.FRenderE;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import static com.nali.small.entity.memo.client.render.FRenderSeMath.interpolateRotation;

@SideOnly(Side.CLIENT)
public abstract class MixRenderSleInv
<
	IE extends InvLe,
	RC extends IClientDaS,
	R extends RenderS<BD, RC>,
	BD extends IBothDaNe & IBothDaSn,
	E extends EntityLivingBase,
	I extends IMixE<BD, E>,
	MC extends MixCIE<RC, R, BD, E, I, MB, ?, C>,
	MB extends MixBoxSleInv<RC, R, BD, E, I, MC, ?, C>,
	C extends ClientLe<RC, R, BD, E, I, MC, MB, ?> & IClientERsInv & IBothEInv<IE>
> extends MixRenderSe<RC, R, BD, E, I, MC, MB, C>
{
//	public LayerSleInvArrow<RC, R, SD, BD, E, I, MC, ?, MB, C> layersleinvarrow;
//	public LayerSleInvItem<IE, RC, R, SD, BD, E, I, MC, ?, MB, C> layersleinvitem;

	public float
	body_rot,
	net_head_yaw;

	public MixRenderSleInv(C c)
	{
		super(c);
//		this.layersleinvarrow = new LayerSleInvArrow(c);
//		this.layersleinvitem = new LayerSleInvItem(c);
	}

	@Override
	public void updateData(float partialTicks)
	{
		E e = this.c.i.getE();
		this.body_rot = (float)Math.toRadians(interpolateRotation(e.prevRenderYawOffset, e.renderYawOffset, partialTicks));
		super.updateData(partialTicks);
		this.net_head_yaw = this.head_rot - this.body_rot;
	}

	@Override
	public void doRender(FRenderE<E> rendere, double ox, double oy, double oz, float partialTicks)
	{
		this.renderLayer(rendere, ox, oy, oz, partialTicks);
		super.doRender(rendere, ox, oy, oz, partialTicks);
	}

	public void renderLayer(FRenderE<E> rendere, double ox, double oy, double oz, float partialTicks)
	{
		R r = this.c.r;
		GL11.glPushMatrix();

		GL11.glTranslated(ox, oy, oz);
		GL11.glScalef(r.scale, r.scale, r.scale);
		GL11.glTranslated(-ox, -oy, -oz);
//		this.layersleinvitem.x = (float)ox;
//		this.layersleinvitem.y = (float)oy;
//		this.layersleinvitem.z = (float)oz;
//		this.layersleinvitem.layer(partialTicks);
//		this.layersleinvarrow.layer(rendere, (float)ox, (float)oy, (float)oz, partialTicks);

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
