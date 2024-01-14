package com.nali.list.messages;

import com.nali.small.networks.NetworksMessage;
import net.minecraftforge.fml.relauncher.Side;

public class CapabilitiesClientMessage extends NetworksMessage
{
    public static Side SIDE = Side.CLIENT;

    public CapabilitiesClientMessage()
    {

    }

    public CapabilitiesClientMessage(byte[] data)
    {
        this.data = data;
    }
}
