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
public class PageKey extends Page
{
	public static List<Integer>
		TEXTURE_INTEGER_LIST = new ArrayList(),
		ARRAY_BUFFER_INTEGER_LIST = new ArrayList();

	public static byte BYTE = 1;

	public static float
		RIGHT,
		BT;

	@Override
	public void init()
	{
		if ((BYTE & 1) == 1)
		{
			this.clear(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST);
			RIGHT = 0;

			//s0-initKey
			Minecraft minecraft = SMALLGUI.mc;

			float scale = FONT_SH * 0.5F;
			int h = (int)(FONT_MH * scale);
			String string = STRING_ARRAY[18];
			int i = (int)(minecraft.fontRenderer.getStringWidth(string) * scale);

			this.initTextHorizontal
			(
				ARRAY_BUFFER_INTEGER_LIST,
				TEXTURE_INTEGER_LIST,
				string,
				i,
				h,
				SmallGui.WIDTH -
					i -
					2.0F * 0.005F * SmallGui.WIDTH,
				2.0F * 0.005F * SmallGui.HEIGHT,
				scale
			);

			RIGHT += i;
			//e0-initKey
			this.initPlus();

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
		this.draw(COLOR_VEC4_2D_FLOAT_ARRAY[1]);
	}

	@Override
	public void draw()
	{
		this.draw(null);
	}

	public void draw(float[] color_float_array)
	{
		MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 3);
		OpenGlHelper.glUseProgram(rs.program);
		int v = rs.attriblocation_int_array[0];
		GL20.glEnableVertexAttribArray(v);

		boolean bt = BT > 0.0F;

		this.drawQuadVUv
		(
			rs,
			VEC2_FLOAT_ARRAY,
			bt ? COLOR_VEC4_2D_FLOAT_ARRAY[4] : color_float_array == null ? COLOR_VEC4_2D_FLOAT_ARRAY[0] : color_float_array,
			ARRAY_BUFFER_INTEGER_LIST.get(0),
			TEXTURE_INTEGER_LIST.get(0)
		);

		this.drawPlus(rs, color_float_array);

		if (bt)
		{
			BT -= SMALLGUI.partial_ticks;
		}

		GL20.glDisableVertexAttribArray(v);
	}

	public void drawPlus(MemoS rs, float[] color_float_array)
	{

	}

	public void initPlus()
	{

	}
}
