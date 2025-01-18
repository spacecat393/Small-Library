package com.nali.list.gui.da.client;

import com.nali.list.network.message.ClientMessage;
import com.nali.small.gui.page.entity.me.PageAttribute;

public class CDaAttribute
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageAttribute.STATE & 2) == 0)
		{
			PageAttribute.STATE |= 2;
			PageAttribute.BYTE_ARRAY = clientmessage.data;
			PageAttribute.STATE |= 4;
		}
	}
}
