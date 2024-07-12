package com.nali.small.gui;

import com.nali.Nali;
import com.nali.list.data.SmallData;
import com.nali.small.config.MyConfig;
import com.nali.system.opengl.OpenGLBuffer;
import com.nali.system.opengl.memo.client.MemoSo;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

import java.nio.ByteBuffer;

import static com.nali.system.opengl.memo.client.MemoCurrent.*;

@SideOnly(Side.CLIENT)
public abstract class MixGUI extends GuiContainer
{
    public static byte STATE;
    public static float WIDTH, HEIGHT, FONT_BOX;

    public MixGUI(Container inventorySlotsIn)
    {
        super(inventorySlotsIn);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        if (WIDTH != this.width || HEIGHT != this.height || FONT_BOX != MyConfig.CLIENT.font_box)
        {
            WIDTH = this.width;
            HEIGHT = this.height;
            FONT_BOX = MyConfig.CLIENT.font_box;
            this.init();
        }
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
    }

    public float[] createQuadVertices(float x, float y, float width, float height, float fwidth, float fheight)
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
        float nx1 = (2.0F * x / fwidth) - 1.0F;
        float ny1 = (2.0F * y / fheight) - 1.0F;

        float nx2 = (2.0F * width / fwidth) - 1.0F;
        float ny2 = (2.0F * height / fheight) - 1.0F;

        return new float[]
        {
            nx1, ny2, 0.0F, 1.0F,
            nx1, ny1, 0.0F, 0.0F,
            nx2, ny1, 1.0F, 0.0F,

            nx1, ny2, 0.0F, 1.0F,
            nx2, ny1, 1.0F, 0.0F,
            nx2, ny2, 1.0F, 1.0F
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

    public int genQuad(float x, float y, float width, float height, float fwidth, float fheight)
    {
        GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, OPENGL_INTBUFFER);
        GL_ARRAY_BUFFER_BINDING = OPENGL_INTBUFFER.get(0);

        float[] quad_vertices = this.createQuadVertices(x, y, width, height, fwidth, fheight);
//        float[] quad_vertices =
//        {
//            -1.0f,  1.0f,
//            -1.0f, -1.0f,
//            1.0f, -1.0f,
//
//            -1.0f,  1.0f,
//            1.0f, -1.0f,
//            1.0f,  1.0f
//        };
//        float[] quad_vertices =
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

//        for (int i = 0; i < quad_vertices.length; ++i)
//        {
//            quad_vertices[i] *= 2.0F;
//        }

        ByteBuffer bytebuffer = OpenGLBuffer.createFloatByteBuffer(quad_vertices, true);

        //load
//        int array_buffer = OpenGlHelper.glGenBuffers();
        int array_buffer = OpenGLBuffer.loadFloatBuffer(bytebuffer);
//        OpenGlHelper.glBindBuffer(GL15.GL_ARRAY_BUFFER, array_buffer);
//        OpenGlHelper.glBufferData(GL15.GL_ARRAY_BUFFER, bytebuffer, GL15.GL_STATIC_DRAW);
//        //set
//        OpenGlHelper.glBindBuffer(GL15.GL_ARRAY_BUFFER, array_buffer);
//        GL20.glVertexAttribPointer(0, 2, GL11.GL_FLOAT, false, 2 * Float.BYTES, 0);
//        GL20.glEnableVertexAttribArray(0);

        OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, GL_ARRAY_BUFFER_BINDING);

        return array_buffer;
    }

    public void drawQuad(float[] m4x4_float_array, int array_buffer, int texture)
    {
        MemoSo rs = Nali.I.clientloader.storeo.rs_list.get(SmallData.SHADER_O_STEP + 3);

        //takeDefault
        GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, OPENGL_INTBUFFER);
        GL_ARRAY_BUFFER_BINDING = OPENGL_INTBUFFER.get(0);

        GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM, OPENGL_INTBUFFER);
        GL_CURRENT_PROGRAM = OPENGL_INTBUFFER.get(0);

        GL_ACTIVE_TEXTURE = OPENGL_INTBUFFER.get(0);
        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);

        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
        GL_TEXTURE_BINDING_2D_0 = OPENGL_INTBUFFER.get(0);

//        GL_TEXTURE_WRAP_S_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S);
//        GL_TEXTURE_WRAP_T_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T);
        GL_TEXTURE_MIN_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
        GL_TEXTURE_MAG_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);

        //enableBuffer
        OpenGLBuffer.setFloatBuffer(0, array_buffer, 4);
//        OpenGlHelper.glBindBuffer(GL15.GL_ARRAY_BUFFER, array_buffer);
//        GL20.glVertexAttribPointer(0, 4, GL11.GL_FLOAT, false, 0, 0);

        OpenGlHelper.glUseProgram(rs.program);
        GL20.glEnableVertexAttribArray(0);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

        OPENGL_FIXED_PIPE_FLOATBUFFER.limit(16);
        OPENGL_FIXED_PIPE_FLOATBUFFER.clear();
        OPENGL_FIXED_PIPE_FLOATBUFFER.put(m4x4_float_array);
        OPENGL_FIXED_PIPE_FLOATBUFFER.flip();
        OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[1], false, OPENGL_FIXED_PIPE_FLOATBUFFER);

        GL11.glGetInteger(GL11.GL_MATRIX_MODE, OPENGL_INTBUFFER);
        int gl_matrix_mode = OPENGL_INTBUFFER.get(0);
        OPENGL_FLOATBUFFER.limit(16);
        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, OPENGL_FLOATBUFFER);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        float aspect_ratio = (float)this.mc.displayWidth / (float)this.mc.displayHeight;
        GL11.glOrtho(-aspect_ratio, aspect_ratio, -1, 1, -1, 1);
//        GL11.glOrtho(-1, 1, -1, 1, -1, 1);
//        GL11.glOrtho(0, this.mc.displayWidth, 0, this.mc.displayHeight, 1000, 3000);
        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, OPENGL_FIXED_PIPE_FLOATBUFFER);
        OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[2], false, OPENGL_FIXED_PIPE_FLOATBUFFER);

        GL11.glMatrixMode(gl_matrix_mode);
        GL11.glLoadMatrix(OPENGL_FLOATBUFFER);

        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);

        //setDefault
        GL20.glDisableVertexAttribArray(0);
//        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D_0);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL_TEXTURE_WRAP_S_0);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL_TEXTURE_WRAP_T_0);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL_TEXTURE_MIN_FILTER_0);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_TEXTURE_MAG_FILTER_0);

        OpenGlHelper.glUseProgram(GL_CURRENT_PROGRAM);

        OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, GL_ARRAY_BUFFER_BINDING);
    }

    public abstract void init();
}
