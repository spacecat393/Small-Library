package com.nali.list.netmethods.servermessage;

import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;

import static com.nali.list.handlers.ServerHandler.canPass;
import static com.nali.small.entities.memory.server.ServerEntitiesMemory.ENTITIES_MAP;

public class SetManageItem
{
    public static byte ID = 31;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        SkinningEntities skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)skinningentities.bothentitiesmemory;
            int id = (int)BytesReader.getFloat(servermessage.data, 1 + 16);
            BlockPos blockpos = null;

            if (servermessage.data.length > 1 + 16 + 4 + 4)
            {
                float x = BytesReader.getFloat(servermessage.data, 1 + 16 + 4);
                float y = BytesReader.getFloat(servermessage.data, 1 + 16 + 4 + 4);
                float z = BytesReader.getFloat(servermessage.data, 1 + 16 + 4 + 4 + 4);
                blockpos = new BlockPos(x, y, z);
            }

            if (id == 2)
            {
                if (blockpos != null)
                {
                    serverentitiesmemory.entitiesaimemory.skinningentitiesmanageitem.out_blockpos = blockpos;
                }
                else
                {
                    serverentitiesmemory.entitiesaimemory.skinningentitiesmanageitem.out_blockpos = null;
                    serverentitiesmemory.entitiesaimemory.skinningentitiesmanageitem.state ^= 8;
                }
            }
            else
            {
                if (blockpos != null)
                {
                    serverentitiesmemory.entitiesaimemory.skinningentitiesmanageitem.in_blockpos = blockpos;
                }
                else
                {
                    serverentitiesmemory.entitiesaimemory.skinningentitiesmanageitem.in_blockpos = null;
                    serverentitiesmemory.entitiesaimemory.skinningentitiesmanageitem.state ^= 4;
                }
            }
        }
    }
}
