package com.nali.list.network.method.server;

import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.SetWorkBytes;
import com.nali.list.network.handler.ServerHandler;
import com.nali.networks.NetworksRegistry;
import com.nali.small.Small;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.EntityLeInv;
import com.nali.system.bytes.BytesReader;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.small.entity.memo.server.ServerE.ENTITIES_MAP;

public class OpenInvGUI
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        EntityLeInv skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && ServerHandler.canPass(skinningentities, entityplayermp))
        {
            entityplayermp.getEntityData().setUniqueId("loli_nali", skinningentities.getUniqueID());
            ServerE serverentitiesmemory = (ServerE)skinningentities.bothentitiesmemory;
            entityplayermp.openGui(Small.I, 0, entityplayermp.world, skinningentities.getEntityId(), 0, 0);

            byte[] byte_array = new byte[1 + 4 + serverentitiesmemory.main_work_byte_array.length];
            byte_array[0] = SetWorkBytes.ID;
            BytesWriter.set(byte_array, skinningentities.getEntityId(), 1);
            System.arraycopy(serverentitiesmemory.main_work_byte_array, 0, byte_array, 1 + 4, serverentitiesmemory.main_work_byte_array.length);
            NetworksRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
        }
    }
}
