package com.nali.small.gui.page.inv.select.item;

import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.page.PageEdit;
import com.nali.list.gui.da.server.SDaInvSelect;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SPage;
import com.nali.network.NetworkRegistry;
import com.nali.small.gui.page.inv.PageInv;
import com.nali.small.gui.page.inv.select.PageSelect;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageItem extends PageEdit
{
//	public static byte[] BYTE_ARRAY;//1+1 4*? +1+1+1

//	public static byte
//		STATE,//enter client init
//		PAGE,//0-127
//		MAX_PAGE,//0-119
//		MAX_MIX_PAGE;//0-127

	public int item_id;
	public String item_name;
	public long nbt = -1;

	public PageItem(int item_id)
	{
		this.item_id = item_id;
	}

	//item name size nbt nbt_set
	@Override
	public void init()
	{
		//move
		super.init();

		String item_id_string = "ID " + this.item_id;
		if (item_id_string.length() > 20)
		{
			item_id_string = item_id_string.substring(0, 20) + "...";
		}
		this.item_name = new ItemStack(Item.getItemById(this.item_id)).getDisplayName();
		String name_string = "NAME " + this.item_name;
		if (name_string.length() > 20)
		{
			name_string = name_string.substring(0, 20) + "...";
		}
		String nbt_string = "NBT " + this.nbt;
		if (nbt_string.length() > 20)
		{
			nbt_string = nbt_string.substring(0, 20) + "...";
		}
		this.boxtextall_array = new BoxTextAll[]
		{
			new BoxTextAll("SELECT-ITEM".toCharArray()),
			new BoxTextAll("MENU".toCharArray()),
			new BoxTextAll(item_id_string.toCharArray()),
			new BoxTextAll(name_string.toCharArray()),
			//size
			new BoxTextAll(nbt_string.toCharArray()),
			new BoxTextAll("ITEM-NBT".toCharArray()),
			new BoxTextAll("ACTION".toCharArray()),
			new BoxTextAll("MOVE".toCharArray()),
			new BoxTextAll("DELETE".toCharArray()),
			new BoxTextAll("DONE".toCharArray())
		};

		this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[5 / 8] |= 1 << 5 % 8;

		if ((this.state & 4) == 0)
		{
			this.select = 7;
			this.state |= 4;
		}
	}

	@Override
	public void enter()
	{
//		if ((STATE & 1) == 0)
//		{
//			STATE |= 1;
		byte[] byte_array;
		switch (this.select)
		{
			case 2:
				if ((this.state & 1) == 0)
				{
					this.input_stringbuilder.setLength(0);
					this.input_stringbuilder.append(this.item_id);
					this.select_box = this.input_stringbuilder.length();
				}
				this.state ^= 1;
				this.scroll = 0;
				break;
			case 3:
				if ((this.state & 1) == 0)
				{
					this.input_stringbuilder.setLength(0);
					this.input_stringbuilder.append(this.item_name);
					this.select_box = this.input_stringbuilder.length();
				}
				this.state ^= 1;
				this.scroll = 0;
				break;
			case 4:
				break;
			case 5:
				break;
			case 7:
				byte_array = new byte[1 + 1 + 1 + 1 + 4 + 4 + 8];
				byte_array[0] = SPage.ID;
				byte_array[1] = SDaInvSelect.ID;
				byte_array[2] = SDaInvSelect.I_MOVE;
				ByteWriter.set(byte_array, PageSelect.PAGE, 3);
				ByteWriter.set(byte_array, ByteReader.getInt(PageInv.BYTE_ARRAY, 2 + PageInv.SELECT * 4), 3+4);
				ByteWriter.set(byte_array, this.item_id, 3+4+4);
				ByteWriter.set(byte_array, this.nbt, 3+4+4+4);
				NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
				this.back();
				break;
			case 8:
				byte_array = new byte[1 + 1 + 1 + 1 + 4 + 4];
				byte_array[0] = SPage.ID;
				byte_array[1] = SDaInvSelect.ID;
				byte_array[2] = SDaInvSelect.I_DELETE;
				ByteWriter.set(byte_array, PageSelect.PAGE, 3);
				ByteWriter.set(byte_array, ByteReader.getInt(PageInv.BYTE_ARRAY, 2 + PageInv.SELECT * 4), 3+4);
				ByteWriter.set(byte_array, this.item_id, 3+4+4);
				NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
				this.back();
				break;
			case 9:
				this.back();
		}
//			STATE &= 255-1;
//		}
	}

//	@Override
//	public void draw()
//	{
//		if ((STATE & 4) == 4)
//		{
//			this.state &= 255-4;
//			this.clear();
//			this.init();
//
//			this.gen();
//			STATE &= 255-(2+4);
//		}
//		super.draw();
//	}
}