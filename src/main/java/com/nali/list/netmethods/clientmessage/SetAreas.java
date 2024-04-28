package com.nali.list.netmethods.clientmessage;

import com.nali.list.messages.ClientMessage;
import com.nali.small.gui.features.messages.inventory.AreaGUIFeatures;

public class SetAreas
{
    public static byte ID;

    public static void run(ClientMessage clientmessage)
    {
        AreaGUIFeatures.FLAG = clientmessage.data[1];
    }
}
