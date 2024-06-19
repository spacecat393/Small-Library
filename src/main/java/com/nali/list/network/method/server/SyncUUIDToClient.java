package com.nali.list.network.method.server;

import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.SetUUID;
import com.nali.networks.NetworksRegistry;
import com.nali.small.entity.EntityLeInv;
import com.nali.system.bytes.BytesReader;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class SyncUUIDToClient
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        int id = BytesReader.getInt(servermessage.data, 1);
        Entity entity = entityplayermp.world.getEntityByID(id);
        if (entity instanceof EntityLeInv)
        {
            byte[] byte_array = new byte[1 + 16 + 4];
            byte_array[0] = SetUUID.ID;
            BytesWriter.set(byte_array, entity.getUniqueID(), 1);
            BytesWriter.set(byte_array, id, 1 + 16);
            NetworksRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
        }
        //                entitydatamanager.set(skinningentitieshelper.getIntegerDataParameterArray()[id], BytesReader.getInt(skinningentitiesmessage.data, 21));
    }
}
