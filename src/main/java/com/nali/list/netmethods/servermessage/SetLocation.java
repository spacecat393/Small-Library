package com.nali.list.netmethods.servermessage;

import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;

import static com.nali.list.handlers.ServerHandler.canPass;
import static com.nali.small.entities.memory.server.ServerEntitiesMemory.ENTITIES_MAP;

public class SetLocation
{
    public static byte ID = 25;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        SkinningEntities skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)skinningentities.bothentitiesmemory;
            int id = (int)BytesReader.getFloat(servermessage.data, 1 + 16);
            if (id == 1)
            {
                serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.far = BytesReader.getFloat(servermessage.data, 1 + 16 + 4);
            }
            else
            {
                serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.blockpos_long = new BlockPos(BytesReader.getFloat(servermessage.data, 1 + 16 + 4), BytesReader.getFloat(servermessage.data, 1 + 16 + 4 + 4), BytesReader.getFloat(servermessage.data, 1 + 16 + 4 + 4 + 4)).toLong();
            }
        }
    }
}
