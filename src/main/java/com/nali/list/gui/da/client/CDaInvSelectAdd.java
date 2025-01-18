package com.nali.list.gui.da.client;

import com.nali.list.network.message.ClientMessage;
import com.nali.small.gui.page.inv.select.PageAdd;

public class CDaInvSelectAdd
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageAdd.STATE & 2) == 0)
		{
			PageAdd.STATE |= 2;
			PageAdd.STATE |= 4;
		}
	}
}
