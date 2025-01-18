package com.nali.list.gui.da.client;

import com.nali.list.network.message.ClientMessage;
import com.nali.small.gui.page.chunk.PageList;

public class CDaChunkList
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageList.STATE & 2) == 0)
		{
			PageList.STATE |= 2;
			PageList.BYTE_ARRAY = clientmessage.data;
			PageList.STATE |= 4;
		}
	}
}
