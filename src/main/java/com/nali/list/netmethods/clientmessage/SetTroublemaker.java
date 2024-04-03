package com.nali.list.netmethods.clientmessage;

import com.nali.list.messages.ClientMessage;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.messages.inventory.TroublemakerGUIFeatures;
import com.nali.system.bytes.BytesReader;

import static com.nali.small.gui.features.messages.inventory.TroublemakerGUIFeatures.TROUBLEMAKER_INT_ARRAY;

public class SetTroublemaker
{
    public static byte ID;

    public static void run(ClientMessage clientmessage)
    {
        TROUBLEMAKER_INT_ARRAY = new int[(clientmessage.data.length - 1) / 4];
        int index = 0;
        for (int i = 1; i < clientmessage.data.length; i += 4)
        {
            TROUBLEMAKER_INT_ARRAY[index++] = BytesReader.getInt(clientmessage.data, i);
        }

        MixGui.GUIFEATURESLOADER = new TroublemakerGUIFeatures(MixGui.I);
    }
}
