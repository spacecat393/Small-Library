package com.nali.small.gui.page;

import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.key.Key;
import com.nali.gui.key.KeySelect;
import com.nali.gui.page.PageSelect;
import com.nali.list.gui.data.server.SDataChunkList;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SPage;
import com.nali.network.NetworkRegistry;
import com.nali.system.bytes.ByteReader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageChunkList extends PageSelect
{
	public static byte[] BYTE_ARRAY;//1+1 4*2*? +1+1+1

	public static byte
		STATE,//enter client init
		PAGE,//0-127
		MAX_PAGE,//0-120
		MAX_MIX_PAGE,//0-127
		SELECT;

	@Override
	public void init()
	{
		if (BYTE_ARRAY != null/* && (PageChunkList.STATE & 2) == 0*/)
		{
			short byte_array_length = (short)BYTE_ARRAY.length;
			PAGE = BYTE_ARRAY[byte_array_length - 3];
			MAX_PAGE = BYTE_ARRAY[byte_array_length - 2];
			MAX_MIX_PAGE = BYTE_ARRAY[byte_array_length - 1];

			byte index = 0;
			this.boxtextall_array = new BoxTextAll[2 + MAX_PAGE + 5];
			this.boxtextall_array[index++] = new BoxTextAll("CHUNK-LIST".toCharArray());
			this.boxtextall_array[index++] = new BoxTextAll(("PAGE " + PAGE + " - " + MAX_MIX_PAGE).toCharArray());

			byte id = 0;
			short i = 2;
			while (i < byte_array_length - 3)
			{
//				int id = ByteReader.getInt(BYTE_ARRAY, i);
//				i += 4;
//				int x = ByteReader.getInt(BYTE_ARRAY, i);
//				i += 4;
//				int z = ByteReader.getInt(BYTE_ARRAY, i);
//				i += 4;
				i += 4+4;
				this.boxtextall_array[index++] = new BoxTextAll(("" + id++/* + "x" + x + "z" + z*/).toCharArray());
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

//			BYTE_ARRAY = null;
		}
		else
		{
			this.boxtextall_array = new BoxTextAll[]
			{
				new BoxTextAll("CHUNK-LIST".toCharArray()),
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
//		if ((STATE & 1) == 0)
//		{
//			STATE |= 1;

		byte[] byte_array = new byte[1 + 1 + 1 + 1];
		byte_array[0] = SPage.ID;
		byte_array[1] = SDataChunkList.ID;
		byte_array[3] = PAGE;

		byte boxtextall_array_length = (byte)this.boxtextall_array.length;
		if (boxtextall_array_length == 6)
		{
			switch (this.select)
			{
				case 2:
					byte_array[2] = 0;
					break;
				case 3:
					byte_array[2] = 1;
					break;
				case 4:
					byte_array[2] = 2;
					break;
				case 5:
					this.back();
			}
		}
		else
		{
			if (this.select == (boxtextall_array_length - 4))
			{
				//more
				byte_array[2] = 0;
			}
			else if (this.select == (boxtextall_array_length - 3))
			{
				//less
				byte_array[2] = 1;
			}
			else if (this.select == (boxtextall_array_length - 2))
			{
				//fetch
				byte_array[2] = 2;
			}
			else if (this.select == (boxtextall_array_length - 1))
			{
				this.back();
			}
			else/* if (this.select > 1)*/
			{
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				SELECT = (byte)(this.select - 2);
				int new_index = 2 + SELECT * 2 * 4;
				this.set(new PageChunkPiece(ByteReader.getInt(BYTE_ARRAY, new_index), ByteReader.getInt(BYTE_ARRAY, new_index + 4)), new KeySelect());
				STATE &= 255-1;
				return;
			}
		}
		NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
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

//			GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, RenderO.INTBUFFER);
//			this.gl_array_buffer_binding = RenderO.INTBUFFER.get(0);
			this.gen();
//			OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, this.gl_array_buffer_binding);
			STATE &= 255-(2+4);
		}
		super.draw();
	}
}
