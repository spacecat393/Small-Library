package com.nali.list.netmethods.clientmessage;

import com.nali.list.messages.ClientMessage;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.messages.inventory.TargetGUIFeatures;
import com.nali.system.bytes.BytesReader;

import static com.nali.small.gui.features.messages.inventory.TargetGUIFeatures.TARGET_INT_ARRAY;

public class SetTarget
{
    public static int ID = 3;

    public static void run(ClientMessage clientmessage)
    {
        TARGET_INT_ARRAY = new int[(clientmessage.data.length - 1) / 4];
        int index = 0;
        for (int i = 1; i < clientmessage.data.length; i += 4)
        {
            TARGET_INT_ARRAY[index++] = BytesReader.getInt(clientmessage.data, i);
        }

        MixGui.GUIFEATURESLOADER = new TargetGUIFeatures(MixGui.I);
    }
}
