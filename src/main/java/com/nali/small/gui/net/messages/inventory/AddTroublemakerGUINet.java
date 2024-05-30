package com.nali.small.gui.net.messages.inventory;

import com.nali.list.netmethods.servermessage.AddTroublemaker;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.net.GUINetLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class AddTroublemakerGUINet extends GUINetLoader
{
    public AddTroublemakerGUINet(MixGui mixgui)
    {
        super(mixgui);
    }

    @Override
    public void run()
    {
        this.sendUUIDIntBytes(AddTroublemaker.ID);
    }
}