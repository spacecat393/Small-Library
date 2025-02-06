package com.nali.small.gui.page.entity.si;

import com.nali.gui.page.PageSelect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageSIEArea extends PageSelect
{
	public static byte FLAG;//check_tameable is_tameable | put_player put_owner put_other_tameable put_owner_tameable put_all_tameable put_object

	@Override
	public void init()
	{
	}

	@Override
	public void enter()
	{
	}
}
