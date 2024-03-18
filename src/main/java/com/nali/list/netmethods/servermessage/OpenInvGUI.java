package com.nali.list.netmethods.servermessage;

import com.nali.list.messages.ClientMessage;
import com.nali.list.messages.ServerMessage;
import com.nali.small.Small;
import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.networks.NetworksRegistry;
import com.nali.system.bytes.BytesReader;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.handlers.ServerHandler.canPass;
import static com.nali.small.entities.memory.server.ServerEntitiesMemory.ENTITIES_MAP;

public class OpenInvGUI
{
    public static byte ID = 6;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        SkinningEntities skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            entityplayermp.getEntityData().setUniqueId("loli_nali", skinningentities.getUniqueID());
            ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)skinningentities.bothentitiesmemory;
            entityplayermp.openGui(Small.I, 0, entityplayermp.world, skinningentities.getEntityId(), 0, 0);

            byte[] byte_array = new byte[1 + 4 + serverentitiesmemory.main_work_byte_array.length];
            byte_array[0] = 6;
            BytesWriter.set(byte_array, skinningentities.getEntityId(), 1);
            System.arraycopy(serverentitiesmemory.main_work_byte_array, 0, byte_array, 1 + 4, serverentitiesmemory.main_work_byte_array.length);
            NetworksRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
        }
    }
}
