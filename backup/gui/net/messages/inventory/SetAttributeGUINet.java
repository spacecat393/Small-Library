package com.nali.small.gui.net.messages.inventory;

import com.nali.list.network.method.server.SSetAttribute;
import com.nali.small.Small;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.net.GUINetLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
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
            this.sendUUIDFloatBytes(SSetAttribute.ID, (byte)2);
        }
        catch (Exception e)
        {
            Small.warn(e);
        }
    }
}
