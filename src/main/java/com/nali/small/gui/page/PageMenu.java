package com.nali.small.gui.page;

import com.nali.list.container.gui.SmallGui;
import com.nali.list.data.SmallData;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL20;

import java.util.ArrayList;
import java.util.List;

import static com.nali.list.container.gui.SmallGui.SMALLGUI;
import static com.nali.system.ClientLoader.S_LIST;

@SideOnly(Side.CLIENT)
public class PageMenu extends Page
{
	public static List<Integer>
		TEXTURE_INTEGER_LIST = new ArrayList(),
		ARRAY_BUFFER_INTEGER_LIST = new ArrayList();

	public static String STRING;
	public static byte BYTE = 1;
	public static float
		Y,
		MAX_Y;

	public static float[] M_VEC2_FLOAT_ARRAY = new float[2];

	public PageMenu(String string)
	{
		STRING = string;
	}

	@Override
	public void init()
	{
		if ((BYTE & 1) == 1)
		{
			Y = 0;

			this.clear(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST);

			//s0-initPath
			int i = (int)(FONT_MH * STRING.length() * FONT_SH);
			MAX_Y = ((SmallGui.HEIGHT + i) / SmallGui.HEIGHT) * 2.0F;
			this.initTextVertical
			(
				ARRAY_BUFFER_INTEGER_LIST,
				TEXTURE_INTEGER_LIST,
				STRING,
				FONT_MH_SH,
				i,
				2.0F * 0.005F * SmallGui.WIDTH,
				0.0F,
				FONT_SH
			);
			//e0-initPath

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

		M_VEC2_FLOAT_ARRAY[1] = Y;

		this.drawQuadVUv
		(
			rs,
			M_VEC2_FLOAT_ARRAY,
			COLOR_VEC4_2D_FLOAT_ARRAY[3],
			ARRAY_BUFFER_INTEGER_LIST.get(0),
			TEXTURE_INTEGER_LIST.get(0)
		);

		Y += 0.025F * SMALLGUI.partial_ticks;
		if (Y > MAX_Y)
		{
			Y = 0;
		}

		GL20.glDisableVertexAttribArray(v);
	}
}
