package com.nali.list.network.method.client;

import com.nali.list.network.message.ClientMessage;
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
