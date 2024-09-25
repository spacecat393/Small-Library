package com.nali.small.gui.page;

import com.nali.list.container.gui.SmallGui;
import com.nali.list.data.SmallData;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.gui.key.KeyMenuMe;
import com.nali.small.gui.key.KeyMenuSI;
import com.nali.small.gui.mouse.MouseArmy;
import com.nali.small.gui.mouse.MouseSI;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.lang.reflect.InvocationTargetException;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static com.nali.Nali.error;
import static com.nali.list.container.gui.SmallGui.*;
import static com.nali.small.entity.memo.client.ClientE.C_MAP;
import static com.nali.small.gui.key.Key.KEY;
import static com.nali.small.gui.mouse.Mouse.MOUSE;
import static com.nali.small.gui.page.PageSakura.LEFT;
import static com.nali.small.gui.page.PageText.SEARCH_L;
import static com.nali.small.gui.page.PageText.STRINGBUFFER;
import static com.nali.system.ClientLoader.S_LIST;
import static com.nali.system.opengl.memo.client.MemoC.FULL_ARRAY_BUFFER;

@SideOnly(Side.CLIENT)
public class PageSI extends Page
{
	public static List<Integer>
	TEXTURE_INTEGER_LIST = new ArrayList(),
	ARRAY_BUFFER_INTEGER_LIST = new ArrayList();

	public static byte
		PAGE,
		BYTE = 1;

	public static int
		SIZE,
		A_W,
		A_H;
	public static float
		MAX_Y,
		MAX_Y_STAR;
	public static String STRING = "";
	public static long ID_KEY;

	public static List<Integer> INDEX_INTEGER_LIST = new ArrayList();
	public static int[] INDEX_INT_ARRAY;
	public static List<Byte> SEARCH_SI_BYTE_LIST = new ArrayList();

	public static float[][] M_VEC2_2D_FLOAT_ARRAY = new float[3][2];

	public PageSI(byte page)
	{
		PAGE = page;
		STRINGBUFFER = new StringBuffer(STRING);
	}

	public static void openPageSI()
	{
		PageMenu.BYTE |= 1;
		PAGE_ARRAY = new Page[]
		{
			new PageBlur(),
			new PageMenu(STRING_ARRAY[14] + ((KeyMenuMe.ME & 1) == 1 ? "|" + STRING_ARRAY[0] : "") + "|I" + (int)PageMe.ID_KEY + "D" + (int)(PageMe.ID_KEY >> 32) + "|" + STRING_ARRAY[1]),
			new PageSakura(),
			new PageKey(),
			new PageSI((byte)1)
		};
		KEY = new KeyMenuSI();
		MOUSE = new MouseSI();
		addSet();
	}

	@Override
	public void init()
	{
		String string = STRINGBUFFER.toString();
		if (!string.equals(STRING))
		{
			BYTE |= 1;
		}
		STRING = string;

		if ((BYTE & 1) == 1)
		{
			this.clear(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST);
			SEARCH_SI_BYTE_LIST.clear();

			Minecraft minecraft = SMALLGUI.mc;

			//s0-initCI
			Entity entity = Minecraft.getMinecraft().world.getEntityByID(PageMe.E_ID);
			if (entity == null)
			{
				PageMe.CLIENTE = C_MAP.get(PageMe.ID_KEY);
				try
				{
					Class clasz = EntityList.getClassFromID(PageMe.E_ID);
					PageMe.CLIENTE = (ClientE)clasz.getMethod("getC").invoke(null);
					PageMe.SI_BYTE_ARRAY = (byte[])clasz.getField("SI_BYTE_ARRAY").get(null);
				}
				catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException e)
				{
					error(e);
				}
			}
			else/* if (entity instanceof IMixE)*/
			{
				PageMe.CLIENTE = (ClientE)((IMixE)entity).getB();
				PageMe.SI_BYTE_ARRAY = PageMe.CLIENTE.i.getSI();
			}
			//

			SIZE = PageMe.SI_BYTE_ARRAY.length;
			float
				h2 = FONT_MH_SH + 2.0F * 0.005F * SmallGui.HEIGHT,
				h_offset_y = FONT_MH_SH + 4.0F * 0.005F * SmallGui.HEIGHT,
				x = SmallGui.WIDTH / 2.0F,
//				y = SmallGui.HEIGHT / 2.0F,
				y = SmallGui.HEIGHT,
				h_offset_y2 = h_offset_y * 2.0F;
			A_W = 0;
			String[] string_array = new String[SIZE];
			for (byte b = 0; b < SIZE; ++b)
			{
				byte si = PageMe.SI_BYTE_ARRAY[b];
				string = MouseSI.GI_CLASS_LIST.get(si).getSimpleName();
//				Nali.warn("si " + b + " " + string);
				string_array[b] = string;
				int new_max = (int)(minecraft.fontRenderer.getStringWidth(string) * FONT_SH);
				if (A_W < new_max)
				{
					A_W = new_max;
				}
			}
			x -= A_W / 2.0F;

			for (byte b = 0; b < SIZE; ++b)
			{
//				byte si = PageMe.SI_BYTE_ARRAY[b];
//				string = MouseSI.GI_CLASS_LIST.get(si).getSimpleName();
				string = string_array[b];
				int new_max = (int)(minecraft.fontRenderer.getStringWidth(string) * FONT_SH);

				y -= h2;

				this.initTextHorizontal
				(
					ARRAY_BUFFER_INTEGER_LIST,
					TEXTURE_INTEGER_LIST,
					string,
					new_max,
					FONT_MH_SH,
					x,
					y,
					FONT_SH
				);

				SEARCH_SI_BYTE_LIST.add(PageMe.SI_BYTE_ARRAY[b]);
			}
			//e0-initCI

			//s0-initScroll
			MAX_Y = ((h2 * ARRAY_BUFFER_INTEGER_LIST.size() - (SmallGui.HEIGHT - h_offset_y2)) / SmallGui.HEIGHT) * 2.0F;
			MAX_Y_STAR = ((SmallGui.HEIGHT - h_offset_y2 - FONT_MH_SH) / SmallGui.HEIGHT) * 2.0F;

			float ye = SmallGui.HEIGHT - (FONT_MH_SH + 4.0F * 0.005F * SmallGui.HEIGHT) * 2.0F;
			StringBuilder stringbuilder = new StringBuilder();

			int max_l = (int)(Math.ceil(ye / (FONT_MH * FONT_SH)));
			for (int i = 0; i < max_l; ++i)
			{
				stringbuilder.append("|");
			}

			this.initTextVertical
			(
				ARRAY_BUFFER_INTEGER_LIST,
				TEXTURE_INTEGER_LIST,
				stringbuilder.toString(),
				FONT_MH_SH,
				(int)(max_l * FONT_MH * FONT_SH),
				SmallGui.WIDTH -
					LEFT -
					FONT_MH_SH -
					6.0F * 0.005F * SmallGui.WIDTH,
				SmallGui.HEIGHT -
					FONT_MH_SH -
					4.0F * 0.005F * SmallGui.HEIGHT,
				FONT_SH
			);

			this.initTextVertical
			(
				ARRAY_BUFFER_INTEGER_LIST,
				TEXTURE_INTEGER_LIST,
				STRING_ARRAY[29],
				FONT_MH_SH,
				FONT_MH_SH,
				SmallGui.WIDTH -
					LEFT -
					FONT_MH_SH -
					6.0F * 0.005F * SmallGui.WIDTH,
				SmallGui.HEIGHT -
					FONT_MH_SH -
					4.0F * 0.005F * SmallGui.HEIGHT,
				FONT_SH
			);
			//e0-initScroll

			//s0-initSearch
			string = "________________" + STRING_ARRAY[28];
			SEARCH_L = minecraft.fontRenderer.getStringWidth(string) * FONT_SH;

			this.initTextHorizontal
			(
				ARRAY_BUFFER_INTEGER_LIST,
				TEXTURE_INTEGER_LIST,
				string,
				(int)SEARCH_L,
				FONT_MH_SH,
				SmallGui.WIDTH -
					SEARCH_L -
					2.0F * 0.005F * SmallGui.WIDTH,
				SmallGui.HEIGHT -
					FONT_MH_SH -
					h2,
				FONT_SH
			);
			//e0-initSearch

			BYTE &= 255-1;
		}
	}

	@Override
	public void detect()
	{
		if (ID_KEY == PageMe.ID_KEY)
		{
			BYTE |= 2;
		}
		else
		{
			ID_KEY = PageMe.ID_KEY;
			BYTE |= 1;
			BYTE &= 255-2;
		}

		if ((BYTE & 2) == 2)
		{
			MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 4);

			OpenGlHelper.glUseProgram(rs.program);
			int v = rs.attriblocation_int_array[0];
			GL20.glEnableVertexAttribArray(v);

			int size = ARRAY_BUFFER_INTEGER_LIST.size();

			float
				h_offset = FONT_MH_SH + 4.0F * 0.005F * SmallGui.HEIGHT,
				py = SmallGui.HEIGHT - (h_offset * 2.0F),
				y0 = FONT_MH_SH + 2.0F * 0.005F * SmallGui.HEIGHT,
				y = (MouseArmy.Y / 2.0F * SmallGui.HEIGHT + py) / y0;
			float
				max_y = y + 1.0F,
				min_y = (y - py / y0 - 0.5F);

			float offset = FONT_MH_SH + 4.0F * 0.005F * SmallGui.HEIGHT;

			A_H = (int)(SmallGui.HEIGHT - offset);

			INDEX_INTEGER_LIST.clear();
			M_VEC2_2D_FLOAT_ARRAY[0][1] = MouseSI.Y;

//			A_W = (int)(SmallGui.WIDTH / 2.0F - A_W);
			A_W = (int)(SmallGui.WIDTH/* / 2.0F*/);
			OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_FRAMEBUFFER_0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE_0);
			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, A_W, A_H, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE_0, 0);
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

			for (int i = 0; i < size - 3; ++i)
			{
				float last_id = ((i -(i - 1 * (i / 1 + 1)) - 1) / 1.0F);
				if ((last_id >= min_y && last_id <= max_y))
				{
					INDEX_INTEGER_LIST.add(i);
				}
			}
			int index_size = INDEX_INTEGER_LIST.size();
			INDEX_INT_ARRAY = new int[index_size];
			for (int x = 0; x < index_size; ++x)
			{
				int i = INDEX_INTEGER_LIST.get(x);
				int index = x + 3;
				INDEX_INT_ARRAY[x] = i;

				this.drawQuadVUv
				(
					rs,
					M_VEC2_2D_FLOAT_ARRAY[0],
					new float[]{index/ 255.0F, PAGE/255.0F, 0.0F, 1.0F},
					ARRAY_BUFFER_INTEGER_LIST.get(i),
					TEXTURE_INTEGER_LIST.get(i)
				);
			}

			GL11.glViewport(0, 0, A_W, A_H);

			OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_FRAMEBUFFER);
			M_VEC2_2D_FLOAT_ARRAY[2][1] = (FONT_MH_SH * 2.0F - 4.0F * 0.005F * SmallGui.HEIGHT) / SmallGui.HEIGHT;

			GL20.glDisableVertexAttribArray(v);
			rs = S_LIST.get(SmallData.SHADER_STEP + 3);
			OpenGlHelper.glUseProgram(rs.program);
			v = rs.attriblocation_int_array[0];
			GL20.glEnableVertexAttribArray(v);

			this.drawQuadVUv
			(
				rs,
				M_VEC2_2D_FLOAT_ARRAY[2],
				COLOR_VEC4_2D_FLOAT_ARRAY[0],
				FULL_ARRAY_BUFFER,
				OFFSET_FRAMEBUFFER_TEXTURE_0
			);

			GL20.glDisableVertexAttribArray(v);

			GL11.glViewport(0, 0, (int)SmallGui.WIDTH, (int)SmallGui.HEIGHT);
			rs = S_LIST.get(SmallData.SHADER_STEP + 4);

			OpenGlHelper.glUseProgram(rs.program);
			v = rs.attriblocation_int_array[0];
			GL20.glEnableVertexAttribArray(v);

			int index = size - 3;

			this.drawQuadVUv
			(
				rs,
				VEC2_FLOAT_ARRAY,
				COLOR_VEC4_2D_FLOAT_ARRAY[2],
				ARRAY_BUFFER_INTEGER_LIST.get(index),
				TEXTURE_INTEGER_LIST.get(index)
			);

			index = size - 1;

			this.drawQuadVUv
			(
				rs,
				VEC2_FLOAT_ARRAY,
				COLOR_VEC4_2D_FLOAT_ARRAY[1],
				ARRAY_BUFFER_INTEGER_LIST.get(index),
				TEXTURE_INTEGER_LIST.get(index)
			);

			GL20.glDisableVertexAttribArray(v);
		}
	}

	@Override
	public void preDraw()
	{
		if ((BYTE & 2) == 2)
		{
			//shadow->blur->predraw
			MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 3);
			int v = rs.attriblocation_int_array[0];
			OpenGlHelper.glUseProgram(rs.program);
			GL20.glEnableVertexAttribArray(v);

			int size = ARRAY_BUFFER_INTEGER_LIST.size();
			int index = size - 1;

			this.drawQuadVUv
			(
				rs,
				VEC2_FLOAT_ARRAY,
				//this.color_vec4_2d_float_array[3],
				COLOR_VEC4_2D_FLOAT_ARRAY[0],
				ARRAY_BUFFER_INTEGER_LIST.get(index),
				TEXTURE_INTEGER_LIST.get(index)
			);

			OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_FRAMEBUFFER);

			GL11.glBindTexture(GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE);
			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, A_W, A_H, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);

			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE, 0);
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

			for (int x = 0; x < INDEX_INTEGER_LIST.size(); ++x)
			{
				int i = INDEX_INTEGER_LIST.get(x);
//				float[] color_float_array;
//				color_float_array = MouseArmy.E_PAGE == PAGE && MouseArmy.HIT == x + 3 ? this.color_vec4_2d_float_array[4] : this.color_vec4_2d_float_array[3];

				this.drawQuadVUv
				(
					rs,
					M_VEC2_2D_FLOAT_ARRAY[0],
//					color_float_array,
					COLOR_VEC4_2D_FLOAT_ARRAY[0],
					ARRAY_BUFFER_INTEGER_LIST.get(i),
					TEXTURE_INTEGER_LIST.get(i)
				);
			}
			GL11.glViewport(0, 0, A_W, A_H);
			OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, GL_READ_FRAMEBUFFER_BINDING);
			OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER_BINDING);

			this.drawQuadVUv
			(
				rs,
				M_VEC2_2D_FLOAT_ARRAY[2],
				COLOR_VEC4_2D_FLOAT_ARRAY[0],
				FULL_ARRAY_BUFFER,
				OFFSET_FRAMEBUFFER_TEXTURE
			);

			GL11.glViewport(0, 0, (int)SmallGui.WIDTH, (int)SmallGui.HEIGHT);

			index = size - 3;

			this.drawQuadVUv
			(
				rs,
				VEC2_FLOAT_ARRAY,
//				this.color_vec4_2d_float_array[3],
				COLOR_VEC4_2D_FLOAT_ARRAY[0],
				ARRAY_BUFFER_INTEGER_LIST.get(index),
				TEXTURE_INTEGER_LIST.get(index)
			);

			M_VEC2_2D_FLOAT_ARRAY[1][1] = -MouseSI.Y_STAR;
			index = size - 2;

			this.drawQuadVUv
			(
				rs,
				M_VEC2_2D_FLOAT_ARRAY[1],
//				this.color_vec4_2d_float_array[3],
				COLOR_VEC4_2D_FLOAT_ARRAY[0],
				ARRAY_BUFFER_INTEGER_LIST.get(index),
				TEXTURE_INTEGER_LIST.get(index)
			);

			GL20.glDisableVertexAttribArray(v);
			//shadow
		}
	}

	@Override
	public void draw()
	{
		if ((BYTE & 2) == 2)
		{
			MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 3);
			OpenGlHelper.glUseProgram(rs.program);
			int v = rs.attriblocation_int_array[0];
			GL20.glEnableVertexAttribArray(v);

			OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_FRAMEBUFFER);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE);

			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, A_W, A_H, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE, 0);
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

			for (int x = 0; x < INDEX_INTEGER_LIST.size(); ++x)
			{
				int i = INDEX_INTEGER_LIST.get(x);
				this.drawQuadVUv
				(
					rs,
					M_VEC2_2D_FLOAT_ARRAY[0],
//					MouseArmy.E_PAGE == PAGE && MouseArmy.HIT == x + 3 ? this.color_vec4_2d_float_array[4] : COLOR_VEC4_2D_FLOAT_ARRAY[0],
					COLOR_VEC4_2D_FLOAT_ARRAY[0],
					ARRAY_BUFFER_INTEGER_LIST.get(i),
					TEXTURE_INTEGER_LIST.get(i)
				);
			}

			GL11.glViewport(0, 0, A_W, A_H);

			OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, GL_READ_FRAMEBUFFER_BINDING);
			OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER_BINDING);

			this.drawQuadVUv
			(
				rs,
				M_VEC2_2D_FLOAT_ARRAY[2],
				COLOR_VEC4_2D_FLOAT_ARRAY[0],
				FULL_ARRAY_BUFFER,
				OFFSET_FRAMEBUFFER_TEXTURE
			);

			GL11.glViewport(0, 0, (int)SmallGui.WIDTH, (int)SmallGui.HEIGHT);

			int size = ARRAY_BUFFER_INTEGER_LIST.size();

			int index = size - 1;

			this.drawQuadVUv
			(
				rs,
				VEC2_FLOAT_ARRAY,
				COLOR_VEC4_2D_FLOAT_ARRAY[0],
				ARRAY_BUFFER_INTEGER_LIST.get(index),
				TEXTURE_INTEGER_LIST.get(index)
			);

			index = size - 3;

			this.drawQuadVUv
			(
				rs,
				VEC2_FLOAT_ARRAY,
				COLOR_VEC4_2D_FLOAT_ARRAY[0],
				ARRAY_BUFFER_INTEGER_LIST.get(index),
				TEXTURE_INTEGER_LIST.get(index)
			);

			index = size - 2;

			this.drawQuadVUv
			(
				rs,
				M_VEC2_2D_FLOAT_ARRAY[1],
				COLOR_VEC4_2D_FLOAT_ARRAY[0],
				ARRAY_BUFFER_INTEGER_LIST.get(index),
				TEXTURE_INTEGER_LIST.get(index)
			);

			GL20.glDisableVertexAttribArray(v);
		}
	}
}
