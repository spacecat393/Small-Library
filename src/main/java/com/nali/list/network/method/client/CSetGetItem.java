package com.nali.list.network.method.client;

import com.nali.list.network.message.ClientMessage;
import com.nali.small.gui.features.messages.works.GetItemGUIFeatures;

public class CSetGetItem
{
    public static byte ID;

    public static void run(ClientMessage clientmessage)
    {
        GetItemGUIFeatures.STATE = clientmessage.data[1];
    }
}
