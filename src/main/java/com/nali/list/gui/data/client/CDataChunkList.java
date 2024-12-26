package com.nali.list.gui.data.client;

import com.nali.list.network.message.ClientMessage;
import com.nali.small.gui.page.PageChunkList;

public class CDataChunkList
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageChunkList.STATE & 2) == 0)
		{
			PageChunkList.STATE |= 2;
			PageChunkList.BYTE_ARRAY = clientmessage.data;
			PageChunkList.STATE |= 4;
		}
	}
}
