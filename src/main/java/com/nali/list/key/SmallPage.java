package com.nali.list.key;

import com.nali.gui.key.KeySelect;
import com.nali.gui.page.Page;
import com.nali.key.Key;
import com.nali.small.gui.page.PageSmall;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class SmallPage extends Key
{
	public static byte STATE;//1key 2bit

	public static Page PAGE;
	public static com.nali.gui.key.Key KEY;

	public static List<Page> PAGE_LIST = new ArrayList();
	public static List<com.nali.gui.key.Key> KEY_LIST = new ArrayList();

	@Override
	public void run()
	{
		if (Minecraft.getMinecraft().currentScreen == null)
		{
			if (Keyboard.isKeyDown(Keyboard.KEY_P))
			{
				if ((STATE & 1) == 0)
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
					STATE |= 1;
				}
			}
			else
			{
				STATE &= 255-1;
			}
		}
	}

	public static void setSmallPage()
	{
		if (Page.PAGE != null)
		{
			PAGE.clear();
		}

		if (PAGE == null)
		{
			Page.PAGE = new PageSmall();
			com.nali.gui.key.Key.KEY = new KeySelect();

			PAGE = Page.PAGE;
			KEY = com.nali.gui.key.Key.KEY;

			PAGE_LIST = new ArrayList(Page.PAGE_LIST);
			KEY_LIST = new ArrayList(Page.KEY_LIST);

			Page.PAGE.init();
			Page.WIDTH = -1;
		}
		else
		{
			if ((STATE & 2) == 2)
			{
				PAGE = Page.PAGE;
				KEY = com.nali.gui.key.Key.KEY;

				PAGE_LIST = new ArrayList(Page.PAGE_LIST);
				KEY_LIST = new ArrayList(Page.KEY_LIST);

				Page.PAGE_LIST.clear();
				Page.KEY_LIST.clear();

				Page.PAGE = null;
				com.nali.gui.key.Key.KEY = null;

				Minecraft.getMinecraft().mouseHelper.grabMouseCursor();
			}
			else
			{
				Page.PAGE = PAGE;
				com.nali.gui.key.Key.KEY = KEY;

				Page.PAGE_LIST = PAGE_LIST;
				Page.KEY_LIST = KEY_LIST;

				Page.PAGE.init();
				Page.WIDTH = -1;
			}
		}

		STATE ^= 2;
	}
}
