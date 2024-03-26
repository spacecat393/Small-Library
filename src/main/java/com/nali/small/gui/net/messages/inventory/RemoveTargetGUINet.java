package com.nali.small.gui.net.messages.inventory;

import com.nali.list.netmethods.servermessage.RemoveTarget;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.net.GUINetLoader;

public class RemoveTargetGUINet extends GUINetLoader
{
    public RemoveTargetGUINet(MixGui mixgui)
    {
        super(mixgui);
    }

    @Override
    public void run()
    {
        this.sendUUIDIntBytes(RemoveTarget.ID);
    }
}