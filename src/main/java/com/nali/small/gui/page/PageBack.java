package com.nali.small.gui.page;

import com.nali.Nali;
import com.nali.list.data.SmallData;
import com.nali.system.opengl.OpenGLBuffer;
import com.nali.system.opengl.memo.client.MemoSo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL20;

import java.util.Collections;
import java.util.List;

import static com.nali.list.container.gui.SmallGui.SMALLGUI;

@SideOnly(Side.CLIENT)
public class PageBack extends Page
{
    public static int BACK_ARRAY_BUFFER = -1;

    @Override
    public void init()
    {
        super.init();
        if (BACK_ARRAY_BUFFER != -1)
        {
            OpenGlHelper.glDeleteBuffers(BACK_ARRAY_BUFFER);
        }
        Minecraft minecraft = SMALLGUI.mc;
        int display_width = minecraft.displayWidth,
        display_height = minecraft.displayHeight;
        BACK_ARRAY_BUFFER = OpenGLBuffer.loadFloatBuffer(OpenGLBuffer.createFloatByteBuffer(this.createQuadVUv(0, 0, display_width, display_height, display_width, display_height/*, 1.0F, 1.0F*/), true));
    }

//    @Override
//    public void preDraw()
//    {
////        super.preDraw();
//    }

    @Override
    public void draw()
    {
        MemoSo rs = Nali.I.clientloader.storeo.rs_list.get(SmallData.SHADER_O_STEP + 5);
        OpenGlHelper.glUseProgram(rs.program);
        GL20.glEnableVertexAttribArray(0);
//        this.drawStaticBlur();
        this.drawQuadStatic/*Blur*/(BACK_ARRAY_BUFFER, SMALLGUI.mc.getFramebuffer().framebufferTexture);
        GL20.glDisableVertexAttribArray(0);
    }

    @Override
    public void detect()
    {

    }

//    @Override
//    public void change()
//    {
//
//    }

    @Override
    public List<Integer> getArrayBufferIntegerList()
    {
        return Collections.emptyList();
    }

    @Override
    public List<Integer> getTextureIntegerList()
    {
        return Collections.emptyList();
    }
}
