package com.nali.list.netmethods.servermessage;

import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.handlers.ServerHandler.canPass;
import static com.nali.small.entities.memory.server.ServerEntitiesMemory.ENTITIES_MAP;

public class SetLook
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        SkinningEntities skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            int id = (int)BytesReader.getFloat(servermessage.data, 1 + 16);
            float f = BytesReader.getFloat(servermessage.data, 1 + 16 + 4);

            if (id == 1)
            {
                skinningentities.rotationPitch = f;
            }
            else if (id == 0)
            {
                skinningentities.rotationYaw = f;
            }
        }
    }
}
