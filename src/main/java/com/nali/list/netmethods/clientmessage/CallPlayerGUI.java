package com.nali.list.netmethods.clientmessage;

import com.nali.list.messages.ClientMessage;

import static com.nali.small.gui.OpenGUIHelper.callPlayerGUI;

public class CallPlayerGUI
{
    public static int ID = 2;

    public static void run(ClientMessage clientmessage)
    {
        callPlayerGUI();
    }
}
