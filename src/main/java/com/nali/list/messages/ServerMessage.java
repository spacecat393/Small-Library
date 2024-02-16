package com.nali.list.messages;

import com.nali.small.networks.NetworksMessage;
import net.minecraftforge.fml.relauncher.Side;

public class ServerMessage extends NetworksMessage
{
    public static Side SIDE = Side.SERVER;
    public ServerMessage()
    {

    }

    public ServerMessage(byte[] data)
    {
        this.data = data;
    }
}
