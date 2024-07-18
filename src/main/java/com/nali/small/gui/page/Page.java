package com.nali.small.gui.page;

import com.nali.system.opengl.OpenGLBuffer;
import com.nali.system.opengl.memo.client.MemoSo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import java.nio.IntBuffer;
import java.util.List;

import static com.nali.list.container.gui.SmallGui.SMALLGUI;
import static com.nali.system.opengl.memo.client.MemoCurrent.*;

@SideOnly(Side.CLIENT)
public abstract class Page
{
    public static String[] STRING_ARRAY;
    public static int H, FONT;
    public static byte
//    INDEX,
//    MAX_TW,
    MAX_TH = 9/*-1*/;//FontRenderer.FONT_HEIGHT-SHADOW+y;

    public Page()
    {
        SMALLGUI.state |= 2;
    }

    public void init()
    {
        List<Integer> array_buffer_integer_list = this.getArrayBufferIntegerList();
        List<Integer> texture_integer_list = this.getTextureIntegerList();
        this.getTextureIntegerList();
        for (int texture : texture_integer_list)
        {
            GL11.glDeleteTextures(texture);
        }
        for (int array_buffer : array_buffer_integer_list)
        {
            OpenGlHelper.glDeleteBuffers(array_buffer);
        }

//        INDEX = 0;
//        byte size = (byte)STRING_ARRAY.length;
//        TEXTURE_INT_ARRAY = new int[size];
//        ARRAY_BUFFER_INT_ARRAY = new int[size];
        texture_integer_list.clear();
        array_buffer_integer_list.clear();
        SMALLGUI.state &= 255-2;
    }

    //    public float[] createQuadVertices(float x0, float y0, float x1, float y1, float fwidth, float fheight, float u, float v)
    public float[] createQuadVUv(float x0, float y0, float x1, float y1, float fwidth, float fheight, float u, float v)
    {
//        return new float[]
//        {
//            // First triangle
//            x, y, 0.0f, 0.0f, // Bottom-left
//            x + width, y, 1.0f, 0.0f, // Bottom-right
//            x + width, y + height, 1.0f, 1.0f, // Top-right
//
//            // Second triangle
//            x, y, 0.0f, 0.0f, // Bottom-left
//            x + width, y + height, 1.0f, 1.0f, // Top-right
//            x, y + height, 0.0f, 1.0f // Top-left
//        };
//        return new float[]
//        {
//            // Triangle 1
//            (x - width / 2.0F) / fwidth, (y + height / 2.0F) / fheight, 0.0F, 1.0F,
//            (x + width / 2.0F) / fwidth, (y + height / 2.0F) / fheight, 1.0F, 1.0F,
//            (x + width / 2.0F) / fwidth, (y - height / 2.0F) / fheight, 1.0F, 0.0F,
//
//            // Triangle 2
//            (x - width / 2.0F) / fwidth, (y + height / 2.0F) / fheight, 0.0F, 1.0F,
//            (x + width / 2.0F) / fwidth, (y - height / 2.0F) / fheight, 1.0F, 0.0F,
//            (x - width / 2.0F) / fwidth, (y - height / 2.0F) / fheight, 0.0F, 0.0F
//        };
//        float w = 1.0F / width;
//        float h = 1.0F / height;
//        x = 1.0F / x;
//        y = 1.0F / y;
//        float nx = (2.0F * x / width) - 1.0F;
//        float ny = (2.0F * y / height) - 1.0F;
//        return new float[]
//        {
//            // positions    // texCoords
//            -1.0F + nx,  1.0F + ny,   0.0F,   1.0F,
//            -1.0F + nx,  -1.0F + ny,  0.0F,   0.0F,
//            1.0F + nx,   -1.0F + ny,  1.0F,   0.0F,
//
//            -1.0F + nx,  1.0F + ny,   0.0F,   1.0F,
//            1.0F + nx,   -1.0F + ny,  1.0F,   0.0F,
//            1.0F + nx,   1.0F + ny,   1.0F,   1.0F
//        };
//        x = 0;
//        y = 0;
//        width = fwidth;
//        height = fheight;
        float nx1 = (2.0F * x0 / fwidth) - 1.0F;
        float ny1 = (2.0F * y0 / fheight) - 1.0F;

        float nx2 = (2.0F * x1 / fwidth) - 1.0F;
        float ny2 = (2.0F * y1 / fheight) - 1.0F;

        return new float[]
        {
            nx1, ny2, 0.0F, v,
            nx1, ny1, 0.0F, 0.0F,
            nx2, ny1, u, 0.0F,

            nx1, ny2, 0.0F, v,
            nx2, ny1, u, 0.0F,
            nx2, ny2, u, v
        };

//        return new float[]
//        {
//            // positions    // texCoords
//            -1.0F,  1.0F,   0.0F,   1.0F,
//            -1.0F,  -1.0F,  0.0F,   0.0F,
//            1.0F,   -1.0F,  1.0F,   0.0F,
//
//            -1.0F,  1.0F,   0.0F,   1.0F,
//            1.0F,   -1.0F,  1.0F,   0.0F,
//            1.0F,   1.0F,   1.0F,   1.0F
//        };
    }

    //    public void preDrawText(/*int framebuffer, */String string, int texture_index)
    public void preDrawTextHorizontal(List<Integer> array_buffer_integer_list, List<Integer> texture_integer_list, /*int framebuffer, */String string/*, boolean shadow*/, int texture_index, int width, int height, float x, float y, float scale)
    {
        Minecraft minecraft = SMALLGUI.mc;
//        if (this.framebuffer == -1)
//        {
//        int framebuffer = OpenGlHelper.glGenFramebuffers();
//        }
//        int tw = this.fontRenderer.getStringWidth(string)/*-1*/;//big
//        float x = this.mc.displayWidth / 2.0F, y = this.mc.displayHeight / 2.0F,
//        fx = tw * (float)this.mc.displayWidth / this.width,
//        fy = MAX_TH * (float)this.mc.displayHeight / this.height;
//        GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
//        ARRAY_BUFFER_INT_ARRAY[INDEX] = OpenGLBuffer.loadFloatBuffer(OpenGLBuffer.createFloatByteBuffer(this.createQuadVertices(x - fx, y - fy, x + fx, y + fy, this.mc.displayWidth, this.mc.displayHeight), true));
//        ARRAY_BUFFER_INT_LIST.add(OpenGLBuffer.loadFloatBuffer(OpenGLBuffer.createFloatByteBuffer(this.createQuadVertices(x - fx, y - fy, x + fx, y + fy, this.mc.displayWidth, this.mc.displayHeight, 1.0F, 1.0F), true)));
        array_buffer_integer_list.add(OpenGLBuffer.loadFloatBuffer(OpenGLBuffer.createFloatByteBuffer(this.createQuadVUv(x, y, width + x, height + y, minecraft.displayWidth, minecraft.displayHeight, 1.0F, 1.0F), true)));
        OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, GL_ARRAY_BUFFER_BINDING);

//        GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
//        int draw_frame_buffer = OPENGL_INTBUFFER.get(0);
//        GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
//        int read_frame_buffer = OPENGL_INTBUFFER.get(0);
//        GL11.glGetInteger(GL30.GL_RENDERBUFFER_BINDING, OPENGL_INTBUFFER);
//        int renderbuffer_binding = OPENGL_INTBUFFER.get(0);

//        OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, framebuffer);
//        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, 0);

//        TEXTURE_INT_ARRAY[INDEX] = GL11.glGenTextures();
        int texture = GL11.glGenTextures();
        if (texture_index == -1)
        {
            texture_integer_list.add(texture);
        }
        else
        {
            GL11.glDeleteTextures(texture_integer_list.get(texture_index));
            texture_integer_list.set(texture_index, texture);
        }

        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
        GL_TEXTURE_BINDING_2D = OPENGL_INTBUFFER.get(0);

        GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE, OPENGL_INTBUFFER);
        GL_ACTIVE_TEXTURE = OPENGL_INTBUFFER.get(0);
        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
        GL_TEXTURE_BINDING_2D_0 = OPENGL_INTBUFFER.get(0);
        GL_TEXTURE_MIN_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
        GL_TEXTURE_MAG_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);

//        int ptw = tw + FONT, pth = MAX_TH + FONT;
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, TEXTURE_INT_ARRAY[INDEX]);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
//        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, ptw, pth, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
//        GL11.glViewport(0, 0, ptw, pth);
        GL11.glViewport(0, 0, width, height);
        OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, texture, 0);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D_0);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL_TEXTURE_MIN_FILTER_0);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_TEXTURE_MAG_FILTER_0);

        OpenGlHelper.setActiveTexture(GL_ACTIVE_TEXTURE);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D);

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0D, width, height, 0.0D, 1000.0D, 3000.0D);

        //draw
//        float sx = (float)this.width / tw;
//        float sy = (float)this.height / MAX_TH;
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPushMatrix();
//        GL11.glScalef(sx, sy, 1.0F);
        GL11.glScalef(scale, scale, scale);
//        if (shadow)
//        {
        minecraft.fontRenderer.drawStringWithShadow(string, 0, 0, 0xFFFFFFFF);
//            this.fontRenderer.drawString(string, 0, 0, 0xFFFFFFFF);
//            GL11.glPushMatrix();
//            GL11.glTranslatef((width / 2.0F) / scale, 0.0F, 0.0F);
//            GL11.glScalef(1.1F, 1.1F, 1.1F);
//            GL11.glTranslatef(-((width / 2.0F) / (scale * 1.05F)), 0.0F, 0.0F);
//            this.fontRenderer.drawString(string, 0, 0, 0xFF000000);
//            GL11.glPopMatrix();
//        }
//        else
//        {
//            this.fontRenderer.drawString(string, 0, 0, 0xFFFFFFFF);
//        }
        GL11.glPopMatrix();

//        OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, read_frame_buffer);
//        OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, draw_frame_buffer);
//        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, renderbuffer_binding);

//        OpenGlHelper.glDeleteFramebuffers(framebuffer);
    }

    public void preDrawTextVertical(List<Integer> array_buffer_integer_list, List<Integer> texture_integer_list, String string/*, boolean shadow*/, int texture_index, int width, int height, float x, float y, float scale)
    {
        Minecraft minecraft = SMALLGUI.mc;
        array_buffer_integer_list.add(OpenGLBuffer.loadFloatBuffer(OpenGLBuffer.createFloatByteBuffer(this.createQuadVUv(x, -height + y, width + x, y, minecraft.displayWidth, minecraft.displayHeight, 1.0F/* / (this.mc.displayWidth / (float)this.width)*/, 1.0F), true)));
        OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, GL_ARRAY_BUFFER_BINDING);

        int texture = GL11.glGenTextures();
        if (texture_index == -1)
        {
            texture_integer_list.add(texture);
        }
        else
        {
            GL11.glDeleteTextures(texture_integer_list.get(texture_index));
            texture_integer_list.set(texture_index, texture);
        }

        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
        GL_TEXTURE_BINDING_2D = OPENGL_INTBUFFER.get(0);

        GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE, OPENGL_INTBUFFER);
        GL_ACTIVE_TEXTURE = OPENGL_INTBUFFER.get(0);
        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
        GL_TEXTURE_BINDING_2D_0 = OPENGL_INTBUFFER.get(0);
//        GL_TEXTURE_MIN_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
//        GL_TEXTURE_MAG_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
        GL11.glViewport(0, 0, width, height);
        OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, texture, 0);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D_0);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL_TEXTURE_MIN_FILTER_0);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_TEXTURE_MAG_FILTER_0);

        OpenGlHelper.setActiveTexture(GL_ACTIVE_TEXTURE);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D);

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0D, width, height, 0.0D, 1000.0D, 3000.0D);

        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPushMatrix();
        GL11.glScalef(scale, scale, scale);
        FontRenderer fontrenderer = minecraft.fontRenderer;
        for (int i = 0; i < string.length(); ++i)
        {
            String c_string = "" + string.charAt(i);
            float w = (MAX_TH - fontrenderer.getStringWidth(c_string)) / 2.0F;
            float fy = MAX_TH * i;
            int color = 0xFFFFFFFF;
//            if (shadow)
//            {
            fontrenderer.drawStringWithShadow(c_string, w, fy, color);
//            }
//            else
//            {
//                this.fontRenderer.drawString(c_string, w, fy, color, false);
//            }
        }
        GL11.glPopMatrix();
    }

    //    public void drawQuad(MemoSo rs, float[] m4x4_float_array, float[] color_float_array, int array_buffer, int texture)
    public void drawQuadVUv(MemoSo rs, float[] vec2_float_array, float[] color_float_array, int array_buffer, int texture)
    {
//        MemoSo rs = Nali.I.clientloader.storeo.rs_list.get(SmallData.SHADER_O_STEP + 3);

//        //takeDefault
//        GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, OPENGL_INTBUFFER);
//        GL_ARRAY_BUFFER_BINDING = OPENGL_INTBUFFER.get(0);
//
//        GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM, OPENGL_INTBUFFER);
//        GL_CURRENT_PROGRAM = OPENGL_INTBUFFER.get(0);
//
//        GL_ACTIVE_TEXTURE = OPENGL_INTBUFFER.get(0);
//        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
//
//        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//        GL_TEXTURE_BINDING_2D_0 = OPENGL_INTBUFFER.get(0);
//
////        GL_TEXTURE_WRAP_S_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S);
////        GL_TEXTURE_WRAP_T_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T);
//        GL_TEXTURE_MIN_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
//        GL_TEXTURE_MAG_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);

        //enableBuffer
        OpenGLBuffer.setFloatBuffer(0, array_buffer, 4);
//        OpenGlHelper.glBindBuffer(GL15.GL_ARRAY_BUFFER, array_buffer);
//        GL20.glVertexAttribPointer(0, 4, GL11.GL_FLOAT, false, 0, 0);

//        OpenGlHelper.glUseProgram(rs.program);
//        GL20.glEnableVertexAttribArray(0);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

        OPENGL_FIXED_PIPE_FLOATBUFFER.limit(2);
        OPENGL_FIXED_PIPE_FLOATBUFFER.clear();
        OPENGL_FIXED_PIPE_FLOATBUFFER.put(vec2_float_array);
        OPENGL_FIXED_PIPE_FLOATBUFFER.flip();
        OpenGlHelper.glUniform2(rs.uniformlocation_int_array[0], OPENGL_FIXED_PIPE_FLOATBUFFER);

//        GL11.glGetInteger(GL11.GL_MATRIX_MODE, OPENGL_INTBUFFER);
//        int gl_matrix_mode = OPENGL_INTBUFFER.get(0);
//        OPENGL_FLOATBUFFER.limit(16);
//        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, OPENGL_FLOATBUFFER);
//        GL11.glMatrixMode(GL11.GL_PROJECTION);
//        GL11.glLoadIdentity();
//        float aspect_ratio = (float)this.mc.displayWidth / (float)this.mc.displayHeight;
//        GL11.glOrtho(-aspect_ratio, aspect_ratio, -1, 1, -1, 1);
//        GL11.glOrtho(-1, 1, -1, 1, -1, 1);
//        GL11.glOrtho(0, this.mc.displayWidth, 0, this.mc.displayHeight, 1000, 3000);
//        OPENGL_FIXED_PIPE_FLOATBUFFER.limit(16);
//        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, OPENGL_FIXED_PIPE_FLOATBUFFER);
//        OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[1], false, OPENGL_FIXED_PIPE_FLOATBUFFER);
//        OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[1], false, OPENGL_PROJECTION_MATRIX_FLOATBUFFER);

//        GL11.glMatrixMode(gl_matrix_mode);
//        GL11.glLoadMatrix(OPENGL_FLOATBUFFER);

        OPENGL_FIXED_PIPE_FLOATBUFFER.limit(4);
        OPENGL_FIXED_PIPE_FLOATBUFFER.clear();
        OPENGL_FIXED_PIPE_FLOATBUFFER.put(color_float_array);
        OPENGL_FIXED_PIPE_FLOATBUFFER.flip();
        OpenGlHelper.glUniform4(rs.uniformlocation_int_array[1], OPENGL_FIXED_PIPE_FLOATBUFFER);

        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);

//        //setDefault
//        GL20.glDisableVertexAttribArray(0);
//        OpenGlHelper.setActiveTexture(GL_ACTIVE_TEXTURE);
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D_0);
////        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL_TEXTURE_WRAP_S_0);
////        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL_TEXTURE_WRAP_T_0);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL_TEXTURE_MIN_FILTER_0);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_TEXTURE_MAG_FILTER_0);
//
//        OpenGlHelper.glUseProgram(GL_CURRENT_PROGRAM);
//
//        OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, GL_ARRAY_BUFFER_BINDING);
    }

    public void drawQuadStatic/*Blur*/(int array_buffer, int texture)
    {
        OpenGLBuffer.setFloatBuffer(0, array_buffer, 4);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);
    }

    public abstract void preDraw();
//    {
//        SMALLGUI.state &= 255-2;
//    }

    public abstract void draw(/*MemoSo rs*/);
    public abstract void detect(/*MemoSo rs*/);
//    public abstract void change();

    public abstract List<Integer> getArrayBufferIntegerList();
    public abstract List<Integer> getTextureIntegerList();
}
