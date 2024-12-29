package com.nali.small.gui.page;

import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.key.Key;
import com.nali.gui.key.KeySelect;
import com.nali.gui.page.PageSelect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageChunk extends PageSelect
{
	@Override
	public void init()
	{
		this.boxtextall_array = new BoxTextAll[]
		{
			new BoxTextAll("CHUNK".toCharArray()),
			new BoxTextAll("MENU".toCharArray()),
			new BoxTextAll("CHUNK-LIST".toCharArray()),
			new BoxTextAll("CHUNK-MAP".toCharArray()),
			new BoxTextAll("ACTION".toCharArray()),
			new BoxTextAll("DONE".toCharArray())
		};

		this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[3 / 8] |= 1 << 3 % 8;

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
				this.set(new PageChunkList(), new KeySelect());
				break;
			case 3:
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				this.set(new PageChunkMap(), new KeySelect());
				break;
			case 5:
				this.back();
				break;
		}
	}
}
