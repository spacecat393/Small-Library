package com.nali.list.key;

import com.nali.gui.key.KeySelect;
import com.nali.gui.page.Page;
import com.nali.gui.page.PageSelect;
import com.nali.key.Key;
import com.nali.small.gui.page.PageSmall;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class SmallPage extends Key
{
	public byte state;
	public static Page PAGE;
	public static com.nali.gui.key.Key KEY;

	@Override
	public void run()
	{
		if (Minecraft.getMinecraft().currentScreen == null)
		{
			if (Keyboard.isKeyDown(Keyboard.KEY_P))
			{
				if ((this.state & 1) == 0)
				{
//					if ((this.state & 2) == 2)
//					{
//						Page.PAGE.clear();
//
//						PAGE = Page.PAGE;
//						KEY = com.nali.gui.key.Key.KEY;
//
//						Page.PAGE = null;
//						com.nali.gui.key.Key.KEY = null;
//
//						this.state &= 255-2;
//					}
//					else
//					{
					setSmallPage();
//						this.state |= 2;
//					}
					this.state |= 1;
				}
			}
			else
			{
				this.state &= 255-1;
			}
		}
	}

	public static void setSmallPage()
	{
		if (PAGE == null)
		{
			Page.PAGE = new PageSmall();
			com.nali.gui.key.Key.KEY = new KeySelect();
		}
		else
		{
			((PageSelect)PAGE).state &= 255-2;
			Page.PAGE = PAGE;
			com.nali.gui.key.Key.KEY = KEY;
		}

		Page.PAGE.init();
		Page.WIDTH = -1;
	}
}
