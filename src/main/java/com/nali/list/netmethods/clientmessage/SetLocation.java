package com.nali.list.netmethods.clientmessage;

import com.nali.list.messages.ClientMessage;
import com.nali.small.gui.features.messages.inventory.SetLocationGUIFeatures;
import com.nali.system.bytes.BytesReader;
import net.minecraft.util.math.BlockPos;

public class SetLocation
{
    public static byte ID;

    public static void run(ClientMessage clientmessage)
    {
        SetLocationGUIFeatures.BLOCKPOS = BlockPos.fromLong(BytesReader.getLong(clientmessage.data, 1));
        SetLocationGUIFeatures.FAR = BytesReader.getFloat(clientmessage.data, 1 + 8);
    }
}
