package com.nali.small.gui.page;

import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.key.Key;
import com.nali.gui.key.KeyEdit;
import com.nali.gui.key.KeySelect;
import com.nali.gui.page.PageSelect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageEntityMe extends PageSelect
{
	public int i, d;
	public String name;

	public PageEntityMe(int i, int d, String name)
	{
		this.i = i;
		this.d = d;
		this.name = name;
		this.select = 7;
	}

	@Override
	public void init()
	{
		//use data
		this.boxtextall_array = new BoxTextAll[]
		{
			new BoxTextAll("Entity-ME".toCharArray()),
			new BoxTextAll("MENU".toCharArray()),
			new BoxTextAll("ENTITY-ME-STATE".toCharArray()),
			new BoxTextAll("ENTITY-ME-SI".toCharArray()),
			new BoxTextAll("ENTITY-ME-INV".toCharArray()),
			new BoxTextAll("ENTITY-ME-MAP".toCharArray()),
			new BoxTextAll("ACTION".toCharArray()),
			new BoxTextAll("DONE".toCharArray())
		};
		this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[5 / 8] |= 1 << 5 % 8;
	}

	@Override
	public void enter()
	{
		switch (this.select)
		{
			case 2:
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				this.set(new PageEntityMeState(), new KeyEdit());
				break;
			case 3:
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				this.set(new PageEntityMeSI(), new KeySelect());
				break;
			case 4:
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				this.set(new PageEntityMeInv(), new KeySelect());
				break;
			case 5:
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				this.set(new PageEntityMeMap(), new KeySelect());
				break;
			case 7:
				this.back();
				break;
		}
	}
}
