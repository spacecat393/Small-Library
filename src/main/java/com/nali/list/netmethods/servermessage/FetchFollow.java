package com.nali.list.netmethods.servermessage;

import com.nali.list.messages.ClientMessage;
import com.nali.list.messages.ServerMessage;
import com.nali.list.netmethods.clientmessage.SetFollows;
import com.nali.networks.NetworksRegistry;
import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.ai.SkinningEntitiesFollow;
import com.nali.system.bytes.BytesReader;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.handlers.ServerHandler.canPass;
import static com.nali.small.entities.memory.server.ServerEntitiesMemory.ENTITIES_MAP;

public class FetchFollow
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        SkinningEntities skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            fetch(entityplayermp, ((ServerEntitiesMemory)skinningentities.bothentitiesmemory).entitiesaimemory.skinningentitiesfollow);
        }
    }

    public static void fetch(EntityPlayerMP entityplayermp, SkinningEntitiesFollow skinningentitiesfollow)
    {
        byte[] byte_array = new byte[1 + 1 + 4 + 4];
        byte_array[0] = SetFollows.ID;
        byte_array[1] = skinningentitiesfollow.flag;
        BytesWriter.set(byte_array, skinningentitiesfollow.min_distance, 1 + 1);
        BytesWriter.set(byte_array, skinningentitiesfollow.max_distance, 1 + 1 + 4);
        NetworksRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
    }
}
