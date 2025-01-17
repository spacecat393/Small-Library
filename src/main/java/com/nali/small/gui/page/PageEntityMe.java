package com.nali.small.gui.page;

import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.key.Key;
import com.nali.gui.key.KeyEdit;
import com.nali.gui.key.KeySelect;
import com.nali.gui.page.PageEdit;
import com.nali.list.gui.da.server.SDaEntity;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SPage;
import com.nali.network.NetworkRegistry;
import com.nali.system.bytes.ByteWriter;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageEntityMe extends PageEdit
{
//	public int i, d;
	public static long ID;
	public String name;

	public PageEntityMe(long id, String name)
	{
//		this.i = i;
//		this.d = d;
		ID = id;
		this.name = name;
	}

	@Override
	public void init()
	{
		super.init();

		String name_string = "NAME " + this.name;
		if (name_string.length() > 20)
		{
			name_string = name_string.substring(0, 20) + "...";
		}
		this.boxtextall_array = new BoxTextAll[]
		{
			new BoxTextAll("ME".toCharArray()),
			new BoxTextAll("MENU".toCharArray()),
			//i
			//d
			new BoxTextAll(name_string.toCharArray()),
			//hp
			//mp
			//edit inv in si
			new BoxTextAll("ATTRIBUTE".toCharArray()),
			new BoxTextAll("SI".toCharArray()),
//			new BoxTextAll("ME-INV".toCharArray()),
			new BoxTextAll("MAP".toCharArray()),
			new BoxTextAll("EFFECT".toCharArray()),
			new BoxTextAll("ACTION".toCharArray()),
			new BoxTextAll("DONE".toCharArray())
		};

		this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[6 / 8] |= 1 << 6 % 8;

		if ((this.state & 4) == 0)
		{
			this.select = 8;
			this.state |= 4;
		}
	}

	@Override
	public void enter()
	{
		switch (this.select)
		{
			case 2:
				if ((this.state & 1) == 1)
				{
					this.name = this.input_stringbuilder.toString();

					//rename
					byte[] name_byte_array = this.name.getBytes();
					int name_byte_array_length = name_byte_array.length;
					byte[] byte_array = new byte[1 + 1 + 1 + 1 + 8 + name_byte_array_length];
					byte_array[0] = SPage.ID;
					byte_array[1] = SDaEntity.ID;
					byte_array[2] = SDaEntity.I_RENAME;
					byte_array[3] = PageEntity.PAGE;
//					ByteWriter.set(byte_array, (long)this.d << 32 | this.i, 4);
					ByteWriter.set(byte_array, ID, 4);
					System.arraycopy(name_byte_array, 0, byte_array, 4+8, name_byte_array_length);
					NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
				}
				else
				{
					this.input_stringbuilder.setLength(0);
					this.input_stringbuilder.append(this.name);
					this.select_box = this.input_stringbuilder.length();
				}
				this.state ^= 1;
				this.scroll = 0;
				break;
			case 3:
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				this.set(new PageAttribute(), new KeySelect());
				break;
			case 4:
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				this.set(new PageSI(), new KeySelect());
				break;
//			case 5:
//				PAGE_LIST.add(this);
//				KEY_LIST.add(Key.KEY);
//				this.set(new PageInv(), new KeySelect());
//				break;
			case 5:
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				this.set(new PageEntityMeMap(), new KeySelect());
				break;
			case 6:
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				this.set(new PageEntityMeEffect(), new KeyEdit());
				break;
			case 8:
				this.back();
				break;
		}
	}
}
