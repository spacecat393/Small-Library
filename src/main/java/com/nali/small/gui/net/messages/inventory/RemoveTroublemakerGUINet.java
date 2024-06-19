package com.nali.small.gui.net.messages.inventory;

import com.nali.list.network.method.server.RemoveTroublemaker;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.net.GUINetLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
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