package com.nali.list.network.method.server;

import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.CSetArea;
import com.nali.networks.NetworksRegistry;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.EntityLeInv;
import com.nali.list.entity.ai.AIEArea;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.network.handler.ServerHandler.canPass;
import static com.nali.small.entity.memo.server.ServerE.ENTITIES_MAP;

public class SFetchArea
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        EntityLeInv skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            fetch(entityplayermp, ((ServerE)skinningentities.bothentitiesmemory).entitiesaimemory.skinningentitiesarea);
        }
    }

    public static void fetch(EntityPlayerMP entityplayermp, AIEArea skinningentitiesarea)
    {
        byte[] byte_array = new byte[1 + 1];
        byte_array[0] = CSetArea.ID;
        byte_array[1] = skinningentitiesarea.flag;
        NetworksRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
    }
}
