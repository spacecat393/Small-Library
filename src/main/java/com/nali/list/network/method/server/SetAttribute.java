package com.nali.list.network.method.server;

import com.nali.list.capability.serializable.SmallSakuraSerializable;
import com.nali.list.capability.type.SmallSakuraType;
import com.nali.list.network.message.ServerMessage;
import com.nali.small.entity.EntityLeInv;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.network.handler.ServerHandler.canPass;
import static com.nali.small.entity.memo.server.ServerE.ENTITIES_MAP;

public class SetAttribute
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        EntityLeInv skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            int id = (int)BytesReader.getFloat(servermessage.data, 1 + 16);
            float f = BytesReader.getFloat(servermessage.data, 1 + 16 + 4);
            int need = (int)f;

            SmallSakuraType smallsakuratypes = entityplayermp.getCapability(SmallSakuraSerializable.SMALLSAKURATYPES_CAPABILITY, null);
            byte value = smallsakuratypes.get();

            if (value >= need)
            {
                if (need > 0)
                {
                    smallsakuratypes.set((byte)(value - need));
                }

                int index = 0;
                for (IAttributeInstance iattributeinstance : skinningentities.getAttributeMap().getAllAttributes())
                {
                    if (id == index++)
                    {
                        iattributeinstance.setBaseValue(f);
                        break;
                    }
                }
            }
        }
    }
}
