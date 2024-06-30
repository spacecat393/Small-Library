package com.nali.list.network.method.server;

import com.nali.list.capability.serializable.SmallSakuraSerializable;
import com.nali.list.capability.type.SmallSakuraType;
import com.nali.list.network.message.ServerMessage;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.EntityLeInv;
import com.nali.list.entity.ai.AILeAttack;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.network.handler.ServerHandler.canPass;
import static com.nali.small.entity.memo.server.ServerE.ENTITIES_MAP;

public class SSetAttack
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        EntityLeInv skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            ServerE serverentitiesmemory = (ServerE)skinningentities.bothentitiesmemory;
            AILeAttack skinningentitiesattack = serverentitiesmemory.entitiesaimemory.skinningentitiesattack;
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
                        smallsakuratypes.set((byte)(value - 1));
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
                    smallsakuratypes.set((byte)(value - v));
                    skinningentitiesattack.minimum_distance = x;
                }
            }
            else if (id == 1.4F)
            {
                int v = (int)x;
                if (value >= v)
                {
                    smallsakuratypes.set((byte)(value - v));
                    skinningentitiesattack.max_magic_point = v;
                }
            }

            SFetchAttack.fetch(entityplayermp, skinningentitiesattack);
        }
    }
}
