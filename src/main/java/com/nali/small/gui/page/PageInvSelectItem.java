package com.nali.small.gui.page;

import com.nali.gui.page.PageSelect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageInvSelectItem extends PageSelect
{
	//player inv
	//item name size nbt nbt_set
	@Override
	public void init()
	{
		//move
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