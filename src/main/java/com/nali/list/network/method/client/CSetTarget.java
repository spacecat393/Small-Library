package com.nali.list.network.method.client;

import com.nali.list.network.message.ClientMessage;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.messages.inventory.TargetGUIFeatures;
import com.nali.system.bytes.ByteReader;

import static com.nali.small.gui.features.messages.inventory.TargetGUIFeatures.TARGET_INT_ARRAY;

public class CSetTarget
{
    public static byte ID;

    public static void run(ClientMessage clientmessage)
    {
        TARGET_INT_ARRAY = new int[(clientmessage.data.length - 1) / 4];
        int index = 0;
        for (int i = 1; i < clientmessage.data.length; i += 4)
        {
            TARGET_INT_ARRAY[index++] = ByteReader.getInt(clientmessage.data, i);
        }

        MixGui.GUIFEATURESLOADER = new TargetGUIFeatures(MixGui.I);
    }
}
