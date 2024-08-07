package com.nali.list.container.gui;

import com.nali.list.container.SmallContainer;
import com.nali.small.SmallConfig;
import com.nali.small.gui.key.KeyMenuMe;
import com.nali.small.gui.page.Page;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
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
import java.util.UUID;

import static com.nali.small.entity.memo.client.ClientE.UUID_MAP;
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
    SCALE,
    WIDTH, HEIGHT;
//    LEFT, RIGHT, TOP, DOWN, WO, HO;
//    public static List<Integer>
//    TEXTURE_INT_LIST = new ArrayList(),
//    ARRAY_BUFFER_INT_LIST = new ArrayList();
//    public static byte FLAG;
    public static int
//    FULL_ARRAY_BUFFER = -1,
    OFFSET_RENDER_BUFFER = -1,
//    OFFSET_CUTOFF_FRAMEBUFFER = -1,
//    OFFSET_CUTOFF_FRAMEBUFFER_TEXTURE = -1,
    OFFSET_FRAMEBUFFER = -1, OFFSET_FRAMEBUFFER_0 = -1, OFFSET_FRAMEBUFFER_1 = -1,
    OFFSET_FRAMEBUFFER_TEXTURE = -1, OFFSET_FRAMEBUFFER_TEXTURE_0 = -1;

//    public byte
////    state,//on_hit new_page back_page init
//    hit,
//    page;

//    public int mouse_x, mouse_y;
//    public int eventButton;

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

    public static SmallGui get(EntityPlayer entityplayer, World world, int x, int y, int z)
    {
        if (x != -1)
        {
            Entity entity = world.getEntityByID(x);
            UUID uuid = null;

            if (entity != null)
            {
                uuid = entity.getUniqueID();
            }

            if (uuid == null)
            {
                uuid = UUID_MAP.get(x);
//                ClientE.C_MAP.get(uuid);
            }

            KeyMenuMe.ME &= 255-1;
            openPageMe(uuid);
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
//        if ((this.state & 8) == 8)
//        {
//            this.state &= 255-8;
//        }
//        else
//        {
//            this.state &= 255-1-4;
//        }

        this.partial_ticks = partialTicks;
        GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, OPENGL_INTBUFFER);
        int gl_array_buffer_binding = OPENGL_INTBUFFER.get(0);

        GL11.glGetInteger(GL11.GL_MATRIX_MODE, OPENGL_INTBUFFER);
//        int gl_matrix_mode = OPENGL_INTBUFFER.get(0);
        GL_MATRIX_MODE = OPENGL_INTBUFFER.get(0);
//        OPENGL_FLOATBUFFER.limit(16);
//        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, OPENGL_FLOATBUFFER);
        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, OPENGL_PROJECTION_MATRIX_FLOATBUFFER);
//        GL11.glMatrixMode(GL11.GL_PROJECTION);
//        GL11.glLoadIdentity();

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

        GL11.glEnable(GL11.GL_BLEND);

        GL20.glBlendEquationSeparate(GL14.GL_FUNC_ADD, GL14.GL_FUNC_ADD);

        GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);

        GL11.glGetInteger(GL30.GL_RENDERBUFFER_BINDING, OPENGL_INTBUFFER);
        int renderbuffer_binding = OPENGL_INTBUFFER.get(0);

        boolean gl_depth_test = GL11.glIsEnabled(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_DEPTH_TEST);

//        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, 0);

        GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
//        int draw_frame_buffer = OPENGL_INTBUFFER.get(0);
        GL_DRAW_FRAMEBUFFER_BINDING = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
//        int read_frame_buffer = OPENGL_INTBUFFER.get(0);
        GL_READ_FRAMEBUFFER_BINDING = OPENGL_INTBUFFER.get(0);

        if (WIDTH != this.mc.displayWidth || HEIGHT != this.mc.displayHeight || FONT != SmallConfig.CLIENT.font.scale/* || (FLAG & 1) == 1*//* || (this.state & 8) == 8*//* || MAIN_FRAMEBUFFER == -1*/)
        {
            WIDTH = this.mc.displayWidth;
            HEIGHT = this.mc.displayHeight;

            FONT = SmallConfig.CLIENT.font.scale;
            SCALE = FONT / (this.height / (float)this.mc.displayHeight);
            H = (int)(MAX_TH * SCALE);
//
////            LEFT = 0;
////            RIGHT = 0;
////            TOP = 0;
////            DOWN = 0;
////            WO = 0;
////            HO = 0;
//
//            if (OFFSET_FRAMEBUFFER == -1)
//            {
////                OFFSET_CUTOFF_FRAMEBUFFER = OpenGlHelper.glGenFramebuffers();
////                OFFSET_CUTOFF_FRAMEBUFFER_TEXTURE = GL11.glGenTextures();
//                OFFSET_FRAMEBUFFER = OpenGlHelper.glGenFramebuffers();
//                OFFSET_FRAMEBUFFER_0 = OpenGlHelper.glGenFramebuffers();
//                OFFSET_FRAMEBUFFER_1 = OpenGlHelper.glGenFramebuffers();
//                OFFSET_FRAMEBUFFER_TEXTURE = GL11.glGenTextures();
//                OFFSET_FRAMEBUFFER_TEXTURE_0 = GL11.glGenTextures();
//
//                OFFSET_RENDER_BUFFER = OpenGlHelper.glGenRenderbuffers();
//
////                if (FULL_ARRAY_BUFFER != -1)
////                {
////                    OpenGlHelper.glDeleteBuffers(FULL_ARRAY_BUFFER);
////                }
////                FULL_ARRAY_BUFFER = genBuffer(createFloatByteBuffer(new float[]
////                {
////                    -1, 1, 0.0F, 1.0F,
////                    -1, -1, 0.0F, 0.0F,
////                    1, -1, 1.0F, 0.0F,
////
////                    -1, 1, 0.0F, 1.0F,
////                    1, -1, 1.0F, 0.0F,
////                    1, 1, 1.0F, 1.0F
////                }));
//
////                GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
////                int draw_frame_buffer = OPENGL_INTBUFFER.get(0);
////                GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
////                int read_frame_buffer = OPENGL_INTBUFFER.get(0);
//
////                OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_FRAMEBUFFER);
////                OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE, 0);
//
////                OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, read_frame_buffer);
////                OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, draw_frame_buffer);
//            }
////            GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
////            int draw_frame_buffer = OPENGL_INTBUFFER.get(0);
////            GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
////            int read_frame_buffer = OPENGL_INTBUFFER.get(0);
////
////            OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_FRAMEBUFFER);
////            GL11.glBindTexture(GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE);
////            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, this.mc.displayWidth, this.mc.displayHeight, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
////            OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE, 0);
////
////            OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, read_frame_buffer);
////            OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, draw_frame_buffer);
//
//            //init
//            ////        if (this.texture_int_array != null)
//////        {
////        for (int texture : TEXTURE_INT_LIST)
////        {
////            GL11.glDeleteTextures(texture);
////        }
////        for (int array_buffer : ARRAY_BUFFER_INT_LIST)
////        {
////            OpenGlHelper.glDeleteBuffers(array_buffer);
////        }
////
//////        INDEX = 0;
//////        byte size = (byte)STRING_ARRAY.length;
//////        TEXTURE_INT_ARRAY = new int[size];
//////        ARRAY_BUFFER_INT_ARRAY = new int[size];
////        TEXTURE_INT_LIST.clear();
////        ARRAY_BUFFER_INT_LIST.clear();
////            int framebuffer = OpenGlHelper.glGenFramebuffers();
//
//            //genQuad
////            GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, OPENGL_INTBUFFER);
////            int gl_array_buffer_binding = OPENGL_INTBUFFER.get(0);
//            //genQuad
//
//            //preDraw
////        GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
////        int draw_frame_buffer = OPENGL_INTBUFFER.get(0);
////        GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
////        int read_frame_buffer = OPENGL_INTBUFFER.get(0);
////        GL11.glGetInteger(GL30.GL_RENDERBUFFER_BINDING, OPENGL_INTBUFFER);
////        int renderbuffer_binding = OPENGL_INTBUFFER.get(0);
//
////        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, 0);
//
////        //
////        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
////        GL_TEXTURE_BINDING_2D = OPENGL_INTBUFFER.get(0);
////
////        GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE, OPENGL_INTBUFFER);
////        GL_ACTIVE_TEXTURE = OPENGL_INTBUFFER.get(0);
////        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
////        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
////        GL_TEXTURE_BINDING_2D_0 = OPENGL_INTBUFFER.get(0);
////        GL_TEXTURE_MIN_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
////        GL_TEXTURE_MAG_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);
////        //
////
////        OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_FRAMEBUFFER);
////        GL11.glBindTexture(GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE);
////        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, this.mc.displayWidth, this.mc.displayHeight, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
////        OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, OFFSET_FRAMEBUFFER_TEXTURE, 0);
////
////        //
////        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D_0);
////        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL_TEXTURE_MIN_FILTER_0);
////        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_TEXTURE_MAG_FILTER_0);
////
////        OpenGlHelper.setActiveTexture(GL_ACTIVE_TEXTURE);
////        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D);
////        //
//
////            OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, framebuffer);
//
////        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
////        GL_TEXTURE_BINDING_2D = OPENGL_INTBUFFER.get(0);
////
////        GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE, OPENGL_INTBUFFER);
////        GL_ACTIVE_TEXTURE = OPENGL_INTBUFFER.get(0);
////        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
////        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
////        GL_TEXTURE_BINDING_2D_0 = OPENGL_INTBUFFER.get(0);
////        GL_TEXTURE_MIN_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
////        GL_TEXTURE_MAG_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);
//
////        GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, OPENGL_INTBUFFER);
////        GL_ARRAY_BUFFER_BINDING = OPENGL_INTBUFFER.get(0);
//            //preDraw
//
////        for (String string : STRING_ARRAY)
////        {
////            this.preDrawText(string, -1);
////        }
//
//            //preDraw
////        OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, GL_ARRAY_BUFFER_BINDING);
//
////        OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, GL_READ_FRAMEBUFFER_BINDING);
////        OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER_BINDING);
////        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, renderbuffer_binding);
//            //preDraw
////        if (BACK_ARRAY_BUFFER != -1)
////        {
////            OpenGlHelper.glDeleteBuffers(BACK_ARRAY_BUFFER);
////        }
////        BACK_ARRAY_BUFFER = OpenGLBuffer.loadFloatBuffer(OpenGLBuffer.createFloatByteBuffer(this.createQuadVUv(0, 0, this.mc.displayWidth, this.mc.displayHeight, this.mc.displayWidth, this.mc.displayHeight, 1.0F, 1.0F), true));
////        this.initBuffer();
////            for (Page page : PAGE_ARRAY)
////            {
////                page.init();
////            }
//
////            //genQuad
////            OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, gl_array_buffer_binding);
////            //genQuad
//
////            OpenGlHelper.glDeleteFramebuffers(framebuffer);
////
////            GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
////        }
//            //init
////            this.init();
////            this.state &= 255-8;
////            FLAG &= 255-1;
////            FLAG |= 1;
////            for (Page page : PAGE_ARRAY)
            for (Class clasz : PAGE_CLASS_SET)
            {
//                page.state |= 1;
//                page.setByte((byte)(page.getByte() | 1));
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
//                        Nali.LOGGER.info("BYTE " + clasz);
                    }
//                    error(e);
                }
            }
        }

        OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_FRAMEBUFFER_1);
//        if ((FLAG & 1) == 1)
        for (Page page : PAGE_ARRAY)
        {
//            if ((page.state & 1) == 1)
//            if ((page.getByte() & 1) == 1)
//            {
//                int framebuffer = OpenGlHelper.glGenFramebuffers();
//                OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, framebuffer);
//                OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, OFFSET_FRAMEBUFFER_1);
//            for (Page page : PAGE_ARRAY)
//            {
            page.init();
//            }
//                OpenGlHelper.glDeleteFramebuffers(framebuffer);
//                GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
//            FLAG &= 255-1;
//                page.state &= 255-1;
//            page.setByte((byte)(page.getByte() & 255-1));
//            Nali.LOGGER.info("init");
//            }
        }
        GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);

        //takeDefault
//        GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, OPENGL_INTBUFFER);
//        int gl_array_buffer_binding = OPENGL_INTBUFFER.get(0);

        GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM, OPENGL_INTBUFFER);
        int gl_current_program = OPENGL_INTBUFFER.get(0);

        //t
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
        //t

//        this.preDraw();
//        for (Page page : PAGE_ARRAY)
//        {
//            page.preDraw();
//        }

//        MemoS rs = S_LIST.get(SmallData.SHADER_O_STEP + 4);
//
//        OpenGlHelper.glUseProgram(rs.program);
//        GL20.glEnableVertexAttribArray(0);

//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

//        GL11.glGetInteger(GL11.GL_MATRIX_MODE, OPENGL_INTBUFFER);
//        int gl_matrix_mode = OPENGL_INTBUFFER.get(0);
//        OPENGL_FIXED_PIPE_FLOATBUFFER.limit(16);
//        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, OPENGL_FIXED_PIPE_FLOATBUFFER);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
//        float aspect_ratio = (float)this.mc.displayWidth / (float)this.mc.displayHeight;
//        GL11.glOrtho(-aspect_ratio, aspect_ratio, -1, 1, -1, 1);
        //
        //detect
        //        if ((this.state & 1) == 1)
//        {
//            this.state &= 255-1;
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
//        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

//        //
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D_0);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL_TEXTURE_MIN_FILTER_0);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_TEXTURE_MAG_FILTER_0);
//
//        OpenGlHelper.setActiveTexture(GL_ACTIVE_TEXTURE);
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D);
//        //

//        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, 0);
//            this.drawScreen(0, 0, 0);
//        this.detectText(rs);
        for (Page page : PAGE_ARRAY)
        {
            page.detect();
        }

//        GL11.glGetInteger(GL11.GL_PACK_ALIGNMENT, OPENGL_INTBUFFER);
//        int gl_pack_alignment = OPENGL_INTBUFFER.get(0);
//        GL11.glPixelStorei(GL11.GL_PACK_ALIGNMENT, 2);
        OPENGL_FIXED_PIPE_FLOATBUFFER.limit(4);
        GL11.glReadPixels(MOUSE_X, MOUSE_Y, 1, 1, GL11.GL_RGBA, GL11.GL_FLOAT, OPENGL_FIXED_PIPE_FLOATBUFFER);
//        OPENGL_FIXED_PIPE_FLOATBUFFER.limit(4);
//        GL11.glReadPixels(this.mouse_x, this.mouse_y, 1, 1, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, OPENGL_FIXED_PIPE_FLOATBUFFER);

//        int r = OPENGL_INTBUFFER.get(0) & 0xFF;
//        int g = OPENGL_INTBUFFER.get(1) & 0xFF;
//        int b = OPENGL_INTBUFFER.get(2) & 0xFF;
//        int a = OPENGL_INTBUFFER.get(3) & 0xFF;
//        //read from framebuffer
//        float nx = (2.0F * Mouse.getEventX() / this.mc.displayWidth) - 1.0F;
//        float ny = (2.0F * /*(this.mc.displayHeight - */Mouse.getEventY()/*)*/ / this.mc.displayHeight) - 1.0F;
//        Nali.I.logger.info("X " + nx);
//        Nali.I.logger.info("Y" + ny);
//        Nali.I.logger.info("ID " + r);
//        this.hit = (byte)r;
//        this.hit = (byte)OPENGL_INTBUFFER.get(0);
//        MOUSE.detect();
        HIT = (byte)(OPENGL_FIXED_PIPE_FLOATBUFFER.get(0) * 255.0F);
//        HIT = OPENGL_FIXED_PIPE_FLOATBUFFER.get(0);
//        HIT = (int)(OPENGL_FIXED_PIPE_FLOATBUFFER.get(0) * Integer.MAX_VALUE);
//        PAGE = (byte)(OPENGL_FIXED_PIPE_FLOATBUFFER.get(1) * 255.0F);
        E_PAGE = (byte)(OPENGL_FIXED_PIPE_FLOATBUFFER.get(1) * 255.0F);
        if ((STATE & 1) == 1)
        {
//            Nali.LOGGER.info("HIT " + HIT);
            PAGE = E_PAGE;
            STATE &= 255-1;
        }
//        this.hit = (byte)(OPENGL_FIXED_PIPE_FLOATBUFFER.get(0) * 255.0F);
//        if ((this.state & 1) == 1)
//        {
//            this.page = (byte)(OPENGL_FIXED_PIPE_FLOATBUFFER.get(1) * 255.0F);
////            this.page = (byte)(OPENGL_INTBUFFER.get(1) & 0xFF);
//            SMALLGUI.state &= 255-1;
//        }

//        GL11.glPixelStorei(GL11.GL_PACK_ALIGNMENT, gl_pack_alignment);

        OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, GL_READ_FRAMEBUFFER_BINDING);
        OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER_BINDING);
//        OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, read_frame_buffer);
//        OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, draw_frame_buffer);
//        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, renderbuffer_binding);
//        }
        //detect
//        this.detect(rs);
//        GL20.glDisableVertexAttribArray(0);

//        rs = S_LIST.get(SmallData.SHADER_O_STEP + 3);
//        OpenGlHelper.glUseProgram(rs.program);
//        GL20.glEnableVertexAttribArray(0);

//        OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, GL_READ_FRAMEBUFFER_BINDING);
//        OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER_BINDING);

//        this.drawText(rs);
        for (Page page : PAGE_ARRAY)
        {
            page.preDraw();
        }

        for (Page page : PAGE_ARRAY)
        {
            page.draw();
        }

//        KEY.run();
//        MOUSE.run();
//        this.state &= 255-4;

//        for (Page page : PAGE_ARRAY)
//        {
//            page.change();
//        }

        //setDefault
//        GL11.glMatrixMode(GL11.GL_PROJECTION);
//        GL11.glLoadMatrix(OPENGL_FLOATBUFFER);
        GL11.glLoadMatrix(OPENGL_PROJECTION_MATRIX_FLOATBUFFER);

//        GL11.glMatrixMode(gl_matrix_mode);
        GL11.glMatrixMode(GL_MATRIX_MODE);

//        GL20.glDisableVertexAttribArray(0);

        //t
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D_0);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL_TEXTURE_MIN_FILTER_0);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_TEXTURE_MAG_FILTER_0);
//
//        OpenGlHelper.setActiveTexture(GL_ACTIVE_TEXTURE);
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D);
        //t

        OpenGlHelper.glUseProgram(gl_current_program);

        OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, gl_array_buffer_binding);

        OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, GL_READ_FRAMEBUFFER_BINDING);
        OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER_BINDING);

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

        this.defaultState(mouseX, mouseY);
    }

//    @Override
//    public void handleInput() throws IOException
//    {
//        if (Mouse.isCreated())
//        {
//            do
//            {
////                this.mouseHandled = false;
////                if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.GuiScreenEvent.MouseInputEvent.Pre(this))) continue;
//                this.handleMouseInput();
////                if (this.equals(this.mc.currentScreen) && !this.mouseHandled) net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.GuiScreenEvent.MouseInputEvent.Post(this));
//            }
//            while (Mouse.next());
//        }
//
//        if (Keyboard.isCreated())
//        {
//            while (Keyboard.next())
//            {
//                this.keyHandled = false;
//                if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.GuiScreenEvent.KeyboardInputEvent.Pre(this))) continue;
//                this.handleKeyboardInput();
//                if (this.equals(this.mc.currentScreen) && !this.keyHandled) net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.GuiScreenEvent.KeyboardInputEvent.Post(this));
//            }
//        }
//    }

    @Override
    public void handleMouseInput()
    {
//        Nali.I.logger.info("START");
        int k = Mouse.getEventButton();
        MOUSE_X = Mouse.getEventX();
        MOUSE_Y = Mouse.getEventY();
//        int dwheel = Mouse.getDWheel();
//        EVENTDWHEEL = /*Integer.signum(*/Mouse.getEventDWheel()/*)*/;
        int mouse = Mouse.getEventDWheel();
//        int eventdwheel = Integer.signum(Mouse.getEventDWheel());
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
//            STATE |= 4;
        }
//        if (dwheel != 0)
//        {
//            DWHEEL += dwheel;
//        }
//        DY += Mouse.getDY();
//        DY += Mouse.getDY();
        if (Mouse.getEventButtonState())//c
        {
            STATE |= 2;
//            this.eventButton = k;
        }
        else if (k != -1)//r
        {
//            this.mouse_x = Mouse.getEventX();
//            this.mouse_y = Mouse.getEventY();
//            this.eventButton = -1;
            STATE |= 1;
            STATE &= 255-2;
//            this.state |= 1/*+8*/;
        }
//        Nali.I.logger.info("END");
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException
    {
        if (keyCode != Keyboard.KEY_ESCAPE)
        {
            KEY.detect(typedChar, keyCode);
//            if (typedChar == 'q' || keyCode == Keyboard.KEY_LEFT)
//            {
//                this.state |= 4/*+8*/;
//            }
//            int index = MESSAGE_STRINGBUILDER.length() - 1;
//            char end = MESSAGE_STRINGBUILDER.charAt(index);
//
//            switch (typedChar)
//            {
//                case '\b':
//                {
//                    if (MESSAGE_STRINGBUILDER.length() > 1)
//                    {
//                        MESSAGE_STRINGBUILDER.deleteCharAt(index - 1);
//                    }
//
//                    break;
//                }
//                case '\r':
//                {
//                    if (GUINETLOADER != null)
//                    {
//                        GUINETLOADER.run();
//                    }
//
//                    MESSAGE_STRINGBUILDER.setLength(0);
//                    MESSAGE_STRINGBUILDER.append("!");
//
//                    break;
//                }
//                default:
//                {
//                    boolean isShiftKeyDown = (keyCode == Keyboard.KEY_LSHIFT || keyCode == Keyboard.KEY_RSHIFT);
//
//                    if (!(isShiftKeyDown && (typedChar == ' ' || typedChar == '\0')))
//                    {
//                        MESSAGE_STRINGBUILDER.deleteCharAt(index).append(typedChar).append(end);
//                    }
//
//                    break;
//                }
//            }
        }
        else
        {
            super.keyTyped(typedChar, keyCode);
        }
    }

    //    public void preDrawTextHorizontal(String string, float x, float y)
//    {
//        int i = (int)(this.fontRenderer.getStringWidth(string) * SCALE),
//                box_width = i - this.mc.displayWidth;
//        if (i < this.mc.displayWidth)
//        {
//            box_width = i;
//        }
//        this.preDrawTextHorizontal(string/*, true*/, -1, box_width, H, x, y, SCALE);
//    }

//    public void preDrawTextVertical(/*int framebuffer, */String string, byte shadow_single, int texture_index, int width, int height, float x, float y, float scale)
//    {
////        int box_height = tw - this.mc.displayHeight;
////        if (tw < this.mc.displayHeight)
////        {
////            box_height = 0;
////        }
////        int pth = tw + FONT, ptw = MAX_TH + FONT;
////        int pth = this.mc.displayHeight + ex/*this.mc.displayHeight / 2*/, ptw = this.mc.displayWidth;
////        int /*pth = *//*this.mc.displayHeight + *//*box_height*//*this.mc.displayHeight / 2*//*, */ptw = MAX_TH * (int)scale/*this.mc.displayWidth*/;
////        int ex = this.height;
////        while (ex < 1200)
////        {
////            ex *= 2;
////        }
////        int tw = this.fontRenderer.getStringWidth(string)/*-1*/;//big
////        float x = this.mc.displayWidth / 2.0F, y = this.mc.displayHeight / 2.0F,
////        fy = tw * (float)this.mc.displayHeight / this.height,
////        fx = MAX_TH * (float)this.mc.displayWidth / this.width;
////        ARRAY_BUFFER_INT_LIST.add(OpenGLBuffer.loadFloatBuffer(OpenGLBuffer.createFloatByteBuffer(this.createQuadVertices(x - fx, y - fy, x + fx, y + fy, this.mc.displayWidth, this.mc.displayHeight), true)));
////        ARRAY_BUFFER_INT_LIST.add(OpenGLBuffer.loadFloatBuffer(OpenGLBuffer.createFloatByteBuffer(this.createQuadVertices(0, 0, this.mc.displayWidth, this.mc.displayHeight, this.mc.displayWidth, this.mc.displayHeight, 1.0F/* / (this.mc.displayWidth / (float)this.width)*/, (this.mc.displayHeight/* + (float)this.height*/) / (float)(this.mc.displayHeight + ex)), true)));
//        ARRAY_BUFFER_INT_LIST.add(OpenGLBuffer.loadFloatBuffer(OpenGLBuffer.createFloatByteBuffer(this.createQuadVUv(x, -height + y, width + x, y, this.mc.displayWidth, this.mc.displayHeight, 1.0F/* / (this.mc.displayWidth / (float)this.width)*/, 1.0F), true)));
//        OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, GL_ARRAY_BUFFER_BINDING);
//
//        int texture = GL11.glGenTextures();
//        if (texture_index == -1)
//        {
//            TEXTURE_INT_LIST.add(texture);
//        }
//        else
//        {
//            GL11.glDeleteTextures(TEXTURE_INT_LIST.get(texture_index));
//            TEXTURE_INT_LIST.set(texture_index, texture);
//        }
//
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
//
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
//        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
//        GL11.glViewport(0, 0, width, height);
////        GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
//        OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, texture, 0);
//
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D_0);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL_TEXTURE_MIN_FILTER_0);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_TEXTURE_MAG_FILTER_0);
//
//        OpenGlHelper.setActiveTexture(GL_ACTIVE_TEXTURE);
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D);
//
//
////        GL11.glGetInteger(GL11.GL_MATRIX_MODE, OPENGL_INTBUFFER);
////        int gl_matrix_mode = OPENGL_INTBUFFER.get(0);
////        OPENGL_FLOATBUFFER.limit(16);
////        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, OPENGL_FLOATBUFFER);
//        GL11.glMatrixMode(GL11.GL_PROJECTION);
//        GL11.glLoadIdentity();
////        GlStateManager.ortho(0.0D, scaledresolution.getScaledWidth_double(), scaledresolution.getScaledHeight_double(), 0.0D, 1000.0D, 3000.0D);
////        int height = this.mc.displayHeight;
////        int width = this.mc.displayWidth;
////        this.mc.displayHeight = height + 1080;
////        ScaledResolution scaledresolution = new ScaledResolution(this.mc);
////        GL11.glOrtho(0.0D, scaledresolution.getScaledWidth_double(), scaledresolution.getScaledHeight_double(), 0.0D, 1000.0D, 3000.0D);
//        GL11.glOrtho(0.0D, width, height, 0.0D, 1000.0D, 3000.0D);
////        this.mc.displayHeight = height;
////        Project.gluPerspective(90.0F, (float)this.mc.displayWidth / (float) this.mc.displayHeight, -4000F, 4000.0F);
////        GL11.glMatrixMode(GL11.GL_MODELVIEW);
////        OPENGL_FIXED_PIPE_FLOATBUFFER.limit(16);
////        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, OPENGL_FIXED_PIPE_FLOATBUFFER);//z = -2000
////        GL11.glLoadIdentity();
////        float aspect_ratio = (float)this.mc.displayWidth / (float)this.mc.displayHeight;
////        GL11.glOrtho(-aspect_ratio, aspect_ratio, -1, 1, -1, 1);
////        GL11.glOrtho(-1, 1, -1, 1, -1, 1);
////        GL11.glOrtho(0, this.mc.displayWidth, 0, this.mc.displayHeight, 1000, 3000);
//
//
////        float sy = (float)this.height / tw;
////        float sx = (float)this.width / MAX_TH;
//        GL11.glMatrixMode(GL11.GL_MODELVIEW);
//        GL11.glPushMatrix();
////        this.fontRenderer.drawStringWithShadow(string + "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789", 0, 0, 0xFFFFFFFF/*getRainbowColor4()*/);
////        GL11.glScalef((float)this.width / tw, (float)this.height / MAX_TH, 1.0F);
////        this.fontRenderer.drawStringWithShadow(string, 0, 0, 0xFFFFFFFF/*getRainbowColor4()*/);
//        GL11.glScalef(scale, scale, scale);
////        GL11.glTranslatef(8.0F, 0.0F, 0.0F);
////        GL11.glTranslatef(4.0F, 0.0F, 0.0F);
////        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
////        GL11.glScalef(sx, sy, 1.0F);
//        if ((shadow_single & 2) == 2)
//        {
//            for (int i = 0; i < string.length(); ++i)
//            {
//                this.fontRenderer.drawString("" + string.charAt(i), 0, MAX_TH * i, 0xFFFFFFFF);
//            }
//        }
//        else
//        {
//            GL11.glTranslatef(8.0F, 0.0F, 0.0F);
//            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
//        }
//
//        if ((shadow_single & 1) == 1)
//        {
//            this.fontRenderer.drawStringWithShadow(string, 0, 0, 0xFFFFFFFF/*getRainbowColor4()*/);
////            this.fontRenderer.drawString(string, 0, 0, 0xFFFFFFFF);
////            GL11.glPushMatrix();
////            GL11.glTranslatef(0.0F, (height / 2.0F) / scale, 0.0F);
////            GL11.glScalef(1.1F, 1.1F, 1.1F);
////            GL11.glTranslatef(0.0F, -((height / 2.0F) / (scale * 1.05F)), 0.0F);
////            this.fontRenderer.drawString(string, 0, 0, 0xFF000000);
////            GL11.glPopMatrix();
//        }
//        else if ((shadow_single & 2+1) == 0)
//        {
//            this.fontRenderer.drawString(string, 0, 0, 0xFFFFFFFF);
//        }
//        GL11.glPopMatrix();
//////        this.fontRenderer.drawStringWithShadow(string, 0, 0, 0xFFFFFFFF/*getRainbowColor4()*/);
//////        GL11.glLoadMatrix(OPENGL_FIXED_PIPE_FLOATBUFFER);
////
//////        GL11.glMatrixMode(GL11.GL_PROJECTION);
//////        GL11.glLoadMatrix(OPENGL_FLOATBUFFER);
////        GL11.glMatrixMode(GL11.GL_PROJECTION);
////        GL11.glLoadMatrix(OPENGL_PROJECTION_MATRIX_FLOATBUFFER);
////
//////        GL11.glMatrixMode(gl_matrix_mode);
////        GL11.glMatrixMode(GL_MATRIX_MODE);
//    }
//
////    public float[] createQuadVertices(float x0, float y0, float x1, float y1, float fwidth, float fheight, float u, float v)
//    public float[] createQuadVUv(float x0, float y0, float x1, float y1, float fwidth, float fheight, float u, float v)
//    {
////        return new float[]
////        {
////            // First triangle
////            x, y, 0.0f, 0.0f, // Bottom-left
////            x + width, y, 1.0f, 0.0f, // Bottom-right
////            x + width, y + height, 1.0f, 1.0f, // Top-right
////
////            // Second triangle
////            x, y, 0.0f, 0.0f, // Bottom-left
////            x + width, y + height, 1.0f, 1.0f, // Top-right
////            x, y + height, 0.0f, 1.0f // Top-left
////        };
////        return new float[]
////        {
////            // Triangle 1
////            (x - width / 2.0F) / fwidth, (y + height / 2.0F) / fheight, 0.0F, 1.0F,
////            (x + width / 2.0F) / fwidth, (y + height / 2.0F) / fheight, 1.0F, 1.0F,
////            (x + width / 2.0F) / fwidth, (y - height / 2.0F) / fheight, 1.0F, 0.0F,
////
////            // Triangle 2
////            (x - width / 2.0F) / fwidth, (y + height / 2.0F) / fheight, 0.0F, 1.0F,
////            (x + width / 2.0F) / fwidth, (y - height / 2.0F) / fheight, 1.0F, 0.0F,
////            (x - width / 2.0F) / fwidth, (y - height / 2.0F) / fheight, 0.0F, 0.0F
////        };
////        float w = 1.0F / width;
////        float h = 1.0F / height;
////        x = 1.0F / x;
////        y = 1.0F / y;
////        float nx = (2.0F * x / width) - 1.0F;
////        float ny = (2.0F * y / height) - 1.0F;
////        return new float[]
////        {
////            // positions    // texCoords
////            -1.0F + nx,  1.0F + ny,   0.0F,   1.0F,
////            -1.0F + nx,  -1.0F + ny,  0.0F,   0.0F,
////            1.0F + nx,   -1.0F + ny,  1.0F,   0.0F,
////
////            -1.0F + nx,  1.0F + ny,   0.0F,   1.0F,
////            1.0F + nx,   -1.0F + ny,  1.0F,   0.0F,
////            1.0F + nx,   1.0F + ny,   1.0F,   1.0F
////        };
////        x = 0;
////        y = 0;
////        width = fwidth;
////        height = fheight;
//        float nx1 = (2.0F * x0 / fwidth) - 1.0F;
//        float ny1 = (2.0F * y0 / fheight) - 1.0F;
//
//        float nx2 = (2.0F * x1 / fwidth) - 1.0F;
//        float ny2 = (2.0F * y1 / fheight) - 1.0F;
//
//        return new float[]
//        {
//            nx1, ny2, 0.0F, v,
//            nx1, ny1, 0.0F, 0.0F,
//            nx2, ny1, u, 0.0F,
//
//            nx1, ny2, 0.0F, v,
//            nx2, ny1, u, 0.0F,
//            nx2, ny2, u, v
//        };
//
////        return new float[]
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
//    }

//    public float[] createQuadV(float x0, float y0, float x1, float y1, float fwidth, float fheight)
//    {
//        float nx1 = (2.0F * x0 / fwidth) - 1.0F;
//        float ny1 = (2.0F * y0 / fheight) - 1.0F;
//
//        float nx2 = (2.0F * x1 / fwidth) - 1.0F;
//        float ny2 = (2.0F * y1 / fheight) - 1.0F;
//
//        return new float[]
//        {
//            nx1, ny2,
//            nx1, ny1,
//            nx2, ny1,
//
//            nx1, ny2,
//            nx2, ny1,
//            nx2, ny2,
//        };
//    }

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

//    public void drawQuadV(MemoS rs, float[] m4x4_float_array, float[] color_float_array, int array_buffer)
//    {
//        OpenGLBuffer.setFloatBuffer(0, array_buffer, 4);
//
//        OPENGL_FIXED_PIPE_FLOATBUFFER.limit(16);
//        OPENGL_FIXED_PIPE_FLOATBUFFER.clear();
//        OPENGL_FIXED_PIPE_FLOATBUFFER.put(m4x4_float_array);
//        OPENGL_FIXED_PIPE_FLOATBUFFER.flip();
//        OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[0], false, OPENGL_FIXED_PIPE_FLOATBUFFER);
//
//        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, OPENGL_FIXED_PIPE_FLOATBUFFER);
//        OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[1], false, OPENGL_FIXED_PIPE_FLOATBUFFER);
//
//        OPENGL_FIXED_PIPE_FLOATBUFFER.limit(4);
//        OPENGL_FIXED_PIPE_FLOATBUFFER.clear();
//        OPENGL_FIXED_PIPE_FLOATBUFFER.put(color_float_array);
//        OPENGL_FIXED_PIPE_FLOATBUFFER.flip();
//        OpenGlHelper.glUniform4(rs.uniformlocation_int_array[2], OPENGL_FIXED_PIPE_FLOATBUFFER);
//
//        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);
//    }

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

//    public abstract void initBuffer();
//    public abstract void preDraw();
//    public abstract void drawText(MemoS rs);
//    public abstract void drawStaticBlur();
//    public abstract void detectText(MemoS rs);
//    public abstract void detectModel(MemoS rs);
}
