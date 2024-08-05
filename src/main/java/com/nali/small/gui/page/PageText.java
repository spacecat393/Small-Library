package com.nali.small.gui.page;

import com.nali.list.data.SmallData;
import com.nali.small.gui.key.KeyMenu;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL20;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.nali.key.KeyHelper.generateRainbowColor;
import static com.nali.list.container.gui.SmallGui.SCALE;
import static com.nali.list.container.gui.SmallGui.SMALLGUI;
import static com.nali.system.ClientLoader.S_LIST;

@SideOnly(Side.CLIENT)
public class PageText extends Page
{
    public static List<Integer>
    TEXTURE_INTEGER_LIST = new ArrayList(),
    ARRAY_BUFFER_INTEGER_LIST = new ArrayList();

    public static byte BYTE = 1;
    public static float SEARCH_L;

    public static StringBuffer STRINGBUFFER = new StringBuffer();
    public static String STRING;

    public float[][] vec2_2d_float_array;
//    public float[][] color_vec4_2d_float_array;

//    public long last_time = Minecraft.getSystemTime();

    public PageText()
    {
        this.vec2_2d_float_array = new float[1][2];
//        super.init();
//        this.clear(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST);
    }

    @Override
    public void init()
    {
        String string = STRINGBUFFER.toString();
        if (!string.equals(STRING))
        {
            BYTE |= 1;
        }
        STRING = string;
//        byte b = this.getByte();
//        if ((b & 1) == 1)
        Minecraft minecraft = SMALLGUI.mc;
        int display_width = minecraft.displayWidth,
        display_height = minecraft.displayHeight;
        float h2 = 2.0F * 0.005F * display_height;
        float x = display_width - SEARCH_L - 2.0F * 0.005F * display_width,
        y = display_height - H - h2;

        if ((BYTE & 1) == 1)
        {
//            Nali.LOGGER.info("init");
            this.clear(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST);
            if (!STRING.isEmpty())
            {

                int l = (int)(minecraft.fontRenderer.getStringWidth(string) * SCALE);
                this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, "_", H, H, x + l, y, SCALE);
                this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string, l, H, x, y, SCALE);

            }
            else
            {
                this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, "_", H, H, x, y, SCALE);
            }

//            this.setByte((byte)(b & 255-1));
            BYTE &= 255-1;
        }
    }

    @Override
    public void draw()
    {
        MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 3);
        OpenGlHelper.glUseProgram(rs.program);
        int v = rs.attriblocation_int_array[0];
        GL20.glEnableVertexAttribArray(v);

        Color color = generateRainbowColor();
        float[] color_float_array = new float[]{color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, 1.0F};
        if (!STRING.isEmpty())
        {
            this.drawQuadVUv(rs, this.vec2_2d_float_array[0], color_float_array, ARRAY_BUFFER_INTEGER_LIST.get(1), TEXTURE_INTEGER_LIST.get(1));
//            long current_time = Minecraft.getSystemTime();
//            if (current_time - this.last_time >= 1000)
//            {
////                BYTE ^= 2;
//                this.last_time = current_time;
//            }
        }

        if (/*(BYTE & 2) == 2 && */(KeyMenu.STATE & 4) == 4)
        {
            this.drawQuadVUv(rs, this.vec2_2d_float_array[0], color_float_array, ARRAY_BUFFER_INTEGER_LIST.get(0), TEXTURE_INTEGER_LIST.get(0));
        }

        GL20.glDisableVertexAttribArray(v);
    }

    @Override
    public void preDraw()
    {
        this.draw();
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
//    public List<Integer> getArrayBufferIntegerList()
//    {
//        return ARRAY_BUFFER_INTEGER_LIST;
//    }
//
//    @Override
//    public List<Integer> getTextureIntegerList()
//    {
//        return TEXTURE_INTEGER_LIST;
//    }
}
