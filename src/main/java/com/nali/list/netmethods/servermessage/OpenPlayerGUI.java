package com.nali.list.netmethods.servermessage;

import com.nali.list.messages.ServerMessage;
import com.nali.small.Small;
import net.minecraft.entity.player.EntityPlayerMP;

public class OpenPlayerGUI
{
    public static byte ID = 9;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
//        entityplayermp.closeScreen();
//        NetworksRegistry.I.sendTo(new ClientMessage(new byte[]{2}), entityplayermp);
        entityplayermp.openGui(Small.I, 1, entityplayermp.world, 0, 0, 0);
    }
}