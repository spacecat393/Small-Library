package com.nali.list.netmethods.servermessage;

import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.datasync.DataParameter;

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
            DataParameter<Byte>[] byte_dataparameter_array = skinningentities.getByteDataParameterArray();
            for (int i = 0; i < byte_dataparameter_array.length; ++i)
            {
                serverentitiesmemory.sync_byte_array[i] = skinningentities.getDataManager().get(byte_dataparameter_array[i]);
            }

            int id = BytesReader.getInt(servermessage.data, 17);
            byte bit8 = servermessage.data[21];
            serverentitiesmemory.sync_byte_array[id] ^= bit8;
            skinningentities.getDataManager().set(skinningentities.getByteDataParameterArray()[id], serverentitiesmemory.sync_byte_array[id]);
        }
    }
}
