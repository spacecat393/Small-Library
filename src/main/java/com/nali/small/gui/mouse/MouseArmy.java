package com.nali.small.gui.mouse;

import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SEToC;
import com.nali.network.NetworkRegistry;
import com.nali.small.gui.key.KeyMenuArmy;
import com.nali.small.gui.key.KeyMenuMe;
import com.nali.small.gui.page.PageArmy;
import com.nali.small.gui.page.PageMe;
import com.nali.system.bytes.ByteWriter;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.list.container.gui.SmallGui.SMALLGUI;
import static com.nali.small.gui.page.Page.H;
import static com.nali.small.gui.page.PageArmy.*;
import static com.nali.small.gui.page.PageMe.openPageMe;

@SideOnly(Side.CLIENT)
public class MouseArmy extends Mouse
{
    public static float /*X, */Y, Y_STAR, DRAG;
    public float /*mouse_x, */mouse_y;
//    public int dwheel;

    public MouseArmy()
    {
        Y = 0;
    }

    @Override
    public void run()
    {
//        float drag = 0;
        if (PageArmy.PAGE == PAGE && HIT == 2)
        {
            KeyMenuArmy.STATE |= 4;
        }
        else if ((STATE & 1) == 1 && PAGE == 0 && HIT == 0)
        {
            KeyMenuArmy.STATE &= 255-4;
        }

        if ((STATE & 2) == 2)
        {
//            X = this.mouse_x - MOUSE_X;
//            float v = (this.mouse_y - MOUSE_Y) * 0.05F * SMALLGUI.partial_ticks;
//            Y -= v;
//            Y_STAR -= v;
            float drag = (this.mouse_y - MOUSE_Y)/* * 0.1F*//* * SMALLGUI.partial_ticks*/;
//            STATE &= 255-2;

            if (drag > 45)
            {
                drag = 45;
            }
            else if (drag < -45)
            {
                drag = -45;
            }

//            if (drag != 0)
//            {
            if (DRAG > 0 && drag < 0)
            {
                DRAG = 0;
            }
            else if (DRAG < 0 && drag > 0)
            {
                DRAG = 0;
            }
            DRAG += drag * 0.1F;
//            STATE |= 4;
//            }
        }

        Y += DRAG * SMALLGUI.partial_ticks * 0.005F;

        if (DRAG > 0)
        {
            DRAG -= SMALLGUI.partial_ticks/* * 0.05F*/;
        }
        else if (DRAG < 0)
        {
            DRAG += SMALLGUI.partial_ticks/* * 0.05F*/;
        }

        if ((int)DRAG == 0)
        {
            DRAG = 0;
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
//        if (EVENTDWHEEL > 2)
//        {
//            EVENTDWHEEL = 2;
//        }
//        else if (EVENTDWHEEL < -2)
//        {
//            EVENTDWHEEL = -2;
//        }

        Y -= EVENTDWHEEL * SMALLGUI.partial_ticks * 0.005F;
//        if ((STATE & 4) == 4)
//        {
//            STATE &= 255-4;
//        }
//        else
//        {
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
//        }
//        Y += SMALLGUI.mc.mouseHelper.getDwheel * 0.05F * SMALLGUI.partial_ticks;

//        if (Y < -0.25F)
//        {
//            Y = -0.25F;
//        }
        if (Y < 0)
        {
            Y = 0;
        }

//        if (Y_STAR < 0)
//        {
//            Y_STAR = 0;
//        }
//
//        if (Y_STAR < -0.25F)
//        {
//            Y_STAR = -0.25F;
//        }

//        Minecraft minecraft = SMALLGUI.mc;
////        float display_height = minecraft.displayHeight * 2.0F;
////        float A = (SMALLGUI.height / display_height);
////        float SCALE = FONT / A;
////        float H = (int)(MAX_TH * SCALE);
//        float
//        display_height = minecraft.displayHeight;
////        float h_offset = (H + 4.0F * 0.005F * display_height) * 2.0F;
////        float h_offset = H + 4.0F * 0.005F * display_height;
////////        y = display_height - scale / (SMALLGUI.height / (float)display_height) - 4.0F * 0.005F * display_height - H + h_offset + height;
//////
////        float scale = 75.0F / ((float)SMALLGUI.height / display_height);
//////        float offset = (MAX_TH * FONT + 4.0F * 0.005F * display_height/* - 2.0F * 0.005F * display_height*/)/* * 2.0F*/;
////        // / max full height
//////        float height = ((/*75.0F * (display_height / (float)SMALLGUI.height)*/scale + 2.0F * 0.005F * display_height + offset) * C_MAP.size()) / ((float)display_height - offset/*(offset / 4.0F)*/)/* + h_offset*//* + (8.0F * 0.005F * display_height) * 2.0F*//* + offset*/;
////////        float h = SMALLGUI.height / (float)(display_height/* - offset*/);
////////        height = (((height + 2.0F * 0.005F * display_height) * C_MAP.size()) * h);
//////        float height = (((((scale + 2.0F * 0.005F * display_height) * C_MAP.size()) / (SMALLGUI.height / (float)display_height)))) / ((float)display_height - offset);
////        float height = (((scale + 2.0F * 0.005F * display_height) * C_MAP.size() - (display_height - (h_offset * 2.0F))) / display_height) * 2.0F;
        if (Y > MAX_Y)
        {
            Y = MAX_Y;
        }

//        float max_y_star = 2.0F-0.5F;
//        float max_y_star = ((display_height - h_offset - (H * 2.0F)/* + *//*h_offset*//*(h_offset * 2.0F)*/) / /*(*/display_height/* - h_offset * 2.0F)*/) * 2.0F;
//        float max_y_star = (display_height - h_offset) / minecraft.displayHeight;
//        float ye = display_height - MAX_TH * FONT * 2.0F + 8.0F * 0.005F * display_height + (offset * 2.0F);
//        int max_l = (int)(Math.ceil(ye / (MAX_TH * FONT)))/* + 1*/;
//        float max_y_star = (((max_l * MAX_TH * FONT) + (offset * 2.0F))) / (display_height - (offset * 2.0F));
        if (PageArmy.PAGE == PAGE)//126 -126 /2
        {
            int id = /*(int)(Y / MAX_Y_OFFSET) * 62 + */HIT - 3;
            if (HIT > 2 + 62)//re
            {
//                Nali.LOGGER.info("ID R " + (id - 62));
////                Nali.LOGGER.info("ID FR " + (INDEX_INTEGER_LIST.get((id - 62) * 7) / 7));
//                Nali.LOGGER.info("ID FR " + (INDEX_INT_ARRAY[(id - 62)/* * 7*/] / 7));
//                Nali.LOGGER.info("UUID " + SEARCH_UUID_LIST.get(INDEX_INT_ARRAY[id - 62] / 7));
////                Nali.LOGGER.info("ID FR " + (SEARCH_INTEGER_LIST.get(INDEX_INT_ARRAY[id - 62/* * 7*/] / 7)/* / 7*/));
                byte[] byte_array = new byte[1 + 16];
                byte_array[0] = SEToC.ID;
//                ByteWriter.set(byte_array, PageArmy.UUID_ARRAY[id - 62], 1);
//                ByteWriter.set(byte_array, PageArmy.UUID_ARRAY[INDEX_INTEGER_LIST.get((id - 62) * 7) / 7], 1);
//                ByteWriter.set(byte_array, PageArmy.UUID_ARRAY[INDEX_INT_ARRAY[(id - 62)/* * 7*/] / 7], 1);
//                ByteWriter.set(byte_array, PageArmy.UUID_ARRAY[SEARCH_INTEGER_LIST.get(INDEX_INT_ARRAY[id - 62/* * 7*/] / 7)/* / 7*/], 1);
                ByteWriter.set(byte_array, SEARCH_UUID_LIST.get(INDEX_INT_ARRAY[id - 62] / 7), 1);
                NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
            }
            else if (HIT > 2)//me
            {
//                Nali.LOGGER.info("ID M " + id);
////                Nali.LOGGER.info("ID FM " + (INDEX_INTEGER_LIST.get(id * 7) / 7));
//                Nali.LOGGER.info("ID FM " + (INDEX_INT_ARRAY[id/* * 7*/] / 7));
//                Nali.LOGGER.info("UUID " + SEARCH_UUID_LIST.get(INDEX_INT_ARRAY[id] / 7));
////                Nali.LOGGER.info("ID FM " + (SEARCH_INTEGER_LIST.get(INDEX_INT_ARRAY[id/* * 7*/] / 7)/* / 7*/));
                KeyMenuMe.ME |= 1;
//                openPageMe(PageArmy.UUID_ARRAY[id]);
//                openPageMe(PageArmy.UUID_ARRAY[INDEX_INTEGER_LIST.get(id * 7) / 7]);
//                openPageMe(PageArmy.UUID_ARRAY[INDEX_INT_ARRAY[id/* * 7*/] / 7]);
//                openPageMe(PageArmy.UUID_ARRAY[SEARCH_INTEGER_LIST.get(INDEX_INT_ARRAY[id/* * 7*/] / 7)/* / 7*/]);
                PageMe.UUID = SEARCH_UUID_LIST.get(INDEX_INT_ARRAY[id] / 7);
                openPageMe();
            }
        }

        if ((STATE & 2) == 2 && /*PageArmy.PAGE == PAGE && */HIT == 1)
        {
            float
            display_height = SMALLGUI.mc.displayHeight,
            mouse_y = display_height - MOUSE_Y,
            h_offset_y = H + 4.0F * 0.005F * display_height;

            Y_STAR = (mouse_y - h_offset_y) / (display_height) * 2.0F;
            Y = MAX_Y * (Y_STAR / MAX_Y_STAR);
        }
        else
        {
            Y_STAR = MAX_Y_STAR * (Y / MAX_Y);
        }
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
