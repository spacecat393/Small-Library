//package com.nali.small.gui.page;
//
//import com.nali.list.container.gui.SmallGui;
//import com.nali.list.data.SmallData;
//import com.nali.small.entity.memo.client.ClientE;
//import com.nali.small.gui.key.KeyMenuMe;
//import com.nali.small.gui.mouse.MouseMe;
//import com.nali.small.gui.mouse.MouseSmall;
//import com.nali.system.opengl.memo.client.MemoS;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.FontRenderer;
//import net.minecraft.client.renderer.OpenGlHelper;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import org.lwjgl.opengl.GL20;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.nali.list.container.gui.SmallGui.*;
//import static com.nali.small.gui.key.Key.KEY;
//import static com.nali.small.gui.mouse.Mouse.HIT;
//import static com.nali.small.gui.mouse.Mouse.MOUSE;
//import static com.nali.system.ClientLoader.S_LIST;
//
//@SideOnly(Side.CLIENT)
//public class PageMe extends Page
//{
//	public static List<Integer>
//		TEXTURE_INTEGER_LIST = new ArrayList(),
//		ARRAY_BUFFER_INTEGER_LIST = new ArrayList();
//
//	public static long ID_KEY;
//	public static int E_ID;
//	public static ClientE CLIENTE;
//	public static byte[] SI_BYTE_ARRAY;
//
//	public static byte
//		PAGE,
//		BYTE = 1;
//
//	public static float[][] C_COLOR_VEC4_2D_FLOAT_ARRAY;
//
//	public PageMe(byte page)
//	{
//		PAGE = page;
//		C_COLOR_VEC4_2D_FLOAT_ARRAY = new float[][]
//		{
//			new float[]{1.0F/255.0F, PAGE/255.0F, 0.0F, 1.0F}, //0
//			new float[]{2.0F/255.0F, PAGE/255.0F, 0.0F, 1.0F}, //1
//			new float[]{3.0F/255.0F, PAGE/255.0F, 0.0F, 1.0F} //2
//		};
//	}
//
//	public static void openPageMe()
//	{
//		PageMenu.BYTE |= 1;
//		PAGE_ARRAY = new Page[]
//		{
//			new PageBlur(),
//			new PageMenu(STRING_ARRAY[14] + ((KeyMenuMe.ME & 1) == 1 ? "|" + STRING_ARRAY[0] : "") + "|I" + (int)PageMe.ID_KEY + "D" + (int)(PageMe.ID_KEY >> 32)),
//			new PageSakura(),
//			new PageKey(),
//			new PageMe((byte)1)
//		};
//		KEY = new KeyMenuMe();
//		MOUSE = new MouseMe();
//		addSet();
//	}
//
//	@Override
//	public void init()
//	{
//		if ((BYTE & 1) == 1)
//		{
//			this.clear(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST);
//
//			//s0-initMe
//			Minecraft minecraft = SMALLGUI.mc;
//			FontRenderer fontrenderer = minecraft.fontRenderer;
//
//			String[] string_array = new String[]
//			{
//				STRING_ARRAY[1],
//				STRING_ARRAY[2],
//				STRING_ARRAY[5]
//			};
//
//			int max = 0;
//			for (String s : string_array)
//			{
//				int new_max = (int)(fontrenderer.getStringWidth(s) * FONT_SH);
//				if (max < new_max)
//				{
//					max = new_max;
//				}
//			}
//
//			String string = string_array[0];
//			this.initTextHorizontal
//			(
//				ARRAY_BUFFER_INTEGER_LIST,
//				TEXTURE_INTEGER_LIST,
//				string,
//				max,
//				FONT_MH_SH,
//				SmallGui.WIDTH / 2.0F -
//					max / 2.0F,
//				SmallGui.HEIGHT / 2.0F,
//				FONT_SH
//			);
//
//			string = string_array[1];
//			this.initTextHorizontal
//			(
//				ARRAY_BUFFER_INTEGER_LIST,
//				TEXTURE_INTEGER_LIST,
//				string,
//				max,
//				FONT_MH_SH,
//				SmallGui.WIDTH / 2.0F -
//					max / 2.0F,
//				SmallGui.HEIGHT / 2.0F -
//					FONT_MH_SH -
//					2.0F * 0.005F * SmallGui.HEIGHT,
//				FONT_SH
//			);
//
//			string = string_array[2];
//			this.initTextHorizontal
//			(
//				ARRAY_BUFFER_INTEGER_LIST,
//				TEXTURE_INTEGER_LIST,
//				string,
//				max,
//				FONT_MH_SH,
//				SmallGui.WIDTH / 2.0F -
//					max / 2.0F,
//				SmallGui.HEIGHT / 2.0F -
//					FONT_MH_SH * 2.0F -
//					2.0F * 2.0F * 0.005F * SmallGui.HEIGHT,
//				FONT_SH
//			);
//			//e0-initMe
//
//			BYTE &= 255-1;
//		}
//	}
//
//	@Override
//	public void detect()
//	{
//		MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 4);
//
//		OpenGlHelper.glUseProgram(rs.program);
//		int v = rs.attriblocation_int_array[0];
//		GL20.glEnableVertexAttribArray(v);
//
//		this.drawQuadVUv
//		(
//			rs,
//			VEC2_FLOAT_ARRAY,
//			C_COLOR_VEC4_2D_FLOAT_ARRAY[0],
//			ARRAY_BUFFER_INTEGER_LIST.get(0),
//			TEXTURE_INTEGER_LIST.get(0)
//		);
//
//		this.drawQuadVUv
//		(
//			rs,
//			VEC2_FLOAT_ARRAY,
//			C_COLOR_VEC4_2D_FLOAT_ARRAY[1],
//			ARRAY_BUFFER_INTEGER_LIST.get(1),
//			TEXTURE_INTEGER_LIST.get(1)
//		);
//
//		this.drawQuadVUv
//		(
//			rs,
//			VEC2_FLOAT_ARRAY,
//			C_COLOR_VEC4_2D_FLOAT_ARRAY[2],
//			ARRAY_BUFFER_INTEGER_LIST.get(2),
//			TEXTURE_INTEGER_LIST.get(2)
//		);
//
//		GL20.glDisableVertexAttribArray(v);
//	}
//
//	@Override
//	public void preDraw()
//	{
//		MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 3);
//		OpenGlHelper.glUseProgram(rs.program);
//		int v = rs.attriblocation_int_array[0];
//		GL20.glEnableVertexAttribArray(v);
//
//		this.drawQuadVUv
//		(
//			rs,
//			VEC2_FLOAT_ARRAY,
//			MouseSmall.E_PAGE == PAGE && HIT == 1 ? COLOR_VEC4_2D_FLOAT_ARRAY[2] : COLOR_VEC4_2D_FLOAT_ARRAY[1],
//			ARRAY_BUFFER_INTEGER_LIST.get(0),
//			TEXTURE_INTEGER_LIST.get(0)
//		);
//
//		this.drawQuadVUv
//		(
//			rs,
//			VEC2_FLOAT_ARRAY,
//			MouseSmall.E_PAGE == PAGE && HIT == 2 ? COLOR_VEC4_2D_FLOAT_ARRAY[2] : COLOR_VEC4_2D_FLOAT_ARRAY[1],
//			ARRAY_BUFFER_INTEGER_LIST.get(1),
//			TEXTURE_INTEGER_LIST.get(1)
//		);
//
//		this.drawQuadVUv
//		(
//			rs,
//			VEC2_FLOAT_ARRAY,
//			MouseSmall.E_PAGE == PAGE && HIT == 3 ? COLOR_VEC4_2D_FLOAT_ARRAY[2] : COLOR_VEC4_2D_FLOAT_ARRAY[1],
//			ARRAY_BUFFER_INTEGER_LIST.get(2),
//			TEXTURE_INTEGER_LIST.get(2)
//		);
//
//		GL20.glDisableVertexAttribArray(v);
//	}
//
//	@Override
//	public void draw()
//	{
//		MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 3);
//		OpenGlHelper.glUseProgram(rs.program);
//		int v = rs.attriblocation_int_array[0];
//		GL20.glEnableVertexAttribArray(v);
//
//		this.drawQuadVUv
//		(
//			rs,
//			VEC2_FLOAT_ARRAY,
//			MouseSmall.E_PAGE == PAGE && HIT == 1 ? COLOR_VEC4_2D_FLOAT_ARRAY[2] : COLOR_VEC4_2D_FLOAT_ARRAY[0],
//			ARRAY_BUFFER_INTEGER_LIST.get(0),
//			TEXTURE_INTEGER_LIST.get(0)
//		);
//
//		this.drawQuadVUv
//		(
//			rs,
//			VEC2_FLOAT_ARRAY,
//			MouseSmall.E_PAGE == PAGE && HIT == 2 ? COLOR_VEC4_2D_FLOAT_ARRAY[2] : COLOR_VEC4_2D_FLOAT_ARRAY[0],
//			ARRAY_BUFFER_INTEGER_LIST.get(1),
//			TEXTURE_INTEGER_LIST.get(1)
//		);
//
//		this.drawQuadVUv
//		(
//			rs,
//			VEC2_FLOAT_ARRAY,
//			MouseSmall.E_PAGE == PAGE && HIT == 3 ? COLOR_VEC4_2D_FLOAT_ARRAY[2] : COLOR_VEC4_2D_FLOAT_ARRAY[0],
//			ARRAY_BUFFER_INTEGER_LIST.get(2),
//			TEXTURE_INTEGER_LIST.get(2)
//		);
//
//		GL20.glDisableVertexAttribArray(v);
//	}
//}
