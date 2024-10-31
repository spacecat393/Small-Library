package com.nali.small.gui.page;

import com.nali.list.container.gui.SmallGui;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.list.container.gui.SmallGui.SMALLGUI;

@SideOnly(Side.CLIENT)
public class PageKeyArmy extends PageKey
{
	public static float BT27;

	@Override
	public void initPlus()
	{
		super.initPlus();
		Minecraft minecraft = SMALLGUI.mc;

		float scale = FONT_SH * 0.5F;
		int h = (int)(FONT_MH * scale);
		String string = STRING_ARRAY[27];
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
				RIGHT -
				4.0F * 0.005F * SmallGui.WIDTH,
			2.0F * 0.005F * SmallGui.HEIGHT,
			scale
		);

		RIGHT += i;
	}

	@Override
	public void drawPlus(MemoS rs, float[] color_float_array)
	{
		super.drawPlus(rs, color_float_array);

		boolean bt = BT27 > 0.0F;

		this.drawQuadVUv
		(
			rs,
			VEC2_FLOAT_ARRAY,
			bt ? COLOR_VEC4_2D_FLOAT_ARRAY[4] : color_float_array == null ? COLOR_VEC4_2D_FLOAT_ARRAY[0] : color_float_array,
			ARRAY_BUFFER_INTEGER_LIST.get(1),
			TEXTURE_INTEGER_LIST.get(1)
		);

		if (bt)
		{
			BT27 -= SMALLGUI.partial_ticks;
		}
	}
}
