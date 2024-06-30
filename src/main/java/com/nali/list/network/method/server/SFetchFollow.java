package com.nali.list.network.method.server;

import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.CSetFollow;
import com.nali.list.network.handler.ServerHandler;
import com.nali.networks.NetworksRegistry;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.EntityLeInv;
import com.nali.list.entity.ai.AILeFollow;
import com.nali.system.bytes.BytesReader;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.small.entity.memo.server.ServerE.ENTITIES_MAP;

public class SFetchFollow
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        EntityLeInv skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && ServerHandler.canPass(skinningentities, entityplayermp))
        {
            fetch(entityplayermp, ((ServerE)skinningentities.bothentitiesmemory).entitiesaimemory.skinningentitiesfollow);
        }
    }

    public static void fetch(EntityPlayerMP entityplayermp, AILeFollow skinningentitiesfollow)
    {
        byte[] byte_array = new byte[1 + 1 + 4 + 4];
        byte_array[0] = CSetFollow.ID;
        byte_array[1] = skinningentitiesfollow.flag;
        BytesWriter.set(byte_array, skinningentitiesfollow.min_distance, 1 + 1);
        BytesWriter.set(byte_array, skinningentitiesfollow.max_distance, 1 + 1 + 4);
        NetworksRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
    }
}
