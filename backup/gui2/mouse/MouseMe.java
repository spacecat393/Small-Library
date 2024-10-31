package com.nali.small.gui.mouse;

import com.nali.small.gui.page.PageSmall;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.small.gui.page.PageSI.openPageSI;

@SideOnly(Side.CLIENT)
public class MouseMe extends Mouse
{
	@Override
	public void run()
	{
		if (PageSmall.PAGE == PAGE)
		{
			if (HIT == 1)
			{
				openPageSI();
			}
		}

		super.run();
	}
}
