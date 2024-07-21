package com.nali.small.gui.key;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.list.container.gui.SmallGui.SMALLGUI;

@SideOnly(Side.CLIENT)
public class KeyMenuArmy extends KeyMenu
{
    @Override
    public void run()
    {
        if (this.result)
        {
            SMALLGUI.defaultPage();
//            super.run();
        }
    }
}
