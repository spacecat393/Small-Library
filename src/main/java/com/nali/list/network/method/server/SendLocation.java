package com.nali.list.network.method.server;

import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.SetLocation;
import com.nali.networks.NetworksRegistry;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.EntityLeInv;
import com.nali.system.bytes.BytesReader;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.network.handler.ServerHandler.canPass;
import static com.nali.small.entity.memo.server.ServerE.ENTITIES_MAP;

public class SendLocation
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        EntityLeInv skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            ServerE serverentitiesmemory = (ServerE)skinningentities.bothentitiesmemory;
            byte[] byte_array = new byte[1 + 8 + 4];
            byte_array[0] = SetLocation.ID;
            BytesWriter.set(byte_array, serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.blockpos_long, 1);
            BytesWriter.set(byte_array, serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.far, 1 + 8);
            NetworksRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
        }
    }
}
