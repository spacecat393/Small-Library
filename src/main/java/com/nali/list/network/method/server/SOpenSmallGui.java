package com.nali.list.network.method.server;

import com.nali.list.container.SmallContainer;
import com.nali.list.network.message.ServerMessage;
import com.nali.small.Small;
import net.minecraft.entity.player.EntityPlayerMP;

public class SOpenSmallGui
{
	public static byte ID;

	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
	{
////		entityplayermp.closeScreen();
////		NetworksRegistry.I.sendTo(new ClientMessage(new byte[]{2}), entityplayermp);
//		entityplayermp.openGui(Small.I, PlayerContainer.ID, entityplayermp.world, 0, 0, 0);
		entityplayermp.openGui(Small.I, SmallContainer.ID, entityplayermp.world, -1, 0, 0);
	}
}