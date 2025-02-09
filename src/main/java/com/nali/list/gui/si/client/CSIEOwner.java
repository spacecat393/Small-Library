package com.nali.list.gui.si.client;

import com.nali.list.network.message.ClientMessage;
import com.nali.small.gui.page.entity.si.PageSIEOwner;

public class CSIEOwner
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageSIEOwner.STATE & 2) == 0)
		{
			PageSIEOwner.STATE |= 2;
			PageSIEOwner.OWNER_NAME = new String(clientmessage.data, 2, clientmessage.data.length - 2);
			PageSIEOwner.STATE |= 4;
		}
	}
}
