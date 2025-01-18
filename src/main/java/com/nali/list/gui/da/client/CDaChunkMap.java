package com.nali.list.gui.da.client;

import com.nali.list.network.message.ClientMessage;
import com.nali.small.gui.page.chunk.PageMap;

public class CDaChunkMap
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageMap.STATE & 2) == 0)
		{
			PageMap.STATE |= 2;
			PageMap.BYTE_ARRAY = clientmessage.data;
			PageMap.STATE |= 4;
		}
	}
}
