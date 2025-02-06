package com.nali.small.gui.page.chunk;

import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.key.Key;
import com.nali.gui.key.KeySelect;
import com.nali.gui.page.PageSelect;
import com.nali.list.gui.da.server.SDaChunkMap;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SPageDa;
import com.nali.network.NetworkRegistry;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageMap extends PageSelect
{
	public static byte[] BYTE_ARRAY;//1+1 (8+4*2)*? +1+1+1

	public static byte
		STATE,//enter client init
		MAX_PAGE,//0-118
		SELECT;
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
			this.boxtextall_array = new BoxTextAll[3 + MAX_PAGE + 6];
			this.boxtextall_array[index++] = new BoxTextAll("CHUNK-MAP".toCharArray());
			this.boxtextall_array[index++] = new BoxTextAll(("PAGE " + PAGE).toCharArray());
			this.boxtextall_array[index++] = new BoxTextAll(("MAX-PAGE " + MAX_MIX_PAGE).toCharArray());

			short i = 2;
			while (i < byte_array_length - 4 - 1 - 4)
			{
				long id = ByteReader.getLong(BYTE_ARRAY, i);
				i += 8+4+4;
				String text_string = (int)id + " " + (int)(id >> 32);
				if (text_string.length() > 20)
				{
					text_string = text_string.substring(0, 20) + "...";
				}
				this.boxtextall_array[index++] = new BoxTextAll(text_string.toCharArray());
			}

			this.boxtextall_array[index++] = new BoxTextAll("ACTION".toCharArray());
			this.boxtextall_array[index++] = new BoxTextAll("CLEAN".toCharArray());
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
			byte new_index = (byte)(index - 6);
			this.group_byte_array[new_index / 8] |= 1 << new_index % 8;
		}
		else
		{
			this.boxtextall_array = new BoxTextAll[]
			{
				new BoxTextAll("CHUNK-MAP".toCharArray()),
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
		byte[] byte_array = new byte[1 + 1 + 1 + 4];
		byte_array[0] = SPageDa.ID;
		byte_array[1] = SDaChunkMap.ID;
		ByteWriter.set(byte_array, PAGE, 3);

		byte boxtextall_array_length = (byte)this.boxtextall_array.length;
		if (boxtextall_array_length == 6)
		{
			switch (this.select)
			{
				case 2:
					byte_array[2] = SDaChunkMap.I_MORE;
					break;
				case 3:
					byte_array[2] = SDaChunkMap.I_LESS;
					break;
				case 4:
					byte_array[2] = SDaChunkMap.I_FETCH;
					break;
				case 5:
					this.back();
			}
		}
		else
		{
			if (this.select == (boxtextall_array_length - 5))
			{
				byte_array[2] = SDaChunkMap.I_DELETE_ALL;
			}
			else if (this.select == (boxtextall_array_length - 4))
			{
				byte_array[2] = SDaChunkMap.I_MORE;
			}
			else if (this.select == (boxtextall_array_length - 3))
			{
				byte_array[2] = SDaChunkMap.I_LESS;
			}
			else if (this.select == (boxtextall_array_length - 2))
			{
				byte_array[2] = SDaChunkMap.I_FETCH;
			}
			else if (this.select == (boxtextall_array_length - 1))
			{
				this.back();
			}
			else
			{
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				SELECT = (byte)(this.select - 3);
				int new_index = 2 + SELECT * (8 + 2 * 4);
				long id = ByteReader.getLong(BYTE_ARRAY, new_index);
				this.set(new PagePiece((int)id, (int)(id >> 32), ByteReader.getInt(BYTE_ARRAY, new_index + 8), ByteReader.getInt(BYTE_ARRAY, new_index + 8 + 4)), new KeySelect());
				STATE &= 255-1;
				return;
			}
		}
		NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
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
}
