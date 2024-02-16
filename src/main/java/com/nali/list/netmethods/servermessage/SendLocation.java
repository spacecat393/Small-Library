package com.nali.list.netmethods.servermessage;

import com.nali.list.messages.ClientMessage;
import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.networks.NetworksRegistry;
import com.nali.system.bytes.BytesReader;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.handlers.ServerHandler.canPass;

public class SendLocation
{
    public static int ID = 26;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        SkinningEntities skinningentities = SkinningEntities.SERVER_ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            byte[] byte_array = new byte[1 + 8 + 4];
            byte_array[0] = 8;
            BytesWriter.set(byte_array, skinningentities.skinningentitiessetlocation.blockpos_long, 1);
            BytesWriter.set(byte_array, skinningentities.skinningentitiessetlocation.far, 1 + 8);
            NetworksRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
        }
    }
}
