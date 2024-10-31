package com.nali.small.gui.mouse;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Mouse
{
	public static Mouse MOUSE;

	public static int
		MOUSE_X,
		MOUSE_Y,
		HIT;

	public static float EVENTDWHEEL;

	public static byte
		E_PAGE,
		STATE;//rc cme scroll ?cms

	public Mouse()
	{
		STATE = 0;
	}

	public void run()
	{
		HIT = -1;
	}

	public void handleMouseInput()
	{
		MOUSE_X = org.lwjgl.input.Mouse.getEventX();
		MOUSE_Y = org.lwjgl.input.Mouse.getEventY();

		int k = org.lwjgl.input.Mouse.getEventButton();
		int mouse = org.lwjgl.input.Mouse.getEventDWheel();
		float eventdwheel = 0;

		if (mouse > 0)
		{
			eventdwheel = 1.5F;
		}
		else if (mouse < 0)
		{
			eventdwheel = -1.5F;
		}

		if (eventdwheel != 0)
		{
			if (EVENTDWHEEL > 0 && eventdwheel < 0)
			{
				EVENTDWHEEL = 0;
			}
			else if (EVENTDWHEEL < 0 && eventdwheel > 0)
			{
				EVENTDWHEEL = 0;
			}
			EVENTDWHEEL += eventdwheel;
		}

		if (org.lwjgl.input.Mouse.getEventButtonState())//c
		{
			STATE |= 2;
		}
		else if (k != -1)//r
		{
			STATE |= 1;
			STATE &= 255-2;
		}
	}
}
