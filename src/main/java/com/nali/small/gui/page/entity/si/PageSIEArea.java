package com.nali.small.gui.page.entity.si;

import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.page.PageSelect;
import com.nali.list.gui.si.server.SSIEArea;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SPageSI;
import com.nali.network.NetworkRegistry;
import com.nali.small.gui.page.PageSmall;
import com.nali.small.gui.page.entity.me.PageMe;
import com.nali.system.bytes.ByteWriter;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageSIEArea extends PageSelect
{
	public static byte
		STATE,//enter client init
		FLAG;//check_tameable is_tameable | put_player put_owner put_other_tameable put_owner_tameable put_all_tameable put_object

	@Override
	public void init()
	{
		this.boxtextall_array = new BoxTextAll[]
		{
			new BoxTextAll("SIE-AREA".toCharArray()),
			new BoxTextAll("FLAG".toCharArray()),
			new BoxTextAll(("PUT_PLAYER " + ((FLAG & 4) == 4)).toCharArray()),
			new BoxTextAll(("PUT_OWNER " + ((FLAG & 8) == 8)).toCharArray()),
			new BoxTextAll(("PUT_OTHER_TAMEABLE " + ((FLAG & 16) == 16)).toCharArray()),
			new BoxTextAll(("PUT_OWNER_TAMEABLE " + ((FLAG & 32) == 32)).toCharArray()),
			new BoxTextAll(("PUT_ALL_TAMEABLE " + ((FLAG & 64) == 64)).toCharArray()),
			new BoxTextAll(("PUT_OBJECT " + ((FLAG & 128) == 128)).toCharArray()),
			new BoxTextAll("ACTION".toCharArray()),
			new BoxTextAll("FETCH".toCharArray()),
			new BoxTextAll("BACK".toCharArray())
		};

		this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[7 / 8] |= 1 << 7 % 8;

		if ((this.state & 4) == 0)
		{
			this.select = 9;
			this.state |= 4;
		}
	}

	@Override
	public void enter()
	{
		switch (this.select)
		{
			case 2:
				this.sendNet(SSIEArea.I_PUT_PLAYER);
				break;
			case 3:
				this.sendNet(SSIEArea.I_PUT_OWNER);
				break;
			case 4:
				this.sendNet(SSIEArea.I_PUT_OTHER_TAMEABLE);
				break;
			case 5:
				this.sendNet(SSIEArea.I_PUT_OWNER_TAMEABLE);
				break;
			case 6:
				this.sendNet(SSIEArea.I_PUT_ALL_TAMEABLE);
				break;
			case 7:
				this.sendNet(SSIEArea.I_PUT_OBJECT);
				break;
			case 9:
				this.sendNet(SSIEArea.I_FETCH);
				break;
			case 10:
				this.back();
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
		PageSmall.NET_BYTE_ARRAY[1] = SSIEArea.ID;
		PageSmall.NET_BYTE_ARRAY[2] = b2;
		ByteWriter.set(PageSmall.NET_BYTE_ARRAY, PageMe.ID, 3);
		NetworkRegistry.I.sendToServer(new ServerMessage(PageSmall.NET_BYTE_ARRAY));
	}
}
