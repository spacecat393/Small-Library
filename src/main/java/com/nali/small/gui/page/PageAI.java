package com.nali.small.gui.page;

import com.nali.list.data.SmallData;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.gui.key.KeyMenuAI;
import com.nali.small.gui.key.KeyMenuMe;
import com.nali.small.gui.mouse.MouseAI;
import com.nali.small.gui.mouse.MouseArmy;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static com.nali.list.container.gui.SmallGui.*;
import static com.nali.small.entity.memo.client.ClientE.C_MAP;
import static com.nali.small.gui.key.Key.KEY;
import static com.nali.small.gui.mouse.Mouse.MOUSE;
import static com.nali.small.gui.mouse.MouseArmy.Y;
import static com.nali.small.gui.mouse.MouseArmy.Y_STAR;
import static com.nali.small.gui.page.PageSakura.LEFT;
import static com.nali.small.gui.page.PageText.SEARCH_L;
import static com.nali.small.gui.page.PageText.STRINGBUFFER;
import static com.nali.system.ClientLoader.S_LIST;
import static com.nali.system.opengl.memo.client.MemoC.FULL_ARRAY_BUFFER;

@SideOnly(Side.CLIENT)
public class PageAI extends Page
{
	public static List<Integer>
	TEXTURE_INTEGER_LIST = new ArrayList(),
	ARRAY_BUFFER_INTEGER_LIST = new ArrayList();

	public float[][] vec2_2d_float_array;
	public float[][] color_vec4_2d_float_array;

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

	public static List<Integer> INDEX_INTEGER_LIST = new ArrayList();
	public static int[] INDEX_INT_ARRAY;
	public static List<Byte> SEARCH_AI_BYTE_LIST = new ArrayList();

	public PageAI(byte page)
	{
		PAGE = page;
		this.vec2_2d_float_array = new float[1][2];
		this.color_vec4_2d_float_array = new float[6][4];
		this.color_vec4_2d_float_array[0] = new float[]{1.0F, 1.0F, 1.0F, 1.0F};
		this.color_vec4_2d_float_array[1] = new float[]{0.5F, 1.0F, 0.5F, 1.0F};

		this.color_vec4_2d_float_array[2] = new float[]{0.0F, 0.0F, 0.0F, 1.0F};
	}

	public static void openPageAI()
	{
		PageMenu.BYTE |= 1;
		STRINGBUFFER = new StringBuffer(STRING);
		PAGE_ARRAY = new Page[]
		{
			new PageBlur(),
			new PageMenu(STRING_ARRAY[14] + ((KeyMenuMe.ME & 1) == 1 ? "|" + STRING_ARRAY[0] : "") + "|" + PageMe.UUID + "|" + STRING_ARRAY[1]),
			new PageSakura(),
			new PageKey(),
			new PageAI((byte)1)
		};
		KEY = new KeyMenuAI();
		MOUSE = new MouseAI();
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
			this.initC();
			BYTE &= 255-1;
		}
	}

	@Override
	public void draw()
	{
		if ((BYTE & 2) == 2)
		{
			Minecraft minecraft = SMALLGUI.mc;

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
				this.drawQuadVUv(rs, this.vec2_2d_float_array[0], MouseArmy.E_PAGE == PAGE && MouseArmy.HIT == x + 3 ? this.color_vec4_2d_float_array[4] : this.color_vec4_2d_float_array[0], ARRAY_BUFFER_INTEGER_LIST.get(i), TEXTURE_INTEGER_LIST.get(i));
			}

			GL11.glViewport(0, 0, A_W, A_H);

			OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, GL_READ_FRAMEBUFFER_BINDING);
			OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER_BINDING);

			this.drawQuadVUv(rs, this.vec2_2d_float_array[3], this.color_vec4_2d_float_array[0], FULL_ARRAY_BUFFER, OFFSET_FRAMEBUFFER_TEXTURE);

			GL11.glViewport(0, 0, minecraft.displayWidth, minecraft.displayHeight);

			int size = ARRAY_BUFFER_INTEGER_LIST.size();

			int index = size - 1;
			this.drawQuadVUv(rs, this.vec2_2d_float_array[1], this.color_vec4_2d_float_array[0], ARRAY_BUFFER_INTEGER_LIST.get(index), TEXTURE_INTEGER_LIST.get(index));

			index = size - 3;
			this.drawQuadVUv(rs, this.vec2_2d_float_array[1], this.color_vec4_2d_float_array[0], ARRAY_BUFFER_INTEGER_LIST.get(index), TEXTURE_INTEGER_LIST.get(index));
			index = size - 2;
			this.drawQuadVUv(rs, this.vec2_2d_float_array[2], this.color_vec4_2d_float_array[0], ARRAY_BUFFER_INTEGER_LIST.get(index), TEXTURE_INTEGER_LIST.get(index));

			GL20.glDisableVertexAttribArray(v);
		}
	}

	@Override
	public void preDraw()
	{
		if ((BYTE & 2) == 2)
		{
			//shadow->blur->predraw
			Minecraft minecraft = SMALLGUI.mc;
			MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 3);
			int v = rs.attriblocation_int_array[0];
			OpenGlHelper.glUseProgram(rs.program);
			GL20.glEnableVertexAttribArray(v);

			int size = ARRAY_BUFFER_INTEGER_LIST.size();
			int index = size - 1;
			this.drawQuadVUv(rs, this.vec2_2d_float_array[1], this.color_vec4_2d_float_array[3], ARRAY_BUFFER_INTEGER_LIST.get(index), TEXTURE_INTEGER_LIST.get(index));

			OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_FRAMEBUFFER);

			GL11.glBindTexture(GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE);
			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, A_W, A_H, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);

			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE, 0);
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

			for (int x = 0; x < INDEX_INTEGER_LIST.size(); ++x)
			{
				int i = INDEX_INTEGER_LIST.get(x);
				float[] color_float_array;

				color_float_array = MouseArmy.E_PAGE == PAGE && MouseArmy.HIT == x + 3 ? this.color_vec4_2d_float_array[4] : this.color_vec4_2d_float_array[3];

				this.drawQuadVUv(rs, this.vec2_2d_float_array[0], color_float_array, ARRAY_BUFFER_INTEGER_LIST.get(i), TEXTURE_INTEGER_LIST.get(i));
			}
			GL11.glViewport(0, 0, A_W, A_H);
			OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, GL_READ_FRAMEBUFFER_BINDING);
			OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER_BINDING);

			this.drawQuadVUv(rs, this.vec2_2d_float_array[3], this.color_vec4_2d_float_array[0], FULL_ARRAY_BUFFER, OFFSET_FRAMEBUFFER_TEXTURE);
			GL11.glViewport(0, 0, minecraft.displayWidth, minecraft.displayHeight);

			index = size - 3;
			this.drawQuadVUv(rs, this.vec2_2d_float_array[1], this.color_vec4_2d_float_array[3], ARRAY_BUFFER_INTEGER_LIST.get(index), TEXTURE_INTEGER_LIST.get(index));
			this.vec2_2d_float_array[2][1] = -Y_STAR;
			index = size - 2;
			this.drawQuadVUv(rs, this.vec2_2d_float_array[2], this.color_vec4_2d_float_array[3], ARRAY_BUFFER_INTEGER_LIST.get(index), TEXTURE_INTEGER_LIST.get(index));
			GL20.glDisableVertexAttribArray(v);
			//shadow
		}
	}

	@Override
	public void detect()
	{
		if (SIZE != C_MAP.size())
		{
			BYTE |= 1;
		}

		if (SIZE > 0 && SIZE == C_MAP.size())
		{
			BYTE |= 2;
		}
		else
		{
			BYTE &= 255-2;
		}

		if ((BYTE & 2) == 2)
		{
			MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 4);

			OpenGlHelper.glUseProgram(rs.program);
			int v = rs.attriblocation_int_array[0];
			GL20.glEnableVertexAttribArray(v);

			int size = ARRAY_BUFFER_INTEGER_LIST.size();

			Minecraft minecraft = SMALLGUI.mc;
			float
				display_width = minecraft.displayWidth,
				display_height = SMALLGUI.mc.displayHeight,
				h_offset = H + 4.0F * 0.005F * display_height,
				py = display_height - (h_offset * 2.0F),
				y0 = H + 2.0F * 0.005F * display_height,
				y = (MouseArmy.Y / 2.0F * display_height + py) / y0;
			float
				max_y = y + 1.0F,
				min_y = (y - py / y0 - 0.5F);

			float offset = H + 4.0F * 0.005F * display_height;

			A_H = (int)(display_height - offset);

			INDEX_INTEGER_LIST.clear();
			this.vec2_2d_float_array[0][1] = Y;

			A_W = (int)(display_width / 2.0F - A_W);
			OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_FRAMEBUFFER_0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE_0);
			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, A_W, A_H, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE_0, 0);
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

			for (int i = 0; i < size - 3; ++i)
			{
//				float last_id = ((i -(i - 7 * (i / 7 + 1)) - 1) / 7.0F);
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

				this.drawQuadVUv(rs, this.vec2_2d_float_array[0], new float[]{index/ 255.0F, PAGE/255.0F, 0.0F, 1.0F}, ARRAY_BUFFER_INTEGER_LIST.get(i), TEXTURE_INTEGER_LIST.get(i));
			}

			int displayHeight = minecraft.displayHeight;
			int displayWidth = minecraft.displayWidth;

			GL11.glViewport(0, 0, A_W, A_H);

			OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_FRAMEBUFFER);
			this.vec2_2d_float_array[3][1] = (H * 2.0F - 4.0F * 0.005F * display_height) / display_height;

			GL20.glDisableVertexAttribArray(v);
			rs = S_LIST.get(SmallData.SHADER_STEP + 3);
			OpenGlHelper.glUseProgram(rs.program);
			v = rs.attriblocation_int_array[0];
			GL20.glEnableVertexAttribArray(v);

			this.drawQuadVUv(rs, this.vec2_2d_float_array[3], this.color_vec4_2d_float_array[0], FULL_ARRAY_BUFFER, OFFSET_FRAMEBUFFER_TEXTURE_0);
			GL20.glDisableVertexAttribArray(v);

			GL11.glViewport(0, 0, displayWidth, displayHeight);
			rs = S_LIST.get(SmallData.SHADER_STEP + 4);

			OpenGlHelper.glUseProgram(rs.program);
			v = rs.attriblocation_int_array[0];
			GL20.glEnableVertexAttribArray(v);

			int index = size - 3;
			this.drawQuadVUv(rs, this.vec2_2d_float_array[1], this.color_vec4_2d_float_array[1], ARRAY_BUFFER_INTEGER_LIST.get(index), TEXTURE_INTEGER_LIST.get(index));

			index = size - 1;
			this.drawQuadVUv(rs, this.vec2_2d_float_array[1], this.color_vec4_2d_float_array[2], ARRAY_BUFFER_INTEGER_LIST.get(index), TEXTURE_INTEGER_LIST.get(index));

			GL20.glDisableVertexAttribArray(v);
		}
	}

	public void initC()
	{
		SEARCH_AI_BYTE_LIST.clear();

		ClientE c = C_MAP.get(PageMe.UUID);
		byte[] ai_byte_array = c.i.getSI();
		SIZE = ai_byte_array.length;
		Minecraft minecraft = SMALLGUI.mc;
		int
			display_width = minecraft.displayWidth,
			display_height = minecraft.displayHeight;
		float
			h2 = H + 2.0F * 0.005F * display_height,
			h_offset_y = H + 4.0F * 0.005F * display_height,
			x = display_width / 2.0F,
			y = display_height / 2.0F,
			h_offset_y2 = h_offset_y * 2.0F;
		A_W = 0;
		String[] string_array = new String[SIZE];
		for (byte b = 0; b < SIZE; ++b)
		{
			byte ai = ai_byte_array[b];
			String string = MouseAI.GI_CLASS_LIST.get(ai).getSimpleName();
			string_array[b] = string;
			int new_max = (int)(minecraft.fontRenderer.getStringWidth(string) * SCALE);
			if (A_W < new_max)
			{
				A_W = new_max;
			}
		}
		x -= A_W / 2.0F;

		for (byte b = 0; b < SIZE; ++b)
		{
			this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string_array[b], A_W, H, x, y, SCALE);
			y -= h2;

			SEARCH_AI_BYTE_LIST.add(ai_byte_array[b]);
		}

		MAX_Y = ((h2 * ARRAY_BUFFER_INTEGER_LIST.size() - (display_height - h_offset_y2)) / display_height) * 2.0F;
		MAX_Y_STAR = ((display_height - h_offset_y2 - H) / display_height) * 2.0F;

		float ye = display_height - (H + 4.0F * 0.005F * display_height) * 2.0F;
		StringBuilder stringbuilder = new StringBuilder();

		int max_l = (int)(Math.ceil(ye / (MAX_TH * SCALE)));
		for (int i = 0; i < max_l; ++i)
		{
			stringbuilder.append("|");
		}

		this.initTextVertical(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, stringbuilder.toString(), H, (int)(max_l * MAX_TH * SCALE), display_width - LEFT - H - 6.0F * 0.005F * display_width, display_height - H - 4.0F * 0.005F * display_height, SCALE);
		this.initTextVertical(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, STRING_ARRAY[29], H, H, display_width - LEFT - H - 6.0F * 0.005F * display_width, display_height - H - 4.0F * 0.005F * display_height, SCALE);

		//search
		String string = "________________" + STRING_ARRAY[28];
		SEARCH_L = minecraft.fontRenderer.getStringWidth(string) * SCALE;

		this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string, (int)SEARCH_L, H, display_width - SEARCH_L - 2.0F * 0.005F * display_width, display_height - H - h2, SCALE);
	}
}
