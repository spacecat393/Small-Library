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
    @Override
    public void run()
    {
        if ((this.state & 1) == 1)
        {
            SMALLGUI.defaultPage();
//            super.run();
        }
        if ((this.state & 2) == 2)
        {
            FLAG |= 1;
            this.state &= 255-2;
        }
    }

    @Override
    public void detect(char typedChar, int keyCode)
    {
        super.detect(typedChar, keyCode);
        if (typedChar == 'r')
        {
            PageMenuArmy.BT27 = 5.0F;
            NetworkRegistry.I.sendToServer(new ServerMessage(new byte[]{SSToC.ID}));
            this.state |= 2;
        }
    }
}
