package com.nali.small.gui.page;

import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.key.Key;
import com.nali.gui.key.KeySelect;
import com.nali.gui.page.Page;
import com.nali.gui.page.PageConfig;
import com.nali.gui.page.PageSelect;
import com.nali.list.key.SmallPage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageSmall extends PageSelect
{
	public PageSmall()
	{
		this.select = 2;
	}

	@Override
	public void init()
	{
		this.boxtextall_array = new BoxTextAll[]
		{
			new BoxTextAll("SMALL-PAGE".toCharArray()),
			new BoxTextAll("GAME".toCharArray()),
			new BoxTextAll("CHUNK".toCharArray()),
			new BoxTextAll("ENTITY".toCharArray()),
			new BoxTextAll("CONFIG".toCharArray()),
			new BoxTextAll("ACTION".toCharArray()),
			new BoxTextAll("DONE".toCharArray())
		};
		this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[4 / 8] |= 1 << 4 % 8;
	}

	@Override
	public void enter()
	{
		switch (this.select)
		{
			case 4:
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				this.set(new PageConfig(), new KeySelect());
				break;
			case 6:
				this.back();
				break;
		}
	}

	@Override
	public void back()
	{
		this.exit();
//		this.state |= 2;
	}

	@Override
	public void exit()
	{
		Page.PAGE.clear();

		SmallPage.PAGE = Page.PAGE;
		SmallPage.KEY = Key.KEY;

//			PAGE_LIST.clear();
//			KEY_LIST.clear();
		Page.PAGE = null;
		Key.KEY = null;

		this.state |= 2;
	}
}
