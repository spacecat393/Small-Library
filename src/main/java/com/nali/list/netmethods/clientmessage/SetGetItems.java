package com.nali.list.netmethods.clientmessage;

import com.nali.list.messages.ClientMessage;
import com.nali.small.gui.features.messages.works.GetItemGUIFeatures;

public class SetGetItems
{
    public static byte ID;

    public static void run(ClientMessage clientmessage)
    {
        GetItemGUIFeatures.STATE = clientmessage.data[1];
    }
}
