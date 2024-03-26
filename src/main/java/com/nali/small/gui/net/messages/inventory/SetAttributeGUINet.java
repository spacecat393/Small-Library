package com.nali.small.gui.net.messages.inventory;

import com.nali.list.netmethods.servermessage.SetAttribute;
import com.nali.small.Small;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.net.GUINetLoader;

public class SetAttributeGUINet extends GUINetLoader
{
    public SetAttributeGUINet(MixGui mixgui)
    {
        super(mixgui);
    }

    @Override
    public void run()
    {
        try
        {
            this.sendUUIDFloatBytes(SetAttribute.ID, (byte)2);
        }
        catch (Exception e)
        {
            Small.warn(e);
        }
    }
}
