package com.nali.small.gui.page.inv.select;

import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.key.Key;
import com.nali.gui.key.KeyEdit;
import com.nali.list.gui.da.server.SDaInv;
import com.nali.list.gui.da.server.SDaInvSelect;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SPageDa;
import com.nali.network.NetworkRegistry;
import com.nali.small.gui.page.PageSmall;
import com.nali.small.gui.page.inv.PageInv;
import com.nali.small.gui.page.inv.select.item.PageItem;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageSelect extends com.nali.gui.page.PageSelect
{
	public static byte[] BYTE_ARRAY;//1+1 4*? +1+1+1

	public static byte
		STATE,//enter client init
		MAX_PAGE;//0-117
//		SELECT;
	public static int
		PAGE,
		MAX_MIX_PAGE;

	@Override
	public void init()
	{
		if (BYTE_ARRAY != null)
		{
			short byte_array_length = (short)BYTE_ARRAY.length;
			PAGE = ByteReader.getInt(BYTE_ARRAY, byte_array_length - 4 - 1 - 4);
			MAX_PAGE = BYTE_ARRAY[byte_array_length - 1 - 4];
			MAX_MIX_PAGE = ByteReader.getInt(BYTE_ARRAY, byte_array_length - 4);

			byte index = 0;
			this.boxtextall_array = new BoxTextAll[3 + MAX_PAGE + 7];
			this.boxtextall_array[index++] = new BoxTextAll("INV-SELECT".toCharArray());
			this.boxtextall_array[index++] = new BoxTextAll(("PAGE " + PAGE).toCharArray());
			this.boxtextall_array[index++] = new BoxTextAll(("MAX-PAGE " + MAX_MIX_PAGE).toCharArray());

			short i = 2;
			while (i < byte_array_length - 4 - 1 - 4)
			{
				int id = ByteReader.getInt(BYTE_ARRAY, i);
				i += 4;
				String text_string = id + " " + new ItemStack(Item.getItemById(id)).getDisplayName();
				if (text_string.length() > 20)
				{
					text_string = text_string.substring(0, 20) + "...";
				}
				this.boxtextall_array[index++] = new BoxTextAll(text_string.toCharArray());
			}

			this.boxtextall_array[index++] = new BoxTextAll("ACTION".toCharArray());
			this.boxtextall_array[index++] = new BoxTextAll("MORE".toCharArray());
			this.boxtextall_array[index++] = new BoxTextAll("LESS".toCharArray());

			if ((this.state & 4) == 0)
			{
				this.select = index;
				this.state |= 4;
			}

			this.boxtextall_array[index++] = new BoxTextAll("FETCH".toCharArray());
			this.boxtextall_array[index++] = new BoxTextAll("ADD".toCharArray());
			this.boxtextall_array[index++] = new BoxTextAll("DELETE".toCharArray());
			this.boxtextall_array[index] = new BoxTextAll("BACK".toCharArray());

			this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
			this.group_byte_array[0 / 8] |= 1 << 0 % 8;
			this.group_byte_array[1 / 8] |= 1 << 1 % 8;
			byte new_index = (byte)(index - 7);
			this.group_byte_array[new_index / 8] |= 1 << new_index % 8;

//			BYTE_ARRAY = null;
		}
		else
		{
			this.boxtextall_array = new BoxTextAll[]
			{
				new BoxTextAll("INV-SELECT".toCharArray()),
				new BoxTextAll("ACTION".toCharArray()),
				new BoxTextAll("MORE".toCharArray()),
				new BoxTextAll("LESS".toCharArray()),
				new BoxTextAll("FETCH".toCharArray()),
				new BoxTextAll("ADD".toCharArray()),
				new BoxTextAll("DELETE".toCharArray()),
				new BoxTextAll("BACK".toCharArray())
			};

			this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
			this.group_byte_array[0 / 8] |= 1 << 0 % 8;

			if ((this.state & 4) == 0)
			{
				this.select = 4;
				this.state |= 4;
			}
		}
	}

	@Override
	public void enter()
	{
//		if ((STATE & 1) == 0)
//		{
//			STATE |= 1;
		byte boxtextall_array_length = (byte)this.boxtextall_array.length;
		if (boxtextall_array_length == 8)
		{
			switch (this.select)
			{
				case 2:
					this.sendNet(SDaInvSelect.ID, SDaInvSelect.B_MORE);
					break;
				case 3:
					this.sendNet(SDaInvSelect.ID, SDaInvSelect.B_LESS);
					break;
				case 4:
					this.sendNet(SDaInvSelect.ID, SDaInvSelect.B_FETCH);
					break;
				case 5:
					PAGE_LIST.add(this);
					KEY_LIST.add(Key.KEY);
//						SELECT = (byte)(this.select - 2);
//					int new_index = 2 + SELECT * (8 + 2 * 4);
//					long id = ByteReader.getLong(BYTE_ARRAY, new_index);
					this.set(new PageAdd(/*(int)id, (int)(id >> 32), NAME_STRING_ARRAY[SELECT]*/), new KeyEdit());
					STATE &= 255-1;
				case 6:
					this.sendNet(SDaInv.ID, SDaInv.B_DELETE);
					this.back();
					break;
				case 7:
					this.back();
			}
		}
		else
		{
			if (this.select == (boxtextall_array_length - 6))
			{
				this.sendNet(SDaInvSelect.ID, SDaInvSelect.B_MORE);
			}
			else if (this.select == (boxtextall_array_length - 5))
			{
				this.sendNet(SDaInvSelect.ID, SDaInvSelect.B_LESS);
			}
			else if (this.select == (boxtextall_array_length - 4))
			{
				this.sendNet(SDaInvSelect.ID, SDaInvSelect.B_FETCH);
			}
			else if (this.select == (boxtextall_array_length - 3))
			{
				//add
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
//					SELECT = (byte)(this.select - 2);
//					int new_index = 2 + SELECT * (8 + 2 * 4);
//					long id = ByteReader.getLong(BYTE_ARRAY, new_index);
				this.set(new PageAdd(/*(int)id, (int)(id >> 32), NAME_STRING_ARRAY[SELECT]*/), new KeyEdit());
				STATE &= 255-1;
			}
			else if (this.select == (boxtextall_array_length - 2))
			{
				this.sendNet(SDaInv.ID, SDaInv.B_DELETE);
				this.back();
			}
			else if (this.select == (boxtextall_array_length - 1))
			{
				this.back();
			}
			else/* if (this.select > 1)*/
			{
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
//					SELECT = (byte)(this.select - 2);
//					int new_index = 2 + SELECT * (8 + 2 * 4);
//					long id = ByteReader.getLong(BYTE_ARRAY, new_index);
				this.set(new PageItem(ByteReader.getInt(BYTE_ARRAY, 2 + (byte)(this.select - 3) * 4)/*(int)id, (int)(id >> 32), NAME_STRING_ARRAY[SELECT]*/), new KeyEdit());
				STATE &= 255-1;
			}
		}
//			STATE &= 255-1;
//		}
	}

	@Override
	public void draw()
	{
		if ((STATE & 4) == 4)
		{
			this.state &= 255-4;
			this.clear();
			this.init();

			this.gen();
			STATE &= 255-(2+4);
		}
		super.draw();
	}

	public void sendNet(byte b1, byte b2)
	{
		PageSmall.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 4 + 4];
		PageSmall.NET_BYTE_ARRAY[0] = SPageDa.ID;
		PageSmall.NET_BYTE_ARRAY[1] = b1;
		PageSmall.NET_BYTE_ARRAY[2] = b2;
		if (b1 == SDaInv.ID)
		{
			ByteWriter.set(PageSmall.NET_BYTE_ARRAY, PageInv.PAGE, 3);
		}
		else
		{
			ByteWriter.set(PageSmall.NET_BYTE_ARRAY, PAGE, 3);
		}
		ByteWriter.set(PageSmall.NET_BYTE_ARRAY, ByteReader.getInt(PageInv.BYTE_ARRAY, 2 + PageInv.SELECT * 4), 3+4);
		NetworkRegistry.I.sendToServer(new ServerMessage(PageSmall.NET_BYTE_ARRAY));
	}
}