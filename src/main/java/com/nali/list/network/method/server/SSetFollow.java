package com.nali.list.network.method.server;

import com.nali.list.capability.serializable.SmallSakuraSerializable;
import com.nali.list.capability.type.SmallSakuraType;
import com.nali.list.network.message.ServerMessage;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.EntityLeInv;
import com.nali.list.entity.ai.AILeFollow;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.network.handler.ServerHandler.canPass;
import static com.nali.list.network.method.server.SFetchFollow.fetch;
import static com.nali.small.entity.memo.server.ServerE.ENTITIES_MAP;

public class SSetFollow
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        EntityLeInv skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            ServerE serverentitiesmemory = (ServerE)skinningentities.bothentitiesmemory;
            AILeFollow skinningentitiesfollow = serverentitiesmemory.entitiesaimemory.skinningentitiesfollow;
            float id = BytesReader.getFloat(servermessage.data, 1 + 16);
            float x = BytesReader.getFloat(servermessage.data, 1 + 16 + 4);

            SmallSakuraType smallsakuratypes = entityplayermp.getCapability(SmallSakuraSerializable.SMALLSAKURATYPES_CAPABILITY, null);
            byte value = smallsakuratypes.get();

            if (id == 1.1F)
            {
                if (x == 1)
                {
                    if (value >= 1)
                    {
                        smallsakuratypes.set((byte)(value - 1));
                        skinningentitiesfollow.flag |= 4;
                    }
                }
                else
                {
                    skinningentitiesfollow.flag &= 255 - 4;
                }
            }
            else if (id == 1.2F)
            {
                if (x == 1)
                {
                    if (value >= 1)
                    {
                        smallsakuratypes.set((byte)(value - 1));
                        skinningentitiesfollow.flag |= 2;
                    }
                }
                else
                {
                    skinningentitiesfollow.flag &= 255 - 2;
                }
            }
            else if (id == 2.1F)
            {
                skinningentitiesfollow.max_distance = x;
            }
            else if (id == 2.2F)
            {
                skinningentitiesfollow.min_distance = x;
            }

            fetch(entityplayermp, skinningentitiesfollow);
        }
    }
}
