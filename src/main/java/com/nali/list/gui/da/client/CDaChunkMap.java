package com.nali.list.gui.da.client;

import com.nali.list.network.message.ClientMessage;
import com.nali.small.gui.page.PageChunkMap;

public class CDaChunkMap
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageChunkMap.STATE & 2) == 0)
		{
			PageChunkMap.STATE |= 2;
			PageChunkMap.BYTE_ARRAY = clientmessage.data;
			PageChunkMap.STATE |= 4;
		}
	}
}
