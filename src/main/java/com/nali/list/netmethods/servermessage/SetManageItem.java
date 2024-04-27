package com.nali.list.netmethods.servermessage;

import com.nali.list.capabilitiesserializations.SmallSakuraSerializations;
import com.nali.list.capabilitiestypes.SmallSakuraTypes;
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
    public static byte ID;

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

//            Small.LOGGER.info("ID " + id);
//            Small.LOGGER.info("X " + x);

            SmallSakuraTypes smallsakuratypes = entityplayermp.getCapability(SmallSakuraSerializations.SMALLSAKURATYPES_CAPABILITY, null);
            int value = smallsakuratypes.get();

//            if (id >= 2)
//            {
            if (id == 2.1F)
            {
                if (x == 1)
                {
                    if (value >= 1)
                    {
                        smallsakuratypes.set(value - 1);
                        skinningentitiesmanageitem.state |= 32;
                    }
                }
                else
                {
                    skinningentitiesmanageitem.state &= 255 - 32;
                }
            }
            else if (id == 2.2F)
            {
                if (x == 1)
                {
                    if (value >= 1)
                    {
                        smallsakuratypes.set(value - 1);
                        skinningentitiesmanageitem.state |= 2;
                    }
                }
                else
                {
                    skinningentitiesmanageitem.state &= 255 - 2;
                }
            }
            else if (id == 2.3F)
            {
                int v = (int)x;
                if (value >= v)
                {
                    smallsakuratypes.set(value - v);
                    skinningentitiesmanageitem.random_area_out = v;
                }
            }
            else if (id == 2.0F)
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
//            }
//            else if (id >= 1)
//            {
            else if (id == 1.1F)
            {
                if (x == 1)
                {
                    if (value >= 1)
                    {
                        smallsakuratypes.set(value - 1);
                        skinningentitiesmanageitem.state |= 16;
                    }
                }
                else
                {
                    skinningentitiesmanageitem.state &= 255 - 16;
                }
            }
            else if (id == 1.2F)
            {
                if (x == 1)
                {
                    if (value >= 1)
                    {
                        smallsakuratypes.set(value - 1);
                        skinningentitiesmanageitem.state |= 1;
                    }
                }
                else
                {
                    skinningentitiesmanageitem.state &= 255 - 1;
                }
            }
            else if (id == 1.3F)
            {
                int v = (int)x;
                if (value >= v)
                {
                    smallsakuratypes.set(value - v);
                    skinningentitiesmanageitem.random_area_in = v;
                }
            }
            else if (id == 1.0F)
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
//            }

            fetch(entityplayermp, skinningentitiesmanageitem);
        }
    }
}
