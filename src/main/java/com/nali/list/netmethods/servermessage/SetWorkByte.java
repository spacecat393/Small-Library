package com.nali.list.netmethods.servermessage;

import com.nali.list.messages.ClientMessage;
import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.networks.NetworksRegistry;
import com.nali.system.bytes.BytesReader;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.UUID;

import static com.nali.list.handlers.ServerHandler.canPass;
import static com.nali.small.entities.memory.server.ServerEntitiesMemory.ENTITIES_MAP;

public class SetWorkByte
{
    public static byte ID = 1;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        UUID uuid = BytesReader.getUUID(servermessage.data, 1);
        SkinningEntities skinningentities = ENTITIES_MAP.get(uuid);
        //                Entity temp_entity = ((WorldServer)entityplayermp.world).getEntityFromUuid(BytesReader.getUUID(servermessage.data, 1));
        //                if (!(temp_entity instanceof SkinningEntitiesHelper))
        //                {
        //                    break;
        //                }

        //                SkinningEntitiesHelper skinningentitieshelper = (SkinningEntitiesHelper)temp_entity;
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)skinningentities.bothentitiesmemory;
//                        EntityDataManager entitydatamanager = skinningentities.getDataManager();
            int id = BytesReader.getInt(servermessage.data, 17);

//                        DataParameter<Byte>[] byte_dataparameter_array = skinningentities.getByteDataParameterArray();
            if (servermessage.data[21] == 0)
            {
                serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.SOFT_READY()] = 1;
                serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.HARD_READY()] = 0;
//                            entitydatamanager.set(byte_dataparameter_array[serverentitiesmemory.workbytes.SOFT_READY()], (byte)1);
//                            entitydatamanager.set(byte_dataparameter_array[serverentitiesmemory.workbytes.HARD_READY()], (byte)0);
            }
            else
            {
                serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.SOFT_READY()] = 0;
                serverentitiesmemory.main_work_byte_array[serverentitiesmemory.workbytes.HARD_READY()] = 1;
//                            entitydatamanager.set(byte_dataparameter_array[serverentitiesmemory.workbytes.HARD_READY()], (byte)1);
//                            entitydatamanager.set(byte_dataparameter_array[serverentitiesmemory.workbytes.SOFT_READY()], (byte)0);
            }

            serverentitiesmemory.main_work_byte_array[id] = servermessage.data[21];
//                        entitydatamanager.set(byte_dataparameter_array[id], servermessage.data[21]);

            byte[] byte_array = new byte[1 + 4 + serverentitiesmemory.main_work_byte_array.length];
            byte_array[0] = 6;
            BytesWriter.set(byte_array, skinningentities.getEntityId(), 1);
            System.arraycopy(serverentitiesmemory.main_work_byte_array, 0, byte_array, 1 + 4, serverentitiesmemory.main_work_byte_array.length);
            NetworksRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
        }
    }
}
