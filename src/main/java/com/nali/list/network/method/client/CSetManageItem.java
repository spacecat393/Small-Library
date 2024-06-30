package com.nali.list.network.method.client;

import com.nali.list.network.message.ClientMessage;
import com.nali.small.gui.features.messages.works.ManageItemGUIFeatures;
import com.nali.system.bytes.ByteReader;
import net.minecraft.util.math.BlockPos;

public class CSetManageItem
{
    public static byte ID;

    public static void run(ClientMessage clientmessage)
    {
        ManageItemGUIFeatures.STATE = clientmessage.data[1];
        ManageItemGUIFeatures.IN_BLOCKPOS = clientmessage.data[1 + 1] == -1 ? null : BlockPos.fromLong(ByteReader.getLong(clientmessage.data, 1 + 1));
        ManageItemGUIFeatures.OUT_BLOCKPOS = clientmessage.data[1 + 1 + 8] == -1 ? null : BlockPos.fromLong(ByteReader.getLong(clientmessage.data, 1 + 1 + 8));
        ManageItemGUIFeatures.RANDOM_AREA_IN = ByteReader.getInt(clientmessage.data, 1 + 1 + 8 + 8);
        ManageItemGUIFeatures.RANDOM_AREA_OUT = ByteReader.getInt(clientmessage.data, 1 + 1 + 8 + 8 + 4);
    }
}
