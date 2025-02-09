package com.nali.small.gui.page.entity.si;

import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.page.PageSelect;
import com.nali.list.gui.si.server.SSIEOwner;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SPageSI;
import com.nali.network.NetworkRegistry;
import com.nali.small.gui.page.PageSmall;
import com.nali.small.gui.page.entity.me.PageMe;
import com.nali.system.bytes.ByteWriter;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageSIEOwner extends PageSelect
{
	public static byte
		STATE;//enter client init
	public static String OWNER_NAME = "NULL";

	@Override
	public void init()
	{
		this.boxtextall_array = new BoxTextAll[]
		{
			new BoxTextAll("SIE-AREA".toCharArray()),
			new BoxTextAll("DATA".toCharArray()),
			new BoxTextAll(("OWN " + OWNER_NAME).toCharArray()),
			new BoxTextAll("ACTION".toCharArray()),
			new BoxTextAll("SET".toCharArray()),
			new BoxTextAll("DELETE".toCharArray()),
			new BoxTextAll("BACK".toCharArray())
		};

		this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[2 / 8] |= 1 << 2 % 8;

		if ((this.state & 4) == 0)
		{
			this.select = 6;
			this.state |= 4;
		}
	}

	@Override
	public void enter()
	{
		switch (this.select)
		{
			case 2:
				this.sendNet(SSIEOwner.B_FETCH);
				break;
			case 4:
				this.sendNet(SSIEOwner.B_SET);
				break;
			case 5:
				this.sendNet(SSIEOwner.B_DELETE);
				break;
			case 6:
				this.back();
				break;
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
		PageSmall.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 8];
		PageSmall.NET_BYTE_ARRAY[0] = SPageSI.ID;
		PageSmall.NET_BYTE_ARRAY[1] = SSIEOwner.ID;
		PageSmall.NET_BYTE_ARRAY[2] = b2;
		ByteWriter.set(PageSmall.NET_BYTE_ARRAY, PageMe.ID, 3);
		NetworkRegistry.I.sendToServer(new ServerMessage(PageSmall.NET_BYTE_ARRAY));
	}
}
