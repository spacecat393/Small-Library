package com.nali.list.gui.si.client;

import com.nali.list.network.message.ClientMessage;
import com.nali.small.gui.page.entity.si.PageSIEArea;

public class CSIEArea
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageSIEArea.STATE & 2) == 0)
		{
			PageSIEArea.STATE |= 2;
			PageSIEArea.FLAG = clientmessage.data[2];
//			PageSIEArea.BYTE_ARRAY = clientmessage.data;
			PageSIEArea.STATE |= 4;
		}
	}
}
