package com.nali.small.gui.net.messages.inventory;

import com.nali.list.netmethods.servermessage.Scale;
import com.nali.small.Small;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.net.GUINetLoader;

public class ScaleGUINet extends GUINetLoader
{
    public ScaleGUINet(MixGui mixgui)
    {
        super(mixgui);
    }

    @Override
    public void run()
    {
        try
        {
            this.sendUUIDFloatBytes(Scale.ID, (byte)2);
        }
        catch (Exception e)
        {
            Small.warn(e);
        }
    }
}
