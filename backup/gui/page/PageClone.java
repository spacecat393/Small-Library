//package com.nali.small.gui.page;
//
//import com.nali.list.container.gui.SmallGui;
//import com.nali.render.RenderO;
//import com.nali.small.entity.memo.client.ClientE;
//import com.nali.system.opengl.memo.client.MemoA1;
//import com.nali.system.opengl.memo.client.MemoC;
//import com.nali.system.opengl.memo.client.MemoS;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.FontRenderer;
//import net.minecraft.client.renderer.OpenGlHelper;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import org.lwjgl.opengl.GL11;
//import org.lwjgl.opengl.GL13;
//import org.lwjgl.opengl.GL14;
//import org.lwjgl.opengl.GL20;
//
//import java.nio.ByteBuffer;
//import java.nio.IntBuffer;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@SideOnly(Side.CLIENT)
//public abstract class PageClone
//{
//	public static PageClone[] PAGE_ARRAY;
//	public static Set<Class> PAGE_CLASS_SET = new HashSet();
//
//	public static List<Integer>
//		TEXTURE_INTEGER_LIST = new ArrayList(),
//		ARRAY_BUFFER_INTEGER_LIST = new ArrayList();
//
//	public static List<Byte> INIT_BYTE_LIST = new ArrayList();//1bit as init
//	public static byte INIT_BYTE_BIT;
//	//1+2+4=7 1+2+4+8=15
//
//	public static float[] VEC2_FLOAT_ARRAY = new float[2];
//	public static float[][] COLOR_VEC4_2D_FLOAT_ARRAY =
//	{
//		new float[]{1.0F, 1.0F, 1.0F, 1.0F}, //0
//		new float[]{0.0F, 0.0F, 0.0F, 1.0F}, //1
//		new float[]{0.5F, 1.0F, 0.5F, 1.0F}, //2
//		new float[]{1.0F, 0.5F, 0.5F, 1.0F}, //3
//		new float[]{0.5F, 0.5F, 1.0F, 1.0F} //4
//	};
//
//	public static String[] STRING_ARRAY;
//	public static byte
//		FONT_MH = 9;
//	public static float
//		FONT_SH,
//
//		SCREEN_RW,
//		SCREEN_RH;
//	public static int
//		FONT_MH_SH,
//		FONT_S,
//
//		//current
//		GL_MATRIX_MODE,
//		GL_DRAW_FRAMEBUFFER_BINDING,
//		GL_READ_FRAMEBUFFER_BINDING,
//		GL_ARRAY_BUFFER_BINDING,
//		GL_TEXTURE_BINDING_2D,
//		GL_ACTIVE_TEXTURE,
//		GL_TEXTURE_BINDING_2D_0,
//		GL_TEXTURE_MIN_FILTER_0,
//		GL_TEXTURE_MAG_FILTER_0;
//
//	public static void setByte(/*byte max_bit*/)
//	{
//		//INIT_BYTE_LIST need 1 size on start
////		int new_size = 8 - INIT_BYTE_BIT;
////		INIT_BYTE_BIT += max_bit;
////		byte max_byte = (byte)Math.ceil((INIT_BYTE_BIT - new_size) / 8.0F);
////		for (byte mb = 0; mb < max_byte; ++mb)
////		{
////			INIT_BYTE_LIST.add((byte)0);
////		}
////		INIT_BYTE_BIT %= 8;
//		//Bit 1
//		INIT_BYTE_BIT %= 8;
//		if (INIT_BYTE_BIT == 0)
//		{
//			INIT_BYTE_LIST.add((byte)0);
//		}
//		++INIT_BYTE_BIT;
//	}
//
//	public void clear()
//	{
//		for (int array_buffer : ARRAY_BUFFER_INTEGER_LIST)
//		{
//			OpenGlHelper.glDeleteBuffers(array_buffer);
//		}
//		for (int texture : TEXTURE_INTEGER_LIST)
//		{
//			GL11.glDeleteTextures(texture);
//		}
//
//		ARRAY_BUFFER_INTEGER_LIST.clear();
//		TEXTURE_INTEGER_LIST.clear();
//	}
//
//	public float[] createQuadVUv(float x0, float y0, float x1, float y1, float fwidth, float fheight)
//	{
//		float nx1 = (2.0F * x0 / fwidth) - 1.0F;
//		float ny1 = (2.0F * y0 / fheight) - 1.0F;
//
//		float nx2 = (2.0F * x1 / fwidth) - 1.0F;
//		float ny2 = (2.0F * y1 / fheight) - 1.0F;
//
//		return new float[]
//		{
//			nx1, ny2, 0.0F, 1.0F,
//			nx1, ny1, 0.0F, 0.0F,
//			nx2, ny1, 1.0F, 0.0F,
//
//			nx1, ny2, 0.0F, 1.0F,
//			nx2, ny1, 1.0F, 0.0F,
//			nx2, ny2, 1.0F, 1.0F
//		};
//	}
//
//	public void initTextHorizontal(String string, int width, int height, float x, float y, float scale)
//	{
//		Minecraft minecraft = SmallGui.SMALLGUI.mc;
//		ARRAY_BUFFER_INTEGER_LIST.add(MemoA1.genBuffer(MemoC.createFloatByteBuffer(this.createQuadVUv(x, y, width + x, height + y, SmallGui.WIDTH, SmallGui.HEIGHT/*, 1.0F, 1.0F*/)/*, true*/)));
//
//		int texture = GL11.glGenTextures();
//		TEXTURE_INTEGER_LIST.add(texture);
//
//		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//		GL_TEXTURE_BINDING_2D = OPENGL_INTBUFFER.get(0);
//
//		GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE, OPENGL_INTBUFFER);
//		GL_ACTIVE_TEXTURE = OPENGL_INTBUFFER.get(0);
//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
//		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//		GL_TEXTURE_BINDING_2D_0 = OPENGL_INTBUFFER.get(0);
//
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
//		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
//
//		GL11.glViewport(0, 0, width, height);
//		OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, texture, 0);
//
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D_0);
//
//		OpenGlHelper.setActiveTexture(GL_ACTIVE_TEXTURE);
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D);
//
//		GL11.glMatrixMode(GL11.GL_PROJECTION);
//		GL11.glLoadIdentity();
//		GL11.glOrtho(0.0D, width, height, 0.0D, 1000.0D, 3000.0D);
//
//		//draw
//		GL11.glMatrixMode(GL11.GL_MODELVIEW);
//		GL11.glPushMatrix();
//		GL11.glScalef(scale, scale, scale);
//		minecraft.fontRenderer.drawStringWithShadow(string, 0, 0, 0xFFFFFFFF);
//		GL11.glPopMatrix();
//	}
//
//	public void initTextVertical(List<Integer> array_buffer_integer_list, List<Integer> texture_integer_list, String string, int width, int height, float x, float y, float scale)
//	{
//		Minecraft minecraft = SMALLGUI.mc;
//		array_buffer_integer_list.add(genBuffer(createFloatByteBuffer(this.createQuadVUv(x, -height + y, width + x, y, SmallGui.WIDTH, SmallGui.HEIGHT))));
////		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, GL_ARRAY_BUFFER_BINDING);
//
//		int texture = GL11.glGenTextures();
//		texture_integer_list.add(texture);
//
//		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//		GL_TEXTURE_BINDING_2D = OPENGL_INTBUFFER.get(0);
//
//		GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE, OPENGL_INTBUFFER);
//		GL_ACTIVE_TEXTURE = OPENGL_INTBUFFER.get(0);
//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
//		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//		GL_TEXTURE_BINDING_2D_0 = OPENGL_INTBUFFER.get(0);
//
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
//		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
//		GL11.glViewport(0, 0, width, height);
//		OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, texture, 0);
//
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D_0);
//
//		OpenGlHelper.setActiveTexture(GL_ACTIVE_TEXTURE);
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D);
//
//		GL11.glMatrixMode(GL11.GL_PROJECTION);
//		GL11.glLoadIdentity();
//		GL11.glOrtho(0.0D, width, height, 0.0D, 1000.0D, 3000.0D);
//
//		GL11.glMatrixMode(GL11.GL_MODELVIEW);
//		GL11.glPushMatrix();
//		GL11.glScalef(scale, scale, scale);
//		FontRenderer fontrenderer = minecraft.fontRenderer;
//		for (int i = 0; i < string.length(); ++i)
//		{
//			String c_string = "" + string.charAt(i);
//			float w = (FONT_MH - fontrenderer.getStringWidth(c_string)) / 2.0F;
//			float fy = FONT_MH * i;
//			int color = 0xFFFFFFFF;
//			fontrenderer.drawStringWithShadow(c_string, w, fy, color);
//		}
//		GL11.glPopMatrix();
//	}
//
//	public void drawQuadVUv(MemoS rs, float[] vec2_float_array, float[] color_float_array, int array_buffer, int texture)
//	{
//		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, array_buffer);
//		GL20.glVertexAttribPointer(rs.attriblocation_int_array[0], 4, GL11.GL_FLOAT, false, 0, 0);
//
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
//
//		OPENGL_FIXED_PIPE_FLOATBUFFER.limit(2);
//		OPENGL_FIXED_PIPE_FLOATBUFFER.clear();
//		OPENGL_FIXED_PIPE_FLOATBUFFER.put(vec2_float_array);
//		OPENGL_FIXED_PIPE_FLOATBUFFER.flip();
//		OpenGlHelper.glUniform2(rs.uniformlocation_int_array[0], OPENGL_FIXED_PIPE_FLOATBUFFER);
//
//		OPENGL_FIXED_PIPE_FLOATBUFFER.limit(4);
//		OPENGL_FIXED_PIPE_FLOATBUFFER.clear();
//		OPENGL_FIXED_PIPE_FLOATBUFFER.put(color_float_array);
//		OPENGL_FIXED_PIPE_FLOATBUFFER.flip();
//		OpenGlHelper.glUniform4(rs.uniformlocation_int_array[1], OPENGL_FIXED_PIPE_FLOATBUFFER);
//
//		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);
//	}
//
//	public void drawQuadStatic(MemoS rs, int array_buffer, int texture)
//	{
//		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//		GL_TEXTURE_BINDING_2D = OPENGL_INTBUFFER.get(0);
//
//		GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE, OPENGL_INTBUFFER);
//		GL_ACTIVE_TEXTURE = OPENGL_INTBUFFER.get(0);
//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
//		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//		GL_TEXTURE_BINDING_2D_0 = OPENGL_INTBUFFER.get(0);
//		GL_TEXTURE_MIN_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
//		GL_TEXTURE_MAG_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);
//		//
//
//		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, array_buffer);
//		GL20.glVertexAttribPointer(rs.attriblocation_int_array[0], 4, GL11.GL_FLOAT, false, 0, 0);
//
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
//
//		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);
//
//		//
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D_0);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL_TEXTURE_MIN_FILTER_0);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_TEXTURE_MAG_FILTER_0);
//
//		OpenGlHelper.setActiveTexture(GL_ACTIVE_TEXTURE);
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D);
//	}
//
//	public void initModel(List<Integer> array_buffer_integer_list, List<Integer> texture_integer_list, ClientE c/*, int texture_index*/, int width, int height, float x, float y, float scale)
//	{
//		array_buffer_integer_list.add(genBuffer(createFloatByteBuffer(this.createQuadVUv(x, y, width + x, height + y, SmallGui.WIDTH, SmallGui.HEIGHT/*, 1.0F, 1.0F*/)/*, true*/)));
////		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, GL_ARRAY_BUFFER_BINDING);
//
//		int texture = GL11.glGenTextures();
//		texture_integer_list.add(texture);
//
//		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//		GL_TEXTURE_BINDING_2D = OPENGL_INTBUFFER.get(0);
//
//		GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE, OPENGL_INTBUFFER);
//		GL_ACTIVE_TEXTURE = OPENGL_INTBUFFER.get(0);
//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
//		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//		GL_TEXTURE_BINDING_2D_0 = OPENGL_INTBUFFER.get(0);
//
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
//		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
//		GL11.glViewport(0, 0, width, height);
//		OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, texture, 0);
//
//		OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, OFFSET_RENDER_BUFFER);
//		OpenGlHelper.glRenderbufferStorage(OpenGlHelper.GL_RENDERBUFFER, GL14.GL_DEPTH_COMPONENT24, width, height);
//		OpenGlHelper.glFramebufferRenderbuffer(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, OpenGlHelper.GL_RENDERBUFFER, OFFSET_RENDER_BUFFER);
//
//		GL11.glClear(/*GL11.GL_COLOR_BUFFER_BIT | */GL11.GL_DEPTH_BUFFER_BIT);
//
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D_0);
//
//		OpenGlHelper.setActiveTexture(GL_ACTIVE_TEXTURE);
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D);
//
//		GL11.glMatrixMode(GL11.GL_PROJECTION);
//		GL11.glLoadIdentity();
//		GL11.glOrtho(0.0D, width, height, 0.0D, 1000.0D, 3000.0D);
//
//		GL11.glMatrixMode(GL11.GL_MODELVIEW);
//		GL11.glPushMatrix();
//		float p = -scale / 2.0F;
//		GL11.glTranslatef(p / PageClone.SCREEN_RW, (p - (scale / 4.0F)) / PageClone.SCREEN_RH, /*-scale * 4.0F*/0);
//		GL11.glScalef((scale - (scale / 2.5F)) / PageClone.SCREEN_RW, (scale - (scale / 2.5F)) / PageClone.SCREEN_RH, scale);
//		GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
//		RenderO r = c.r;
//		r.lig_b = -1.0F;
//		if (!c.should_render)
//		{
//			c.onReadNBT();
//		}
//		r.draw();
//		GL11.glPopMatrix();
//
//		OpenGlHelper.glFramebufferRenderbuffer(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, OpenGlHelper.GL_RENDERBUFFER, 0);
//	}
//
//	public void initBox(List<Integer> array_buffer_integer_list, List<Integer> texture_integer_list, float x, float y, int width, int height, ByteBuffer bytebuffer)
//	{
//		int texture = GL11.glGenTextures();
//		array_buffer_integer_list.add(genBuffer(createFloatByteBuffer(this.createQuadVUv(x, y, width + x, height + y, SmallGui.WIDTH, SmallGui.HEIGHT))));
//		texture_integer_list.add(texture);
//
//		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//		GL_TEXTURE_BINDING_2D = OPENGL_INTBUFFER.get(0);
//
//		GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE, OPENGL_INTBUFFER);
//		GL_ACTIVE_TEXTURE = OPENGL_INTBUFFER.get(0);
//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
//		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//		GL_TEXTURE_BINDING_2D_0 = OPENGL_INTBUFFER.get(0);
//
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
//
//		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, 1, 1, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, bytebuffer);
//		GL11.glViewport(0, 0, width, height);
//		OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, texture, 0);
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D_0);
//		OpenGlHelper.setActiveTexture(GL_ACTIVE_TEXTURE);
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D);
//	}
//
//	public abstract void init();
//	public abstract void draw();
//	public abstract void preDraw();
//}
