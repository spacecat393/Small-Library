package com.nali.list.netmethods.servermessage;

import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.handlers.ServerHandler.canPass;

public class SetXYZ
{
    public static int ID = 23;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        SkinningEntities skinningentities = SkinningEntities.SERVER_ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            skinningentities.setPositionAndUpdate(BytesReader.getFloat(servermessage.data, 1 + 16), BytesReader.getFloat(servermessage.data, 1 + 16 + 4), BytesReader.getFloat(servermessage.data, 1 + 16 + 4 + 4));
        }
    }
}
