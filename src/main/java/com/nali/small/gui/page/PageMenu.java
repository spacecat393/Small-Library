package com.nali.small.gui.page;

import com.nali.list.data.SmallData;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL20;

import java.util.ArrayList;
import java.util.List;

import static com.nali.list.container.gui.SmallGui.*;
import static com.nali.system.ClientLoader.S_LIST;

@SideOnly(Side.CLIENT)
public class PageMenu extends Page
{
	public static List<Integer>
	TEXTURE_INTEGER_LIST = new ArrayList(),
	ARRAY_BUFFER_INTEGER_LIST = new ArrayList();

	public float[][] vec2_2d_float_array;
	public float[][] color_vec4_2d_float_array;

	public static String STRING;
	public static byte BYTE = 1;
	public static float
	/*X,*/
	Y,
	MAX_Y;

	public PageMenu(String string)
	{
		STRING = string;
		this.vec2_2d_float_array = new float[1][2];
		this.color_vec4_2d_float_array = new float[1][4];
		this.color_vec4_2d_float_array[0] = new float[]{1.0F, 0.5F, 0.5F, 1.0F};
	}

	@Override
	public void init()
	{
//		if ((BYTE & 1) == 1)
//		byte b = this.getByte();
//		if ((b & 1) == 1)
		if ((BYTE & 1) == 1)
		{
			Y = 0;

//			super.init();
			this.clear(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST);

			this.initPath(/*-1*/);

//			this.setByte((byte)(b & 255-1));
			BYTE &= 255-1;
		}
	}

//	@Override
//	public void preDraw()
//	{
//		byte sakura = SMALLGUI.mc.player.getEntityData().getByte("Nali_sakura");
//		if (SAKURA != sakura || (SMALLGUI.state & 2) == 2)
//		{
//			SMALLGUI.state |= 8;
////			int framebuffer = OpenGlHelper.glGenFramebuffers();
////			OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, framebuffer);
////
//////			GL11.glLoadIdentity();
//////			GL11.glOrtho(0.0D, width, height, 0.0D, 1000.0D, 3000.0D);
//////			GL11.glMatrixMode(GL11.GL_MODELVIEW);
////
//////			if (false)
//////			{
//////				this.drawPath(0);
//////			}
//////			if (SAKURA != sakura)
//////			{
//////			GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, OPENGL_INTBUFFER);
//////			GL_ARRAY_BUFFER_BINDING = OPENGL_INTBUFFER.get(0);
////
////			this.drawPath(0);
////			this.drawSakura(sakura, 1);
////
//////			OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, GL_ARRAY_BUFFER_BINDING);
//////			}
////
//////			GL11.glMatrixMode(GL11.GL_PROJECTION);
//////			GL11.glLoadMatrix(OPENGL_PROJECTION_MATRIX_FLOATBUFFER);
////
//////			GL11.glMatrixMode(GL_MATRIX_MODE);
////
////			OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, GL_READ_FRAMEBUFFER_BINDING);
////			OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER_BINDING);
////
////			OpenGlHelper.glDeleteFramebuffers(framebuffer);
////
////			Minecraft minecraft = SMALLGUI.mc;
////			GL11.glViewport(0, 0, minecraft.displayWidth, minecraft.displayHeight);
//			SMALLGUI.state &= 255-2;
//		}
//
////		super.preDraw();
//	}

	@Override
	public void draw()
	{
		MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 3);
		OpenGlHelper.glUseProgram(rs.program);
		int v = rs.attriblocation_int_array[0];
		GL20.glEnableVertexAttribArray(v);

		this.vec2_2d_float_array[0][1] = Y;
		this.drawQuadVUv(rs, this.vec2_2d_float_array[0], /*color_float_array == null ? */this.color_vec4_2d_float_array[0]/* : color_float_array*/, ARRAY_BUFFER_INTEGER_LIST.get(0), TEXTURE_INTEGER_LIST.get(0));
//		if ((SMALLGUI.state & 4) == 4)
//		{
//			BT = 5.0F;
//		}
		Y += 0.025F * SMALLGUI.partial_ticks;
		if (Y > MAX_Y)
		{
			Y = 0;
		}

		GL20.glDisableVertexAttribArray(v);
////		byte sakura = SMALLGUI.mc.player.getEntityData().getByte("Nali_sakura");
////		if (SAKURA != sakura)
////		{
////			FLAG |= 1;
////		}
//
//		this.draw(null);
	}

	@Override
	public void preDraw()
	{
//		this.draw(this.color_vec4_2d_float_array[3]);
		this.draw();
	}

	@Override
	public void detect()
	{
	}

//	@Override
//	public byte getByte()
//	{
//		return BYTE;
//	}
//
//	@Override
//	public void setByte(byte b)
//	{
//		BYTE = b;
//	}

//	@Override
//	public void change()
//	{
//
//	}

//	@Override
//	public List<Integer> getArrayBufferIntegerList()
//	{
//		return ARRAY_BUFFER_INTEGER_LIST;
//	}
//
//	@Override
//	public List<Integer> getTextureIntegerList()
//	{
//		return TEXTURE_INTEGER_LIST;
//	}

//	public void drawPlus(/*float[] color_float_array*/)
//	{
//		MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 3);
//		OpenGlHelper.glUseProgram(rs.program);
//		int v = rs.attriblocation_int_array[0];
//		GL20.glEnableVertexAttribArray(v);
//
//		this.vec2_2d_float_array[0][1] = Y;
//		this.drawQuadVUv(rs, this.vec2_2d_float_array[0], /*color_float_array == null ? */this.color_vec4_2d_float_array[0]/* : color_float_array*/, ARRAY_BUFFER_INTEGER_LIST.get(0), TEXTURE_INTEGER_LIST.get(0));
////		if ((SMALLGUI.state & 4) == 4)
////		{
////			BT = 5.0F;
////		}
//		Y += 0.025F * SMALLGUI.partial_ticks;
//		if (Y > MAX_Y)
//		{
//			Y = 0;
//		}
//
//		GL20.glDisableVertexAttribArray(v);
//	}

	public void initPath(/*int index*/)
	{
		Minecraft minecraft = SMALLGUI.mc;
		int display_height = minecraft.displayHeight;

//		String string = STRING_ARRAY[14];//+ " > ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		int i = (int)(MAX_TH * STRING.length() * SCALE);
//		box_height = i - display_height;
//		if (i < display_height)
//		{
//			box_height = i;
//		}
//		int max_w = 0;
//		for (char c : string.toCharArray())
//		{
//			int w = this.fontRenderer.getStringWidth("" + c);
//			if (max_w < w)
//			{
//				max_w = w;
//			}
//		}
//		max_w = (int)(max_w * SCALE);
//		MAX_Y = ((display_height + box_height) / (float)display_height) * 2.0F;
		MAX_Y = ((display_height + i) / (float)display_height) * 2.0F;
//		this.preDrawTextVertical(string, false, index, H, (int)(box_height * SCALE), 0.0F, 0.0F, SCALE);
//		this.preDrawTextVertical(string, true, index, max_w, box_height, (H - max_w) / 2.0F, 0.0F, SCALE);
//		LEFT = 2.0F * 0.005F * minecraft.displayWidth;
//		this.preDrawTextVertical(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, STRING/*, true*//*, index*/, H, box_height, 2.0F * 0.005F * minecraft.displayWidth, 0.0F, SCALE);
		this.initTextVertical(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, STRING/*, true*//*, index*/, H, i, 2.0F * 0.005F * minecraft.displayWidth, 0.0F, SCALE);
//		this.preDrawTextVertical(string, false, index, H, (int)(box_height * SCALE * 1.1F), 0.0F, -(SCALE * 1.1F)/* / 2.0F*/, SCALE * 1.1F);
//		TOP -= box_height;
//		TOP -= i;
	}

	public void initPlus()
	{

	}
}
