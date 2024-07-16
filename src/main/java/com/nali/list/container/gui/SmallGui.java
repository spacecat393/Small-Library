package com.nali.list.container.gui;

import com.nali.list.container.SmallContainer;
import com.nali.math.M4x4;
import com.nali.small.gui.MixGUI;
import com.nali.system.opengl.OpenGLBuffer;
import com.nali.system.opengl.memo.client.MemoSo;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import static com.nali.system.opengl.memo.client.MemoCurrent.*;
import static com.nali.system.opengl.memo.client.MemoCurrent.GL_DRAW_FRAMEBUFFER_BINDING;

@SideOnly(Side.CLIENT)
public class SmallGui extends MixGUI
{
    public static int[] S_TEXTURE_INT_ARRAY = new int[0], S_ARRAY_BUFFER_INT_ARRAY = new int[0];
    public static byte SAKURA = -1;
    public static float /*X, */Y, MAX_Y;
    public static M4x4[] M4X4;
    static
    {
        M4X4 = new M4x4[3];
        M4X4[0] = new M4x4();
//        M4X4[0].translateXYZ(0.0F, 0.0F, 0.0F);
        M4X4[1] = new M4x4();
        M4X4[1].translateXYZ(0.0F, 0.5F, 0.0F);
        M4X4[2] = new M4x4();
        M4X4[2].translateXYZ(0.0F, 0.25F, 0.0F);
    }

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
    public void initBuffer()
    {
        Y = 0;

        for (int texture : S_TEXTURE_INT_ARRAY)
        {
            GL11.glDeleteTextures(texture);
        }
        for (int array_buffer : S_ARRAY_BUFFER_INT_ARRAY)
        {
            OpenGlHelper.glDeleteBuffers(array_buffer);
        }

//        S_TEXTURE_INT_ARRAY = new int[1];
        S_ARRAY_BUFFER_INT_ARRAY = new int[1];

//        int tw = this.mc.displayWidth, th = this.mc.displayHeight;
//        float aspect_ratio = (float)this.mc.displayWidth / (float)this.mc.displayHeight;
//        float x = this.mc.displayWidth / 2.0F, y = this.mc.displayHeight / 2.0F,
//        fx = tw * (float)this.mc.displayWidth / this.width,
//        fy = th * (float)this.mc.displayHeight / this.height;
//        GL11.glViewport(0, 0, tw, th);
        S_ARRAY_BUFFER_INT_ARRAY[0] = OpenGLBuffer.loadFloatBuffer(OpenGLBuffer.createFloatByteBuffer(this.createQuadVertices(0, 0, this.mc.displayWidth, this.mc.displayHeight, this.mc.displayWidth, this.mc.displayHeight, 1.0F, 1.0F), true));
//        S_ARRAY_BUFFER_INT_ARRAY[0] = OpenGLBuffer.loadFloatBuffer(OpenGLBuffer.createFloatByteBuffer(this.createQuadVertices(x - fx, y - fy, x + fx, y + fy, this.mc.displayWidth, this.mc.displayHeight), true));

//        S_TEXTURE_INT_ARRAY[0] = GL11.glGenTextures();
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, S_TEXTURE_INT_ARRAY[0]);
//        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, this.mc.displayWidth, this.mc.displayHeight, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
//        GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
//        OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, S_TEXTURE_INT_ARRAY[0], 0);

        //draw block / item
//        GL11.glMatrixMode(GL11.GL_PROJECTION);
//        GL11.glLoadIdentity();
//        GL11.glOrtho(0.0D, ~width, ~height, 0.0D, 1000.0D, 3000.0D);
//        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        this.drawPath(-1);
        this.drawSakura(this.mc.player.getEntityData().getByte("Nali_sakura"), -1);

//        GL11.glMatrixMode(GL11.GL_PROJECTION);
//        GL11.glLoadMatrix(OPENGL_PROJECTION_MATRIX_FLOATBUFFER);
//
//        GL11.glMatrixMode(GL_MATRIX_MODE);
    }

    @Override
    public void preDraw()
    {
        byte sakura = this.mc.player.getEntityData().getByte("Nali_sakura");
        if (SAKURA != sakura || false)
        {
            int framebuffer = OpenGlHelper.glGenFramebuffers();
            OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, framebuffer);

//            GL11.glLoadIdentity();
//            GL11.glOrtho(0.0D, width, height, 0.0D, 1000.0D, 3000.0D);
//            GL11.glMatrixMode(GL11.GL_MODELVIEW);

            if (false)
            {
                this.drawPath(15);
            }
            if (SAKURA != sakura)
            {
//            GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, OPENGL_INTBUFFER);
//            GL_ARRAY_BUFFER_BINDING = OPENGL_INTBUFFER.get(0);

                this.drawSakura(sakura, 16);

//            OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, GL_ARRAY_BUFFER_BINDING);

                OpenGlHelper.glDeleteFramebuffers(framebuffer);
            }

//            GL11.glMatrixMode(GL11.GL_PROJECTION);
//            GL11.glLoadMatrix(OPENGL_PROJECTION_MATRIX_FLOATBUFFER);
//
//            GL11.glMatrixMode(GL_MATRIX_MODE);

            OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, GL_READ_FRAMEBUFFER_BINDING);
            OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER_BINDING);

            GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        super.drawScreen(mouseX, mouseY, partialTicks);
//        float a = (this.height / (float)this.mc.displayHeight);
//        float scale = 2.0F / (this.height / (float)this.mc.displayHeight);
        Y += 0.025F * partialTicks;
        if (Y > MAX_Y)
        {
            Y = 0;
        }
    }

    @Override
    public void drawText(MemoSo rs)
    {
//        M4x4 m4x4 = new Quaternion(0.0F, 0.0F, 1.57079633F).getM4x4();
        M4x4 m4x4 = new M4x4();
//        m4x4.translateUV(0.0F, Y);
        m4x4.translateXYZ(0.0F, Y, 0.0F);
//        M4x4 scale_m4x4 = new M4x4();
//        scale_m4x4.scale(1.78F, 0.58F, 1.0F);
//        scale_m4x4.multiply(m4x4.mat, 0);
        this.drawQuad(rs, m4x4.mat, new float[]{1.0F, 1.0F, 1.0F, 1.0F}, ARRAY_BUFFER_INT_LIST.get(15), TEXTURE_INT_LIST.get(15));
        this.drawQuad(rs, M4X4[0].mat, new float[]{1.0F, 1.0F, 1.0F, 1.0F}, ARRAY_BUFFER_INT_LIST.get(16), TEXTURE_INT_LIST.get(16));

        this.drawQuad(rs, M4X4[1].mat, new float[]{1.0F, 1.0F, 1.0F, 1.0F}, ARRAY_BUFFER_INT_LIST.get(0), TEXTURE_INT_LIST.get(0));
        this.drawQuad(rs, M4X4[2].mat, new float[]{1.0F, 1.0F, 1.0F, 1.0F}, ARRAY_BUFFER_INT_LIST.get(3), TEXTURE_INT_LIST.get(3));

//        m4x4 = new M4x4();
//        this.drawQuad(rs, m4x4.mat, new float[]{1.0F, 1.0F, 1.0F, 1.0F}, ARRAY_BUFFER_INT_LIST.get(14), TEXTURE_INT_LIST.get(14));

//        this.drawQuad(rs, new Quaternion(0.0F, 0.0F, 1.57079633F).getM4x4().mat, new float[]{1.0F, 1.0F, 1.0F, 0.75F}, ARRAY_BUFFER_INT_LIST.get(5), TEXTURE_INT_LIST.get(5));
//        this.drawQuad(rs, new Quaternion(0.0F, 0.0F, 1.57079633F / 2.0F).getM4x4().mat, new float[]{1.0F, 1.0F, 1.0F, 0.75F}, ARRAY_BUFFER_INT_LIST.get(11), TEXTURE_INT_LIST.get(11));
//        this.drawQuad(rs, new Quaternion(0.0F, 0.0F, 1.57079633F).getM4x4().mat, new float[]{1.0F, 1.0F, 1.0F, 0.75F}, ARRAY_BUFFER_INT_ARRAY[0], TEXTURE_INT_ARRAY[0]);
//        this.drawQuad(rs, new Quaternion(0.0F, 0.0F, 1.57079633F / 2.0F).getM4x4().mat, new float[]{1.0F, 1.0F, 1.0F, 0.75F}, ARRAY_BUFFER_INT_ARRAY[0], TEXTURE_INT_ARRAY[0]);
    }

    @Override
    public void drawStaticBlur()
    {
        //draw full texture without projection and rotation
//        M4x4 m4x4 = new M4x4();
//        m4x4.scale(1.78F, 1.0F, 1.0F);
//        new Quaternion(0.0F, 0.0F, 0.0F).getM4x4().multiply(m4x4.mat, 0);
//        this.drawQuad(rs, m4x4.mat, new float[]{0.0F, 0.0F, 0.0F, 0.5F}, S_ARRAY_BUFFER_INT_ARRAY[0], TEXTURE_INT_ARRAY[0]);
//        this.drawQuad(rs, new Quaternion(0.0F, 0.0F, 1.57079633F / 2.0F).getM4x4().mat, new float[]{0.0F, 0.0F, 0.0F, 0.5F}, S_ARRAY_BUFFER_INT_ARRAY[0], TEXTURE_INT_ARRAY[0]);
        this.drawQuadStaticBlur(S_ARRAY_BUFFER_INT_ARRAY[0], this.mc.getFramebuffer().framebufferTexture);
    }

    @Override
    public void detectText(MemoSo rs)
    {
        this.drawQuad(rs, M4X4[1].mat, new float[]{1.0F/255.0F, 0.0F, 0.0F, 1.0F}, ARRAY_BUFFER_INT_LIST.get(0), TEXTURE_INT_LIST.get(0));
        this.drawQuad(rs, M4X4[2].mat, new float[]{2.0F/255.0F, 0.0F, 0.0F, 1.0F}, ARRAY_BUFFER_INT_LIST.get(3), TEXTURE_INT_LIST.get(3));
    }

    public void drawPath(int index)
    {
        String string = STRING_ARRAY[14];//+ " > ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int i = (int)(this.fontRenderer.getStringWidth(string) * SCALE),
        box_height = i - this.mc.displayHeight;
        if (i < this.mc.displayHeight)
        {
            box_height = i;
        }
        MAX_Y = ((this.mc.displayHeight + box_height) / (float)this.mc.displayHeight) * 2.0F;
        this.preDrawTextVertical(string, index, W, (int)(box_height * SCALE), 0.0F, 0.0F, SCALE);
    }

    public void drawSakura(byte sakura, int index)
    {
        SAKURA = sakura;//(byte)this.mc.player.getEntityData().getInteger("Nali_sakura");
        String string = STRING_ARRAY[11] + " " + SAKURA;
        int i = (int)(this.fontRenderer.getStringWidth(string) * SCALE),
        box_height = i - this.mc.displayHeight;
        if (i < this.mc.displayHeight)
        {
            box_height = i;
        }
        this.preDrawTextVertical(string, index, W, (int)(box_height * SCALE), W, box_height, SCALE);
    }

    //    public void drawFont(String string)
//    {
//        //avoid same renderbuffer
//        // transparent ? state
//        int tw = this.fontRenderer.getStringWidth(string)/*-1*/;//big
////        int th = 9-1;//FontRenderer.FONT_HEIGHT-SHADOW;
////        float t_width = tw, t_height = th;
//        int framebuffer = OpenGlHelper.glGenFramebuffers();
////        int renderbuffer = OpenGlHelper.glGenRenderbuffers();
//
//        GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
//        int draw_framebuffer_binding = OPENGL_INTBUFFER.get(0);
//        GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
//        int read_framebuffer_binding = OPENGL_INTBUFFER.get(0);
//        GL11.glGetInteger(GL30.GL_RENDERBUFFER_BINDING, OPENGL_INTBUFFER);
//        int renderbuffer_binding = OPENGL_INTBUFFER.get(0);
//
//        OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, framebuffer);
//        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, 0);
//
//        //0
//        int texture = GL11.glGenTextures();
//
//        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//        GL_TEXTURE_BINDING_2D = OPENGL_INTBUFFER.get(0);
////        GL_TEXTURE_WRAP_S_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S);
////        GL_TEXTURE_WRAP_T_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T);
//        GL_TEXTURE_MIN_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
//        GL_TEXTURE_MAG_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);
//
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
////        GL11.glViewport(this.mc.displayWidth / 2, this.mc.displayHeight / 2, this.mc.displayWidth, this.mc.displayHeight);
////        GL11.glViewport(0, 0, this.mc.displayWidth / 2, this.mc.displayHeight / 2);
//        int ptw = tw + 4, pth = MAX_TH + 4;
//
//        GL11.glViewport(0, 0, ptw, pth);
////        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, this.mc.displayWidth/* / 2*/, this.mc.displayHeight/* / 2*/, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
//        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, ptw, pth, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
//
//        OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, texture, 0);
////        OpenGlHelper.glRenderbufferStorage(OpenGlHelper.GL_RENDERBUFFER, GL11.GL_DEPTH_COMPONENT, ptw, pth);
////        OpenGlHelper.glFramebufferRenderbuffer(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, OpenGlHelper.GL_RENDERBUFFER, renderbuffer);
//
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D);
////        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL_TEXTURE_WRAP_S_0);
////        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL_TEXTURE_WRAP_T_0);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL_TEXTURE_MIN_FILTER_0);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_TEXTURE_MAG_FILTER_0);
//
//        //0
//        this.mc.getFramebuffer().checkFramebufferComplete();
////        if (GL30.glCheckFramebufferStatus(OpenGlHelper.GL_FRAMEBUFFER) == GL30.GL_FRAMEBUFFER_COMPLETE)
////        {
////        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("" + this.mc.player.getEntityData().getInteger("Nali_sakura"), 25, 11, 0xFFFFACDF/*getRainbowColor4()*/);
////        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("HOME", this.width / 2.0F - 4.0F*3.0F, this.height / 2.0F - 3.0F, 0xFFFFACDF/*getRainbowColor4()*/);
////        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("HOME", 0, 0, 0xFFFFACDF/*getRainbowColor4()*/);
////        GL11.glClearColor(1.0F, 0, 0, 1.0F);
////        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
////        }
//
//        //1
//
////        float s = Math.min((float)this.width / tw, (float)this.height / th);
//        float sx = (float)this.width / tw;
//        float sy = (float)this.height / MAX_TH;
//
////        float tx = (this.width - (tw * sx)) / 2.0F;
////        float ty = (this.height - (th * sy)) / 2.0F;
//
//        GL11.glPushMatrix();
////        GL11.glTranslated(tx, ty, 0);
//        GL11.glScalef(sx, sy, 1.0F);
//
////        OPENGL_FIXED_PIPE_FLOATBUFFER.limit(16);
////        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, OPENGL_FIXED_PIPE_FLOATBUFFER);
////        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, OPENGL_FIXED_PIPE_FLOATBUFFER_1);
////        GL11.glGetInteger(GL11.GL_MATRIX_MODE, OPENGL_INTBUFFER);
////        GL_MATRIX_MODE = OPENGL_INTBUFFER.get(0);
//
////        GL11.glMatrixMode(GL11.GL_PROJECTION);
////        GL11.glOrtho(320 - this.mc.displayWidth/2.0F, 320 + this.mc.displayWidth/2.0F, 240 + this.mc.displayHeight/2.0F, 240 - this.mc.displayHeight/2.0F, -1, 1);
//////        GL11.glLoadIdentity();
////        GL11.glMatrixMode(GL11.GL_MODELVIEW);
////        GL11.glLoadIdentity();
////        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("HOME", 0, 0, 0xFFFFACDF/*getRainbowColor4()*/);
//        this.fontRenderer.drawStringWithShadow("HOME", 0, 0, 0xFFFFFFFF/*getRainbowColor4()*/);
//
////        GL11.glMatrixMode(GL11.GL_PROJECTION);
////        GL11.glLoadMatrix(OPENGL_FIXED_PIPE_FLOATBUFFER);
////        GL11.glMatrixMode(GL11.GL_MODELVIEW);
////        GL11.glLoadMatrix(OPENGL_FIXED_PIPE_FLOATBUFFER_1);
////        GL11.glMatrixMode(GL_MATRIX_MODE);
//
////        GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
//        GL11.glPopMatrix();
//        //1
//
//        OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, read_framebuffer_binding);
//        OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, draw_framebuffer_binding);
////        GL11.glViewport(0, 0, 4*3*2, 3*2);
//        GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
//
////        x = this.width / 2.0F - 4.0F*3.0F, y = this.height / 2.0F - 3.0F;
//        float x = this.mc.displayWidth / 2.0F, y = this.mc.displayHeight / 2.0F;
////        int array_buffer = this.genQuad(this.width / 2.0F - 4.0F*3.0F, this.height / 2.0F - 3.0F, t_width, t_height, this.mc.displayWidth, this.mc.displayHeight);
////        int array_buffer = this.genQuad(this.mc.displayWidth / 2.0F, 0, 4*3*2, 3*2);
////        int array_buffer = this.genQuad(0.5F, 0.5F, 4*3*2, 3*2);
//        float fx = tw * (float)this.mc.displayWidth / this.width;
//        float fy = MAX_TH * (float)this.mc.displayHeight / this.height;
//        int array_buffer = this.genQuad(x - fx, y - fy, x + fx, y + fy, this.mc.displayWidth, this.mc.displayHeight);
////        int array_buffer = this.genQuad(0, 0, this.mc.displayWidth, this.mc.displayHeight, this.mc.displayWidth, this.mc.displayHeight);
////        int array_buffer = this.genQuad(this.width / 2.0F - 4.0F*3.0F, this.height / 2.0F - 3.0F, t_width, t_height, this.mc.displayWidth, this.mc.displayHeight);
////        int array_buffer = this.genQuad(0, 0, this.mc.displayWidth, this.mc.displayHeight);
////        int array_buffer = this.genQuad((this.mc.displayWidth - t_width) / 2, (this.mc.displayHeight - t_height) / 2, t_width, t_height);
////        int array_buffer = this.genQuad();
////        M4x4 m4x4 = new M4x4();
////        m4x4.rotateZ(1.57079633F);
////        this.drawQuad(m4x4.mat, array_buffer, texture);
////        M4x4 m4x4 = new M4x4();
////        m4x4.scale(1.0F / sx, 1.0F / sy, 1.0F);
////        new Quaternion(0.0F, 0.0F, 1.57079633F).getM4x4().multiply(m4x4.mat, 0);
////        this.drawQuad(m4x4.mat, array_buffer, texture);
//        this.drawQuad(new Quaternion(0.0F, 0.0F, 1.57079633F).getM4x4().mat, new float[]{1.0F, 1.0F, 1.0F, 0.75F}, array_buffer, texture);
//        this.drawQuad(new Quaternion(0.0F, 0.0F, 1.57079633F / 2.0F).getM4x4().mat, new float[]{1.0F, 1.0F, 1.0F, 0.75F}, array_buffer, texture);
//        this.drawQuad(M4x4.IDENTITY, new float[]{1.0F, 1.0F, 1.0F, 0.75F}, array_buffer, texture);
//
//        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, renderbuffer_binding);
//
//        OpenGlHelper.glDeleteFramebuffers(framebuffer);
//        GL11.glDeleteTextures(texture);
//        OpenGlHelper.glDeleteBuffers(array_buffer);
//    }
}
