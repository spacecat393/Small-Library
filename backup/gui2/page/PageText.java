package com.nali.small.gui.page;

import com.nali.list.container.gui.SmallGui;
import com.nali.list.data.SmallData;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL20;

import java.util.ArrayList;
import java.util.List;

import static com.nali.list.container.gui.SmallGui.SMALLGUI;
import static com.nali.system.ClientLoader.S_LIST;

@SideOnly(Side.CLIENT)
public class PageText extends Page
{
	public static List<Integer>
		TEXTURE_INTEGER_LIST = new ArrayList(),
		ARRAY_BUFFER_INTEGER_LIST = new ArrayList();

	public static byte
		BYTE = 1,//init1 2-4-8-16-32-64-128
		INDEX;

	//init size & data
	public static float[] L_FLOAT_ARRAY;
	public static String[] STRING_ARRAY;
	public static float[][] COLOR_FLOAT_ARRAY;

	//init size
	public static String[] CURRENT_STRING_ARRAY;

	@Override
	public void init()
	{
		byte init = (byte)(BYTE >> 1);
		boolean should_init = (BYTE & 1) == 1;

		if (INDEX == init)
		{
			INDEX = 0;

			if (should_init)
			{
				this.clear(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST);
				BYTE &= 255-1;
			}
		}

		if (!STRING_ARRAY[INDEX].equals(CURRENT_STRING_ARRAY[INDEX]))
		{
			BYTE |= 1;
		}
		CURRENT_STRING_ARRAY[INDEX] = STRING_ARRAY[INDEX];

		if (should_init)
		{
			//s0-initInputText
			Minecraft minecraft = SMALLGUI.mc;
			float
				h2 = 2.0F * 0.005F * SmallGui.HEIGHT,
				x = SmallGui.WIDTH - L_FLOAT_ARRAY[INDEX] - 2.0F * 0.005F * SmallGui.WIDTH,
				y = SmallGui.HEIGHT - FONT_MH_SH - h2;

			String string = STRING_ARRAY[INDEX];
			if (!string.isEmpty())
			{
				int l = (int)(minecraft.fontRenderer.getStringWidth(string) * FONT_SH);

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
			//e0-initInputText
		}
	}

	@Override
	public void detect()
	{

	}

	@Override
	public void preDraw()
	{
		if (ARRAY_BUFFER_INTEGER_LIST.size() > INDEX)
		{
			MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 3);
			OpenGlHelper.glUseProgram(rs.program);
			int v = rs.attriblocation_int_array[0];
			GL20.glEnableVertexAttribArray(v);

			this.drawQuadVUv
			(
				rs,
				VEC2_FLOAT_ARRAY,
				COLOR_FLOAT_ARRAY[INDEX],
				ARRAY_BUFFER_INTEGER_LIST.get(INDEX),
				TEXTURE_INTEGER_LIST.get(INDEX)
			);

			GL20.glDisableVertexAttribArray(v);
		}
	}

	@Override
	public void draw()
	{
		this.preDraw();
		++INDEX;
	}
}
