package com.nali.small.gui.page;

import com.nali.gui.page.PageEdit;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageEntityMeInvSelectItemPlayer extends PageEdit
{
	@Override
	public void init()
	{
		if ((this.state & 4) == 0)
		{
			this.select = 2;
			this.state |= 4;
		}
	}

	@Override
	public void enter()
	{

	}
}