package com.nali.small.gui.key;

import com.nali.small.gui.page.PageKey;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class KeyMenu extends Key
{
	public static byte STATE;//back reload on_type

	public KeyMenu()
	{
		STATE = 0;
	}

	@Override
	public void run()
	{
		STATE &= 255-1;
	}

	@Override
	public void detect(char typedChar, int keyCode)
	{
		if (typedChar == 'q' || keyCode == Keyboard.KEY_LEFT)
		{
			PageKey.BT = 5.0F;
			STATE |= 1;
		}
	}

	public static void type(StringBuffer stringbuffer, char typed_char, int key_code)
	{
		switch (typed_char)
		{
			case '\b':
				int length = stringbuffer.length();
				if (length > 0)
				{
					stringbuffer.deleteCharAt(length - 1);
				}
				break;
//				case '\r':
////					if (GUINETLOADER != null)
////					{
////						GUINETLOADER.run();
////					}
////
////					STRINGBUFFER.setLength(0);
//					break;
			default:
//					if (keyCode != Keyboard.KEY_ESCAPE)
				boolean isShiftKeyDown = (key_code == Keyboard.KEY_LSHIFT || key_code == Keyboard.KEY_RSHIFT);
				if (!isShiftKeyDown)
				{
					stringbuffer.append(typed_char);
				}
		}
	}
}
