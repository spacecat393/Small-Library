package com.nali.list.netmethods.servermessage;

import com.nali.list.messages.ClientMessage;
import com.nali.list.messages.ServerMessage;
import com.nali.small.networks.NetworksRegistry;
import net.minecraft.entity.player.EntityPlayerMP;

public class CloseScreen
{
    public static int ID = 9;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        entityplayermp.closeScreen();
        NetworksRegistry.I.sendTo(new ClientMessage(new byte[]{2}), entityplayermp);
    }
}