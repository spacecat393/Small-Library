package com.nali.small.gui.page;

import com.nali.gui.box.text.BoxTextAZ;
import com.nali.gui.page.Page;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageSmall extends Page
{
	public BoxTextAZ[] boxtextaz_array;

	@Override
	public void init()
	{
		this.boxtextaz_array = new BoxTextAZ[]
		{
			new BoxTextAZ("GAME".toCharArray()),
			new BoxTextAZ("CHUNK".toCharArray()),
			new BoxTextAZ("ENTITY".toCharArray()),

			new BoxTextAZ("CONFIG".toCharArray()),
			new BoxTextAZ("SOUND".toCharArray()),
			new BoxTextAZ("PRE".toCharArray())
		};
	}

	@Override
	public void gen()
	{

	}

	@Override
	public void draw()
	{

	}

	@Override
	public void clear()
	{

	}

	@Override
	public void render()
	{

	}
}
