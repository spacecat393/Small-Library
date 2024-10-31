package com.nali.list.container.gui;

import com.nali.list.container.SmallContainer;
import com.nali.small.gui.key.Key;
import com.nali.small.gui.mouse.Mouse;
import com.nali.small.gui.page.Page;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;

@SideOnly(Side.CLIENT)
public class SmallGui extends GuiContainer
{
	public static SmallGui SMALLGUI;
	public static float
		PARTIAL_TICKS,
		WIDTH,
		HEIGHT;

	public SmallGui(Container container)
	{
		super(container);
		SMALLGUI = this;

		if (Page.PAGE_ARRAY == null)
		{
//			PageSmall.openPageSmall();
		}
	}

	public static SmallGui get(EntityPlayer entityplayer, World world, int x, int y, int z)//i d e
	{
		if (x != -1)
		{
//			KeyMenuMe.ME &= 255-1;
//			PageMe.ID_KEY = (long)y << 32 | x;
//			PageMe.E_ID = z;
//
//			PageMe.openPageMe();
		}
		return new SmallGui(new SmallContainer());
	}

	public static void addSet()
	{
		for (Page page : Page.PAGE_ARRAY)
		{
			Class page_class = page.getClass();
			if (Page.PAGE_CLASS_SET.contains(page_class))
			{
				Page.PAGE_CLASS_SET.add(page_class);
				Page.setByte();
			}
		}

//		//s0-init all
//		int
//			texture_size = 0,
//			buffer_size = 0;
//
//		for (Page page : PAGE_ARRAY)
//		{
//			//use index pointer start/end
//			//1 list for reach all but keepin
//			//only vec with framebuffer
//			texture_size += page.texture_size();
//			buffer_size += page.buffer_size();
//		}
//		//e0-init all
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		PARTIAL_TICKS = partialTicks;

		Key.KEY.run();
		Mouse.MOUSE.run();

//		//s0-takeDefault
//		GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, Page.INTBUFFER);
//		int gl_array_buffer_binding = Page.INTBUFFER.get(0);
//
//		GL11.glGetInteger(GL11.GL_MATRIX_MODE, Page.INTBUFFER);
//		Page.GL_MATRIX_MODE = Page.INTBUFFER.get(0);
//
//		GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, Page.OPENGL_PROJECTION_MATRIX_FLOATBUFFER);
//
//		boolean gl_blend = GL11.glIsEnabled(GL11.GL_BLEND);
//
//		GL11.glGetInteger(GL20.GL_BLEND_EQUATION_RGB, Page.INTBUFFER);
//		int gl_blend_equation_rgb = Page.INTBUFFER.get(0);
//
//		GL11.glGetInteger(GL20.GL_BLEND_EQUATION_ALPHA, Page.INTBUFFER);
//		int gl_blend_equation_alpha = Page.INTBUFFER.get(0);
//
//		GL11.glGetInteger(GL14.GL_BLEND_SRC_RGB, Page.INTBUFFER);
//		int gl_blend_src_rgb = Page.INTBUFFER.get(0);
//
//		GL11.glGetInteger(GL14.GL_BLEND_SRC_ALPHA, Page.INTBUFFER);
//		int gl_blend_src_alpha = Page.INTBUFFER.get(0);
//
//		GL11.glGetInteger(GL14.GL_BLEND_DST_RGB, Page.INTBUFFER);
//		int gl_blend_dst_rgb = Page.INTBUFFER.get(0);
//
//		GL11.glGetInteger(GL14.GL_BLEND_DST_ALPHA, Page.INTBUFFER);
//		int gl_blend_dst_alpha = Page.INTBUFFER.get(0);
//
//		GL11.glGetInteger(GL30.GL_RENDERBUFFER_BINDING, Page.INTBUFFER);
//		int renderbuffer_binding = Page.INTBUFFER.get(0);
//
//		boolean gl_depth_test = GL11.glIsEnabled(GL11.GL_DEPTH_TEST);
//
//		GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, Page.INTBUFFER);
//		Page.GL_DRAW_FRAMEBUFFER_BINDING = Page.INTBUFFER.get(0);
//
//		GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, Page.INTBUFFER);
//		Page.GL_READ_FRAMEBUFFER_BINDING = Page.INTBUFFER.get(0);
//
////		Page.BYTEBUFFER.limit(16);
//		GL11.glGetBoolean(GL11.GL_DEPTH_WRITEMASK, Page.BYTEBUFFER);
//		boolean gl_depth_writemask = (Page.BYTEBUFFER.get(0) & 1) == 1;
//		//e0-takeDefault
//
//		GL11.glEnable(GL11.GL_BLEND);
//		GL20.glBlendEquationSeparate(GL14.GL_FUNC_ADD, GL14.GL_FUNC_ADD);
//		GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
//
//		GL11.glDepthMask(false);
//		GL11.glDisable(GL11.GL_DEPTH_TEST);
//
//		if (WIDTH != this.mc.displayWidth || HEIGHT != this.mc.displayHeight || Page.FONT_S != SmallConfig.CLIENT.font.scale/* || (FLAG & 1) == 1*//* || (this.state & 8) == 8*//* || MAIN_FRAMEBUFFER == -1*/)
//		{
//			WIDTH = this.mc.displayWidth;
//			HEIGHT = this.mc.displayHeight;
//
//			Page.FONT_S = SmallConfig.CLIENT.font.scale;
//
//			Page.SCREEN_RW = this.width / WIDTH;
//			Page.SCREEN_RH = this.height / HEIGHT;
//			Page.FONT_SH = Page.FONT_S / Page.SCREEN_RH;
//			Page.FONT_MH_SH = (int)(Page.FONT_MH * Page.FONT_SH);
//
//			for (Class clasz : Page.PAGE_CLASS_SET)
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
//		OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, Page.OFFSET_FRAMEBUFFER_1);
//
//		for (Page page : Page.PAGE_ARRAY)
//		{
//			page.init();
//		}
//
//		// GL11.glViewport(0, 0, (int)WIDTH, (int)HEIGHT);
//		//
//		// //s1-takeDefault
//		GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM, Page.INTBUFFER);
//		int gl_current_program = Page.INTBUFFER.get(0);
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
//		OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, Page.GL_READ_FRAMEBUFFER_BINDING);
//		OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, Page.GL_DRAW_FRAMEBUFFER_BINDING);
//
//		for (Page page : Page.PAGE_ARRAY)
//		{
//			page.preDraw();
//		}
//
//		for (Page page : Page.PAGE_ARRAY)
//		{
//			page.draw();
//		}
//
//		//s1-setDefault
//		GL11.glLoadMatrix(Page.OPENGL_PROJECTION_MATRIX_FLOATBUFFER);
//
//		GL11.glMatrixMode(Page.GL_MATRIX_MODE);
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

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public void handleMouseInput()
	{
		Mouse.MOUSE.handleMouseInput();
	}

	@Override
	public void keyTyped(char typed_char, int key_code) throws IOException
	{
		if (!Key.KEY.keyTyped(typed_char, key_code))
		{
			super.keyTyped(typed_char, key_code);
		}
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
	}
}
