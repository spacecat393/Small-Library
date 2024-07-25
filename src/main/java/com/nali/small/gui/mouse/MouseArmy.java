package com.nali.small.gui.mouse;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.list.container.gui.SmallGui.SMALLGUI;
import static com.nali.small.entity.memo.client.ClientE.C_MAP;
import static com.nali.small.gui.page.Page.H;

@SideOnly(Side.CLIENT)
public class MouseArmy extends Mouse
{
    public static float /*X, */Y, Y_STAR;
    public float /*mouse_x, */mouse_y;
//    public int dwheel;

    public MouseArmy()
    {
        Y = 0;
    }

    @Override
    public void run()
    {
        if ((STATE & 2) == 2)
        {
//            X = this.mouse_x - MOUSE_X;
//            float v = (this.mouse_y - MOUSE_Y) * 0.05F * SMALLGUI.partial_ticks;
//            Y -= v;
//            Y_STAR -= v;
            Y -= (this.mouse_y - MOUSE_Y) * 0.05F * SMALLGUI.partial_ticks;
//            STATE &= 255-2;
        }

//        Y += (DWHEEL - this.dwheel) * 0.05F * SMALLGUI.partial_ticks;
//        Y += DWHEEL * 0.05F * SMALLGUI.partial_ticks;
//        if (DWHEEL > 0)
//        {
//            Y += 0.05F * SMALLGUI.partial_ticks;
//        }
//        else
//        {
//            Y -= 0.05F * SMALLGUI.partial_ticks;
//        }
//        Y += DY * 0.05F * SMALLGUI.partial_ticks;
//        float v = EVENTDWHEEL * 0.05F * SMALLGUI.partial_ticks;
//        Y += v;
//        Y_STAR += v;
        Y += EVENTDWHEEL * 0.05F * SMALLGUI.partial_ticks;
        if ((STATE & 4) == 4)
        {
            STATE &= 255-4;
        }
        else
        {
            if (EVENTDWHEEL > 0)
            {
                EVENTDWHEEL -= /*5.0F * */SMALLGUI.partial_ticks;

            }
            else
            {
                EVENTDWHEEL += /*5.0F * */SMALLGUI.partial_ticks;
            }

//            if (EVENTDWHEEL < 1 || EVENTDWHEEL > -1)
            if ((int)EVENTDWHEEL == 0)
            {
                EVENTDWHEEL = 0;
            }
        }
//        Y += SMALLGUI.mc.mouseHelper.getDwheel * 0.05F * SMALLGUI.partial_ticks;

//        if (Y < -0.25F)
//        {
//            Y = -0.25F;
//        }
        if (Y < 0)
        {
            Y = 0;
        }

        if (Y_STAR < 0)
        {
            Y_STAR = 0;
        }
//
//        if (Y_STAR < -0.25F)
//        {
//            Y_STAR = -0.25F;
//        }

        Minecraft minecraft = SMALLGUI.mc;
//        float display_height = minecraft.displayHeight * 2.0F;
//        float A = (SMALLGUI.height / display_height);
//        float SCALE = FONT / A;
//        float H = (int)(MAX_TH * SCALE);
        float
        display_height = minecraft.displayHeight;
//        float h_offset = (H + 4.0F * 0.005F * display_height) * 2.0F;
        float h_offset = (H + 4.0F * 0.005F * display_height);
////        y = display_height - scale / (SMALLGUI.height / (float)display_height) - 4.0F * 0.005F * display_height - H + h_offset + height;
//
        float scale = 75.0F / ((float)SMALLGUI.height / display_height);
//        float offset = (MAX_TH * FONT + 4.0F * 0.005F * display_height/* - 2.0F * 0.005F * display_height*/)/* * 2.0F*/;
        // / max full height
//        float height = ((/*75.0F * (display_height / (float)SMALLGUI.height)*/scale + 2.0F * 0.005F * display_height + offset) * C_MAP.size()) / ((float)display_height - offset/*(offset / 4.0F)*/)/* + h_offset*//* + (8.0F * 0.005F * display_height) * 2.0F*//* + offset*/;
////        float h = SMALLGUI.height / (float)(display_height/* - offset*/);
////        height = (((height + 2.0F * 0.005F * display_height) * C_MAP.size()) * h);
//        float height = (((((scale + 2.0F * 0.005F * display_height) * C_MAP.size()) / (SMALLGUI.height / (float)display_height)))) / ((float)display_height - offset);
        float height = (((scale + 2.0F * 0.005F * display_height) * C_MAP.size() - (display_height - (h_offset * 2.0F))) / display_height) * 2.0F;
        if (Y > height)
        {
            Y = height;
        }

//        float max_y_star = 2.0F-0.5F;
        float max_y_star = ((display_height - h_offset - (H * 2.0F)/* + *//*h_offset*//*(h_offset * 2.0F)*/) / /*(*/display_height/* - h_offset * 2.0F)*/) * 2.0F;
//        float max_y_star = (display_height - h_offset) / minecraft.displayHeight;
//        float ye = display_height - MAX_TH * FONT * 2.0F + 8.0F * 0.005F * display_height + (offset * 2.0F);
//        int max_l = (int)(Math.ceil(ye / (MAX_TH * FONT)))/* + 1*/;
//        float max_y_star = (((max_l * MAX_TH * FONT) + (offset * 2.0F))) / (display_height - (offset * 2.0F));
        Y_STAR = max_y_star * (Y / height);
//        Y_STAR = Y;

//        float max_y_star = 2.0F;

//        if (Y_STAR > max_y_star)
//        {
//            Y_STAR = max_y_star;
//        }

//        float h = (-HO / ((float)display_height - offset));
//        if (Y > h)
//        {
//            Y = h;
//        }

//        this.mouse_x = MOUSE_X;
        this.mouse_y = MOUSE_Y;
//        this.dwheel = DWHEEL;

        super.run();
    }
}
