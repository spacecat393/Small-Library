//package com.nali.small.gui.key;
//
//import com.nali.list.network.message.ServerMessage;
//import com.nali.list.network.method.server.SSToC;
//import com.nali.network.NetworkRegistry;
//import com.nali.small.gui.page.PageArmy;
//import com.nali.small.gui.page.PageKeyArmy;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//import static com.nali.small.gui.page.PageSmall.openPageSmall;
//
//@SideOnly(Side.CLIENT)
//public class KeyMenuArmy extends KeyMenu
//{
////	public KeyMenuArmy()
////	{
////		STRINGBUFFER = new StringBuffer();
////	}
//
//	@Override
//	public void run()
//	{
//		if ((STATE & 1) == 1)
//		{
//			openPageSmall();
////			super.run();
//		}
//		if ((STATE & 2) == 2)
//		{
////			FLAG |= 1;
//			STATE &= 255-2;
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
//		else if (typed_char == 'r')
//		{
//			PageKeyArmy.BT27 = 5.0F;
//			NetworkRegistry.I.sendToServer(new ServerMessage(new byte[]{SSToC.ID}));
//			STATE |= 2;
//			PageArmy.BYTE |= 1;
//		}
//		else
//		{
//			super.detect(typed_char, key_code);
//		}
//	}
//}
