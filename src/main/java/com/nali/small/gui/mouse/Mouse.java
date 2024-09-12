package com.nali.small.gui.mouse;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Mouse
{
	public static Mouse MOUSE;

	public static int MOUSE_X, MOUSE_Y/*, EVENTDWHEEL*//*, DY*//*, DWHEEL*/;
	public static float EVENTDWHEEL;

//	public static int HIT;
	public static byte
		HIT,
		PAGE,
		E_PAGE,
		STATE;//rc cme scroll ?cms

	public Mouse()
	{
		STATE = 0;
	}

//	public void detect()
//	{
//		HIT = (byte)(OPENGL_FIXED_PIPE_FLOATBUFFER.get(0) * 255.0F);
//		if ((STATE & 1) == 1)
//		{
//			PAGE = (byte)(OPENGL_FIXED_PIPE_FLOATBUFFER.get(1) * 255.0F);
//		}
//	}

	public void run()
	{
		HIT = -1;
		PAGE = 0;
//		STATE &= 255-1;
	}
}
