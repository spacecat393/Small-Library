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

import static com.nali.list.container.gui.SmallGui.SCALE;
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

	public float[][] vec2_2d_float_array;
	public float[][] color_vec4_2d_float_array;

	public PageKey()
	{
		this.vec2_2d_float_array = new float[1][2];
		this.color_vec4_2d_float_array = new float[3][4];
		this.color_vec4_2d_float_array[0] = new float[]{1.0F, 1.0F, 1.0F, 1.0F};
		this.color_vec4_2d_float_array[1] = new float[]{0.5F, 0.5F, 1.0F, 1.0F};

		this.color_vec4_2d_float_array[2] = new float[]{0.0F, 0.0F, 0.0F, 1.0F};
	}

	@Override
	public void init()
	{
//		super.init();
//		this.clear(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST);

//		byte b = this.getByte();
//		if ((b & 1) == 1)
		if ((BYTE & 1) == 1)
		{
			this.clear(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST);
			RIGHT = 0;

			Minecraft minecraft = SMALLGUI.mc;
			int display_width = minecraft.displayWidth,
			display_height = minecraft.displayHeight;

			float scale = SCALE * 0.5F;
			int h = (int)(MAX_TH * scale);
			String string = STRING_ARRAY[18];
			int i = (int)(minecraft.fontRenderer.getStringWidth(string) * scale);
			//		box_width = i - display_width;
			//		if (i < display_width)
			//		{
			//			box_width = i;
			//		}
			//		this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string/*, true*//*, -1*/, box_width, h, display_width - box_width - 2.0F * 0.005F * display_width, 2.0F * 0.005F * display_height, scale);
			this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string/*, true*//*, -1*/, i, h, display_width - i - 2.0F * 0.005F * display_width, 2.0F * 0.005F * display_height, scale);
			//		RIGHT += box_width;
			RIGHT += i;
			this.initPlus();
//			this.setByte((byte)(b & 255-1));
			BYTE &= 255-1;
		}
	}

	@Override
	public void draw()
	{
		this.draw(null);
	}

	@Override
	public void preDraw()
	{
		this.draw(this.color_vec4_2d_float_array[2]);
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
//
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

	public void draw(float[] color_float_array)
	{
		MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 3);
		OpenGlHelper.glUseProgram(rs.program);
		int v = rs.attriblocation_int_array[0];
		GL20.glEnableVertexAttribArray(v);

		boolean bt = BT > 0.0F;
		this.drawQuadVUv(rs, this.vec2_2d_float_array[0], bt ? this.color_vec4_2d_float_array[1] : color_float_array == null ? this.color_vec4_2d_float_array[0] : color_float_array, ARRAY_BUFFER_INTEGER_LIST.get(0), TEXTURE_INTEGER_LIST.get(0));
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
