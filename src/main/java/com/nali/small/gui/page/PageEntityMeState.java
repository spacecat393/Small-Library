package com.nali.small.gui.page;

import com.nali.gui.page.PageEdit;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageEntityMeState extends PageEdit
{
	public PageEntityMeState()
	{
		this.select = 2;
	}

	@Override
	public void init()
	{
		//edit name
		//edit state
	}

	@Override
	public void enter()
	{

	}
}
