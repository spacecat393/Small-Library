package com.nali.list.network.method.server;

import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.SetTarget;
import com.nali.list.network.handler.ServerHandler;
import com.nali.networks.NetworksRegistry;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.EntityLeInv;
import com.nali.system.bytes.BytesReader;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.small.entity.memo.server.ServerE.ENTITIES_MAP;

public class ViewTarget
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        EntityLeInv skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));

        if (skinningentities != null && ServerHandler.canPass(skinningentities, entityplayermp))
        {
            ServerE serverentitiesmemory = (ServerE)skinningentities.bothentitiesmemory;
            int size = serverentitiesmemory.entitiesaimemory.skinningentitiesarea.target_list.size() * 4;
            byte[] byte_array = new byte[1 + size];
            byte_array[0] = SetTarget.ID;
            int index = 0;
            for (int i = 1; i < size; i += 4)
            {
                BytesWriter.set(byte_array, serverentitiesmemory.entitiesaimemory.skinningentitiesarea.target_list.get(index++), i);
            }

            NetworksRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
        }
    }
}
