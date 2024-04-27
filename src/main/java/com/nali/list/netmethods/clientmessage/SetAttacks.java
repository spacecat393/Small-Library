package com.nali.list.netmethods.clientmessage;

import com.nali.list.messages.ClientMessage;
import com.nali.small.gui.features.messages.works.AttackGUIFeatures;
import com.nali.system.bytes.BytesReader;

public class SetAttacks
{
    public static byte ID;

    public static void run(ClientMessage clientmessage)
    {
        AttackGUIFeatures.FLAG = clientmessage.data[1];
        AttackGUIFeatures.MINIMUM_DISTANCE = BytesReader.getFloat(clientmessage.data, 1 + 1);
    }
}
