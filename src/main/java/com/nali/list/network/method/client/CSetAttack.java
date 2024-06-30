package com.nali.list.network.method.client;

import com.nali.list.network.message.ClientMessage;
import com.nali.small.gui.features.messages.works.AttackGUIFeatures;
import com.nali.system.bytes.ByteReader;

public class CSetAttack
{
    public static byte ID;

    public static void run(ClientMessage clientmessage)
    {
        AttackGUIFeatures.FLAG = clientmessage.data[1];
        AttackGUIFeatures.MINIMUM_DISTANCE = ByteReader.getFloat(clientmessage.data, 1 + 1);
        AttackGUIFeatures.MAX_MAGIC_POINT = ByteReader.getInt(clientmessage.data, 1 + 1 + 4);
    }
}
