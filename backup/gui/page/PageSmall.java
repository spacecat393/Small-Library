//package com.nali.small.gui.page;
//
//import com.nali.list.container.gui.SmallGui;
//import com.nali.list.data.SmallData;
//import com.nali.small.gui.key.Key;
//import com.nali.small.gui.key.KeyMenu;
//import com.nali.small.gui.mouse.Mouse;
//import com.nali.small.gui.mouse.MouseSmall;
//import com.nali.system.opengl.memo.client.MemoS;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.FontRenderer;
//import net.minecraft.client.renderer.OpenGlHelper;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import org.lwjgl.opengl.GL20;
//
//@SideOnly(Side.CLIENT)
//public class PageSmall extends Page
//{
//	public static int INDEX;
//
//	public static void openPageSmall()
//	{
//		PageMenu.BYTE |= 1;
//		PAGE_ARRAY = new Page[]
//		{
//			new PageBlur(),
//			new PageMenu(STRING_ARRAY[14]),
//			new PageSakura(),
//			new PageKey(),
//			new PageSmall()
//		};
//		Key.KEY = new KeyMenu();
//		Mouse.MOUSE = new MouseSmall();
//		addSet();
//	}
//
//	@Override
//	public void init()
//	{
//		if ((BYTE & 1) == 1)
//		{
//			this.clear();
//
//			//s0-init2Text
//			Minecraft minecraft = SMALLGUI.mc;
//			FontRenderer fontrenderer = minecraft.fontRenderer;
//
//			String[] string_array = new String[]
//			{
//				STRING_ARRAY[0],
//				STRING_ARRAY[3],
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
//			//0
//			this.initTextHorizontal
//			(
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
//			//1
//			this.initTextHorizontal
//			(
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
//			//e0-init2Text
//
//			BYTE &= 255-1;
//		}
//	}
//
//	@Override
//	public void preDraw()
//	{
//		this.draw(1);
//	}
//
//	@Override
//	public void draw()
//	{
//		this.draw(0);
//	}
//
//	public void draw(int color_index)
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
//			MouseSmall.E_PAGE == PAGE && HIT == 1 ? COLOR_VEC4_2D_FLOAT_ARRAY[2] : COLOR_VEC4_2D_FLOAT_ARRAY[color_index],
//			ARRAY_BUFFER_INTEGER_LIST.get(INDEX),
//			TEXTURE_INTEGER_LIST.get(INDEX)
//		);
//
//		this.drawQuadVUv
//		(
//			rs,
//			VEC2_FLOAT_ARRAY,
//			MouseSmall.E_PAGE == PAGE && HIT == 2 ? COLOR_VEC4_2D_FLOAT_ARRAY[2] : COLOR_VEC4_2D_FLOAT_ARRAY[color_index],
//			ARRAY_BUFFER_INTEGER_LIST.get(INDEX + 1),
//			TEXTURE_INTEGER_LIST.get(INDEX + 1)
//		);
//
//		GL20.glDisableVertexAttribArray(v);
//	}
//}
