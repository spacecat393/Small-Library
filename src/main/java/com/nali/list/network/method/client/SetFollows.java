package com.nali.list.network.method.client;

import com.nali.list.network.message.ClientMessage;
import com.nali.small.gui.features.messages.works.FollowGUIFeatures;
import com.nali.system.bytes.BytesReader;

public class SetFollows
{
    public static byte ID;

    public static void run(ClientMessage clientmessage)
    {
        FollowGUIFeatures.FLAG = clientmessage.data[1];
        FollowGUIFeatures.MAX_DISTANCE = BytesReader.getFloat(clientmessage.data, 1 + 1);
        FollowGUIFeatures.MIN_DISTANCE = BytesReader.getFloat(clientmessage.data, 1 + 1 + 4);
    }
}
