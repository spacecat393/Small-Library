package com.nali.small.gui.net.messages.inventory;

import com.nali.list.netmethods.servermessage.RemoveTroublemaker;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.net.GUINetLoader;

public class RemoveTroublemakerGUINet extends GUINetLoader
{
    public RemoveTroublemakerGUINet(MixGui mixgui)
    {
        super(mixgui);
    }

    @Override
    public void run()
    {
        this.sendUUIDIntBytes(RemoveTroublemaker.ID);
    }
}