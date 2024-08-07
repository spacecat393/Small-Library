package com.nali.small.gui.page;

import com.nali.list.data.NaliData;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL20;

import static com.nali.list.container.gui.SmallGui.SMALLGUI;
import static com.nali.system.ClientLoader.S_LIST;
import static com.nali.system.opengl.memo.client.MemoC.FULL_ARRAY_BUFFER;

@SideOnly(Side.CLIENT)
public class PageBlur extends Page
{
//    public static byte BYTE = 1;
//    public static int BACK_ARRAY_BUFFER = -1;

//    @Override
//    public void init()
//    {
//        super.init();
////        if (BACK_ARRAY_BUFFER != -1)
////        {
////            OpenGlHelper.glDeleteBuffers(BACK_ARRAY_BUFFER);
////        }
//        Minecraft minecraft = SMALLGUI.mc;
//        int display_width = minecraft.displayWidth,
//        display_height = minecraft.displayHeight;
////        BACK_ARRAY_BUFFER = genBuffer(createFloatByteBuffer(this.createQuadVUv(0, 0, display_width, display_height, display_width, display_height/*, 1.0F, 1.0F*/)/*, true*/));
//    }

//    @Override
//    public void preDraw()
//    {
////        super.preDraw();
//    }

    @Override
    public void init()
    {

    }

    @Override
    public void draw()
    {
        MemoS rs = S_LIST.get(NaliData.SHADER_STEP/* + 0*/);
        OpenGlHelper.glUseProgram(rs.program);
        int v = rs.attriblocation_int_array[0];
        GL20.glEnableVertexAttribArray(v);
//        this.drawStaticBlur();
//        this.drawQuadStatic/*Blur*/(rs, BACK_ARRAY_BUFFER, SMALLGUI.mc.getFramebuffer().framebufferTexture);
        this.drawQuadStatic/*Blur*/(rs, FULL_ARRAY_BUFFER, SMALLGUI.mc.getFramebuffer().framebufferTexture);
        GL20.glDisableVertexAttribArray(v);
    }

    @Override
    public void preDraw()
    {

    }

    @Override
    public void detect()
    {

    }

//    @Override
//    public byte getByte()
//    {
//        return BYTE;
//    }
//
//    @Override
//    public void setByte(byte b)
//    {
//        BYTE = b;
//    }

//    @Override
//    public void change()
//    {
//
//    }

//    @Override
//    public List<Integer> getArrayBufferIntegerList()
//    {
//        return Collections.emptyList();
//    }
//
//    @Override
//    public List<Integer> getTextureIntegerList()
//    {
//        return Collections.emptyList();
//    }
}
