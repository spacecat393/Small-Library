package com.nali.small.gui.mouse;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Mouse
{
    public static Mouse MOUSE;

    public static byte
    HIT, PAGE,
    STATE;//rc

//    public void detect()
//    {
//        HIT = (byte)(OPENGL_FIXED_PIPE_FLOATBUFFER.get(0) * 255.0F);
//        if ((STATE & 1) == 1)
//        {
//            PAGE = (byte)(OPENGL_FIXED_PIPE_FLOATBUFFER.get(1) * 255.0F);
//        }
//    }

    public void run()
    {
        HIT = 0;
        PAGE = 0;
    }
}
