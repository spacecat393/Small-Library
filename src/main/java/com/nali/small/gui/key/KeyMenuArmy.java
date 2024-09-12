package com.nali.small.gui.key;

import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SSToC;
import com.nali.network.NetworkRegistry;
import com.nali.small.gui.page.PageArmy;
import com.nali.small.gui.page.PageKeyArmy;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import static com.nali.small.gui.page.PageSmall.openPageSmall;
import static com.nali.small.gui.page.PageText.STRINGBUFFER;

@SideOnly(Side.CLIENT)
public class KeyMenuArmy extends KeyMenu
{
//	public KeyMenuArmy()
//	{
//		STRINGBUFFER = new StringBuffer();
//	}

	@Override
	public void run()
	{
		if ((STATE & 1) == 1)
		{
			openPageSmall();
//			super.run();
		}
		if ((STATE & 2) == 2)
		{
//			FLAG |= 1;
			STATE &= 255-2;
		}
	}

	@Override
	public void detect(char typedChar, int keyCode)
	{
		super.detect(typedChar, keyCode);
		if ((STATE & 4) == 4)
		{
			switch (typedChar)
			{
				case '\b':
					int length = STRINGBUFFER.length();
					if (length > 0)
					{
						STRINGBUFFER.deleteCharAt(length - 1);
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
					boolean isShiftKeyDown = (keyCode == Keyboard.KEY_LSHIFT || keyCode == Keyboard.KEY_RSHIFT);
					if (!isShiftKeyDown)
					{
						STRINGBUFFER.append(typedChar);
					}
			}
		}
		else if (typedChar == 'r')
		{
			PageKeyArmy.BT27 = 5.0F;
			NetworkRegistry.I.sendToServer(new ServerMessage(new byte[]{SSToC.ID}));
			STATE |= 2;
			PageArmy.BYTE |= 1;
		}
	}
}
