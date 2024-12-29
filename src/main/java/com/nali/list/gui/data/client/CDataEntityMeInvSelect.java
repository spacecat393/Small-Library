package com.nali.list.gui.data.client;

import com.nali.list.network.message.ClientMessage;
import com.nali.small.gui.page.PageEntityMeInvSelect;

public class CDataEntityMeInvSelect
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageEntityMeInvSelect.STATE & 2) == 0)
		{
			PageEntityMeInvSelect.STATE |= 2;
			PageEntityMeInvSelect.BYTE_ARRAY = clientmessage.data;
			PageEntityMeInvSelect.STATE |= 4;
		}
	}
}
