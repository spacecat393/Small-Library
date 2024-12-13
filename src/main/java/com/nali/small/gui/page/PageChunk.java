package com.nali.small.gui.page;

import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.page.PageSelect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageChunk extends PageSelect
{
	public int
		page,
		max_page;

	public PageChunk()
	{
		this.select = 2;
	}

	@Override
	public void init()
	{
		int limit_page = this.max_page;
		if (limit_page > 251)
		{
			limit_page = 251;
		}
		int max_page = this.max_page / 251;
		int size = this.max_page - this.page * limit_page * max_page;

		int index = 0;
		this.boxtextall_array = new BoxTextAll[1 + 1 + size + 1 + 1];
		this.boxtextall_array[index++] = new BoxTextAll("OPENGL-CONFIG".toCharArray());
		this.boxtextall_array[index++] = new BoxTextAll(("PAGE " + this.page + " / " + this.max_page).toCharArray());

		//chunk

		this.boxtextall_array[index++] = new BoxTextAll("ACTION".toCharArray());
		this.boxtextall_array[index] = new BoxTextAll("BACK".toCharArray());

		this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[4 / 8] |= 1 << 4 % 8;
	}

	@Override
	public void enter()
	{

	}

	@Override
	public void exit()
	{

	}
}
