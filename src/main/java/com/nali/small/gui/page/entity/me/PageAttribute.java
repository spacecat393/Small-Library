package com.nali.small.gui.page.entity.me;

import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.key.Key;
import com.nali.gui.page.PageSelect;
import com.nali.list.gui.da.server.SDaAttribute;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SPageDa;
import com.nali.network.NetworkRegistry;
import com.nali.small.SmallAttribute;
import com.nali.small.gui.page.PageSmall;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageAttribute extends PageSelect
{
	public static byte[] BYTE_ARRAY;//1+1 (4)*?+? +1+1+1

	public static byte
		STATE,//enter client init
		PAGE,//0-127
		MAX_PAGE,//0-120
		MAX_MIX_PAGE;//0-127

	@Override
	public void init()
	{
		//edit attribute
		if (BYTE_ARRAY != null)
		{
			short byte_array_length = (short)BYTE_ARRAY.length;
			PAGE = BYTE_ARRAY[byte_array_length - 3];
			MAX_PAGE = BYTE_ARRAY[byte_array_length - 2];
			MAX_MIX_PAGE = BYTE_ARRAY[byte_array_length - 1];

			byte index = 0;
			this.boxtextall_array = new BoxTextAll[2 + MAX_PAGE + 5];
			this.boxtextall_array[index++] = new BoxTextAll("ME-ATTRIBUTE".toCharArray());
			this.boxtextall_array[index++] = new BoxTextAll(("PAGE " + PAGE + " - " + MAX_MIX_PAGE).toCharArray());

			short i = 2;
			while (i < byte_array_length - 3)
			{
				int attribute_id = ByteReader.getInt(BYTE_ARRAY, i);
				i += 4;
				IAttribute iattribute = SmallAttribute.IATTRIBUTE_MAP.get(attribute_id);
				String baseattribute_string = attribute_id + " " + iattribute.getName();
				if (baseattribute_string.length() > 20)
				{
					baseattribute_string = baseattribute_string.substring(0, 20) + "...";
				}
				this.boxtextall_array[index++] = new BoxTextAll(baseattribute_string.toCharArray());
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
			byte new_index = (byte)(index - 5);
			this.group_byte_array[new_index / 8] |= 1 << new_index % 8;
		}
		else
		{
			this.boxtextall_array = new BoxTextAll[]
			{
				new BoxTextAll("ME-ATTRIBUTE".toCharArray()),
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
					this.sendNet(SDaAttribute.I_MORE);
					break;
				case 3:
					this.sendNet(SDaAttribute.I_LESS);
					break;
				case 4:
					this.sendNet(SDaAttribute.I_FETCH);
					break;
				case 5:
					this.back();
			}
		}
		else
		{
			if (this.select == (boxtextall_array_length - 4))
			{
				this.sendNet(SDaAttribute.I_MORE);
			}
			else if (this.select == (boxtextall_array_length - 3))
			{
				this.sendNet(SDaAttribute.I_LESS);
			}
			else if (this.select == (boxtextall_array_length - 2))
			{
				this.sendNet(SDaAttribute.I_FETCH);
			}
			else if (this.select == (boxtextall_array_length - 1))
			{
				this.back();
			}
			else
			{
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
//				byte select = (byte)(this.select - 2);
//				this.set(new PageAttributeE(), new KeyEdit());
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
		PageSmall.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 1 + 8];
		PageSmall.NET_BYTE_ARRAY[0] = SPageDa.ID;
		PageSmall.NET_BYTE_ARRAY[1] = SDaAttribute.ID;
		PageSmall.NET_BYTE_ARRAY[2] = b2;
		PageSmall.NET_BYTE_ARRAY[3] = PAGE;
		ByteWriter.set(PageSmall.NET_BYTE_ARRAY, PageMe.ID, 4);
		NetworkRegistry.I.sendToServer(new ServerMessage(PageSmall.NET_BYTE_ARRAY));
	}
}
