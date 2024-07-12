package com.nali.list.container.gui;

import com.nali.list.container.SmallContainer;
import com.nali.math.M4x4;
import com.nali.math.Quaternion;
import com.nali.small.gui.MixGUI;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import java.nio.IntBuffer;

import static com.nali.system.opengl.memo.client.MemoCurrent.*;

@SideOnly(Side.CLIENT)
public class SmallGui extends MixGUI
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
    public void init()
    {
////        if (this.framebuffer == -1)
////        {
//        int framebuffer = OpenGlHelper.glGenFramebuffers();
////        }
//        if (this.texture != -1)
//        {
//            GL11.glDeleteTextures(this.texture);
//            OpenGlHelper.glDeleteBuffers(this.array_buffer);
//        }
//
//        GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
//        int draw_frame_buffer = OPENGL_INTBUFFER.get(0);
//        GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
//        int read_frame_buffer = OPENGL_INTBUFFER.get(0);
//        GL11.glGetInteger(GL30.GL_RENDERBUFFER_BINDING, OPENGL_INTBUFFER);
//        int renderbuffer_binding = OPENGL_INTBUFFER.get(0);
//
//        OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, framebuffer);
//        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, 0);
//
//        int texture = GL11.glGenTextures();
//
//        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//        GL_TEXTURE_BINDING_2D = OPENGL_INTBUFFER.get(0);
//        GL_TEXTURE_MIN_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
//        GL_TEXTURE_MAG_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);
//
//        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, ptw, pth, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
//        GL11.glViewport(0, 0, ptw, pth);
//        OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, texture, 0);
//
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL_TEXTURE_MIN_FILTER_0);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_TEXTURE_MAG_FILTER_0);
//
//        //draw
//        //draw own model with re-tex
//        //draw from mc without re-tex
//
//        OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, read_frame_buffer);
//        OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, draw_frame_buffer);
//        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, renderbuffer_binding);
//
//        OpenGlHelper.glDeleteFramebuffers(framebuffer);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        super.drawScreen(mouseX, mouseY, partialTicks);
        //avoid same renderbuffer
        // transparent ? state
        int tw = this.fontRenderer.getStringWidth("HOME")/*-1*/;//big
        int th = 9-1;//FontRenderer.FONT_HEIGHT-SHADOW;
//        float t_width = tw, t_height = th;
        int framebuffer = OpenGlHelper.glGenFramebuffers();
//        int renderbuffer = OpenGlHelper.glGenRenderbuffers();

        GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
        int draw_framebuffer_binding = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
        int read_framebuffer_binding = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL30.GL_RENDERBUFFER_BINDING, OPENGL_INTBUFFER);
        int renderbuffer_binding = OPENGL_INTBUFFER.get(0);

        OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, framebuffer);
        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, 0);

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

        OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, texture, 0);
//        OpenGlHelper.glRenderbufferStorage(OpenGlHelper.GL_RENDERBUFFER, GL11.GL_DEPTH_COMPONENT, ptw, pth);
//        OpenGlHelper.glFramebufferRenderbuffer(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, OpenGlHelper.GL_RENDERBUFFER, renderbuffer);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL_TEXTURE_WRAP_S_0);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL_TEXTURE_WRAP_T_0);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL_TEXTURE_MIN_FILTER_0);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_TEXTURE_MAG_FILTER_0);

        //0
        this.mc.getFramebuffer().checkFramebufferComplete();
//        if (GL30.glCheckFramebufferStatus(OpenGlHelper.GL_FRAMEBUFFER) == GL30.GL_FRAMEBUFFER_COMPLETE)
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
        this.fontRenderer.drawStringWithShadow("HOME", 0, 0, 0xFFFFFFFF/*getRainbowColor4()*/);

//        GL11.glMatrixMode(GL11.GL_PROJECTION);
//        GL11.glLoadMatrix(OPENGL_FIXED_PIPE_FLOATBUFFER);
//        GL11.glMatrixMode(GL11.GL_MODELVIEW);
//        GL11.glLoadMatrix(OPENGL_FIXED_PIPE_FLOATBUFFER_1);
//        GL11.glMatrixMode(GL_MATRIX_MODE);

//        GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
        GL11.glPopMatrix();
        //1

        OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, read_framebuffer_binding);
        OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, draw_framebuffer_binding);
        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, renderbuffer_binding);
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
//        M4x4 m4x4 = new M4x4();
//        m4x4.rotateZ(1.57079633F);
//        this.drawQuad(m4x4.mat, array_buffer, texture);
//        M4x4 m4x4 = new M4x4();
//        m4x4.scale(1.0F / sx, 1.0F / sy, 1.0F);
//        new Quaternion(0.0F, 0.0F, 1.57079633F).getM4x4().multiply(m4x4.mat, 0);
//        this.drawQuad(m4x4.mat, array_buffer, texture);
        this.drawQuad(new Quaternion(0.0F, 0.0F, 1.57079633F).getM4x4().mat, array_buffer, texture);
        this.drawQuad(M4x4.IDENTITY, array_buffer, texture);

        OpenGlHelper.glDeleteFramebuffers(framebuffer);
        GL11.glDeleteTextures(texture);
        OpenGlHelper.glDeleteBuffers(array_buffer);
    }
}
