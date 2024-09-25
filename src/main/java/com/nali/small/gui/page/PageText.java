package com.nali.small.gui.page;

import com.nali.list.container.gui.SmallGui;
import com.nali.list.data.SmallData;
import com.nali.small.gui.key.KeyMenu;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL20;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.nali.key.KeyHelper.generateRainbowColor;
import static com.nali.list.container.gui.SmallGui.SMALLGUI;
import static com.nali.system.ClientLoader.S_LIST;

@SideOnly(Side.CLIENT)
public class PageText extends Page
{
	public static List<Integer>
		TEXTURE_INTEGER_LIST = new ArrayList(),
		ARRAY_BUFFER_INTEGER_LIST = new ArrayList();

	public static byte BYTE = 1;
	public static float SEARCH_L;

	public static StringBuffer STRINGBUFFER = new StringBuffer();
	public static String STRING;

	@Override
	public void init()
	{
		String string = STRINGBUFFER.toString();
		if (!string.equals(STRING))
		{
			BYTE |= 1;
		}
		STRING = string;
		Minecraft minecraft = SMALLGUI.mc;
		float h2 = 2.0F * 0.005F * SmallGui.HEIGHT;
		float x = SmallGui.WIDTH - SEARCH_L - 2.0F * 0.005F * SmallGui.WIDTH,
		y = SmallGui.HEIGHT - FONT_MH_SH - h2;

		if ((BYTE & 1) == 1)
		{
			this.clear(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST);

			//s0-initInputText
			if (!STRING.isEmpty())
			{
				int l = (int)(minecraft.fontRenderer.getStringWidth(string) * FONT_SH);
				this.initTextHorizontal
				(
					ARRAY_BUFFER_INTEGER_LIST,
					TEXTURE_INTEGER_LIST,
					"_",
					FONT_MH_SH,
					FONT_MH_SH,
					x + l,
					y,
					FONT_SH
				);

				this.initTextHorizontal
				(
					ARRAY_BUFFER_INTEGER_LIST,
					TEXTURE_INTEGER_LIST,
					string,
					l,
					FONT_MH_SH,
					x,
					y,
					FONT_SH
				);
			}
			else
			{
				this.initTextHorizontal
				(
					ARRAY_BUFFER_INTEGER_LIST,
					TEXTURE_INTEGER_LIST,
					"_",
					FONT_MH_SH,
					FONT_MH_SH,
					x,
					y,
					FONT_SH
				);
			}
			//e0-initInputText

			BYTE &= 255-1;
		}
	}

	@Override
	public void detect()
	{

	}

	@Override
	public void preDraw()
	{
		this.draw();
	}

	@Override
	public void draw()
	{
		MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 3);
		OpenGlHelper.glUseProgram(rs.program);
		int v = rs.attriblocation_int_array[0];
		GL20.glEnableVertexAttribArray(v);

		Color color = generateRainbowColor();
		float[] color_float_array = new float[]{color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, 1.0F};
		if (!STRING.isEmpty())
		{
			this.drawQuadVUv
			(
				rs,
				VEC2_FLOAT_ARRAY,
				color_float_array,
				ARRAY_BUFFER_INTEGER_LIST.get(1),
				TEXTURE_INTEGER_LIST.get(1)
			);
		}

		if ((KeyMenu.STATE & 4) == 4)
		{
			this.drawQuadVUv
			(
				rs,
				VEC2_FLOAT_ARRAY,
				color_float_array,
				ARRAY_BUFFER_INTEGER_LIST.get(0),
				TEXTURE_INTEGER_LIST.get(0)
			);
		}

		GL20.glDisableVertexAttribArray(v);
	}
}
