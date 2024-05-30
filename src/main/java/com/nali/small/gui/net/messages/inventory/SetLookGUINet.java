package com.nali.small.gui.net.messages.inventory;

import com.nali.list.netmethods.servermessage.SetLook;
import com.nali.small.Small;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.net.GUINetLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SetLookGUINet extends GUINetLoader
{
    public SetLookGUINet(MixGui mixgui)
    {
        super(mixgui);
    }

    @Override
    public void run()
    {
        try
        {
            this.sendUUIDFloatBytes(SetLook.ID, (byte)2);
        }
        catch (Exception e)
        {
            Small.warn(e);
        }
    }
}
