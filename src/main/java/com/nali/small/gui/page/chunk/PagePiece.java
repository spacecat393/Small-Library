package com.nali.small.gui.page.chunk;

import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.page.PageSelect;
import com.nali.list.gui.da.server.SDaChunkList;
import com.nali.list.gui.da.server.SDaChunkMap;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SPage;
import com.nali.network.NetworkRegistry;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PagePiece extends PageSelect
{
	public int i = -1, d, x, z;

	public PagePiece(int x, int z)
	{
		this.x = x;
		this.z = z;
	}

	public PagePiece(int i, int d, int x, int z)
	{
		this(x, z);
		this.i = i;
		this.d = d;
	}

	@Override
	public void init()
	{
		if (this.i == -1)
		{
			this.boxtextall_array = new BoxTextAll[]
			{
				new BoxTextAll("CHUNK-PIECE".toCharArray()),
				new BoxTextAll("DATA".toCharArray()),
				new BoxTextAll(("ID " + PageList.SELECT).toCharArray()),
				new BoxTextAll(("X " + this.x).toCharArray()),
				new BoxTextAll(("Z " + this.z).toCharArray()),
				new BoxTextAll("ACTION".toCharArray()),
				new BoxTextAll("DELETE".toCharArray()),
				new BoxTextAll("DONE".toCharArray())
			};

			this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
			this.group_byte_array[0 / 8] |= 1 << 0 % 8;
			this.group_byte_array[4 / 8] |= 1 << 4 % 8;

			if ((this.state & 4) == 0)
			{
				this.select = 6;
				this.state |= 4;
			}

			this.min_select = 6;
		}
		else
		{
			this.boxtextall_array = new BoxTextAll[]
			{
				new BoxTextAll("CHUNK-PIECE".toCharArray()),
				new BoxTextAll("DATA".toCharArray()),
				new BoxTextAll(("I " + this.i).toCharArray()),
				new BoxTextAll(("D " + this.d).toCharArray()),
				new BoxTextAll(("X " + this.x).toCharArray()),
				new BoxTextAll(("Z " + this.z).toCharArray()),
				new BoxTextAll("ACTION".toCharArray()),
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

			this.min_select = 7;
		}
	}

	@Override
	public void enter()
	{
		byte select = this.select;
		if (this.i == -1)
		{
			switch (select)
			{
				case 6:
					byte[] byte_array = new byte[1 + 1 + 1 + 4 + 1];
					byte_array[0] = SPage.ID;
					byte_array[1] = SDaChunkList.ID;
					byte_array[2] = SDaChunkList.I_DELETE;
					ByteWriter.set(byte_array, PageList.PAGE, 3);
//					ByteWriter.set(byte_array, PageList.SELECT, 3 + 4);
					byte_array[3 + 4] = PageList.SELECT;
					NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
					this.back();
					break;
				case 7:
					this.back();
					break;
			}
		}
		else
		{
			switch (select)
			{
				case 7:
					byte[] byte_array = new byte[1 + 1 + 1 + 8];
					ByteWriter.set(byte_array, ByteReader.getLong(PageMap.BYTE_ARRAY, 2 + PageMap.SELECT * (8 + 2 * 4)), 3);
					byte_array[0] = SPage.ID;
					byte_array[1] = SDaChunkMap.ID;
					byte_array[2] = SDaChunkMap.I_DELETE;
					NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
					this.back();
					break;
				case 8:
					this.back();
					break;
			}
		}
	}
}
