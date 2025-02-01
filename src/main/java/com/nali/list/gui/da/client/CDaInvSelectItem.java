package com.nali.list.gui.da.client;

import com.nali.list.network.message.ClientMessage;
import com.nali.small.gui.page.inv.select.item.PageItem;
import com.nali.system.bytes.ByteReader;

public class CDaInvSelectItem
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
//		if ((PageItem.STATE & 2) == 0)
//		{
//			PageItem.STATE |= 2;
//			PageItem.BYTE_ARRAY = clientmessage.data;
		PageItem.ITEM_SIZE = ByteReader.getLong(clientmessage.data, 2);
		PageItem.STATE |= 4;
//		}
	}
}
