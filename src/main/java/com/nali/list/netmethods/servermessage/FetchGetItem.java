package com.nali.list.netmethods.servermessage;

import com.nali.list.messages.ClientMessage;
import com.nali.list.messages.ServerMessage;
import com.nali.list.netmethods.clientmessage.SetGetItems;
import com.nali.networks.NetworksRegistry;
import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.ai.SkinningEntitiesGetItem;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.handlers.ServerHandler.canPass;
import static com.nali.small.entities.memory.server.ServerEntitiesMemory.ENTITIES_MAP;

public class FetchGetItem
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        SkinningEntities skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            fetch(entityplayermp, ((ServerEntitiesMemory)skinningentities.bothentitiesmemory).entitiesaimemory.skinningentitiesgetitem);
        }
    }

    public static void fetch(EntityPlayerMP entityplayermp, SkinningEntitiesGetItem skinningentitiesgetitem)
    {
        byte[] byte_array = new byte[1 + 1];
        byte_array[0] = SetGetItems.ID;
        byte_array[1] = skinningentitiesgetitem.flag;
        NetworksRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
    }
}
