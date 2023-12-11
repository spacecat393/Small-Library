package com.nali.list.messages;

import com.nali.ilol.networks.NetworksMessage;
import net.minecraftforge.fml.relauncher.Side;

public class OpenGUIMessage extends NetworksMessage
{
    public static Side SIDE = Side.CLIENT;
    public OpenGUIMessage()
    {

    }

    public OpenGUIMessage(byte[] data)
    {
        this.data = data;
    }
}
