package com.nali.list.messages;

import com.nali.networks.NetworksMessage;
import net.minecraftforge.fml.relauncher.Side;

public class ClientMessage extends NetworksMessage
{
    public static Side SIDE = Side.CLIENT;
    public ClientMessage()
    {

    }

    public ClientMessage(byte[] data)
    {
        this.data = data;
    }
}
