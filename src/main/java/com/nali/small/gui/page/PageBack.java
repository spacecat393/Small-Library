package com.nali.small.gui.page;

import com.nali.list.data.SmallData;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL20;

import java.util.Collections;
import java.util.List;

import static com.nali.list.container.gui.SmallGui.SMALLGUI;
import static com.nali.system.ClientLoader.S_LIST;
import static com.nali.system.opengl.memo.client.MemoA1.genFloatBuffer;
import static com.nali.system.opengl.memo.client.MemoC.createFloatByteBuffer;

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
        BACK_ARRAY_BUFFER = genFloatBuffer(createFloatByteBuffer(this.createQuadVUv(0, 0, display_width, display_height, display_width, display_height/*, 1.0F, 1.0F*/)/*, true*/));
    }

//    @Override
//    public void preDraw()
//    {
////        super.preDraw();
//    }

    @Override
    public void draw()
    {
        MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 5);
        OpenGlHelper.glUseProgram(rs.program);
        int v = rs.attriblocation_int_array[0];
        GL20.glEnableVertexAttribArray(v);
//        this.drawStaticBlur();
        this.drawQuadStatic/*Blur*/(rs, BACK_ARRAY_BUFFER, SMALLGUI.mc.getFramebuffer().framebufferTexture);
        GL20.glDisableVertexAttribArray(v);
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
