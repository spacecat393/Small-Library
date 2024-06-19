package com.nali.list.network.method.server;

import com.nali.list.capability.serializable.SmallSakuraSerializable;
import com.nali.list.capability.type.SmallSakuraType;
import com.nali.list.network.message.ServerMessage;
import com.nali.small.entity.EntityLeInv;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.network.handler.ServerHandler.canPass;
import static com.nali.small.entity.memo.server.ServerE.ENTITIES_MAP;

public class Scale
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        EntityLeInv skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        float s = BytesReader.getFloat(servermessage.data, 1 + 16);
        if (skinningentities != null && s >= 0.5F && canPass(skinningentities, entityplayermp))
        {
            int need = (int)s;

            SmallSakuraType smallsakuratypes = entityplayermp.getCapability(SmallSakuraSerializable.SMALLSAKURATYPES_CAPABILITY, null);
            byte value = smallsakuratypes.get();

            if (value >= need)
            {
                smallsakuratypes.set((byte)(value - need));
                skinningentities.getDataManager().set(skinningentities.getFloatDataParameterArray()[0], s);
            }
        }
    }
}
