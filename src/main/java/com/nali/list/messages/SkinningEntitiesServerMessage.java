package com.nali.list.messages;

import com.nali.small.networks.NetworksMessage;
import net.minecraftforge.fml.relauncher.Side;

public class SkinningEntitiesServerMessage extends NetworksMessage
{
    public static Side SIDE = Side.SERVER;
    public SkinningEntitiesServerMessage()
    {

    }

    public SkinningEntitiesServerMessage(byte[] data)
    {
        this.data = data;
    }
}
