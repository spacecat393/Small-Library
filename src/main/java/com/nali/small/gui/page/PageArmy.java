package com.nali.small.gui.page;

import com.nali.list.container.gui.SmallGui;
import com.nali.list.data.SmallData;
import com.nali.list.network.method.client.CSToC;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.client.ClientE;
import com.nali.small.gui.key.KeyMenuArmy;
import com.nali.small.gui.mouse.MouseArmy;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.nali.list.container.gui.SmallGui.*;
import static com.nali.small.entity.memo.client.ClientE.C_MAP;
import static com.nali.small.gui.key.Key.KEY;
import static com.nali.small.gui.mouse.Mouse.MOUSE;
import static com.nali.system.ClientLoader.S_LIST;
import static com.nali.system.opengl.memo.client.MemoC.FULL_ARRAY_BUFFER;

@SideOnly(Side.CLIENT)
public class PageArmy extends Page
{
	public static List<Integer>
		TEXTURE_INTEGER_LIST = new ArrayList(),
		ARRAY_BUFFER_INTEGER_LIST = new ArrayList();
	public static int
		SIZE,

		A_W,
		A_H;
	public static float
		MAX_Y,
		MAX_Y_STAR;
	public static List<Integer> INDEX_INTEGER_LIST = new ArrayList();
	public static List<Long> SEARCH_ID_KEY_LIST = new ArrayList();
	public static int[] INDEX_INT_ARRAY;
	public static byte
		PAGE,
		BYTE = 1;//init lock
	public static String STRING = "";

	public static float[][] M_VEC2_2D_FLOAT_ARRAY = new float[3][2];
	public static float[][] C_COLOR_VEC4_2D_FLOAT_ARRAY;

	public PageArmy(byte page)
	{
		PAGE = page;
		PageText.STRINGBUFFER = new StringBuffer(STRING);

		C_COLOR_VEC4_2D_FLOAT_ARRAY = new float[][]
		{
			new float[]{1.0F/255.0F, PAGE/255.0F, 0.0F, 1.0F}, //0
			new float[]{2.0F/255.0F, PAGE/255.0F, 0.0F, 1.0F} //1
		};
	}

	public static void openPageArmy()
	{
		PageMenu.BYTE |= 1;
		if (PageKeyArmy.ARRAY_BUFFER_INTEGER_LIST.size() != 2)
		{
			PageKeyArmy.BYTE |= 1;
		}

		PAGE_ARRAY = new Page[]
		{
			new PageBlur(),
			new PageMenu(STRING_ARRAY[14] + "|" + STRING_ARRAY[0]),
			new PageSakura(),
			new PageKeyArmy(),
			new PageArmy((byte)1),
			new PageText()
		};
		KEY = new KeyMenuArmy();
		MOUSE = new MouseArmy();
		addSet();
	}

	@Override
	public void init()
	{
		String string = PageText.STRINGBUFFER.toString();
		if (!string.equals(STRING))
		{
			BYTE |= 1;
			CSToC.STATE |= 4;
		}
		STRING = string;

		if ((BYTE & 1) == 1 && (CSToC.STATE & 1+4) != 0)
		{
			CSToC.STATE &= 255-1;

			this.clear(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST);
			//s0-initC
			SIZE = C_MAP.size();
			SEARCH_ID_KEY_LIST.clear();
			Minecraft minecraft = SMALLGUI.mc;
			float scale = 75.0F, text_scale = FONT_SH / 2.0F;
			int
				text_height = (int)(FONT_MH * text_scale),
				width = (int)(scale / Page.SCREEN_RW),
				id = 0;
			float
				h2 = 2.0F * 0.005F * SmallGui.HEIGHT,
				h_offset_y = FONT_MH_SH +
					4.0F * 0.005F * SmallGui.HEIGHT,
				height_f = scale / Page.SCREEN_RH,
				h_offset = FONT_MH_SH/* + h2*/ - h2,
				x = PageSakura.LEFT +
					FONT_MH_SH +
					6.0F * 0.005F * SmallGui.WIDTH,
				x_offset = x + scale / Page.SCREEN_RW + 2.0F * 0.005F * SmallGui.WIDTH,
				y = SmallGui.HEIGHT - scale / Page.SCREEN_RH - 4.0F * 0.005F * SmallGui.HEIGHT - FONT_MH_SH - h_offset,
				h_offset_y2 = h_offset_y * 2.0F;

			int height = (int)height_f;

			Set<Long> keys_set = new HashSet(C_MAP.keySet());
			for (long id_key : keys_set)
			{
				ClientE c = C_MAP.get(id_key);

				if (c != null)
				{
					String[] string_array = new String[4];

					IMixE i = c.i;
					string_array[1] = "";

					if (i == null)
					{
						string_array[0] = id + " " + c.name_string;

						if (c.hp > 0)
						{
							//sync
							if (false)//negative
							{
								string_array[1] += STRING_ARRAY[21];
							}
							else if (false)//teamup
							{
								string_array[1] += STRING_ARRAY[17];
							}
							else
							{
								string_array[1] += STRING_ARRAY[22];
							}
						}
						else
						{
							string_array[1] += STRING_ARRAY[20];
						}

						if ((c.state & 1) == 1)
						{
							string_array[1] += " " + STRING_ARRAY[25];
						}

						string_array[2] = DimensionManager.getProviderType((int)(c.key >> 32)).getName();
						string_array[3] = "X " + String.format("%.4f", c.x) + " Y " + String.format("%.4f", c.y) + " Z " + String.format("%.4f", c.z);
					}
					else
					{
						Entity e = i.getE();
						if (e.isEntityAlive())
						{
							//sync
							if (false)//negative
							{
								string_array[1] += STRING_ARRAY[21];
							}
							else if (false)//teamup
							{
								string_array[1] += STRING_ARRAY[17];
							}
							else
							{
								string_array[1] += STRING_ARRAY[22];
							}
						}
						else
						{
							string_array[1] += STRING_ARRAY[20];
						}

						if (false)
						{
							string_array[1] += " " + STRING_ARRAY[25];
						}

						string_array[0] = id + " " + e.getName()/* + " "*/;
						string_array[2] = e.world.provider.getDimensionType().getName();
						string_array[3] = "X " + String.format("%.4f", e.posX) + " Y " + String.format("%.4f", e.posY) + " Z " + String.format("%.4f", e.posZ);
					}
					++id;

					boolean out = false;
					for (String text_string : string_array)
					{
						if (text_string.contains(PageText.STRINGBUFFER.toString()))
						{
							out = false;
							break;
						}
						else
						{
							out = true;
						}
					}
					if (out)
					{
						continue;
					}

					SEARCH_ID_KEY_LIST.add(id_key);

					ByteBuffer bytebuffer = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder());
					bytebuffer.put((byte)0);
					bytebuffer.put((byte)0);
					bytebuffer.put((byte)0);
					bytebuffer.put((byte)(255/4.0F));
					bytebuffer.flip();
					this.initBox
					(
						ARRAY_BUFFER_INTEGER_LIST,
						TEXTURE_INTEGER_LIST,
						x,
						y,
						(int)(SmallGui.WIDTH -
							PageSakura.LEFT -
							FONT_MH_SH -
							8.0F * 0.005F * SmallGui.WIDTH -
							x),
						(int)(height / 2.0F),
						bytebuffer
					);

					this.initModel
					(
						ARRAY_BUFFER_INTEGER_LIST,
						TEXTURE_INTEGER_LIST,
						c,
						width,
						height,
						x,
						y,
						-scale
					);

					string = string_array[0];
					int l = (int)(minecraft.fontRenderer.getStringWidth(string) * FONT_SH);

					this.initTextHorizontal
					(
						ARRAY_BUFFER_INTEGER_LIST,
						TEXTURE_INTEGER_LIST,
						string,
						l,
						FONT_MH_SH,
						x_offset,
						y +
							text_height * 2.0F +
							4.0F * 0.005F * SmallGui.WIDTH,
						FONT_SH
					);

					string = STRING_ARRAY[26];
					l = (int)(minecraft.fontRenderer.getStringWidth(string) * text_scale);

					this.initTextHorizontal
					(
						ARRAY_BUFFER_INTEGER_LIST,
						TEXTURE_INTEGER_LIST,
						string,
						l,
						text_height,
						x +
							width -
							l,
						y,
						text_scale
					);

					string = string_array[1];
					l = (int)(minecraft.fontRenderer.getStringWidth(string) * FONT_SH);

					this.initTextHorizontal
					(
						ARRAY_BUFFER_INTEGER_LIST,
						TEXTURE_INTEGER_LIST,
						string,
						l,
						FONT_MH_SH,
						x,
						y +
							height -
							FONT_MH_SH,
						FONT_SH
					);

					string = string_array[2];
					l = (int)(minecraft.fontRenderer.getStringWidth(string) * text_scale);

					this.initTextHorizontal
					(
						ARRAY_BUFFER_INTEGER_LIST,
						TEXTURE_INTEGER_LIST,
						string,
						l,
						text_height,
						x_offset,
						y +
							text_height +
							2.0F * 0.005F * SmallGui.WIDTH,
						text_scale
					);

					string = string_array[3];
					l = (int)(minecraft.fontRenderer.getStringWidth(string) * text_scale);

					this.initTextHorizontal
					(
						ARRAY_BUFFER_INTEGER_LIST,
						TEXTURE_INTEGER_LIST,
						string,
						l,
						text_height,
						x_offset,
						y,
						text_scale
					);

					y -= scale / Page.SCREEN_RH + h2;
				}
			}
			//e0-initC

			//s0-initScroll
			MAX_Y = (((height_f + h2) * (ARRAY_BUFFER_INTEGER_LIST.size() / 7) - (SmallGui.HEIGHT - h_offset_y2)) / SmallGui.HEIGHT) * 2.0F;
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
					PageSakura.LEFT -
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
					PageSakura.LEFT -
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
			PageText.SEARCH_L = minecraft.fontRenderer.getStringWidth(string) * FONT_SH;

			this.initTextHorizontal
			(
				ARRAY_BUFFER_INTEGER_LIST,
				TEXTURE_INTEGER_LIST,
				string,
				(int)PageText.SEARCH_L,
				FONT_MH_SH,
				SmallGui.WIDTH -
					PageText.SEARCH_L -
					2.0F * 0.005F * SmallGui.WIDTH,
				SmallGui.HEIGHT -
					FONT_MH_SH -
					h2,
				FONT_SH
			);
			CSToC.STATE |= 2;
			CSToC.STATE &= 255-4;
			//e0-initSearch

			BYTE &= 255-1/*-2*/;
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

			float
				scale = 75.0F / Page.SCREEN_RH,
				h_offset = FONT_MH_SH + 4.0F * 0.005F * SmallGui.HEIGHT,
				py = SmallGui.HEIGHT - (h_offset * 2.0F),
				y0 = scale + 2.0F * 0.005F * SmallGui.HEIGHT,
				y = (MouseArmy.Y / 2.0F * SmallGui.HEIGHT + py) / y0;
			float
				max_y = y + 1.0F,
				min_y = (y - py / y0 - 0.5F);

			float offset = FONT_MH_SH + 4.0F * 0.005F * SmallGui.HEIGHT;

			A_H = (int)(SmallGui.HEIGHT - offset);

			INDEX_INTEGER_LIST.clear();
			M_VEC2_2D_FLOAT_ARRAY[0][1] = MouseArmy.Y;

			A_W = (int)(SmallGui.WIDTH -
				PageSakura.LEFT +
				FONT_MH_SH +
				6.0F * 0.005F * SmallGui.WIDTH);
			OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_FRAMEBUFFER_0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE_0);
			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, A_W, A_H, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE_0, 0);
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

			for (int i = 0; i < size - 3; ++i)
			{
				float last_id = ((i -(i - 7 * (i / 7 + 1)) - 1) / 7.0F);
				if (/*(new_id >= min_y && new_id <= max_y) || */(last_id >= min_y && last_id <= max_y))
				{
					INDEX_INTEGER_LIST.add(i);
				}
			}
			int index_size = INDEX_INTEGER_LIST.size();
			INDEX_INT_ARRAY = new int[index_size / 7];
			for (int x = 0; x < index_size; ++x)
			{
				int i = INDEX_INTEGER_LIST.get(x);
				float[] color_float_array;
				int index = x / 7 + 3;
				if (i % 7 == 3)
				{
					color_float_array = new float[]{(index + 62) / 255.0F, PAGE/255.0F, 0.0F, 1.0F};
				}
				else
				{
					color_float_array = new float[]{index / 255.0F, PAGE/255.0F, 0.0F, 1.0F};
				}
				if (i % 7 == 0)
				{
					INDEX_INT_ARRAY[x / 7] = i;
				}

				this.drawQuadVUv
				(
					rs,
					M_VEC2_2D_FLOAT_ARRAY[0],
					color_float_array,
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
				C_COLOR_VEC4_2D_FLOAT_ARRAY[0],
				ARRAY_BUFFER_INTEGER_LIST.get(index),
				TEXTURE_INTEGER_LIST.get(index)
			);

			index = size - 1;

			this.drawQuadVUv
			(
				rs,
				VEC2_FLOAT_ARRAY,
				C_COLOR_VEC4_2D_FLOAT_ARRAY[1],
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
				COLOR_VEC4_2D_FLOAT_ARRAY[1],
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
				float[] color_float_array;
				byte b = (byte)(i % 7);
				if (b == 0)
				{
					color_float_array = COLOR_VEC4_2D_FLOAT_ARRAY[0];
				}
				else
				{
					color_float_array = (b == 3 && MouseArmy.E_PAGE == PAGE && MouseArmy.HIT == (x / 7) + 62 + 3) || (b != 3 && MouseArmy.E_PAGE == PAGE && MouseArmy.HIT == (x / 7) + 3) ? COLOR_VEC4_2D_FLOAT_ARRAY[2] : COLOR_VEC4_2D_FLOAT_ARRAY[1];
				}

				this.drawQuadVUv
				(
					rs,
					M_VEC2_2D_FLOAT_ARRAY[0],
					color_float_array,
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
				COLOR_VEC4_2D_FLOAT_ARRAY[1],
				ARRAY_BUFFER_INTEGER_LIST.get(index),
				TEXTURE_INTEGER_LIST.get(index)
			);

			M_VEC2_2D_FLOAT_ARRAY[1][1] = -MouseArmy.Y_STAR;
			index = size - 2;

			this.drawQuadVUv
			(
				rs,
				M_VEC2_2D_FLOAT_ARRAY[1],
				COLOR_VEC4_2D_FLOAT_ARRAY[1],
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
				byte b = (byte)(i % 7);
				if (b != 0)
				{
					this.drawQuadVUv
					(
						rs,
						M_VEC2_2D_FLOAT_ARRAY[0],
						(b == 3 && MouseArmy.E_PAGE == PAGE && MouseArmy.HIT == (x / 7) + 62 + 3) || (b != 3 && MouseArmy.E_PAGE == PAGE && MouseArmy.HIT == (x / 7) + 3) ? COLOR_VEC4_2D_FLOAT_ARRAY[2] : COLOR_VEC4_2D_FLOAT_ARRAY[0],
						ARRAY_BUFFER_INTEGER_LIST.get(i),
						TEXTURE_INTEGER_LIST.get(i)
					);
				}
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
