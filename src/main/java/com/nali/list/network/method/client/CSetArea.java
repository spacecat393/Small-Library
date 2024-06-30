package com.nali.list.network.method.client;

import com.nali.list.network.message.ClientMessage;
import com.nali.small.gui.features.messages.inventory.AreaGUIFeatures;

public class CSetArea
{
    public static byte ID;

    public static void run(ClientMessage clientmessage)
    {
        AreaGUIFeatures.FLAG = clientmessage.data[1];
    }
}
