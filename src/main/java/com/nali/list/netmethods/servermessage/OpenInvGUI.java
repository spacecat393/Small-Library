package com.nali.list.netmethods.servermessage;

import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.small.entities.memory.server.ServerEntitiesMemory.ENTITIES_MAP;

public class OpenInvGUI
{
    public static byte ID = 6;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        SkinningEntities skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null)
        {
            skinningentities.sendInventoryGUI(entityplayermp);
        }
    }
}
