package com.nali.list.gui.data.client;

import com.nali.list.network.message.ClientMessage;
import com.nali.small.gui.page.PageInvSelectAdd;

public class CDataInvSelectAdd
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageInvSelectAdd.STATE & 2) == 0)
		{
			PageInvSelectAdd.STATE |= 2;
			PageInvSelectAdd.STATE |= 4;
		}
	}
}
