package com.nali.list.netmethods.servermessage;

import com.nali.list.messages.ClientMessage;
import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.networks.NetworksRegistry;
import com.nali.system.bytes.BytesReader;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.handlers.ServerHandler.canPass;

public class ViewTarget
{
    public static int ID = 11;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        SkinningEntities skinningentities = SkinningEntities.SERVER_ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));

        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            int size = skinningentities.skinningentitiesarea.target_arraylist.size() * 4;
            byte[] byte_array = new byte[1 + size];
            byte_array[0] = 3;
            int index = 0;
            for (int i = 1; i < size; i += 4)
            {
                BytesWriter.set(byte_array, skinningentities.skinningentitiesarea.target_arraylist.get(index++), i);
            }

            NetworksRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
        }
    }
}
