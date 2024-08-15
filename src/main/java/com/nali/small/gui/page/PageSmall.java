package com.nali.small.gui.page;

import com.nali.list.data.SmallData;
import com.nali.small.gui.key.KeyMenu;
import com.nali.small.gui.mouse.MouseSmall;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL20;

import java.util.ArrayList;
import java.util.List;

import static com.nali.list.container.gui.SmallGui.*;
import static com.nali.small.gui.key.Key.KEY;
import static com.nali.small.gui.mouse.Mouse.HIT;
import static com.nali.small.gui.mouse.Mouse.MOUSE;
import static com.nali.system.ClientLoader.S_LIST;

@SideOnly(Side.CLIENT)
public class PageSmall extends Page
{
    public static List<Integer>
    TEXTURE_INTEGER_LIST = new ArrayList(),
    ARRAY_BUFFER_INTEGER_LIST = new ArrayList();

    public float[][] vec2_2d_float_array;
    public float[][] color_vec4_2d_float_array;

    public static byte PAGE,
    BYTE = 1;

    public PageSmall(byte page)
    {
        PAGE = page;
        this.vec2_2d_float_array = new float[1][2];
        this.color_vec4_2d_float_array = new float[5][4];
        this.color_vec4_2d_float_array[0] = new float[]{1.0F, 1.0F, 1.0F, 1.0F};
        this.color_vec4_2d_float_array[1] = new float[]{0.5F, 1.0F, 0.5F, 1.0F};

        this.color_vec4_2d_float_array[2] = new float[]{1.0F/* / Integer.MAX_VALUE*//255.0F, PAGE/255.0F, 0.0F, 1.0F};
        this.color_vec4_2d_float_array[3] = new float[]{2.0F/* / Integer.MAX_VALUE*//255.0F, PAGE/255.0F, 0.0F, 1.0F};

        this.color_vec4_2d_float_array[4] = new float[]{0.0F, 0.0F, 0.0F, 1.0F};
    }

    public static void openPageSmall()
    {
        PageMenu.BYTE |= 1;
        PAGE_ARRAY = new Page[]
        {
            new PageBlur(),
            new PageMenu(STRING_ARRAY[14]),
            new PageSakura(),
            new PageKey(),
            new PageSmall((byte)1)
        };
        KEY = new KeyMenu();
        MOUSE = new MouseSmall();
//        FLAG |= 1;
        addSet();
    }

    @Override
    public void init()
    {
//        byte b = this.getByte();
//        if ((b & 1) == 1)
        if ((BYTE & 1) == 1)
        {
//            super.init();
            this.clear(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST);
            Minecraft minecraft = SMALLGUI.mc;
            FontRenderer fontrenderer = minecraft.fontRenderer;
            int display_width = minecraft.displayWidth;
            int display_height = minecraft.displayHeight;

            String[] string_array = new String[]
            {
                STRING_ARRAY[0],
                STRING_ARRAY[3],
            };

            int max = 0;
            for (String s : string_array)
            {
                int new_max = (int)(fontrenderer.getStringWidth(s) * SCALE);
                if (max < new_max)
                {
                    max = new_max;
                }
            }

            String string = string_array[0];
//            int i = (int)(fontrenderer.getStringWidth(string) * SCALE);
    //        box_width = i - SMALLGUI.mc.displayWidth;
    //        if (i < SMALLGUI.mc.displayWidth)
    //        {
    //            box_width = i;
    //        }
    //        this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string/*, true*//*, -1*/, box_width, H, display_width / 2.0F - box_width / 2.0F, display_height / 2.0F/* - H / 2.0F*/ + 2.0F * 0.005F * display_height, SCALE);
            this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string/*, true*//*, -1*/, max, H, display_width / 2.0F - max / 2.0F, display_height / 2.0F/* - H / 2.0F*//* + 2.0F * 0.005F * display_height*/, SCALE);

            string = string_array[1];
//            i = (int)(fontrenderer.getStringWidth(string) * SCALE);
    //        box_width = i - SMALLGUI.mc.displayWidth;
    //        if (i < SMALLGUI.mc.displayWidth)
    //        {
    //            box_width = i;
    //        }
    //        this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string/*, true*//*, -1*/, box_width, H, display_width / 2.0F - box_width / 2.0F, display_height / 2.0F - H/* / 2.0F*/ - 2.0F * 0.005F * display_height, SCALE);
            this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string/*, true*//*, -1*/, max, H, display_width / 2.0F - max / 2.0F, display_height / 2.0F - H/* / 2.0F*/ - 2.0F * 0.005F * display_height, SCALE);
//            this.setByte((byte)(b & 255-1));
            BYTE &= 255-1;
        }
    }

//    @Override
//    public void preDraw()
//    {
////        super.preDraw();
//    }

    @Override
    public void draw()
    {
        MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 3);
        OpenGlHelper.glUseProgram(rs.program);
        int v = rs.attriblocation_int_array[0];
        GL20.glEnableVertexAttribArray(v);

        this.drawQuadVUv(rs, this.vec2_2d_float_array[0], MouseSmall.E_PAGE == PAGE && HIT == 1 ? this.color_vec4_2d_float_array[1] : this.color_vec4_2d_float_array[0], ARRAY_BUFFER_INTEGER_LIST.get(0), TEXTURE_INTEGER_LIST.get(0));
        this.drawQuadVUv(rs, this.vec2_2d_float_array[0], MouseSmall.E_PAGE == PAGE && HIT == 2 ? this.color_vec4_2d_float_array[1] : this.color_vec4_2d_float_array[0], ARRAY_BUFFER_INTEGER_LIST.get(1), TEXTURE_INTEGER_LIST.get(1));

        GL20.glDisableVertexAttribArray(v);

//        if (SMALLGUI.page == PAGE)
//        {
//            if (SMALLGUI.hit == 1)
//            {
//                PAGE_ARRAY = new Page[]
//                {
//                    new PageBack(),
//                    new PageMenu(STRING_ARRAY[14] + "|" + STRING_ARRAY[0]),
//                    new PageArmy()
//                };
//            }
//
//            SMALLGUI.hit = 0;
//            SMALLGUI.page = 0;
//        }
    }

    @Override
    public void preDraw()
    {
        MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 3);
        OpenGlHelper.glUseProgram(rs.program);
        int v = rs.attriblocation_int_array[0];
        GL20.glEnableVertexAttribArray(v);

        this.drawQuadVUv(rs, this.vec2_2d_float_array[0], MouseSmall.E_PAGE == PAGE && HIT == 1 ? this.color_vec4_2d_float_array[1] : this.color_vec4_2d_float_array[4], ARRAY_BUFFER_INTEGER_LIST.get(0), TEXTURE_INTEGER_LIST.get(0));
        this.drawQuadVUv(rs, this.vec2_2d_float_array[0], MouseSmall.E_PAGE == PAGE && HIT == 2 ? this.color_vec4_2d_float_array[1] : this.color_vec4_2d_float_array[4], ARRAY_BUFFER_INTEGER_LIST.get(1), TEXTURE_INTEGER_LIST.get(1));

        GL20.glDisableVertexAttribArray(v);
    }

    @Override
    public void detect()
    {
        MemoS rs = S_LIST.get(SmallData.SHADER_STEP + 4);

        OpenGlHelper.glUseProgram(rs.program);
        int v = rs.attriblocation_int_array[0];
        GL20.glEnableVertexAttribArray(v);

        this.drawQuadVUv(rs, this.vec2_2d_float_array[0], this.color_vec4_2d_float_array[2], ARRAY_BUFFER_INTEGER_LIST.get(0), TEXTURE_INTEGER_LIST.get(0));
        this.drawQuadVUv(rs, this.vec2_2d_float_array[0], this.color_vec4_2d_float_array[3], ARRAY_BUFFER_INTEGER_LIST.get(1), TEXTURE_INTEGER_LIST.get(1));

        GL20.glDisableVertexAttribArray(v);
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
