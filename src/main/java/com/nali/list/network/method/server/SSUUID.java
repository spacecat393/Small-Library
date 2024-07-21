package com.nali.list.network.method.server;

import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.CSUUID;
import com.nali.network.NetworkRegistry;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class SSUUID
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        int id = ByteReader.getInt(servermessage.data, 1);
        Entity entity = entityplayermp.world.getEntityByID(id);
//        if (entity instanceof IMixE)
//        {
        byte[] byte_array = new byte[1 + 16 + 4];
        byte_array[0] = CSUUID.ID;
        ByteWriter.set(byte_array, entity.getUniqueID(), 1);
        ByteWriter.set(byte_array, id, 1 + 16);
        NetworkRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
//        }
        //                entitydatamanager.set(skinningentitieshelper.getIntegerDataParameterArray()[id], BytesReader.getInt(skinningentitiesmessage.data, 21));
    }
}
