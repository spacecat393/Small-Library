package com.nali.small.gui.page.entity;

import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.key.Key;
import com.nali.gui.key.KeyEdit;
import com.nali.gui.page.PageSelect;
import com.nali.list.gui.da.server.SDaEntity;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SPageDa;
import com.nali.network.NetworkRegistry;
import com.nali.small.gui.page.PageSmall;
import com.nali.small.gui.page.entity.me.PageMe;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageEntity extends PageSelect
{
	public static byte[] BYTE_ARRAY;//1+1 (8+4)*?+? +1+1+1
	public static String[] NAME_STRING_ARRAY;

	public static byte
		STATE,//enter client init
		MAX_PAGE;//0-119
	public static int
		PAGE,
		MAX_MIX_PAGE;
	public static long[] ID_LONG_ARRAY;

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
			this.boxtextall_array = new BoxTextAll[3 + MAX_PAGE + 5];
			this.boxtextall_array[index++] = new BoxTextAll("ENTITY".toCharArray());
//			this.boxtextall_array[index++] = new BoxTextAll(("PAGE " + PAGE + " - " + MAX_MIX_PAGE).toCharArray());

			this.boxtextall_array[index++] = new BoxTextAll(("PAGE " + PAGE).toCharArray());
			this.boxtextall_array[index++] = new BoxTextAll(("MAX-PAGE " + MAX_MIX_PAGE).toCharArray());

			short i = 2;
			byte n_index = 0;
			NAME_STRING_ARRAY = new String[MAX_PAGE];
			ID_LONG_ARRAY = new long[MAX_PAGE];
			while (i < byte_array_length - 4 - 1 - 4)
			{
				long id = ByteReader.getLong(BYTE_ARRAY, i);
				i += 8;
				int name_length = ByteReader.getInt(BYTE_ARRAY, i);
				i += 4;
				String name_string = new String(BYTE_ARRAY, i, name_length);
				ID_LONG_ARRAY[n_index] = id;
				NAME_STRING_ARRAY[n_index++] = name_string;
				String text_string = (int)id + " " + name_string;
				if (text_string.length() > 20)
				{
					text_string = text_string.substring(0, 20) + "...";
				}
				i += name_length;
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
			this.boxtextall_array[index] = new BoxTextAll("BACK".toCharArray());

			this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
			this.group_byte_array[0 / 8] |= 1 << 0 % 8;
			this.group_byte_array[1 / 8] |= 1 << 1 % 8;
			byte new_index = (byte)(index - 5);
			this.group_byte_array[new_index / 8] |= 1 << new_index % 8;
		}
		else
		{
			this.boxtextall_array = new BoxTextAll[]
			{
				new BoxTextAll("ENTITY".toCharArray()),
				new BoxTextAll("ACTION".toCharArray()),
				new BoxTextAll("MORE".toCharArray()),
				new BoxTextAll("LESS".toCharArray()),
				new BoxTextAll("FETCH".toCharArray()),
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
		byte boxtextall_array_length = (byte)this.boxtextall_array.length;
		if (boxtextall_array_length == 6)
		{
			switch (this.select)
			{
				case 2:
					this.sendNet(SDaEntity.I_MORE);
					break;
				case 3:
					this.sendNet(SDaEntity.I_LESS);
					break;
				case 4:
					this.sendNet(SDaEntity.I_FETCH);
					break;
				case 5:
					this.back();
			}
		}
		else
		{
			if (this.select == (boxtextall_array_length - 4))
			{
				this.sendNet(SDaEntity.I_MORE);
			}
			else if (this.select == (boxtextall_array_length - 3))
			{
				this.sendNet(SDaEntity.I_LESS);
			}
			else if (this.select == (boxtextall_array_length - 2))
			{
				this.sendNet(SDaEntity.I_FETCH);
			}
			else if (this.select == (boxtextall_array_length - 1))
			{
				this.back();
			}
			else
			{
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				byte select = (byte)(this.select - 3);
				this.set(new PageMe(ID_LONG_ARRAY[select], NAME_STRING_ARRAY[select]), new KeyEdit());
				STATE &= 255-1;
			}
		}
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

	public void sendNet(byte b2)
	{
		PageSmall.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 4];
		PageSmall.NET_BYTE_ARRAY[0] = SPageDa.ID;
		PageSmall.NET_BYTE_ARRAY[1] = SDaEntity.ID;
		PageSmall.NET_BYTE_ARRAY[2] = b2;
		ByteWriter.set(PageSmall.NET_BYTE_ARRAY, PAGE, 3);
		NetworkRegistry.I.sendToServer(new ServerMessage(PageSmall.NET_BYTE_ARRAY));
	}
}
