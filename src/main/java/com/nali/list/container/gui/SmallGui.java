package com.nali.list.container.gui;

import com.nali.list.container.SmallContainer;
import com.nali.small.SmallConfig;
import com.nali.small.gui.key.KeyMenuMe;
import com.nali.small.gui.page.Page;
import com.nali.small.gui.page.PageMe;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.IntBuffer;
import java.util.HashSet;
import java.util.Set;

import static com.nali.small.gui.key.Key.KEY;
import static com.nali.small.gui.mouse.Mouse.*;
import static com.nali.small.gui.page.Page.*;
import static com.nali.small.gui.page.PageMe.openPageMe;
import static com.nali.small.gui.page.PageSmall.openPageSmall;
import static com.nali.system.opengl.memo.client.MemoC.*;

@SideOnly(Side.CLIENT)
public class SmallGui extends GuiContainer
{
	public static SmallGui SMALLGUI;
	public static Page[] PAGE_ARRAY;
	public static Set<Class> PAGE_CLASS_SET = new HashSet();
	public static float
		WIDTH,
		HEIGHT;
	public static int
		OFFSET_RENDER_BUFFER = -1,

		OFFSET_FRAMEBUFFER = -1,
		OFFSET_FRAMEBUFFER_0 = -1,
		OFFSET_FRAMEBUFFER_1 = -1,

		OFFSET_FRAMEBUFFER_TEXTURE = -1,
		OFFSET_FRAMEBUFFER_TEXTURE_0 = -1;

	public float partial_ticks;

	public SmallGui(Container container)
	{
		super(container);
		SMALLGUI = this;

		if (PAGE_ARRAY == null)
		{
			openPageSmall();
		}
	}

	public static SmallGui get(EntityPlayer entityplayer, World world, int x, int y, int z)//i d e
	{
		if (x != -1)
		{
			KeyMenuMe.ME &= 255-1;
			PageMe.ID_KEY = (long)y << 32 | x;
			PageMe.E_ID = z;

			openPageMe();
		}
		return new SmallGui(new SmallContainer());
	}

	public static void addSet()
	{
		for (Page page : PAGE_ARRAY)
		{
			PAGE_CLASS_SET.add(page.getClass());
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		KEY.run();
		MOUSE.run();

		this.partial_ticks = partialTicks;

		//s0-takeDefault
		GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, OPENGL_INTBUFFER);
		int gl_array_buffer_binding = OPENGL_INTBUFFER.get(0);

		GL11.glGetInteger(GL11.GL_MATRIX_MODE, OPENGL_INTBUFFER);
		GL_MATRIX_MODE = OPENGL_INTBUFFER.get(0);

		GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, OPENGL_PROJECTION_MATRIX_FLOATBUFFER);

		boolean gl_blend = GL11.glIsEnabled(GL11.GL_BLEND);

		GL11.glGetInteger(GL20.GL_BLEND_EQUATION_RGB, OPENGL_INTBUFFER);
		int gl_blend_equation_rgb = OPENGL_INTBUFFER.get(0);

		GL11.glGetInteger(GL20.GL_BLEND_EQUATION_ALPHA, OPENGL_INTBUFFER);
		int gl_blend_equation_alpha = OPENGL_INTBUFFER.get(0);

		GL11.glGetInteger(GL14.GL_BLEND_SRC_RGB, OPENGL_INTBUFFER);
		int gl_blend_src_rgb = OPENGL_INTBUFFER.get(0);

		GL11.glGetInteger(GL14.GL_BLEND_SRC_ALPHA, OPENGL_INTBUFFER);
		int gl_blend_src_alpha = OPENGL_INTBUFFER.get(0);

		GL11.glGetInteger(GL14.GL_BLEND_DST_RGB, OPENGL_INTBUFFER);
		int gl_blend_dst_rgb = OPENGL_INTBUFFER.get(0);

		GL11.glGetInteger(GL14.GL_BLEND_DST_ALPHA, OPENGL_INTBUFFER);
		int gl_blend_dst_alpha = OPENGL_INTBUFFER.get(0);

		GL11.glGetInteger(GL30.GL_RENDERBUFFER_BINDING, OPENGL_INTBUFFER);
		int renderbuffer_binding = OPENGL_INTBUFFER.get(0);

		boolean gl_depth_test = GL11.glIsEnabled(GL11.GL_DEPTH_TEST);

		GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
		GL_DRAW_FRAMEBUFFER_BINDING = OPENGL_INTBUFFER.get(0);

		GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
		GL_READ_FRAMEBUFFER_BINDING = OPENGL_INTBUFFER.get(0);
		//e0-takeDefault

		GL11.glEnable(GL11.GL_BLEND);
		GL20.glBlendEquationSeparate(GL14.GL_FUNC_ADD, GL14.GL_FUNC_ADD);
		GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);

		GL11.glDisable(GL11.GL_DEPTH_TEST);

		if (WIDTH != this.mc.displayWidth || HEIGHT != this.mc.displayHeight || FONT_S != SmallConfig.CLIENT.font.scale/* || (FLAG & 1) == 1*//* || (this.state & 8) == 8*//* || MAIN_FRAMEBUFFER == -1*/)
		{
			WIDTH = this.mc.displayWidth;
			HEIGHT = this.mc.displayHeight;

			Page.FONT_S = SmallConfig.CLIENT.font.scale;

			Page.SCREEN_RW = this.width / WIDTH;
			Page.SCREEN_RH = this.height / HEIGHT;
			Page.FONT_SH = Page.FONT_S / Page.SCREEN_RH;
			Page.FONT_MH_SH = (int)(FONT_MH * FONT_SH);

			for (Class clasz : PAGE_CLASS_SET)
			{
				try
				{
					Field byte_field = clasz.getField("BYTE");
					byte_field.set(null, (byte)((byte)byte_field.get(null) | 1));
				}
				catch (NoSuchFieldException | IllegalAccessException e)
				{
					try
					{
						Field byte_field = clasz.getDeclaredField("BYTE");
						byte_field.set(null, (byte)((byte)byte_field.get(null) | 1));
					}
					catch (NoSuchFieldException | IllegalAccessException ex)
					{
					}
				}
			}
		}

		//s0-OFFSET_FRAMEBUFFER_1
		OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_FRAMEBUFFER_1);

		for (Page page : PAGE_ARRAY)
		{
			page.init();
		}

		GL11.glViewport(0, 0, (int)WIDTH, (int)HEIGHT);

		//s1-takeDefault
		GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM, OPENGL_INTBUFFER);
		int gl_current_program = OPENGL_INTBUFFER.get(0);
		//e1-takeDefault

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		//e0-OFFSET_FRAMEBUFFER_1

		//s0-OFFSET_FRAMEBUFFER
		OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_FRAMEBUFFER);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, (int)WIDTH, (int)HEIGHT, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
		OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE, 0);

		for (Page page : PAGE_ARRAY)
		{
			page.detect();
		}

		OPENGL_BYTEBUFFER.limit(4);
		GL11.glReadPixels(MOUSE_X, MOUSE_Y, 1, 1, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, OPENGL_BYTEBUFFER);
		HIT = OPENGL_BYTEBUFFER.get(0);
		E_PAGE = OPENGL_BYTEBUFFER.get(1);
		if ((STATE & 1) == 1)
		{
			PAGE = E_PAGE;
			STATE &= 255-1;
		}
		//e0-OFFSET_FRAMEBUFFER

		//s0-MAIN_FRAMEBUFFER
		OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, GL_READ_FRAMEBUFFER_BINDING);
		OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER_BINDING);

		for (Page page : PAGE_ARRAY)
		{
			page.preDraw();
		}

		for (Page page : PAGE_ARRAY)
		{
			page.draw();
		}

		//s1-setDefault
		GL11.glLoadMatrix(OPENGL_PROJECTION_MATRIX_FLOATBUFFER);

		GL11.glMatrixMode(GL_MATRIX_MODE);

		OpenGlHelper.glUseProgram(gl_current_program);

		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, gl_array_buffer_binding);

//		OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, GL_READ_FRAMEBUFFER_BINDING);
//		OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER_BINDING);

		OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, renderbuffer_binding);

		if (gl_blend)
		{
			GL11.glEnable(GL11.GL_BLEND);
		}
		else
		{
			GL11.glDisable(GL11.GL_BLEND);
		}

		GL20.glBlendEquationSeparate(gl_blend_equation_rgb, gl_blend_equation_alpha);
		GL14.glBlendFuncSeparate(gl_blend_src_rgb, gl_blend_dst_rgb, gl_blend_src_alpha, gl_blend_dst_alpha);

		if (gl_depth_test)
		{
			GL11.glEnable(GL11.GL_DEPTH_TEST);
		}
		else
		{
			GL11.glDisable(GL11.GL_DEPTH_TEST);
		}
		//e1-setDefault
		//e0-MAIN_FRAMEBUFFER

		this.defaultState(mouseX, mouseY);
	}

	@Override
	public void handleMouseInput()
	{
		MOUSE_X = Mouse.getEventX();
		MOUSE_Y = Mouse.getEventY();

		int k = Mouse.getEventButton();
		int mouse = Mouse.getEventDWheel();
		float eventdwheel = 0;

		if (mouse > 0)
		{
			eventdwheel = 1.5F;
		}
		else if (mouse < 0)
		{
			eventdwheel = -1.5F;
		}

		if (eventdwheel != 0)
		{
			if (EVENTDWHEEL > 0 && eventdwheel < 0)
			{
				EVENTDWHEEL = 0;
			}
			else if (EVENTDWHEEL < 0 && eventdwheel > 0)
			{
				EVENTDWHEEL = 0;
			}
			EVENTDWHEEL += eventdwheel;
		}

		if (Mouse.getEventButtonState())//c
		{
			STATE |= 2;
		}
		else if (k != -1)//r
		{
			STATE |= 1;
			STATE &= 255-2;
		}
	}

	@Override
	public void keyTyped(char typedChar, int keyCode) throws IOException
	{
		if (keyCode != Keyboard.KEY_ESCAPE)
		{
			KEY.detect(typedChar, keyCode);
		}
		else
		{
			super.keyTyped(typedChar, keyCode);
		}
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
	}

	public void defaultState(int mouseX, int mouseY)
	{
		GlStateManager.disableRescaleNormal();
		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableLighting();
		GlStateManager.disableDepth();
		RenderHelper.enableGUIStandardItemLighting();

		GlStateManager.pushMatrix();

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableRescaleNormal();
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		RenderHelper.disableStandardItemLighting();
		RenderHelper.enableGUIStandardItemLighting();
		net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.GuiContainerEvent.DrawForeground(this, mouseX, mouseY));

		GlStateManager.popMatrix();

		GlStateManager.enableLighting();
		GlStateManager.enableDepth();
		RenderHelper.enableStandardItemLighting();
	}
}
