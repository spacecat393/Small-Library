package com.nali.small.gui.mouse;

import com.nali.small.gui.page.PageSmall;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.small.gui.page.PageArmy.openPageArmy;

@SideOnly(Side.CLIENT)
public class MouseSmall extends Mouse
{
	@Override
	public void run()
	{
		if (PageSmall.PAGE == PAGE)
		{
			if (HIT == 1)
			{
				openPageArmy();
			}
		}

		super.run();
	}

//	@Override
//	public void detect()
//	{
//		super.detect();
//		if ((STATE & 1) == 1)
//		{
//			PAGE = (byte)(OPENGL_FIXED_PIPE_FLOATBUFFER.get(1) * 255.0F);
//////			this.page = (byte)(OPENGL_INTBUFFER.get(1) & 0xFF);
////			SMALLGUI.state &= 255-1;
//		}
//	}
}
