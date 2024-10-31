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
public class PageSakura extends Page
{
	public static List<Integer>
		TEXTURE_INTEGER_LIST = new ArrayList(),
		ARRAY_BUFFER_INTEGER_LIST = new ArrayList();

	public static byte
		BYTE = 1,
		SAKURA = -1;

	public static float
		LEFT;

	@Override
	public void init()
	{
		byte sakura = SMALLGUI.mc.player.getEntityData().getByte("Nali_sakura");
		if (SAKURA != sakura)
		{
			BYTE |= 1;
		}

		if ((BYTE & 1) == 1)
		{
			LEFT = 0;
			this.clear(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST);

			//s0-initSakura
			Minecraft minecraft = SMALLGUI.mc;
			SAKURA = sakura;

			String string = STRING_ARRAY[11] + " " + SAKURA;
			int i = (int)(minecraft.fontRenderer.getStringWidth(string) * FONT_SH);

			this.initTextHorizontal
			(
				ARRAY_BUFFER_INTEGER_LIST,
				TEXTURE_INTEGER_LIST,
				string,
				i,
				FONT_MH_SH,
				FONT_MH_SH +
					4.0F * 0.005F * SmallGui.WIDTH,
				SmallGui.HEIGHT -
					FONT_MH_SH -
					2.0F * 0.005F * SmallGui.HEIGHT,
				FONT_SH
			);

			LEFT += i;
			//e0-initSakura

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

		this.drawQuadVUv
		(
			rs,
			VEC2_FLOAT_ARRAY,
			color_float_array == null ? COLOR_VEC4_2D_FLOAT_ARRAY[0] : color_float_array,
			ARRAY_BUFFER_INTEGER_LIST.get(0),
			TEXTURE_INTEGER_LIST.get(0)
		);

		GL20.glDisableVertexAttribArray(v);
	}
}
