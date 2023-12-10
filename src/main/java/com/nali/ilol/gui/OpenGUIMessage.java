package com.nali.ilol.gui;

import com.nali.ilol.networks.NetworksMessage;

public class OpenGUIMessage extends NetworksMessage
{
    public OpenGUIMessage()
    {

    }

    public OpenGUIMessage(byte[] data)
    {
        this.data = data;
    }
}
