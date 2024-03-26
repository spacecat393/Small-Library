package com.nali.small.gui.net.messages.inventory;

import com.nali.list.netmethods.servermessage.SetXYZ;
import com.nali.small.Small;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.net.GUINetLoader;

public class SetXYZGUINet extends GUINetLoader
{
    public SetXYZGUINet(MixGui mixgui)
    {
        super(mixgui);
    }

    @Override
    public void run()
    {
        try
        {
            this.sendUUIDFloatBytes(SetXYZ.ID, (byte)4);
        }
        catch (Exception e)
        {
            Small.warn(e);
        }
    }
}
