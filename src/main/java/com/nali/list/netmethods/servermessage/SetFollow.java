package com.nali.list.netmethods.servermessage;

import com.nali.list.capabilitiesserializations.SmallSakuraSerializations;
import com.nali.list.capabilitiestypes.SmallSakuraTypes;
import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.ai.SkinningEntitiesFollow;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.handlers.ServerHandler.canPass;
import static com.nali.list.netmethods.servermessage.FetchFollow.fetch;
import static com.nali.small.entities.memory.server.ServerEntitiesMemory.ENTITIES_MAP;

public class SetFollow
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        SkinningEntities skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)skinningentities.bothentitiesmemory;
            SkinningEntitiesFollow skinningentitiesfollow = serverentitiesmemory.entitiesaimemory.skinningentitiesfollow;
            float id = BytesReader.getFloat(servermessage.data, 1 + 16);
            float x = BytesReader.getFloat(servermessage.data, 1 + 16 + 4);

            SmallSakuraTypes smallsakuratypes = entityplayermp.getCapability(SmallSakuraSerializations.SMALLSAKURATYPES_CAPABILITY, null);
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
