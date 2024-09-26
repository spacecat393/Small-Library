package com.nali.small.gui.key;

import com.nali.small.gui.page.PageText;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.small.gui.page.PageMe.openPageMe;

@SideOnly(Side.CLIENT)
public class KeyMenuSI extends KeyMenu
{
	@Override
	public void run()
	{
		if ((STATE & 1) == 1)
		{
			openPageMe();
		}
	}

	@Override
	public void detect(char typed_char, int key_code)
	{
		super.detect(typed_char, key_code);
		if ((STATE & 4) == 4)
		{
			type(PageText.STRINGBUFFER, typed_char, key_code);
		}
	}
}
