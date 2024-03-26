package com.nali.list.netmethods.servermessage;

import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.ai.SkinningEntitiesManageItem;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;

import static com.nali.list.handlers.ServerHandler.canPass;
import static com.nali.list.netmethods.servermessage.FetchManageItem.fetch;
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
            SkinningEntitiesManageItem skinningentitiesmanageitem = serverentitiesmemory.entitiesaimemory.skinningentitiesmanageitem;
            float id = BytesReader.getFloat(servermessage.data, 1 + 16);
            BlockPos blockpos = null;

            float x = BytesReader.getFloat(servermessage.data, 1 + 16 + 4);
            if (servermessage.data.length > 1 + 16 + 4 + 4)
            {
                float y = BytesReader.getFloat(servermessage.data, 1 + 16 + 4 + 4);
                float z = BytesReader.getFloat(servermessage.data, 1 + 16 + 4 + 4 + 4);
                blockpos = new BlockPos(x, y, z);
            }

            if (id > 1)
            {
                if (id == 2.1)
                {
                    if (x == 1)
                    {
                        skinningentitiesmanageitem.state |= 32;
                    }
                    else
                    {
                        skinningentitiesmanageitem.state &= 255 - 32;
                    }
                }
                else if (id == 2.2)
                {
                    if (x == 1)
                    {
                        skinningentitiesmanageitem.state |= 2;
                    }
                    else
                    {
                        skinningentitiesmanageitem.state &= 255 - 2;
                    }
                }
                else if (id == 2.3)
                {
                    skinningentitiesmanageitem.random_area_out = (int)x;
                }
                else
                {
                    if (blockpos != null)
                    {
                        skinningentitiesmanageitem.out_blockpos = blockpos;
                    }
                    else
                    {
                        skinningentitiesmanageitem.out_blockpos = null;
                        skinningentitiesmanageitem.state ^= 8;
                    }
                }
            }
            else
            {
                if (id == 1.1)
                {
                    if (x == 1)
                    {
                        skinningentitiesmanageitem.state |= 16;
                    }
                    else
                    {
                        skinningentitiesmanageitem.state &= 255 - 16;
                    }
                }
                else if (id == 1.2)
                {
                    if (x == 1)
                    {
                        skinningentitiesmanageitem.state |= 1;
                    }
                    else
                    {
                        skinningentitiesmanageitem.state &= 255 - 1;
                    }
                }
                else if (id == 1.3)
                {
                    skinningentitiesmanageitem.random_area_in = (int)x;
                }
                else
                {
                    if (blockpos != null)
                    {
                        skinningentitiesmanageitem.in_blockpos = blockpos;
                    }
                    else
                    {
                        skinningentitiesmanageitem.in_blockpos = null;
                        skinningentitiesmanageitem.state ^= 4;
                    }
                }
            }

            fetch(entityplayermp, skinningentitiesmanageitem);
        }
    }
}
