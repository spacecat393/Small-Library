package com.nali.small.gui.key;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.small.gui.page.PageMe.openPageMe;

@SideOnly(Side.CLIENT)
public class KeyMenuAI extends KeyMenu
{
    @Override
    public void run()
    {
        if ((STATE & 1) == 1)
        {
            openPageMe();
        }
    }
}
