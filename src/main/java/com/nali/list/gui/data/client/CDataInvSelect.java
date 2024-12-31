package com.nali.list.gui.data.client;

import com.nali.list.network.message.ClientMessage;
import com.nali.small.gui.page.PageInvSelect;

public class CDataInvSelect
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageInvSelect.STATE & 2) == 0)
		{
			PageInvSelect.STATE |= 2;
			PageInvSelect.BYTE_ARRAY = clientmessage.data;
			PageInvSelect.STATE |= 4;
		}
	}
}
