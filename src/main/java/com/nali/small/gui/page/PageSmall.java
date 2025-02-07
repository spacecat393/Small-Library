package com.nali.small.gui.page;

import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.key.Key;
import com.nali.gui.key.KeyEdit;
import com.nali.gui.key.KeySelect;
import com.nali.gui.page.PageConfig;
import com.nali.gui.page.PageSelect;
import com.nali.list.key.SmallPage;
import com.nali.small.gui.page.chunk.PageChunk;
import com.nali.small.gui.page.entity.PageEntity;
import com.nali.small.gui.page.inv.PageInv;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageSmall extends PageSelect
{
	public static byte[] NET_BYTE_ARRAY;

	@Override
	public void init()
	{
		this.boxtextall_array = new BoxTextAll[]
		{
			new BoxTextAll("SMALL".toCharArray()),
			new BoxTextAll("GAME".toCharArray()),
			new BoxTextAll("INV".toCharArray()),
			new BoxTextAll("ENTITY".toCharArray()),
			new BoxTextAll("CHUNK".toCharArray()),
			new BoxTextAll("CONFIG".toCharArray()),
			new BoxTextAll("ACTION".toCharArray()),
			new BoxTextAll("DONE".toCharArray())
		};

		this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[5 / 8] |= 1 << 5 % 8;

		if ((this.state & 4) == 0)
		{
			this.select = 2;
			this.state |= 4;
		}
	}

	@Override
	public void enter()
	{
		switch (this.select)
		{
			case 2:
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				this.set(new PageInv(), new KeyEdit());
				break;
			case 3:
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				this.set(new PageEntity(), new KeySelect());
				break;
			case 4:
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				this.set(new PageChunk(), new KeySelect());
				break;
			case 5:
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				this.set(new PageConfig(), new KeySelect());
				break;
			case 7:
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
		SmallPage.setSmallPage();

		super.exit();
	}
}
