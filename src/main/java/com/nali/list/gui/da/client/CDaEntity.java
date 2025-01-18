package com.nali.list.gui.da.client;

import com.nali.list.network.message.ClientMessage;
import com.nali.small.gui.page.entity.PageEntity;

public class CDaEntity
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageEntity.STATE & 2) == 0)
		{
			PageEntity.STATE |= 2;
			PageEntity.BYTE_ARRAY = clientmessage.data;
			PageEntity.STATE |= 4;
		}
	}
}
