package com.nali.list.network.method.server;

import com.nali.list.capability.serializable.SmallSakuraSerializable;
import com.nali.list.capability.type.SmallSakuraType;
import com.nali.list.network.message.ServerMessage;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.EntityLeInv;
import com.nali.list.entity.ai.AIEArea;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.network.handler.ServerHandler.canPass;
import static com.nali.small.entity.memo.server.ServerE.ENTITIES_MAP;

public class SSetArea
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        EntityLeInv skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            ServerE serverentitiesmemory = (ServerE)skinningentities.bothentitiesmemory;
            AIEArea skinningentitiesarea = serverentitiesmemory.entitiesaimemory.skinningentitiesarea;
            float id = BytesReader.getFloat(servermessage.data, 1 + 16);
            float x = BytesReader.getFloat(servermessage.data, 1 + 16 + 4);

            SmallSakuraType smallsakuratypes = entityplayermp.getCapability(SmallSakuraSerializable.SMALLSAKURATYPES_CAPABILITY, null);
            byte value = smallsakuratypes.get();

            if (id == 0.1F)
            {
                if (x == 1)
                {
                    if (value >= 1)
                    {
                        smallsakuratypes.set((byte)(value - 1));
                        skinningentitiesarea.flag |= 4;
                    }
                }
                else
                {
                    skinningentitiesarea.flag &= 255 - 4;
                }
            }
            else if (id == 0.2F)
            {
                if (x == 1)
                {
                    if (value >= 1)
                    {
                        smallsakuratypes.set((byte)(value - 1));
                        skinningentitiesarea.flag |= 8;
                    }
                }
                else
                {
                    skinningentitiesarea.flag &= 255 - 8;
                }
            }
            else if (id == 0.3F)
            {
                if (x == 1)
                {
                    if (value >= 1)
                    {
                        smallsakuratypes.set((byte)(value - 1));
                        skinningentitiesarea.flag |= 128;
                    }
                }
                else
                {
                    skinningentitiesarea.flag &= 255 - 128;
                }
            }
            else if (id == 1.1F)
            {
                if (x == 1)
                {
                    if (value >= 1)
                    {
                        smallsakuratypes.set((byte)(value - 1));
                        skinningentitiesarea.flag |= 16;
                    }
                }
                else
                {
                    skinningentitiesarea.flag &= 255 - 16;
                }
            }
            else if (id == 1.2F)
            {
                if (x == 1)
                {
                    if (value >= 1)
                    {
                        smallsakuratypes.set((byte)(value - 1));
                        skinningentitiesarea.flag |= 32;
                    }
                }
                else
                {
                    skinningentitiesarea.flag &= 255 - 32;
                }
            }
            else if (id == 1.3F)
            {
                if (x == 1)
                {
                    if (value >= 1)
                    {
                        smallsakuratypes.set((byte)(value - 1));
                        skinningentitiesarea.flag |= 64;
                    }
                }
                else
                {
                    skinningentitiesarea.flag &= 255 - 64;
                }
            }

            SFetchArea.fetch(entityplayermp, skinningentitiesarea);
        }
    }
}
