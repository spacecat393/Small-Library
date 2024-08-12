package com.nali.small.gui.net.messages.inventory;

import com.nali.small.gui.MixGui;
import com.nali.small.gui.net.GUINetLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class AddTargetGUINet extends GUINetLoader
{
    public AddTargetGUINet(MixGui mixgui)
    {
        super(mixgui);
    }

    @Override
    public void run()
    {
        this.sendUUIDIntBytes(SAddTarget.ID);
    }
}
