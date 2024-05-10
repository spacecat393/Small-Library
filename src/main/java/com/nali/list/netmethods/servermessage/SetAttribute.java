package com.nali.list.netmethods.servermessage;

import com.nali.list.capabilitiesserializations.SmallSakuraSerializations;
import com.nali.list.capabilitiestypes.SmallSakuraTypes;
import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.handlers.ServerHandler.canPass;
import static com.nali.small.entities.memory.server.ServerEntitiesMemory.ENTITIES_MAP;

public class SetAttribute
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        SkinningEntities skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            int id = (int)BytesReader.getFloat(servermessage.data, 1 + 16);
            float f = BytesReader.getFloat(servermessage.data, 1 + 16 + 4);
            int need = (int)f;

            SmallSakuraTypes smallsakuratypes = entityplayermp.getCapability(SmallSakuraSerializations.SMALLSAKURATYPES_CAPABILITY, null);
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
