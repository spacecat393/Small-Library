package com.nali.small.gui;

import com.nali.Nali;
import com.nali.list.data.SmallData;
import com.nali.small.SmallConfig;
import com.nali.system.opengl.OpenGLBuffer;
import com.nali.system.opengl.memo.client.MemoSo;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static com.nali.system.opengl.memo.client.MemoCurrent.*;

@SideOnly(Side.CLIENT)
public abstract class MixGUI extends GuiContainer
{
    public static float WIDTH, HEIGHT, SCALE;
    public static String[] STRING_ARRAY;
    public static List<Integer>
    TEXTURE_INT_LIST = new ArrayList(),
    ARRAY_BUFFER_INT_LIST = new ArrayList();
    public static int OFFSET_FRAMEBUFFER = -1, OFFSET_FRAMEBUFFER_TEXTURE,
    W,
    FONT;
    public static byte
//    INDEX,
//    MAX_TW,
    MAX_TH = 9/*-1*/;//FontRenderer.FONT_HEIGHT-SHADOW+y;

    public byte state,
    hit;
    public int mouse_x, mouse_y;
//    public int eventButton;

    public MixGUI(Container inventorySlotsIn)
    {
        super(inventorySlotsIn);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
//        GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, OPENGL_INTBUFFER);
//        GL_ARRAY_BUFFER_BINDING = OPENGL_INTBUFFER.get(0);

        GL11.glGetInteger(GL11.GL_MATRIX_MODE, OPENGL_INTBUFFER);
//        int gl_matrix_mode = OPENGL_INTBUFFER.get(0);
        GL_MATRIX_MODE = OPENGL_INTBUFFER.get(0);
//        OPENGL_FLOATBUFFER.limit(16);
//        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, OPENGL_FLOATBUFFER);
        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, OPENGL_PROJECTION_MATRIX_FLOATBUFFER);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
//        GL11.glLoadIdentity();

        GL_BLEND = GL11.glIsEnabled(GL11.GL_BLEND);

        GL11.glGetInteger(GL20.GL_BLEND_EQUATION_RGB, OPENGL_INTBUFFER);
        GL_BLEND_EQUATION_RGB = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL20.GL_BLEND_EQUATION_ALPHA, OPENGL_INTBUFFER);
        GL_BLEND_EQUATION_ALPHA = OPENGL_INTBUFFER.get(0);

        GL11.glGetInteger(GL14.GL_BLEND_SRC_RGB, OPENGL_INTBUFFER);
        GL_BLEND_SRC_RGB = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL14.GL_BLEND_SRC_ALPHA, OPENGL_INTBUFFER);
        GL_BLEND_SRC_ALPHA = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL14.GL_BLEND_DST_RGB, OPENGL_INTBUFFER);
        GL_BLEND_DST_RGB = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL14.GL_BLEND_DST_ALPHA, OPENGL_INTBUFFER);
        GL_BLEND_DST_ALPHA = OPENGL_INTBUFFER.get(0);

        GL11.glEnable(GL11.GL_BLEND);

        GL20.glBlendEquationSeparate(GL14.GL_FUNC_ADD, GL14.GL_FUNC_ADD);

        GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);

        GL11.glGetInteger(GL30.GL_RENDERBUFFER_BINDING, OPENGL_INTBUFFER);
        int renderbuffer_binding = OPENGL_INTBUFFER.get(0);

        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, 0);

        GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
//        int draw_frame_buffer = OPENGL_INTBUFFER.get(0);
        GL_DRAW_FRAMEBUFFER_BINDING = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
//        int read_frame_buffer = OPENGL_INTBUFFER.get(0);
        GL_READ_FRAMEBUFFER_BINDING = OPENGL_INTBUFFER.get(0);

        if (WIDTH != this.width || HEIGHT != this.height || FONT != SmallConfig.CLIENT.font/* || MAIN_FRAMEBUFFER == -1*/)
        {
            SCALE = 2.0F / (this.height / (float)this.mc.displayHeight);
            W = (int)(MAX_TH * SCALE);

            WIDTH = this.width;
            HEIGHT = this.height;
            FONT = SmallConfig.CLIENT.font;
            if (OFFSET_FRAMEBUFFER == -1)
            {
                OFFSET_FRAMEBUFFER = OpenGlHelper.glGenFramebuffers();
                OFFSET_FRAMEBUFFER_TEXTURE = GL11.glGenTextures();

//                GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
//                int draw_frame_buffer = OPENGL_INTBUFFER.get(0);
//                GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
//                int read_frame_buffer = OPENGL_INTBUFFER.get(0);

//                OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_FRAMEBUFFER);
//                OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE, 0);

//                OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, read_frame_buffer);
//                OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, draw_frame_buffer);
            }
//            GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
//            int draw_frame_buffer = OPENGL_INTBUFFER.get(0);
//            GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
//            int read_frame_buffer = OPENGL_INTBUFFER.get(0);
//
//            OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_FRAMEBUFFER);
//            GL11.glBindTexture(GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE);
//            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, this.mc.displayWidth, this.mc.displayHeight, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
//            OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE, 0);
//
//            OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, read_frame_buffer);
//            OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, draw_frame_buffer);
            this.init();
        }

        //takeDefault
        GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, OPENGL_INTBUFFER);
        GL_ARRAY_BUFFER_BINDING = OPENGL_INTBUFFER.get(0);

        GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM, OPENGL_INTBUFFER);
        GL_CURRENT_PROGRAM = OPENGL_INTBUFFER.get(0);

        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
        GL_TEXTURE_BINDING_2D = OPENGL_INTBUFFER.get(0);

        GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE, OPENGL_INTBUFFER);
        GL_ACTIVE_TEXTURE = OPENGL_INTBUFFER.get(0);
        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
        GL_TEXTURE_BINDING_2D_0 = OPENGL_INTBUFFER.get(0);
        GL_TEXTURE_MIN_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
        GL_TEXTURE_MAG_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);

        this.preDraw();

        MemoSo rs = Nali.I.clientloader.storeo.rs_list.get(SmallData.SHADER_O_STEP + 4);

        OpenGlHelper.glUseProgram(rs.program);
        GL20.glEnableVertexAttribArray(0);

//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

//        GL11.glGetInteger(GL11.GL_MATRIX_MODE, OPENGL_INTBUFFER);
//        int gl_matrix_mode = OPENGL_INTBUFFER.get(0);
//        OPENGL_FIXED_PIPE_FLOATBUFFER.limit(16);
//        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, OPENGL_FIXED_PIPE_FLOATBUFFER);
//        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
//        float aspect_ratio = (float)this.mc.displayWidth / (float)this.mc.displayHeight;
//        GL11.glOrtho(-aspect_ratio, aspect_ratio, -1, 1, -1, 1);
        //
        this.detect(rs);
        GL20.glDisableVertexAttribArray(0);

        rs = Nali.I.clientloader.storeo.rs_list.get(SmallData.SHADER_O_STEP + 5);
        OpenGlHelper.glUseProgram(rs.program);
        GL20.glEnableVertexAttribArray(0);
        this.drawStaticBlur();
        GL20.glDisableVertexAttribArray(0);

        rs = Nali.I.clientloader.storeo.rs_list.get(SmallData.SHADER_O_STEP + 3);
        OpenGlHelper.glUseProgram(rs.program);
        GL20.glEnableVertexAttribArray(0);

//        OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, GL_READ_FRAMEBUFFER_BINDING);
//        OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER_BINDING);

        this.drawText(rs);

        //setDefault
//        GL11.glMatrixMode(GL11.GL_PROJECTION);
//        GL11.glLoadMatrix(OPENGL_FLOATBUFFER);
        GL11.glLoadMatrix(OPENGL_PROJECTION_MATRIX_FLOATBUFFER);

//        GL11.glMatrixMode(gl_matrix_mode);
        GL11.glMatrixMode(GL_MATRIX_MODE);

        GL20.glDisableVertexAttribArray(0);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D_0);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL_TEXTURE_MIN_FILTER_0);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_TEXTURE_MAG_FILTER_0);

        OpenGlHelper.setActiveTexture(GL_ACTIVE_TEXTURE);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D);

        OpenGlHelper.glUseProgram(GL_CURRENT_PROGRAM);

        OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, GL_ARRAY_BUFFER_BINDING);

        OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, GL_READ_FRAMEBUFFER_BINDING);
        OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER_BINDING);

        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, renderbuffer_binding);

        if (GL_BLEND)
        {
            GL11.glEnable(GL11.GL_BLEND);
        }
        else
        {
            GL11.glDisable(GL11.GL_BLEND);
        }

        GL20.glBlendEquationSeparate(GL_BLEND_EQUATION_RGB, GL_BLEND_EQUATION_ALPHA);
        GL14.glBlendFuncSeparate(GL_BLEND_SRC_RGB, GL_BLEND_DST_RGB, GL_BLEND_SRC_ALPHA, GL_BLEND_DST_ALPHA);
        this.defaultState(mouseX, mouseY);
    }

    @Override
    public void handleMouseInput()
    {
        int k = Mouse.getEventButton();
        if (Mouse.getEventButtonState())//c
        {
//            this.eventButton = k;
        }
        else if (k != -1)//r
        {
            this.mouse_x = Mouse.getEventX();
            this.mouse_y = Mouse.getEventY();
//            this.eventButton = -1;
            this.state |= 1;
        }
    }

    public void detect(MemoSo rs)
    {
        if ((this.state & 1) == 1)
        {
            this.state &= 255-1;
    //        GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
    //        int draw_frame_buffer = OPENGL_INTBUFFER.get(0);
    //        GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
    //        int read_frame_buffer = OPENGL_INTBUFFER.get(0);
    //        GL11.glGetInteger(GL30.GL_RENDERBUFFER_BINDING, OPENGL_INTBUFFER);
    //        int renderbuffer_binding = OPENGL_INTBUFFER.get(0);

//            OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_FRAMEBUFFER);

//            //
//            GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//            GL_TEXTURE_BINDING_2D = OPENGL_INTBUFFER.get(0);
//
//            GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE, OPENGL_INTBUFFER);
//            GL_ACTIVE_TEXTURE = OPENGL_INTBUFFER.get(0);
//            OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
//            GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//            GL_TEXTURE_BINDING_2D_0 = OPENGL_INTBUFFER.get(0);
//            GL_TEXTURE_MIN_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
//            GL_TEXTURE_MAG_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);
//            //

            OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_FRAMEBUFFER);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE);
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, this.mc.displayWidth, this.mc.displayHeight, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
            OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE, 0);

            //
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D_0);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL_TEXTURE_MIN_FILTER_0);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_TEXTURE_MAG_FILTER_0);

            OpenGlHelper.setActiveTexture(GL_ACTIVE_TEXTURE);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D);
            //

    //        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, 0);
//            this.drawScreen(0, 0, 0);
            this.detectText(rs);

            GL11.glReadPixels(this.mouse_x, this.mouse_y, 1, 1, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, OPENGL_INTBUFFER);

            int r = OPENGL_INTBUFFER.get(0) & 0xFF;
    //        int g = OPENGL_INTBUFFER.get(1) & 0xFF;
    //        int b = OPENGL_INTBUFFER.get(2) & 0xFF;
    //        int a = OPENGL_INTBUFFER.get(3) & 0xFF;
    //        //read from framebuffer
    //        float nx = (2.0F * Mouse.getEventX() / this.mc.displayWidth) - 1.0F;
    //        float ny = (2.0F * /*(this.mc.displayHeight - */Mouse.getEventY()/*)*/ / this.mc.displayHeight) - 1.0F;
    //        Nali.I.logger.info("X " + nx);
    //        Nali.I.logger.info("Y" + ny);
            Nali.I.logger.info("ID " + r);
            this.hit = (byte)r;
//            this.hit = (byte)(OPENGL_INTBUFFER.get(0) & 0xFF);

            OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, GL_READ_FRAMEBUFFER_BINDING);
            OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER_BINDING);
    //        OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, read_frame_buffer);
    //        OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, draw_frame_buffer);
    //        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, renderbuffer_binding);
        }
    }

    public void init()
    {
//        if (this.texture_int_array != null)
//        {
        for (int texture : TEXTURE_INT_LIST)
        {
            GL11.glDeleteTextures(texture);
        }
        for (int array_buffer : ARRAY_BUFFER_INT_LIST)
        {
            OpenGlHelper.glDeleteBuffers(array_buffer);
        }

//        INDEX = 0;
//        byte size = (byte)STRING_ARRAY.length;
//        TEXTURE_INT_ARRAY = new int[size];
//        ARRAY_BUFFER_INT_ARRAY = new int[size];
        TEXTURE_INT_LIST.clear();
        ARRAY_BUFFER_INT_LIST.clear();
        int framebuffer = OpenGlHelper.glGenFramebuffers();

        //genQuad
        GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, OPENGL_INTBUFFER);
        GL_ARRAY_BUFFER_BINDING = OPENGL_INTBUFFER.get(0);
        //genQuad

        //preDraw
//        GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
//        int draw_frame_buffer = OPENGL_INTBUFFER.get(0);
//        GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
//        int read_frame_buffer = OPENGL_INTBUFFER.get(0);
//        GL11.glGetInteger(GL30.GL_RENDERBUFFER_BINDING, OPENGL_INTBUFFER);
//        int renderbuffer_binding = OPENGL_INTBUFFER.get(0);

//        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, 0);

//        //
//        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//        GL_TEXTURE_BINDING_2D = OPENGL_INTBUFFER.get(0);
//
//        GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE, OPENGL_INTBUFFER);
//        GL_ACTIVE_TEXTURE = OPENGL_INTBUFFER.get(0);
//        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
//        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//        GL_TEXTURE_BINDING_2D_0 = OPENGL_INTBUFFER.get(0);
//        GL_TEXTURE_MIN_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
//        GL_TEXTURE_MAG_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);
//        //
//
//        OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_FRAMEBUFFER);
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE);
//        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, this.mc.displayWidth, this.mc.displayHeight, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
//        OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE, 0);
//
//        //
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D_0);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL_TEXTURE_MIN_FILTER_0);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_TEXTURE_MAG_FILTER_0);
//
//        OpenGlHelper.setActiveTexture(GL_ACTIVE_TEXTURE);
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D);
//        //

        OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, framebuffer);

//        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//        GL_TEXTURE_BINDING_2D = OPENGL_INTBUFFER.get(0);
//
//        GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE, OPENGL_INTBUFFER);
//        GL_ACTIVE_TEXTURE = OPENGL_INTBUFFER.get(0);
//        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
//        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//        GL_TEXTURE_BINDING_2D_0 = OPENGL_INTBUFFER.get(0);
//        GL_TEXTURE_MIN_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
//        GL_TEXTURE_MAG_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);

//        GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, OPENGL_INTBUFFER);
//        GL_ARRAY_BUFFER_BINDING = OPENGL_INTBUFFER.get(0);
        //preDraw

        for (String string : STRING_ARRAY)
        {
            this.preDrawText(string, -1);
        }

        //preDraw
//        OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, GL_ARRAY_BUFFER_BINDING);

//        OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, GL_READ_FRAMEBUFFER_BINDING);
//        OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER_BINDING);
//        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, renderbuffer_binding);
        //preDraw
        this.initBuffer();

        //genQuad
        OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, GL_ARRAY_BUFFER_BINDING);
        //genQuad

        OpenGlHelper.glDeleteFramebuffers(framebuffer);

        GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
//        }
    }

    public void preDrawText(/*int framebuffer, */String string, int texture_index)
    {
//        if (this.framebuffer == -1)
//        {
//        int framebuffer = OpenGlHelper.glGenFramebuffers();
//        }
        int tw = this.fontRenderer.getStringWidth(string)/*-1*/;//big
        float x = this.mc.displayWidth / 2.0F, y = this.mc.displayHeight / 2.0F,
        fx = tw * (float)this.mc.displayWidth / this.width,
        fy = MAX_TH * (float)this.mc.displayHeight / this.height;
//        GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
//        ARRAY_BUFFER_INT_ARRAY[INDEX] = OpenGLBuffer.loadFloatBuffer(OpenGLBuffer.createFloatByteBuffer(this.createQuadVertices(x - fx, y - fy, x + fx, y + fy, this.mc.displayWidth, this.mc.displayHeight), true));
        ARRAY_BUFFER_INT_LIST.add(OpenGLBuffer.loadFloatBuffer(OpenGLBuffer.createFloatByteBuffer(this.createQuadVertices(x - fx, y - fy, x + fx, y + fy, this.mc.displayWidth, this.mc.displayHeight, 1.0F, 1.0F), true)));
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
            TEXTURE_INT_LIST.add(texture);
        }
        else
        {
            GL11.glDeleteTextures(TEXTURE_INT_LIST.get(texture_index));
            TEXTURE_INT_LIST.set(texture_index, texture);
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

        int ptw = tw + FONT, pth = MAX_TH + FONT;
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, TEXTURE_INT_ARRAY[INDEX]);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, ptw, pth, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
        GL11.glViewport(0, 0, ptw, pth);
        OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, texture, 0);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D_0);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL_TEXTURE_MIN_FILTER_0);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_TEXTURE_MAG_FILTER_0);

        OpenGlHelper.setActiveTexture(GL_ACTIVE_TEXTURE);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D);

        //draw
        float sx = (float)this.width / tw;
        float sy = (float)this.height / MAX_TH;
        GL11.glPushMatrix();
        GL11.glScalef(sx, sy, 1.0F);
        this.fontRenderer.drawStringWithShadow(string, 0, 0, 0xFFFFFFFF/*getRainbowColor4()*/);
        GL11.glPopMatrix();

//        OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, read_frame_buffer);
//        OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, draw_frame_buffer);
//        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, renderbuffer_binding);

//        OpenGlHelper.glDeleteFramebuffers(framebuffer);
    }

    public void preDrawTextVertical(/*int framebuffer, */String string, int texture_index, int width, int height, float x, float y, float scale)
    {
//        int box_height = tw - this.mc.displayHeight;
//        if (tw < this.mc.displayHeight)
//        {
//            box_height = 0;
//        }
//        int pth = tw + FONT, ptw = MAX_TH + FONT;
//        int pth = this.mc.displayHeight + ex/*this.mc.displayHeight / 2*/, ptw = this.mc.displayWidth;
//        int /*pth = *//*this.mc.displayHeight + *//*box_height*//*this.mc.displayHeight / 2*//*, */ptw = MAX_TH * (int)scale/*this.mc.displayWidth*/;
//        int ex = this.height;
//        while (ex < 1200)
//        {
//            ex *= 2;
//        }
//        int tw = this.fontRenderer.getStringWidth(string)/*-1*/;//big
//        float x = this.mc.displayWidth / 2.0F, y = this.mc.displayHeight / 2.0F,
//        fy = tw * (float)this.mc.displayHeight / this.height,
//        fx = MAX_TH * (float)this.mc.displayWidth / this.width;
//        ARRAY_BUFFER_INT_LIST.add(OpenGLBuffer.loadFloatBuffer(OpenGLBuffer.createFloatByteBuffer(this.createQuadVertices(x - fx, y - fy, x + fx, y + fy, this.mc.displayWidth, this.mc.displayHeight), true)));
//        ARRAY_BUFFER_INT_LIST.add(OpenGLBuffer.loadFloatBuffer(OpenGLBuffer.createFloatByteBuffer(this.createQuadVertices(0, 0, this.mc.displayWidth, this.mc.displayHeight, this.mc.displayWidth, this.mc.displayHeight, 1.0F/* / (this.mc.displayWidth / (float)this.width)*/, (this.mc.displayHeight/* + (float)this.height*/) / (float)(this.mc.displayHeight + ex)), true)));
        ARRAY_BUFFER_INT_LIST.add(OpenGLBuffer.loadFloatBuffer(OpenGLBuffer.createFloatByteBuffer(this.createQuadVertices(x, -height + y, width + x, y, this.mc.displayWidth, this.mc.displayHeight, 1.0F/* / (this.mc.displayWidth / (float)this.width)*/, 1.0F), true)));
        OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, GL_ARRAY_BUFFER_BINDING);

        int texture = GL11.glGenTextures();
        if (texture_index == -1)
        {
            TEXTURE_INT_LIST.add(texture);
        }
        else
        {
            GL11.glDeleteTextures(TEXTURE_INT_LIST.get(texture_index));
            TEXTURE_INT_LIST.set(texture_index, texture);
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

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
        GL11.glViewport(0, 0, width, height);
//        GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
        OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, texture, 0);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D_0);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL_TEXTURE_MIN_FILTER_0);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_TEXTURE_MAG_FILTER_0);

        OpenGlHelper.setActiveTexture(GL_ACTIVE_TEXTURE);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D);


//        GL11.glGetInteger(GL11.GL_MATRIX_MODE, OPENGL_INTBUFFER);
//        int gl_matrix_mode = OPENGL_INTBUFFER.get(0);
//        OPENGL_FLOATBUFFER.limit(16);
//        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, OPENGL_FLOATBUFFER);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
//        GlStateManager.ortho(0.0D, scaledresolution.getScaledWidth_double(), scaledresolution.getScaledHeight_double(), 0.0D, 1000.0D, 3000.0D);
//        int height = this.mc.displayHeight;
//        int width = this.mc.displayWidth;
//        this.mc.displayHeight = height + 1080;
//        ScaledResolution scaledresolution = new ScaledResolution(this.mc);
//        GL11.glOrtho(0.0D, scaledresolution.getScaledWidth_double(), scaledresolution.getScaledHeight_double(), 0.0D, 1000.0D, 3000.0D);
        GL11.glOrtho(0.0D, width, height, 0.0D, 1000.0D, 3000.0D);
//        this.mc.displayHeight = height;
//        Project.gluPerspective(90.0F, (float)this.mc.displayWidth / (float) this.mc.displayHeight, -4000F, 4000.0F);
//        GL11.glMatrixMode(GL11.GL_MODELVIEW);
//        OPENGL_FIXED_PIPE_FLOATBUFFER.limit(16);
//        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, OPENGL_FIXED_PIPE_FLOATBUFFER);//z = -2000
//        GL11.glLoadIdentity();
//        float aspect_ratio = (float)this.mc.displayWidth / (float)this.mc.displayHeight;
//        GL11.glOrtho(-aspect_ratio, aspect_ratio, -1, 1, -1, 1);
//        GL11.glOrtho(-1, 1, -1, 1, -1, 1);
//        GL11.glOrtho(0, this.mc.displayWidth, 0, this.mc.displayHeight, 1000, 3000);


//        float sy = (float)this.height / tw;
//        float sx = (float)this.width / MAX_TH;
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPushMatrix();
//        this.fontRenderer.drawStringWithShadow(string + "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789", 0, 0, 0xFFFFFFFF/*getRainbowColor4()*/);
//        GL11.glScalef((float)this.width / tw, (float)this.height / MAX_TH, 1.0F);
//        this.fontRenderer.drawStringWithShadow(string, 0, 0, 0xFFFFFFFF/*getRainbowColor4()*/);
        GL11.glScalef(scale, scale, scale);
        GL11.glTranslatef(8.0F, 0.0F, 0.0F);
//        GL11.glTranslatef(4.0F, 0.0F, 0.0F);
        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
//        GL11.glScalef(sx, sy, 1.0F);
        this.fontRenderer.drawStringWithShadow(string, 0, 0, 0xFFFFFFFF/*getRainbowColor4()*/);
        GL11.glPopMatrix();
//        this.fontRenderer.drawStringWithShadow(string, 0, 0, 0xFFFFFFFF/*getRainbowColor4()*/);
//        GL11.glLoadMatrix(OPENGL_FIXED_PIPE_FLOATBUFFER);

//        GL11.glMatrixMode(GL11.GL_PROJECTION);
//        GL11.glLoadMatrix(OPENGL_FLOATBUFFER);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadMatrix(OPENGL_PROJECTION_MATRIX_FLOATBUFFER);

//        GL11.glMatrixMode(gl_matrix_mode);
        GL11.glMatrixMode(GL_MATRIX_MODE);
    }

    public float[] createQuadVertices(float x0, float y0, float x1, float y1, float fwidth, float fheight, float u, float v)
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

//    public int genQuad(float x, float y, float width, float height, float fwidth, float fheight)
//    {
////        GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, OPENGL_INTBUFFER);
////        GL_ARRAY_BUFFER_BINDING = OPENGL_INTBUFFER.get(0);
//
////        float[] quad_vertices = this.createQuadVertices(x, y, width, height, fwidth, fheight);
////        float[] quad_vertices =
////        {
////            -1.0f,  1.0f,
////            -1.0f, -1.0f,
////            1.0f, -1.0f,
////
////            -1.0f,  1.0f,
////            1.0f, -1.0f,
////            1.0f,  1.0f
////        };
////        float[] quad_vertices =
////        {
////            // positions    // texCoords
////            -1.0F,  1.0F,   0.0F,   1.0F,
////            -1.0F,  -1.0F,  0.0F,   0.0F,
////            1.0F,   -1.0F,  1.0F,   0.0F,
////
////            -1.0F,  1.0F,   0.0F,   1.0F,
////            1.0F,   -1.0F,  1.0F,   0.0F,
////            1.0F,   1.0F,   1.0F,   1.0F
////        };
//
////        for (int i = 0; i < quad_vertices.length; ++i)
////        {
////            quad_vertices[i] *= 2.0F;
////        }
//
////        ByteBuffer bytebuffer = OpenGLBuffer.createFloatByteBuffer(quad_vertices, true);
//
//        //load
////        int array_buffer = OpenGlHelper.glGenBuffers();
////        int array_buffer = OpenGLBuffer.loadFloatBuffer(bytebuffer);
////        OpenGlHelper.glBindBuffer(GL15.GL_ARRAY_BUFFER, array_buffer);
////        OpenGlHelper.glBufferData(GL15.GL_ARRAY_BUFFER, bytebuffer, GL15.GL_STATIC_DRAW);
////        //set
////        OpenGlHelper.glBindBuffer(GL15.GL_ARRAY_BUFFER, array_buffer);
////        GL20.glVertexAttribPointer(0, 2, GL11.GL_FLOAT, false, 2 * Float.BYTES, 0);
////        GL20.glEnableVertexAttribArray(0);
//
////        OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, GL_ARRAY_BUFFER_BINDING);
//
////        return array_buffer;
//        return OpenGLBuffer.loadFloatBuffer(OpenGLBuffer.createFloatByteBuffer(this.createQuadVertices(x, y, width, height, fwidth, fheight), true));
//    }

    public void drawQuad(MemoSo rs, float[] m4x4_float_array, float[] color_float_array, int array_buffer, int texture)
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

        OPENGL_FIXED_PIPE_FLOATBUFFER.limit(16);
        OPENGL_FIXED_PIPE_FLOATBUFFER.clear();
        OPENGL_FIXED_PIPE_FLOATBUFFER.put(m4x4_float_array);
        OPENGL_FIXED_PIPE_FLOATBUFFER.flip();
        OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[0], false, OPENGL_FIXED_PIPE_FLOATBUFFER);

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
        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, OPENGL_FIXED_PIPE_FLOATBUFFER);
        OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[1], false, OPENGL_FIXED_PIPE_FLOATBUFFER);

//        GL11.glMatrixMode(gl_matrix_mode);
//        GL11.glLoadMatrix(OPENGL_FLOATBUFFER);

        OPENGL_FIXED_PIPE_FLOATBUFFER.limit(4);
        OPENGL_FIXED_PIPE_FLOATBUFFER.clear();
        OPENGL_FIXED_PIPE_FLOATBUFFER.put(color_float_array);
        OPENGL_FIXED_PIPE_FLOATBUFFER.flip();
        OpenGlHelper.glUniform4(rs.uniformlocation_int_array[2], OPENGL_FIXED_PIPE_FLOATBUFFER);

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

    public void drawQuadStaticBlur(int array_buffer, int texture)
    {
        OpenGLBuffer.setFloatBuffer(0, array_buffer, 4);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);
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

    public abstract void initBuffer();
    public abstract void preDraw();
    public abstract void drawText(MemoSo rs);
    public abstract void drawStaticBlur();
    public abstract void detectText(MemoSo rs);
//    public abstract void detectModel(MemoSo rs);
}
