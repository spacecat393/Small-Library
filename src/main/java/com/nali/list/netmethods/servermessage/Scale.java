package com.nali.list.netmethods.servermessage;

import com.nali.list.capabilitiesserializations.SmallSakuraSerializations;
import com.nali.list.capabilitiestypes.SmallSakuraTypes;
import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.handlers.ServerHandler.canPass;

public class Scale
{
    public static byte ID = 20;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        SkinningEntities skinningentities = SkinningEntities.SERVER_ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        float s = BytesReader.getFloat(servermessage.data, 1 + 16);
        if (skinningentities != null && s >= 0.5F && canPass(skinningentities, entityplayermp))
        {
            int need = (int)s;

            SmallSakuraTypes smallsakuratypes = entityplayermp.getCapability(SmallSakuraSerializations.SMALLSAKURATYPES_CAPABILITY, null);
            int value = smallsakuratypes.get();

            if (value >= need)
            {
                smallsakuratypes.set(value - need);
                skinningentities.getDataManager().set(skinningentities.getFloatDataParameterArray()[0], s);
            }
        }
    }
}
