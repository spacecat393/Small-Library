package com.nali.list.netmethods.servermessage;

import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.handlers.ServerHandler.canPass;
import static com.nali.small.entities.memory.server.ServerEntitiesMemory.ENTITIES_MAP;

public class RemoveTroublemaker
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        SkinningEntities skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            ServerEntitiesMemory serverentitiesmemory = (ServerEntitiesMemory)skinningentities.bothentitiesmemory;
//                        String string = new String(servermessage.data, 1 + 16, servermessage.data.length - (1 + 16));
//                        String[] string_array = string.split(" ");

//                        for (String new_string : string_array)
            for (int x = 1 + 16; x < servermessage.data.length; x += 4)
            {
                int id = BytesReader.getInt(servermessage.data, x);
//                            int id = Integer.parseInt(new_string);

                int index = 0;
                for (int i : serverentitiesmemory.entitiesaimemory.skinningentitiesarea.troublemaker_arraylist)
                {
                    if (i == id)
                    {
                        serverentitiesmemory.entitiesaimemory.skinningentitiesarea.troublemaker_arraylist.remove(index);
                        break;
                    }
                    ++index;
                }
            }
        }
    }
}
