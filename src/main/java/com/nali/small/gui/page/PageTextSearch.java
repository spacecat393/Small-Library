//package com.nali.small.gui.page;
//
//import com.nali.list.container.gui.SmallGui;
//import com.nali.list.data.SmallData;
//import com.nali.system.opengl.memo.client.MemoS;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.OpenGlHelper;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import org.lwjgl.opengl.*;
//
//import java.awt.*;
//import java.nio.ByteBuffer;
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.nali.key.KeyHelper.generateRainbowColor;
//import static com.nali.list.container.gui.SmallGui.SMALLGUI;
//import static com.nali.system.ClientLoader.S_LIST;
//import static com.nali.system.opengl.memo.client.MemoC.*;
//
//@SideOnly(Side.CLIENT)
//public class PageTextSearch extends Page
//{
//	public static List<Integer>
//		TEXTURE_INTEGER_LIST = new ArrayList(),
//		ARRAY_BUFFER_INTEGER_LIST = new ArrayList();
//
//	public static byte
//		BYTE = 1,//init1 2-4-8-16-32-64-128
//		INDEX,
//		END_TEXT,
//		FIRST;
//
//	//init size & data
//	public static byte[] STATE_BYTE_ARRAY;//1_endString 2_hasString 4_longString 8_longEndString
//	public static float[] L_FLOAT_ARRAY;
////	public static float[] M_R_FLOAT_ARRAY;
//	public static String[] STRING_ARRAY;
//
//	//init size
//	public static String[] CURRENT_STRING_ARRAY;
//
//	public static long LAST_TIME = Minecraft.getSystemTime();//System.currentTimeMillis();
//	public static int LIMIT_TIME = 1000;
//
//	public float[] color_float_array;
//
//	public static int
//		framebuffer_color_texture,
//		framebuffer_depth_stencil_texture,
//		framebuffer;
//
//	@Override
//	public void init()
//	{
//		if ((FIRST & 1) == 0)
//		{
//			framebuffer_color_texture = GL11.glGenTextures();
//			framebuffer_depth_stencil_texture = GL11.glGenTextures();
//			framebuffer = OpenGlHelper.glGenFramebuffers();
//
//			//to png
////			GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
////			int gl_draw_framebuffer_binding = OPENGL_INTBUFFER.get(0);
////			GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
////			int gl_read_framebuffer_binding = OPENGL_INTBUFFER.get(0);
////
////			OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, Minecraft.getMinecraft().getFramebuffer().framebufferTexture);
////
////			GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
////			int gl_texture_binding_2d = OPENGL_INTBUFFER.get(0);
////			int texture = GL11.glGenTextures();
////			GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
////			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, ARBTextureRg.GL_R32F, (int)SmallGui.WIDTH, (int)SmallGui.HEIGHT, 0, GL11.GL_RED, GL11.GL_FLOAT, (ByteBuffer)null);
////			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, texture, 0);
////			GL11.glBindTexture(GL11.GL_TEXTURE_2D, gl_texture_binding_2d);
////
////			OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, gl_read_framebuffer_binding);
////			OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, gl_draw_framebuffer_binding);
//		}
//
//		//s0-texture
//		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//		int gl_texture_binding_2d = OPENGL_INTBUFFER.get(0);
//
////			int framebuffer_color_texture = GL11.glGenTextures();
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, framebuffer_color_texture);
//		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, (int)SmallGui.WIDTH, (int)SmallGui.HEIGHT, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (ByteBuffer)null);
//
////			int framebuffer_depth_texture = GL11.glGenTextures();
////			int framebuffer_depth_stencil_texture = GL11.glGenTextures();
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, framebuffer_depth_stencil_texture);
//		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, EXTPackedDepthStencil.GL_DEPTH24_STENCIL8_EXT, (int)SmallGui.WIDTH, (int)SmallGui.HEIGHT, 0, EXTPackedDepthStencil.GL_DEPTH_STENCIL_EXT, EXTPackedDepthStencil.GL_UNSIGNED_INT_24_8_EXT, (ByteBuffer)null);
//
////			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, (int)SmallGui.WIDTH, (int)SmallGui.HEIGHT, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (ByteBuffer)null);
//
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, gl_texture_binding_2d);
//		//e0-texture
//
//		if ((FIRST & 1) == 0)
//		{
//			GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
//			int gl_draw_framebuffer_binding = OPENGL_INTBUFFER.get(0);
//			GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
//			int gl_read_framebuffer_binding = OPENGL_INTBUFFER.get(0);
//
//			OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, framebuffer);
//			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, framebuffer_color_texture, 0);
//			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, EXTFramebufferObject.GL_STENCIL_ATTACHMENT_EXT, GL11.GL_TEXTURE_2D, framebuffer_depth_stencil_texture, 0);
//			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, framebuffer_depth_stencil_texture, 0);
//
//			OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, gl_read_framebuffer_binding);
//			OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, gl_draw_framebuffer_binding);
//			FIRST |= 1;
//		}
//
//		byte init = (byte)(BYTE >> 1);
//		boolean should_init = (BYTE & 1) == 1;
//
//		if (INDEX == init)
//		{
//			INDEX = 0;
//
//			if (should_init)
//			{
//				this.clear(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST);
//				BYTE &= 255-1;
//			}
//		}
//
//		if (INDEX == 0)
//		{
//			Color color = generateRainbowColor();
//			this.color_float_array = new float[]{color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, 1.0F};
//
//			long current_time = Minecraft.getSystemTime();
//			if (current_time - LAST_TIME >= LIMIT_TIME)
//			{
//				LAST_TIME = current_time;
//				END_TEXT ^= 1;
//			}
//		}
//
//		if (!STRING_ARRAY[INDEX].equals(CURRENT_STRING_ARRAY[INDEX]))
//		{
//			BYTE |= 1;
//		}
//		CURRENT_STRING_ARRAY[INDEX] = STRING_ARRAY[INDEX];
//
//		if (should_init)
//		{
//			Minecraft minecraft = SMALLGUI.mc;
//			float
//				h2 = 2.0F * 0.005F * SmallGui.HEIGHT,
//				x = SmallGui.WIDTH - L_FLOAT_ARRAY[INDEX] - 2.0F * 0.005F * SmallGui.WIDTH,
//				y = SmallGui.HEIGHT - FONT_MH_SH - h2;
//
//			String string = STRING_ARRAY[INDEX];
//
//			//s0-initSearchEnd
//			String end_string = Page.STRING_ARRAY[30];
//			int l = (int)(minecraft.fontRenderer.getStringWidth(string + end_string) * FONT_SH);
//			this.initTextHorizontal
//			(
//				ARRAY_BUFFER_INTEGER_LIST,
//				TEXTURE_INTEGER_LIST,
//				end_string,
//				(int)(minecraft.fontRenderer.getStringWidth(end_string) * FONT_SH),
//				FONT_MH_SH,
//				x +
//					l,
//				y,
//				FONT_SH
//			);
//
////			if (x + l > )
////			{
////				STATE_BYTE_ARRAY[INDEX] |= 8;
////			}
////			else
////			{
////				STATE_BYTE_ARRAY[INDEX] &= 255-8;
////			}
//			//e0-initSearchEnd
//
//			if (!string.isEmpty())
//			{
//				STATE_BYTE_ARRAY[INDEX] |= 2;
//
//				//s0-initInputText
//				l = (int)(minecraft.fontRenderer.getStringWidth(string) * FONT_SH);
//
//				this.initTextHorizontal
//				(
//					ARRAY_BUFFER_INTEGER_LIST,
//					TEXTURE_INTEGER_LIST,
//					string,
//					l,
//					FONT_MH_SH,
//					x,
//					y,
//					FONT_SH
//				);
//
////				if (x + l > )
////				{
////					STATE_BYTE_ARRAY[INDEX] |= 4;
////				}
////				else
////				{
////					STATE_BYTE_ARRAY[INDEX] &= 255-4;
////				}
//				//e0-initInputText
//			}
//			else
//			{
//				STATE_BYTE_ARRAY[INDEX] &= 255-2;
//			}
//		}
//	}
//
//	@Override
//	public void detect()
//	{
//	}
//
//	boolean should_init;
//
//	@Override
////	public void preDraw()
//	public void draw()
//	{
//		short index = 0;
//		for (int i = 0; i < INDEX; ++i)
//		{
//			index += ((STATE_BYTE_ARRAY[i] & 2) == 2) ? 2 : 1;
//		}
//
//		boolean has_string = (STATE_BYTE_ARRAY[INDEX] & 2) == 2;
//
//		if (ARRAY_BUFFER_INTEGER_LIST.size() > index + (has_string ? 1 : 0))
//		{
//			MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 3);
//			OpenGlHelper.glUseProgram(rs.program);
//			int v = rs.attriblocation_int_array[0];
//			GL20.glEnableVertexAttribArray(v);
//
//			//s-f
////			GL30.glGetFramebufferAttachmentParameter(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL30.GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME, OPENGL_INTBUFFER);
////			int gl_color_attachment0 = OPENGL_INTBUFFER.get(0);
////			GL30.glGetFramebufferAttachmentParameter(OpenGlHelper.GL_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT1, GL30.GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME, OPENGL_INTBUFFER);
////			int gl_color_attachment1 = OPENGL_INTBUFFER.get(0);
////			GL30.glGetFramebufferAttachmentParameter(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL30.GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME, OPENGL_INTBUFFER);
////			int gl_depth_attachment = OPENGL_INTBUFFER.get(0);
////			GL30.glGetFramebufferAttachmentParameter(OpenGlHelper.GL_FRAMEBUFFER, EXTFramebufferObject.GL_STENCIL_ATTACHMENT_EXT, GL30.GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME, OPENGL_INTBUFFER);
////			int gl_stencil_attachment_ext = OPENGL_INTBUFFER.get(0);
//
//			GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
//			int gl_draw_framebuffer_binding = OPENGL_INTBUFFER.get(0);
//			GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
//			int gl_read_framebuffer_binding = OPENGL_INTBUFFER.get(0);
//
//			OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, framebuffer);
//
//			//
////					//s0-onlyStencil
////					renderbufferStencil = OpenGlHelper.glGenRenderbuffers();
////					OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, renderbufferStencil);
////					OpenGlHelper.glRenderbufferStorage(OpenGlHelper.GL_RENDERBUFFER, GL30.GL_STENCIL_INDEX8, width, height);
////					OpenGlHelper.glFramebufferRenderbuffer(OpenGlHelper.GL_FRAMEBUFFER, GL30.GL_STENCIL_ATTACHMENT, OpenGlHelper.GL_RENDERBUFFER, renderbufferStencil);
////					//e0-onlyStencil
////
////					//s0-both
//////					int depthStencilTexture = glGenTextures();
//////					glBindTexture(GL_TEXTURE_2D, depthStencilTexture);
//////
//////					glTexImage2D(GL_TEXTURE_2D, 0, GL_DEPTH24_STENCIL8_EXT, width, height, 0, GL_DEPTH_STENCIL_EXT, GL_UNSIGNED_INT_24_8_EXT, (ByteBuffer) null);
//////
//////
//////					OpenGlHelper.glRenderbufferStorage(OpenGlHelper.GL_RENDERBUFFER, EXTPackedDepthStencil.GL_DEPTH24_STENCIL8_EXT, this.framebufferTextureWidth, this.framebufferTextureHeight);
//////					OpenGlHelper.glFramebufferRenderbuffer(OpenGlHelper.GL_FRAMEBUFFER, EXTFramebufferObject.GL_DEPTH_ATTACHMENT_EXT, OpenGlHelper.GL_RENDERBUFFER, this.depthBuffer);
//////					OpenGlHelper.glFramebufferRenderbuffer(OpenGlHelper.GL_FRAMEBUFFER, EXTFramebufferObject.GL_STENCIL_ATTACHMENT_EXT, OpenGlHelper.GL_RENDERBUFFER, this.depthBuffer);
////					//e0-both
////
//			//s0-use
//			//take
//			GL11.glGetInteger(GL11.GL_STENCIL_WRITEMASK, OPENGL_INTBUFFER);
//			int gl_stencil_writemask = OPENGL_INTBUFFER.get(0);
//
//			// Clear the stencil buffer, setting all values to 1 (default draw region)
////			GL11.glClearStencil(1);
////			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT | GL11.GL_STENCIL_BUFFER_BIT);
//
////			// Set up the stencil function: always pass the stencil test
////			GL11.glStencilFunc(GL11.GL_ALWAYS, 1, 0xFF);  // Pass the test unconditionally
////					glGetIntegerv(GL_STENCIL_BACK_WRITEMASK, stencilBackMask, 0);
////					glStencilMaskSeparate(GL_BACK, stencilBackMask[0]);
//			GL11.glGetInteger(GL11.GL_STENCIL_FAIL, OPENGL_INTBUFFER);
//			int gl_stencil_fail = OPENGL_INTBUFFER.get(0);
//			GL11.glGetInteger(GL11.GL_STENCIL_PASS_DEPTH_FAIL, OPENGL_INTBUFFER);
//			int gl_stencil_pass_depth_fail = OPENGL_INTBUFFER.get(0);
//			GL11.glGetInteger(GL11.GL_STENCIL_PASS_DEPTH_PASS, OPENGL_INTBUFFER);
//			int gl_stencil_pass_depth_pass = OPENGL_INTBUFFER.get(0);
//
//			GL11.glGetInteger(GL20.GL_STENCIL_BACK_FUNC, OPENGL_INTBUFFER);
//			int gl_stencil_back_func = OPENGL_INTBUFFER.get(0);
//			GL11.glGetInteger(GL20.GL_STENCIL_BACK_REF, OPENGL_INTBUFFER);
//			int gl_stencil_back_ref = OPENGL_INTBUFFER.get(0);
//			GL11.glGetInteger(GL20.GL_STENCIL_BACK_VALUE_MASK, OPENGL_INTBUFFER);
//			int gl_stencil_back_value_mask = OPENGL_INTBUFFER.get(0);
//
//			//-
//
////			boolean gl_depth_test = GL11.glIsEnabled(GL11.GL_DEPTH_TEST);
////			boolean gl_depth_writemask = (OPENGL_BYTEBUFFER.get(0) & 1) == 1;
//			boolean gl_stencil_test = GL11.glIsEnabled(GL11.GL_STENCIL_TEST);
//
//			GL11.glEnable(GL11.GL_STENCIL_TEST);
////			GL11.glDepthMask(true);
////			GL11.glEnable(GL11.GL_DEPTH_TEST);
//			//buffer is
//			GL11.glStencilMask(0xFF);  // Enable writing to the stencil buffer
//			GL11.glStencilFunc(GL11.GL_ALWAYS, 1, 0xFF); // Always pass the stencil test
////			GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_REPLACE, GL11.GL_REPLACE);
//			GL11.glStencilOp(GL11.GL_REPLACE, GL11.GL_REPLACE, GL11.GL_REPLACE); // Replace stencil value with reference value (1) on stencil pass
////			GL11.glStencilOp(GL11.GL_ONE, GL11.GL_ONE, GL11.GL_ONE); // Replace stencil value with reference value (1) on stencil pass
//
////			GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_ZERO);  // Set to 0 where fragments are discarded
////			GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_REPLACE);  // Replace the stencil value with 1 where fragments pass
//			//s1-draw
//			if ((STATE_BYTE_ARRAY[INDEX] & 1) == 1 && (END_TEXT & 1) == 1 && !should_init)
//			{
//				this.drawQuadVUv
//				(
//					rs,
//					VEC2_FLOAT_ARRAY,
//					this.color_float_array,
////					FULL_ARRAY_BUFFER,
//					ARRAY_BUFFER_INTEGER_LIST.get(index),
//					TEXTURE_INTEGER_LIST.get(index)
////					Minecraft.getMinecraft().getFramebuffer().framebufferTexture
//				);
//				should_init = true;
//			}
//			//e1-draw
//
////			GL11.glDepthMask(gl_depth_writemask);
////			if (gl_depth_test)
////			{
////				GL11.glEnable(GL11.GL_DEPTH_TEST);
////			}
////			else
////			{
////				GL11.glDisable(GL11.GL_DEPTH_TEST);
////			}
//
//			GL11.glStencilMask(0x00);  // Disable writing to the stencil buffer
////			GL11.glStencilFunc(GL11.GL_NOTEQUAL, 1, 0xFF);  // Only render where the stencil value is 1
//			GL11.glStencilFunc(GL11.GL_EQUAL, 1, 0xFF);  // Only render where the stencil value is 1
////			GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_REPLACE); // Replace stencil value with reference value (1) on stencil pass
//			GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_KEEP);
//
//			if ((STATE_BYTE_ARRAY[INDEX] & 1) == 1 && (END_TEXT & 1) == 1)
//			{
//				this.drawQuadVUv
//				(
//					rs,
//					VEC2_FLOAT_ARRAY,
//					this.color_float_array,
//					FULL_ARRAY_BUFFER,
////					ARRAY_BUFFER_INTEGER_LIST.get(index),
////					TEXTURE_INTEGER_LIST.get(index)
////					Minecraft.getMinecraft().getFramebuffer().framebufferTexture
//					framebuffer_depth_stencil_texture
//				);
//			}
////			this.drawQuadVUv
////			(
////				rs,
////				VEC2_FLOAT_ARRAY,
//////				this.color_float_array,
////				COLOR_VEC4_2D_FLOAT_ARRAY[0],
////				FULL_ARRAY_BUFFER,
////				framebuffer_depth_stencil_texture
////			);
//
//			//how is Stencil work
//			//set
//			GL11.glStencilOp(gl_stencil_fail, gl_stencil_pass_depth_fail, gl_stencil_pass_depth_pass);
//			GL11.glStencilMask(gl_stencil_writemask);
//			GL11.glStencilFunc(gl_stencil_back_func, gl_stencil_back_ref, gl_stencil_back_value_mask);  // Only render where the stencil value is 1
//
//			if (gl_stencil_test)
//			{
//				GL11.glEnable(GL11.GL_STENCIL_TEST);
//			}
//			else
//			{
//				GL11.glDisable(GL11.GL_STENCIL_TEST);
//			}
//
//			//e0-use
//			//s0-test
//			boolean gl_scissor_test = GL11.glIsEnabled(GL11.GL_SCISSOR_TEST);
//
//			GL11.glGetInteger(GL11.GL_SCISSOR_BOX, OPENGL_INTBUFFER);
//			int gl_scissor_box_x = OPENGL_INTBUFFER.get(0);
//			int gl_scissor_box_y = OPENGL_INTBUFFER.get(1);
//			int gl_scissor_box_width = OPENGL_INTBUFFER.get(2);
//			int gl_scissor_box_height = OPENGL_INTBUFFER.get(3);
//
//			GL11.glEnable(GL11.GL_SCISSOR_TEST);
////			GL11.glViewport(0, 0, (int)SmallGui.WIDTH, (int)SmallGui.HEIGHT);
////			GL11.glScissor(10, 10, 200, 200);
//			GL11.glScissor(0, 0, (int)SmallGui.WIDTH, (int)SmallGui.HEIGHT);
//			GL11.glViewport(0, 0, (int)SmallGui.WIDTH / 2, (int)SmallGui.HEIGHT / 2);
//
//			OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, gl_read_framebuffer_binding);
//			OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, gl_draw_framebuffer_binding);
//
//			//s1-draw
//			this.drawQuadVUv
//			(
//				rs,
//				VEC2_FLOAT_ARRAY,
////				this.color_float_array,
//				COLOR_VEC4_2D_FLOAT_ARRAY[0],
//				FULL_ARRAY_BUFFER,
//				framebuffer_color_texture
////				framebuffer_depth_stencil_texture
////				Minecraft.getMinecraft().getFramebuffer().framebufferTexture
//			);
//
//			//glDrawBuffers use as apply texture next draw
////			//draw textures to framebuffer
//////			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().getFramebuffer().framebufferTexture, 0);
////			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT1, GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().getFramebuffer().framebufferTexture, 0);
////			IntBuffer drawBuffers = BufferUtils.createIntBuffer(2);
//////			OPENGL_INTBUFFER.limit(2);
//////			OPENGL_FLOATBUFFER.clear();
//////			OPENGL_INTBUFFER.put(OpenGlHelper.GL_COLOR_ATTACHMENT0);
//////			OPENGL_INTBUFFER.put(GL30.GL_COLOR_ATTACHMENT1);
//////			OPENGL_INTBUFFER.flip();
////
////			drawBuffers.put(OpenGlHelper.GL_COLOR_ATTACHMENT0);
////			drawBuffers.put(GL30.GL_COLOR_ATTACHMENT1);
////			drawBuffers.flip();
////			//won't draw depth and Stencil
//////			GL20.glDrawBuffers(OPENGL_INTBUFFER);
////			GL20.glDrawBuffers(drawBuffers);
//////			OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, gl_read_framebuffer_binding);
////			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, gl_color_attachment0, 0);
////			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT1, GL11.GL_TEXTURE_2D, gl_color_attachment1, 0);
//////			OPENGL_INTBUFFER.limit(16);
//
//			//e1-draw
//
//			GL11.glScissor(gl_scissor_box_x, gl_scissor_box_y, gl_scissor_box_width, gl_scissor_box_height);
//
//			if (gl_scissor_test)
//			{
//				GL11.glEnable(GL11.GL_SCISSOR_TEST);
//			}
//			else
//			{
//				GL11.glDisable(GL11.GL_SCISSOR_TEST);
//			}
//			GL11.glViewport(0, 0, (int)SmallGui.WIDTH, (int)SmallGui.HEIGHT);
//			//e0-test
//
////			GL11.glDeleteTextures(framebuffer_color_texture);
////			GL11.glDeleteTextures(framebuffer_depth_stencil_texture);
////			OpenGlHelper.glDeleteFramebuffers(framebuffer);
//			//e-f
//
//			if (has_string)
//			{
////				if ((STATE_BYTE_ARRAY[INDEX] & 4) == 4)
////				{
////					//s0-onlyStencil
////					renderbufferStencil = OpenGlHelper.glGenRenderbuffers();
////					OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, renderbufferStencil);
////					OpenGlHelper.glRenderbufferStorage(OpenGlHelper.GL_RENDERBUFFER, GL30.GL_STENCIL_INDEX8, width, height);
////					OpenGlHelper.glFramebufferRenderbuffer(OpenGlHelper.GL_FRAMEBUFFER, GL30.GL_STENCIL_ATTACHMENT, OpenGlHelper.GL_RENDERBUFFER, renderbufferStencil);
////					//e0-onlyStencil
////
////					//s0-both
//////					int depthStencilTexture = glGenTextures();
//////					glBindTexture(GL_TEXTURE_2D, depthStencilTexture);
//////
//////					glTexImage2D(GL_TEXTURE_2D, 0, GL_DEPTH24_STENCIL8_EXT, width, height, 0, GL_DEPTH_STENCIL_EXT, GL_UNSIGNED_INT_24_8_EXT, (ByteBuffer) null);
//////
//////
//////					OpenGlHelper.glRenderbufferStorage(OpenGlHelper.GL_RENDERBUFFER, EXTPackedDepthStencil.GL_DEPTH24_STENCIL8_EXT, this.framebufferTextureWidth, this.framebufferTextureHeight);
//////					OpenGlHelper.glFramebufferRenderbuffer(OpenGlHelper.GL_FRAMEBUFFER, EXTFramebufferObject.GL_DEPTH_ATTACHMENT_EXT, OpenGlHelper.GL_RENDERBUFFER, this.depthBuffer);
//////					OpenGlHelper.glFramebufferRenderbuffer(OpenGlHelper.GL_FRAMEBUFFER, EXTFramebufferObject.GL_STENCIL_ATTACHMENT_EXT, OpenGlHelper.GL_RENDERBUFFER, this.depthBuffer);
////					//e0-both
////
////					//s0-use
////					//take
////					GL11.glGetInteger(GL11.GL_STENCIL_WRITEMASK, OPENGL_INTBUFFER);
////					int gl_stencil_writemask = OPENGL_INTBUFFER.get(0);
////
////					// Clear the stencil buffer, setting all values to 1 (default draw region)
////					GL11.glClearStencil(1);
////					GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT);
////
////					// Set up the stencil function: always pass the stencil test
////					GL11.glStencilFunc(GL11.GL_ALWAYS, 1, 0xFF);  // Pass the test unconditionally
//////					glGetIntegerv(GL_STENCIL_BACK_WRITEMASK, stencilBackMask, 0);
//////					glStencilMaskSeparate(GL_BACK, stencilBackMask[0]);
////					GL11.glGetInteger(GL11.GL_STENCIL_FAIL, OPENGL_INTBUFFER);
////					int gl_stencil_fail = OPENGL_INTBUFFER.get(0);
////					GL11.glGetInteger(GL11.GL_STENCIL_PASS_DEPTH_FAIL, OPENGL_INTBUFFER);
////					int gl_stencil_pass_depth_fail = OPENGL_INTBUFFER.get(0);
////					GL11.glGetInteger(GL11.GL_STENCIL_PASS_DEPTH_PASS, OPENGL_INTBUFFER);
////					int gl_stencil_pass_depth_pass = OPENGL_INTBUFFER.get(0);
////
////					GL11.glGetInteger(GL20.GL_STENCIL_BACK_FUNC, OPENGL_INTBUFFER);
////					int gl_stencil_back_func = OPENGL_INTBUFFER.get(0);
////					GL11.glGetInteger(GL20.GL_STENCIL_BACK_REF, OPENGL_INTBUFFER);
////					int gl_stencil_back_ref = OPENGL_INTBUFFER.get(0);
////					GL11.glGetInteger(GL20.GL_STENCIL_BACK_VALUE_MASK, OPENGL_INTBUFFER);
////					int gl_stencil_back_value_mask = OPENGL_INTBUFFER.get(0);
////
////					//-
////					// Stencil operations:
////					// - Set stencil to 0 if depth or stencil test fails (for discarded or skipped fragments)
////					// - Keep the current stencil value for other conditions
////					GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_ZERO);  // Set to 0 where fragments are discarded
////
////					//+
////
////					// Set up the stencil operations
////					GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_REPLACE);  // Replace the stencil value with 1 where fragments pass
////					//-
////
////
////
////					GL11.glStencilMask(0xFF);  // Enable writing to the stencil buffer
////
////
////					GL11.glStencilFunc(GL11.GL_EQUAL, 1, 0xFF);  // Only render where the stencil value is 1
////					GL11.glStencilMask(0x00);  // Disable writing to the stencil buffer
////
////					//set
////					GL11.glStencilMask(gl_stencil_writemask);
////					GL11.glStencilOp(gl_stencil_fail, gl_stencil_pass_depth_fail, gl_stencil_pass_depth_pass);
////					GL11.glStencilFunc(gl_stencil_back_func, gl_stencil_back_ref, gl_stencil_back_value_mask);  // Only render where the stencil value is 1
////
////					//e0-use
////
////					boolean gl_scissor_test = GL11.glIsEnabled(GL11.GL_SCISSOR_TEST);
////
////					GL11.glGetInteger(GL11.GL_SCISSOR_BOX, OPENGL_INTBUFFER);
////
////					GL11.glEnable(GL11.GL_SCISSOR_TEST);
////					GL11.glScissor(10, 10, 10, 10);
////					//draw
////
////					GL11.glScissor(OPENGL_INTBUFFER.get(0), OPENGL_INTBUFFER.get(1), OPENGL_INTBUFFER.get(2), OPENGL_INTBUFFER.get(3));
////
////					if (gl_scissor_test)
////					{
////						GL11.glEnable(GL11.GL_SCISSOR_TEST);
////					}
////					else
////					{
////						GL11.glDisable(GL11.GL_SCISSOR_TEST);
////					}
////				}
//				this.drawQuadVUv
//				(
//					rs,
//					VEC2_FLOAT_ARRAY,
//					this.color_float_array,
//					ARRAY_BUFFER_INTEGER_LIST.get(index + 1),
//					TEXTURE_INTEGER_LIST.get(index + 1)
//				);
//			}
//
//			if ((STATE_BYTE_ARRAY[INDEX] & 1) == 1 && (END_TEXT & 1) == 1)
//			{
////				if ((STATE_BYTE_ARRAY[INDEX] & 8) == 8)
////				{
////
////				}
//				this.drawQuadVUv
//				(
//					rs,
//					VEC2_FLOAT_ARRAY,
//					this.color_float_array,
//					ARRAY_BUFFER_INTEGER_LIST.get(index),
//					TEXTURE_INTEGER_LIST.get(index)
//				);
//			}
//
//			GL20.glDisableVertexAttribArray(v);
//		}
//		++INDEX;
//	}
//
//	@Override
////	public void draw()
//	public void preDraw()
//	{
////		this.preDraw();
////		++INDEX;
//	}
//}
