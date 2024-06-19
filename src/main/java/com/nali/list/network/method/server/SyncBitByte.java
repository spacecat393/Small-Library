package com.nali.list.network.method.server;

import com.nali.list.network.message.ServerMessage;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.EntityLeInv;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.network.handler.ServerHandler.canPass;
import static com.nali.small.entity.memo.server.ServerE.ENTITIES_MAP;

public class SyncBitByte
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        EntityLeInv skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            ServerE serverentitiesmemory = (ServerE)skinningentities.bothentitiesmemory;

//            int id = BytesReader.getInt(servermessage.data, 17);
            byte id = servermessage.data[17];
            int i = id / 8;
            serverentitiesmemory.sync_byte_array[i] ^= (byte)Math.pow(2, id % 8);
            skinningentities.getDataManager().set(skinningentities.getByteDataParameterArray()[i], serverentitiesmemory.sync_byte_array[i]);
        }
    }
}
