package com.nali.list.container.gui;

import com.nali.Nali;
import com.nali.list.container.SmallContainer;
import com.nali.list.data.SmallData;
import com.nali.system.opengl.OpenGLBuffer;
import com.nali.system.opengl.memo.client.MemoSo;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static com.nali.system.opengl.memo.client.MemoCurrent.*;

@SideOnly(Side.CLIENT)
public class SmallGui extends GuiContainer
{
    public SmallGui(Container inventorySlotsIn)
    {
        super(inventorySlotsIn);
//        this.xSize = 1;
//        this.ySize = 1;
    }

    public static SmallGui get(EntityPlayer entityplayer, World world, int x, int y, int z)
    {
        return new SmallGui(new SmallContainer());
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        int tw = this.fontRenderer.getStringWidth("HOME")/*-1*/;
        int th = 9-1;//FontRenderer.FONT_HEIGHT-SHADOW;
//        float t_width = tw, t_height = th;
        int framebuffer = OpenGlHelper.glGenFramebuffers();

        GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
        int draw_frame_buffer = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
        int read_frame_buffer = OPENGL_INTBUFFER.get(0);

        OpenGlHelper.glBindFramebuffer(GL30.GL_FRAMEBUFFER, framebuffer);

        //0
        int texture = GL11.glGenTextures();

        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
        GL_TEXTURE_BINDING_2D = OPENGL_INTBUFFER.get(0);
//        GL_TEXTURE_WRAP_S_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S);
//        GL_TEXTURE_WRAP_T_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T);
        GL_TEXTURE_MIN_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
        GL_TEXTURE_MAG_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
//        GL11.glViewport(this.mc.displayWidth / 2, this.mc.displayHeight / 2, this.mc.displayWidth, this.mc.displayHeight);
//        GL11.glViewport(0, 0, this.mc.displayWidth / 2, this.mc.displayHeight / 2);
        int ptw = tw + 4, pth = th + 4;

        GL11.glViewport(0, 0, ptw, pth);
//        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, this.mc.displayWidth/* / 2*/, this.mc.displayHeight/* / 2*/, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, ptw, pth, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

        GL30.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, texture, 0);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL_TEXTURE_WRAP_S_0);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL_TEXTURE_WRAP_T_0);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL_TEXTURE_MIN_FILTER_0);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_TEXTURE_MAG_FILTER_0);

        //0
        this.mc.getFramebuffer().checkFramebufferComplete();
//        if (GL30.glCheckFramebufferStatus(GL30.GL_FRAMEBUFFER) == GL30.GL_FRAMEBUFFER_COMPLETE)
//        {
//        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("" + this.mc.player.getEntityData().getInteger("sakura_nali"), 25, 11, 0xFFFFACDF/*getRainbowColor4()*/);
//        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("HOME", this.width / 2.0F - 4.0F*3.0F, this.height / 2.0F - 3.0F, 0xFFFFACDF/*getRainbowColor4()*/);
//        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("HOME", 0, 0, 0xFFFFACDF/*getRainbowColor4()*/);
//        GL11.glClearColor(1.0F, 0, 0, 1.0F);
//        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
//        }

        //1

//        float s = Math.min((float)this.width / tw, (float)this.height / th);
        float sx = (float)this.width / tw;
        float sy = (float)this.height / th;

//        float tx = (this.width - (tw * sx)) / 2.0F;
//        float ty = (this.height - (th * sy)) / 2.0F;

        GL11.glPushMatrix();
//        GL11.glTranslated(tx, ty, 0);
        GL11.glScalef(sx, sy, 1.0F);

//        OPENGL_FIXED_PIPE_FLOATBUFFER.limit(16);
//        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, OPENGL_FIXED_PIPE_FLOATBUFFER);
//        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, OPENGL_FIXED_PIPE_FLOATBUFFER_1);
//        GL11.glGetInteger(GL11.GL_MATRIX_MODE, OPENGL_INTBUFFER);
//        GL_MATRIX_MODE = OPENGL_INTBUFFER.get(0);

//        GL11.glMatrixMode(GL11.GL_PROJECTION);
//        GL11.glOrtho(320 - this.mc.displayWidth/2.0F, 320 + this.mc.displayWidth/2.0F, 240 + this.mc.displayHeight/2.0F, 240 - this.mc.displayHeight/2.0F, -1, 1);
////        GL11.glLoadIdentity();
//        GL11.glMatrixMode(GL11.GL_MODELVIEW);
//        GL11.glLoadIdentity();
//        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("HOME", 0, 0, 0xFFFFACDF/*getRainbowColor4()*/);
        this.fontRenderer.drawStringWithShadow("HOME", 0, 0, 0xFFFFACDF/*getRainbowColor4()*/);

//        GL11.glMatrixMode(GL11.GL_PROJECTION);
//        GL11.glLoadMatrix(OPENGL_FIXED_PIPE_FLOATBUFFER);
//        GL11.glMatrixMode(GL11.GL_MODELVIEW);
//        GL11.glLoadMatrix(OPENGL_FIXED_PIPE_FLOATBUFFER_1);
//        GL11.glMatrixMode(GL_MATRIX_MODE);

//        GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
        GL11.glPopMatrix();
        //1

        OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, read_frame_buffer);
        OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, draw_frame_buffer);
//        GL11.glViewport(0, 0, 4*3*2, 3*2);
        GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);

//        x = this.width / 2.0F - 4.0F*3.0F, y = this.height / 2.0F - 3.0F;
        float x = this.mc.displayWidth / 2.0F, y = this.mc.displayHeight / 2.0F;
//        int array_buffer = this.genQuad(this.width / 2.0F - 4.0F*3.0F, this.height / 2.0F - 3.0F, t_width, t_height, this.mc.displayWidth, this.mc.displayHeight);
//        int array_buffer = this.genQuad(this.mc.displayWidth / 2.0F, 0, 4*3*2, 3*2);
//        int array_buffer = this.genQuad(0.5F, 0.5F, 4*3*2, 3*2);
        float fx = tw * (float)this.mc.displayWidth / this.width;
        float fy = th * (float)this.mc.displayHeight / this.height;
        int array_buffer = this.genQuad(x - fx, y - fy, x + fx, y + fy, this.mc.displayWidth, this.mc.displayHeight);
//        int array_buffer = this.genQuad(0, 0, this.mc.displayWidth, this.mc.displayHeight, this.mc.displayWidth, this.mc.displayHeight);
//        int array_buffer = this.genQuad(this.width / 2.0F - 4.0F*3.0F, this.height / 2.0F - 3.0F, t_width, t_height, this.mc.displayWidth, this.mc.displayHeight);
//        int array_buffer = this.genQuad(0, 0, this.mc.displayWidth, this.mc.displayHeight);
//        int array_buffer = this.genQuad((this.mc.displayWidth - t_width) / 2, (this.mc.displayHeight - t_height) / 2, t_width, t_height);
//        int array_buffer = this.genQuad();
        this.drawQuad(array_buffer, texture);

        OpenGlHelper.glDeleteFramebuffers(framebuffer);
        GL11.glDeleteTextures(texture);
        OpenGlHelper.glDeleteBuffers(array_buffer);
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
        float nx1 = (2.0F * x / fwidth) - 1.0F;
        float ny1 = (2.0F * y / fheight) - 1.0F;

        float nx2 = (2.0F * width / fwidth) - 1.0F;
        float ny2 = (2.0F * height / fheight) - 1.0F;

        return new float[]
        {
            nx1, ny2,  0.0f,  1.0f,
            nx1, ny1,  0.0f,  0.0f,
            nx2, ny1,  1.0f,  0.0f,

            nx1, ny2,  0.0f,  1.0f,
            nx2, ny1,  1.0f,  0.0f,
            nx2, ny2,  1.0f,  1.0f
        };
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

    public void drawQuad(int array_buffer, int texture)
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

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
    }
}
