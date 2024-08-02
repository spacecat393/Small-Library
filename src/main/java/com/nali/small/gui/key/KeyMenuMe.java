package com.nali.small.gui.key;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.list.container.gui.SmallGui.FLAG;
import static com.nali.list.container.gui.SmallGui.SMALLGUI;
import static com.nali.small.gui.mouse.MouseSmall.openPageArmy;

@SideOnly(Side.CLIENT)
public class KeyMenuMe extends KeyMenu
{
    public static StringBuffer STRINGBUFFER;
    public static byte ME;//from_army

    public KeyMenuMe()
    {
        STRINGBUFFER = new StringBuffer();
    }

    @Override
    public void run()
    {
        if ((STATE & 1) == 1)
        {
            if ((ME & 1) == 1)
            {
                openPageArmy();
            }
            else
            {
                SMALLGUI.defaultPage();
            }
        }
        if ((STATE & 2) == 2)
        {
            FLAG |= 1;
            STATE &= 255-2;
        }
    }

    @Override
    public void detect(char typedChar, int keyCode)
    {
        super.detect(typedChar, keyCode);
        if ((STATE & 4) == 4)
        {
            STRINGBUFFER.append(typedChar);
        }
    }
}
