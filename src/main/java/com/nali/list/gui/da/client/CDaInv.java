package com.nali.list.gui.da.client;

import com.nali.list.network.message.ClientMessage;
import com.nali.small.gui.page.inv.PageInv;

public class CDaInv
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageInv.STATE & 2) == 0)
		{
			PageInv.STATE |= 2;
			PageInv.BYTE_ARRAY = clientmessage.data;
			PageInv.STATE |= 4;
		}
	}
}
