package com.nali.list.network.method.server;

import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.CSetAttack;
import com.nali.networks.NetworksRegistry;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.EntityLeInv;
import com.nali.list.entity.ai.AILeAttack;
import com.nali.system.bytes.BytesReader;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.network.handler.ServerHandler.canPass;
import static com.nali.small.entity.memo.server.ServerE.ENTITIES_MAP;

public class SFetchAttack
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        EntityLeInv skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            fetch(entityplayermp, ((ServerE)skinningentities.bothentitiesmemory).entitiesaimemory.skinningentitiesattack);
        }
    }

    public static void fetch(EntityPlayerMP entityplayermp, AILeAttack skinningentitiesattack)
    {
        byte[] byte_array = new byte[1 + 1 + 4 + 4];
        byte_array[0] = CSetAttack.ID;
        byte_array[1] = skinningentitiesattack.flag;
        BytesWriter.set(byte_array, skinningentitiesattack.minimum_distance, 1 + 1);
        BytesWriter.set(byte_array, skinningentitiesattack.max_magic_point, 1 + 1 + 4);
        NetworksRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
    }
}
