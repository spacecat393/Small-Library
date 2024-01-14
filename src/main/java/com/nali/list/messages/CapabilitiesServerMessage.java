package com.nali.list.messages;

import com.nali.small.networks.NetworksMessage;
import net.minecraftforge.fml.relauncher.Side;

public class CapabilitiesServerMessage extends NetworksMessage
{
    public static Side SIDE = Side.SERVER;

    public CapabilitiesServerMessage()
    {

    }

    public CapabilitiesServerMessage(byte[] data)
    {
        this.data = data;
    }
}
