package com.nali.small.gui.key;

import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SSToC;
import com.nali.network.NetworkRegistry;
import com.nali.small.gui.page.PageMenuArmy;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.list.container.gui.SmallGui.FLAG;
import static com.nali.list.container.gui.SmallGui.SMALLGUI;

@SideOnly(Side.CLIENT)
public class KeyMenuArmy extends KeyMenu
{
    public static StringBuffer STRINGBUFFER;

    public KeyMenuArmy()
    {
        STRINGBUFFER = new StringBuffer();
    }

    @Override
    public void run()
    {
        if ((STATE & 1) == 1)
        {
            SMALLGUI.defaultPage();
//            super.run();
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
        else if (typedChar == 'r')
        {
            PageMenuArmy.BT27 = 5.0F;
            NetworkRegistry.I.sendToServer(new ServerMessage(new byte[]{SSToC.ID}));
            STATE |= 2;
        }
    }
}
