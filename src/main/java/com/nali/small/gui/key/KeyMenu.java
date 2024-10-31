//package com.nali.small.gui.key;
//
//import com.nali.small.gui.page.PageKey;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import org.lwjgl.input.Keyboard;
//
//@SideOnly(Side.CLIENT)
//public class KeyMenu extends Key
//{
//	public static byte STATE;//back reload on_type
//	public static StringBuffer STRINGBUFFER = new StringBuffer();
//
//	public KeyMenu()
//	{
//		STATE = 0;
//	}
//
//	@Override
//	public void run()
//	{
//		STATE &= 255-1;
//	}
//
//	@Override
//	public void detect(char typedChar, int keyCode)
//	{
//		if (typedChar == 'q' || keyCode == Keyboard.KEY_LEFT)
//		{
//			PageKey.BT = 5.0F;
//			STATE |= 1;
//		}
//	}
//
//	public static void type(char typed_char, int key_code)
//	{
//		switch (typed_char)
//		{
//			case '\b':
//				int length = STRINGBUFFER.length();
//				if (length > 0)
//				{
//					STRINGBUFFER.deleteCharAt(length - 1);
//				}
//				break;
////				case '\r':
//////					if (GUINETLOADER != null)
//////					{
//////						GUINETLOADER.run();
//////					}
//////
//////					STRINGBUFFER.setLength(0);
////					break;
//			default:
////					if (keyCode != Keyboard.KEY_ESCAPE)
//				boolean isShiftKeyDown = (key_code == Keyboard.KEY_LSHIFT || key_code == Keyboard.KEY_RSHIFT);
//				if (!isShiftKeyDown)
//				{
//					STRINGBUFFER.append(typed_char);
//				}
//		}
//	}
//}
