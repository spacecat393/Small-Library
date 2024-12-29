package com.nali.list.gui.data.client;

import com.nali.list.network.message.ClientMessage;
import com.nali.small.gui.page.PageEntityMeInv;

public class CDataEntityMeInv
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageEntityMeInv.STATE & 2) == 0)
		{
			PageEntityMeInv.STATE |= 2;
			PageEntityMeInv.BYTE_ARRAY = clientmessage.data;
			PageEntityMeInv.STATE |= 4;
		}
	}
}
