package com.nali.small.gui.net.messages.player;

import com.nali.list.netmethods.servermessage.DropToSakura;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.net.GUINetLoader;

public class DropToSakuraGUINet extends GUINetLoader
{
    public DropToSakuraGUINet(MixGui mixgui)
    {
        super(mixgui);
    }

    @Override
    public void run()
    {
        this.sendIntBytes(DropToSakura.ID);
    }
}
