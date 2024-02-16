package com.nali.list.netmethods.servermessage;

import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.handlers.ServerHandler.canPass;

public class SetOwner
{
    public static int ID = 2;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        SkinningEntities skinningentities = SkinningEntities.SERVER_ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        //                Entity temp_entity = ((WorldServer)entityplayermp.world).getEntityFromUuid(BytesReader.getUUID(servermessage.data, 1));
        //                if (!(temp_entity instanceof SkinningEntitiesHelper))
        //                {
        //                    break;
        //                }
        //
        //                SkinningEntitiesHelper skinningentitieshelper = (SkinningEntitiesHelper)temp_entity;
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            skinningentities.owner_uuid = entityplayermp.getUniqueID();
//                        EntityDataManager entitydatamanager = skinningentities.getDataManager();
//                        entitydatamanager.set(SkinningEntities.UUID_OPTIONAL_DATAPARAMETER_ARRAY[BytesReader.getInt(servermessage.data, 17)], Optional.fromNullable(entityplayermp.getUniqueID()));
        }
    }
}
