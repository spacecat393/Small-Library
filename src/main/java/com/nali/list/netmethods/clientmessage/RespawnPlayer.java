package com.nali.list.netmethods.clientmessage;

import com.nali.list.messages.ClientMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class RespawnPlayer
{
    public static byte ID = 1;

    public static void run(ClientMessage clientmessage)
    {
        Minecraft minecraft = Minecraft.getMinecraft();
        EntityPlayerSP entityplayersp = minecraft.player;
        entityplayersp.respawnPlayer();
////                entityplayersp.isDead = false;
////                entityplayersp.deathTime = 0;
//////                entityplayersp.respawnPlayer();
////                entityplayersp.setHealth(entityplayersp.getMaxHealth());
//////                entityplayersp.world.spawnEntity(entityplayersp);
////                entityplayersp.respawnPlayer();
////                entityplayersp.setPosition(BytesReader.getFloat(clientmessage.data, 1), BytesReader.getFloat(clientmessage.data, 1 + 4), BytesReader.getFloat(clientmessage.data, 1 + 4 + 4));
//                minecraft.addScheduledTask(() ->
//                {
//                    minecraft.displayGuiScreen(null);
//                });
    }
}
