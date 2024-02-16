package com.nali.list.netmethods.servermessage;

import com.nali.list.messages.ClientMessage;
import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.networks.NetworksRegistry;
import com.nali.system.bytes.BytesReader;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.UUID;

import static com.nali.list.handlers.ServerHandler.canPass;

public class SetWorkByte
{
    public static int ID = 1;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        UUID uuid = BytesReader.getUUID(servermessage.data, 1);
        SkinningEntities skinningentities = SkinningEntities.SERVER_ENTITIES_MAP.get(uuid);
        //                Entity temp_entity = ((WorldServer)entityplayermp.world).getEntityFromUuid(BytesReader.getUUID(servermessage.data, 1));
        //                if (!(temp_entity instanceof SkinningEntitiesHelper))
        //                {
        //                    break;
        //                }

        //                SkinningEntitiesHelper skinningentitieshelper = (SkinningEntitiesHelper)temp_entity;
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
//                        EntityDataManager entitydatamanager = skinningentities.getDataManager();
            int id = BytesReader.getInt(servermessage.data, 17);

//                        DataParameter<Byte>[] byte_dataparameter_array = skinningentities.getByteDataParameterArray();
            if (servermessage.data[21] == 0)
            {
                skinningentities.main_server_work_byte_array[skinningentities.skinningentitiesbytes.SOFT_READY()] = 1;
                skinningentities.main_server_work_byte_array[skinningentities.skinningentitiesbytes.HARD_READY()] = 0;
//                            entitydatamanager.set(byte_dataparameter_array[skinningentities.skinningentitiesbytes.SOFT_READY()], (byte)1);
//                            entitydatamanager.set(byte_dataparameter_array[skinningentities.skinningentitiesbytes.HARD_READY()], (byte)0);
            }
            else
            {
                skinningentities.main_server_work_byte_array[skinningentities.skinningentitiesbytes.SOFT_READY()] = 0;
                skinningentities.main_server_work_byte_array[skinningentities.skinningentitiesbytes.HARD_READY()] = 1;
//                            entitydatamanager.set(byte_dataparameter_array[skinningentities.skinningentitiesbytes.HARD_READY()], (byte)1);
//                            entitydatamanager.set(byte_dataparameter_array[skinningentities.skinningentitiesbytes.SOFT_READY()], (byte)0);
            }

            skinningentities.main_server_work_byte_array[id] = servermessage.data[21];
//                        entitydatamanager.set(byte_dataparameter_array[id], servermessage.data[21]);

            byte[] byte_array = new byte[1 + 4 + skinningentities.main_server_work_byte_array.length];
            byte_array[0] = 6;
            BytesWriter.set(byte_array, skinningentities.getEntityId(), 1);
            System.arraycopy(skinningentities.main_server_work_byte_array, 0, byte_array, 1 + 4, skinningentities.main_server_work_byte_array.length);
            NetworksRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
        }
    }
}
