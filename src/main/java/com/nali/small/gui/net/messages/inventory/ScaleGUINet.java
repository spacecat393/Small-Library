package com.nali.small.gui.net.messages.inventory;

import com.nali.list.network.method.server.Scale;
import com.nali.small.Small;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.net.GUINetLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
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
