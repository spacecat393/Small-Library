//package com.nali.list.container.gui;
//
//import com.nali.list.container.SmallContainer;
//import com.nali.small.SmallConfig;
//import com.nali.small.gui.key.Key;
//import com.nali.small.gui.key.KeyMenuMe;
//import com.nali.small.gui.mouse.Mouse;
//import com.nali.small.gui.page.Page;
//import com.nali.small.gui.page.PageMe;
//import com.nali.small.gui.page.PageSmall;
//import com.nali.system.opengl.memo.client.MemoC;
//import net.minecraft.client.gui.inventory.GuiContainer;
//import net.minecraft.client.renderer.OpenGlHelper;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.inventory.Container;
//import net.minecraft.world.World;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import org.lwjgl.input.Keyboard;
//import org.lwjgl.opengl.*;
//
//import java.io.IOException;
//import java.lang.reflect.Field;
//
//@SideOnly(Side.CLIENT)
//public class SmallGuiClone extends GuiContainer
//{
//	public static SmallGuiClone SMALLGUI;
//	public static float
//		WIDTH,
//		HEIGHT;
//	public static int
//		OFFSET_RENDER_BUFFER = -1,
//
//		OFFSET_FRAMEBUFFER = -1,
//		OFFSET_FRAMEBUFFER_0 = -1,
//		OFFSET_FRAMEBUFFER_1 = -1,
//
//		OFFSET_FRAMEBUFFER_TEXTURE = -1,
//		OFFSET_FRAMEBUFFER_TEXTURE_0 = -1;
//
//	public float partial_ticks;
//
//	public SmallGuiClone(Container container)
//	{
//		super(container);
//		SMALLGUI = this;
//
//		if (Page.PAGE_ARRAY == null)
//		{
//			PageSmall.openPageSmall();
//		}
//	}
//
//	public static SmallGuiClone get(EntityPlayer entityplayer, World world, int x, int y, int z)//i d e
//	{
//		if (x != -1)
//		{
//			KeyMenuMe.ME &= 255-1;
//			PageMe.ID_KEY = (long)y << 32 | x;
//			PageMe.E_ID = z;
//
//			PageMe.openPageMe();
//		}
//		return new SmallGuiClone(new SmallContainer());
//	}
//
//	public static void addSet()
//	{
//		for (Page page : Page.PAGE_ARRAY)
//		{
//			Class page_class = page.getClass();
//			if (Page.PAGE_CLASS_SET.contains(page_class))
//			{
//				Page.PAGE_CLASS_SET.add(page_class);
//				Page.setByte();
//			}
//		}
//
////		//s0-init all
////		int
////			texture_size = 0,
////			buffer_size = 0;
////
////		for (Page page : PAGE_ARRAY)
////		{
////			//use index pointer start/end
////			//1 list for reach all but keepin
////			//only vec with framebuffer
////			texture_size += page.texture_size();
////			buffer_size += page.buffer_size();
////		}
////		//e0-init all
//	}
//
//	@Override
//	public void drawScreen(int mouseX, int mouseY, float partialTicks)
//	{
//		Key.KEY.run();
//		Mouse.MOUSE.run();
//
//		this.partial_ticks = partialTicks;
//
//		//s0-takeDefault
//		GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, MemoC.OPENGL_INTBUFFER);
//		int gl_array_buffer_binding = MemoC.OPENGL_INTBUFFER.get(0);
//
//		GL11.glGetInteger(GL11.GL_MATRIX_MODE, MemoC.OPENGL_INTBUFFER);
//		GL_MATRIX_MODE = MemoC.OPENGL_INTBUFFER.get(0);
//
//		GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, MemoC.OPENGL_PROJECTION_MATRIX_FLOATBUFFER);
//
//		boolean gl_blend = GL11.glIsEnabled(GL11.GL_BLEND);
//
//		GL11.glGetInteger(GL20.GL_BLEND_EQUATION_RGB, MemoC.OPENGL_INTBUFFER);
//		int gl_blend_equation_rgb = MemoC.OPENGL_INTBUFFER.get(0);
//
//		GL11.glGetInteger(GL20.GL_BLEND_EQUATION_ALPHA, MemoC.OPENGL_INTBUFFER);
//		int gl_blend_equation_alpha = MemoC.OPENGL_INTBUFFER.get(0);
//
//		GL11.glGetInteger(GL14.GL_BLEND_SRC_RGB, MemoC.OPENGL_INTBUFFER);
//		int gl_blend_src_rgb = MemoC.OPENGL_INTBUFFER.get(0);
//
//		GL11.glGetInteger(GL14.GL_BLEND_SRC_ALPHA, MemoC.OPENGL_INTBUFFER);
//		int gl_blend_src_alpha = MemoC.OPENGL_INTBUFFER.get(0);
//
//		GL11.glGetInteger(GL14.GL_BLEND_DST_RGB, MemoC.OPENGL_INTBUFFER);
//		int gl_blend_dst_rgb = MemoC.OPENGL_INTBUFFER.get(0);
//
//		GL11.glGetInteger(GL14.GL_BLEND_DST_ALPHA, MemoC.OPENGL_INTBUFFER);
//		int gl_blend_dst_alpha = MemoC.OPENGL_INTBUFFER.get(0);
//
//		GL11.glGetInteger(GL30.GL_RENDERBUFFER_BINDING, MemoC.OPENGL_INTBUFFER);
//		int renderbuffer_binding = MemoC.OPENGL_INTBUFFER.get(0);
//
//		boolean gl_depth_test = GL11.glIsEnabled(GL11.GL_DEPTH_TEST);
//
//		GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, MemoC.OPENGL_INTBUFFER);
//		GL_DRAW_FRAMEBUFFER_BINDING = MemoC.OPENGL_INTBUFFER.get(0);
//
//		GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, MemoC.OPENGL_INTBUFFER);
//		GL_READ_FRAMEBUFFER_BINDING = MemoC.OPENGL_INTBUFFER.get(0);
//
//		MemoC.OPENGL_BYTEBUFFER.limit(16);
//		GL11.glGetBoolean(GL11.GL_DEPTH_WRITEMASK, MemoC.OPENGL_BYTEBUFFER);
//		boolean gl_depth_writemask = (MemoC.OPENGL_BYTEBUFFER.get(0) & 1) == 1;
//		//e0-takeDefault
//
//		GL11.glEnable(GL11.GL_BLEND);
//		GL20.glBlendEquationSeparate(GL14.GL_FUNC_ADD, GL14.GL_FUNC_ADD);
//		GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
//
//		GL11.glDepthMask(false);
//		GL11.glDisable(GL11.GL_DEPTH_TEST);
//
//		if (WIDTH != this.mc.displayWidth || HEIGHT != this.mc.displayHeight || FONT_S != SmallConfig.CLIENT.font.scale/* || (FLAG & 1) == 1*//* || (this.state & 8) == 8*//* || MAIN_FRAMEBUFFER == -1*/)
//		{
//			WIDTH = this.mc.displayWidth;
//			HEIGHT = this.mc.displayHeight;
//
//			Page.FONT_S = SmallConfig.CLIENT.font.scale;
//
//			Page.SCREEN_RW = this.width / WIDTH;
//			Page.SCREEN_RH = this.height / HEIGHT;
//			Page.FONT_SH = Page.FONT_S / Page.SCREEN_RH;
//			Page.FONT_MH_SH = (int)(FONT_MH * FONT_SH);
//
//			for (Class clasz : PAGE_CLASS_SET)
//			{
//				try
//				{
//					Field byte_field = clasz.getField("BYTE");
//					byte_field.set(null, (byte)((byte)byte_field.get(null) | 1));
//				}
//				catch (NoSuchFieldException | IllegalAccessException e)
//				{
//					try
//					{
//						Field byte_field = clasz.getDeclaredField("BYTE");
//						byte_field.set(null, (byte)((byte)byte_field.get(null) | 1));
//					}
//					catch (NoSuchFieldException | IllegalAccessException ex)
//					{
//					}
//				}
//			}
//		}
//
//		//s0-OFFSET_FRAMEBUFFER_1
//		OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_FRAMEBUFFER_1);
//
//		for (Page page : PAGE_ARRAY)
//		{
//			page.init();
//		}
//
//		// GL11.glViewport(0, 0, (int)WIDTH, (int)HEIGHT);
//		//
//		// //s1-takeDefault
//		GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM, OPENGL_INTBUFFER);
//		int gl_current_program = OPENGL_INTBUFFER.get(0);
//		// //e1-takeDefault
//
//		// GL11.glMatrixMode(GL11.GL_PROJECTION);
//		// GL11.glLoadIdentity();
//		// //e0-OFFSET_FRAMEBUFFER_1
//		//
//		// //s0-OFFSET_FRAMEBUFFER
//		// OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_FRAMEBUFFER);
//		// GL11.glBindTexture(GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE);
//		// GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, (int)WIDTH, (int)HEIGHT, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
//		// OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE, 0);
//		//
//		// OPENGL_BYTEBUFFER.limit(4);
//		// GL11.glReadPixels(MOUSE_X, MOUSE_Y, 1, 1, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, OPENGL_BYTEBUFFER);
//		// HIT = (short)(OPENGL_BYTEBUFFER.get(0) & 0xFF);
//		// E_PAGE = OPENGL_BYTEBUFFER.get(1);
//		// if ((STATE & 1) == 1)
//		// {
//		// 	PAGE = (short)(E_PAGE & 0xFF);
//		// 	STATE &= 255-1;
//		// }
//		// //e0-OFFSET_FRAMEBUFFER
//
//		//s0-MAIN_FRAMEBUFFER
//		OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, GL_READ_FRAMEBUFFER_BINDING);
//		OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER_BINDING);
//
//		for (Page page : PAGE_ARRAY)
//		{
//			page.preDraw();
//		}
//
//		for (Page page : PAGE_ARRAY)
//		{
//			page.draw();
//		}
//
//		//s1-setDefault
//		GL11.glLoadMatrix(OPENGL_PROJECTION_MATRIX_FLOATBUFFER);
//
//		GL11.glMatrixMode(GL_MATRIX_MODE);
//
//		OpenGlHelper.glUseProgram(gl_current_program);
//
//		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, gl_array_buffer_binding);
//
////		OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, GL_READ_FRAMEBUFFER_BINDING);
////		OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER_BINDING);
//
//		OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, renderbuffer_binding);
//
//		if (gl_blend)
//		{
//			GL11.glEnable(GL11.GL_BLEND);
//		}
//		else
//		{
//			GL11.glDisable(GL11.GL_BLEND);
//		}
//
//		GL20.glBlendEquationSeparate(gl_blend_equation_rgb, gl_blend_equation_alpha);
//		GL14.glBlendFuncSeparate(gl_blend_src_rgb, gl_blend_dst_rgb, gl_blend_src_alpha, gl_blend_dst_alpha);
//
//		if (gl_depth_test)
//		{
//			GL11.glEnable(GL11.GL_DEPTH_TEST);
//		}
//		else
//		{
//			GL11.glDisable(GL11.GL_DEPTH_TEST);
//		}
//
//		GL11.glDepthMask(gl_depth_writemask);
//		//e1-setDefault
//		//e0-MAIN_FRAMEBUFFER
//
////		this.defaultState(mouseX, mouseY);
//		super.drawScreen(mouseX, mouseY, partialTicks);
//	}
//
//	@Override
//	public void handleMouseInput()
//	{
//		MOUSE_X = org.lwjgl.input.Mouse.getEventX();
//		MOUSE_Y = org.lwjgl.input.Mouse.getEventY();
//
//		int k = org.lwjgl.input.Mouse.getEventButton();
//		int mouse = org.lwjgl.input.Mouse.getEventDWheel();
//		float eventdwheel = 0;
//
//		if (mouse > 0)
//		{
//			eventdwheel = 1.5F;
//		}
//		else if (mouse < 0)
//		{
//			eventdwheel = -1.5F;
//		}
//
//		if (eventdwheel != 0)
//		{
//			if (EVENTDWHEEL > 0 && eventdwheel < 0)
//			{
//				EVENTDWHEEL = 0;
//			}
//			else if (EVENTDWHEEL < 0 && eventdwheel > 0)
//			{
//				EVENTDWHEEL = 0;
//			}
//			EVENTDWHEEL += eventdwheel;
//		}
//
//		if (org.lwjgl.input.Mouse.getEventButtonState())//c
//		{
//			STATE |= 2;
//		}
//		else if (k != -1)//r
//		{
//			STATE |= 1;
//			STATE &= 255-2;
//		}
//	}
//
//	@Override
//	public void keyTyped(char typedChar, int keyCode) throws IOException
//	{
//		if (keyCode != Keyboard.KEY_ESCAPE)
//		{
//			Key.KEY.detect(typedChar, keyCode);
//		}
//		else
//		{
//			super.keyTyped(typedChar, keyCode);
//		}
//	}
//
//	@Override
//	public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
//	{
//	}
//
////	public void defaultState(int mouseX, int mouseY)
////	{
////		GlStateManager.disableRescaleNormal();
////		RenderHelper.disableStandardItemLighting();
////		GlStateManager.disableLighting();
////		GlStateManager.disableDepth();
////		RenderHelper.enableGUIStandardItemLighting();
////
////		GlStateManager.pushMatrix();
////
////		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
////		GlStateManager.enableRescaleNormal();
////		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
////		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
////
////		RenderHelper.disableStandardItemLighting();
////		RenderHelper.enableGUIStandardItemLighting();
////		net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.GuiContainerEvent.DrawForeground(this, mouseX, mouseY));
////
////		GlStateManager.popMatrix();
////
////		GlStateManager.enableLighting();
////		GlStateManager.enableDepth();
////		RenderHelper.enableStandardItemLighting();
////	}
//}
