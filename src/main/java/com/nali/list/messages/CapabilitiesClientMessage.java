package com.nali.list.messages;

import com.nali.ilol.networks.NetworksMessage;
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
