package com.nali.small.gui.key;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.small.gui.page.PageAI.openPageAI;

@SideOnly(Side.CLIENT)
public class KeyMenuGI extends KeyMenu
{
	@Override
	public void run()
	{
		if ((STATE & 1) == 1)
		{
			openPageAI();
		}
	}
}
