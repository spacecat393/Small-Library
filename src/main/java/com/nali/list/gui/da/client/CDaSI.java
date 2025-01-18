package com.nali.list.gui.da.client;

import com.nali.list.network.message.ClientMessage;
import com.nali.small.gui.page.entity.me.PageSI;

public class CDaSI
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageSI.STATE & 2) == 0)
		{
			PageSI.STATE |= 2;
			PageSI.BYTE_ARRAY = clientmessage.data;
			PageSI.STATE |= 4;
		}
	}
}
