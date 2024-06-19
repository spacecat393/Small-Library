package com.nali.list.network.method.server;

import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.SetGetItems;
import com.nali.networks.NetworksRegistry;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.EntityLeInv;
import com.nali.list.entity.ai.AILeInvGetItem;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.network.handler.ServerHandler.canPass;
import static com.nali.small.entity.memo.server.ServerE.ENTITIES_MAP;

public class FetchGetItem
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        EntityLeInv skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            fetch(entityplayermp, ((ServerE)skinningentities.bothentitiesmemory).entitiesaimemory.skinningentitiesgetitem);
        }
    }

    public static void fetch(EntityPlayerMP entityplayermp, AILeInvGetItem skinningentitiesgetitem)
    {
        byte[] byte_array = new byte[1 + 1];
        byte_array[0] = SetGetItems.ID;
        byte_array[1] = skinningentitiesgetitem.flag;
        NetworksRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
    }
}
