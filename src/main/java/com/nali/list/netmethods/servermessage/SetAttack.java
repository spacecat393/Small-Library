package com.nali.list.netmethods.servermessage;

import com.nali.list.capabilitiesserializations.SmallSakuraSerializations;
import com.nali.list.capabilitiestypes.SmallSakuraTypes;
import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.ai.SkinningEntitiesAttack;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.handlers.ServerHandler.canPass;
import static com.nali.list.netmethods.servermessage.FetchAttack.fetch;
import static com.nali.small.entities.memory.server.ServerEntitiesMemory.ENTITIES_MAP;

public class SetAttack
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        SkinningEntities skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)skinningentities.bothentitiesmemory;
            SkinningEntitiesAttack skinningentitiesattack = serverentitiesmemory.entitiesaimemory.skinningentitiesattack;
            float id = BytesReader.getFloat(servermessage.data, 1 + 16);
            float x = BytesReader.getFloat(servermessage.data, 1 + 16 + 4);

            SmallSakuraTypes smallsakuratypes = entityplayermp.getCapability(SmallSakuraSerializations.SMALLSAKURATYPES_CAPABILITY, null);
            int value = smallsakuratypes.get();

            if (id == 1.1F)
            {
                if (x == 1)
                {
                    if (value >= 1)
                    {
                        smallsakuratypes.set(value - 1);
                        skinningentitiesattack.flag |= 16;
                    }
                }
                else
                {
                    skinningentitiesattack.flag &= 255 - 16;
                }
            }
            else if (id == 1.2F)
            {
                if (x == 1)
                {
                    if (value >= 1)
                    {
                        smallsakuratypes.set(value - 1);
                        skinningentitiesattack.flag |= 8;
                    }
                }
                else
                {
                    skinningentitiesattack.flag &= 255 - 8;
                }
            }
            else if (id == 1.3F)
            {
                int v = (int)x;
                if (value >= v)
                {
                    smallsakuratypes.set(value - v);
                    skinningentitiesattack.minimum_distance = x;
                }
            }
            else if (id == 1.4F)
            {
                int v = (int)x;
                if (value >= v)
                {
                    smallsakuratypes.set(value - v);
                    skinningentitiesattack.max_magic_point = v;
                }
            }

            fetch(entityplayermp, skinningentitiesattack);
        }
    }
}
