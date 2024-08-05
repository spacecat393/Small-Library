package com.nali.small.gui.page;

import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.list.container.gui.SmallGui.SCALE;
import static com.nali.list.container.gui.SmallGui.SMALLGUI;

@SideOnly(Side.CLIENT)
public class PageKeyArmy extends PageKey
{
//    public static byte BYTE = 1;
    public static float BT27;

    @Override
    public void initPlus()
    {
        super.initPlus();
//        Nali.LOGGER.info("initP");
        Minecraft minecraft = SMALLGUI.mc;
        int display_width = minecraft.displayWidth,
        display_height = minecraft.displayHeight;

        float scale = SCALE * 0.5F;
        int h = (int)(MAX_TH * scale);
        String string = STRING_ARRAY[27];
        int i = (int)(minecraft.fontRenderer.getStringWidth(string) * scale);
//        box_width = i - display_width;
//        if (i < display_width)
//        {
//            box_width = i;
//        }
//        this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string/*, true*//*, -1*/, box_width, h, display_width - box_width - RIGHT - 4.0F * 0.005F * display_width, 2.0F * 0.005F * display_height, scale);
        this.initTextHorizontal(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST, string/*, true*//*, -1*/, i, h, display_width - i - RIGHT - 4.0F * 0.005F * display_width, 2.0F * 0.005F * display_height, scale);
//        RIGHT += box_width;
        RIGHT += i;
    }

    @Override
    public void drawPlus(MemoS rs, float[] color_float_array)
    {
        super.drawPlus(rs, color_float_array);

        boolean bt = BT27 > 0.0F;
        this.drawQuadVUv(rs, this.vec2_2d_float_array[0], bt ? this.color_vec4_2d_float_array[1] : color_float_array == null ? this.color_vec4_2d_float_array[0] : color_float_array, ARRAY_BUFFER_INTEGER_LIST.get(1), TEXTURE_INTEGER_LIST.get(1));
        if (bt)
        {
            BT27 -= SMALLGUI.partial_ticks;
        }
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
}
