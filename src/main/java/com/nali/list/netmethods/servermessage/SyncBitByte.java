package com.nali.list.netmethods.servermessage;

import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.handlers.ServerHandler.canPass;
import static com.nali.small.entities.memory.server.ServerEntitiesMemory.ENTITIES_MAP;

public class SyncBitByte
{
    public static byte ID = 29;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        SkinningEntities skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)skinningentities.bothentitiesmemory;

//            int id = BytesReader.getInt(servermessage.data, 17);
            byte id = servermessage.data[17];
            int i = id / 8;
            serverentitiesmemory.sync_byte_array[i] ^= (byte)Math.pow(2, id % 8);// % 8 ? % 8 if != 0 +1
            skinningentities.getDataManager().set(skinningentities.getByteDataParameterArray()[i], serverentitiesmemory.sync_byte_array[i]);
        }
    }
}
