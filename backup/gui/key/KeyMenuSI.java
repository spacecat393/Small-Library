//package com.nali.small.gui.key;
//
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//import static com.nali.small.gui.page.PageMe.openPageMe;
//
//@SideOnly(Side.CLIENT)
//public class KeyMenuSI extends KeyMenu
//{
//	@Override
//	public void run()
//	{
//		if ((STATE & 1) == 1)
//		{
//			openPageMe();
//		}
//	}
//
//	@Override
//	public void detect(char typed_char, int key_code)
//	{
//		if ((STATE & 4) == 4)
//		{
//			type(typed_char, key_code);
//		}
//		else
//		{
//			super.detect(typed_char, key_code);
//		}
//	}
//}
