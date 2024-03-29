package com.nali.small.gui.net.messages.inventory;

import com.nali.list.netmethods.servermessage.AddTarget;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.net.GUINetLoader;

public class AddTargetGUINet extends GUINetLoader
{
    public AddTargetGUINet(MixGui mixgui)
    {
        super(mixgui);
    }

    @Override
    public void run()
    {
        this.sendUUIDIntBytes(AddTarget.ID);
    }
}
