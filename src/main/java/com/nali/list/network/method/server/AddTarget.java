package com.nali.list.network.method.server;

import com.nali.list.network.message.ServerMessage;
import com.nali.small.entity.EntityRegistry;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.EntityLeInv;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.network.handler.ServerHandler.canPass;
import static com.nali.small.entity.memo.server.ServerE.ENTITIES_MAP;

public class AddTarget
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        EntityLeInv skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            ServerE serverentitiesmemory = (ServerE)skinningentities.bothentitiesmemory;
//                        String string = new String(servermessage.data, 1 + 16, servermessage.data.length - (1 + 16));
//                        String[] string_array = string.split(" ");

//                        for (String new_string : string_array)
            for (int index = 1 + 16; index < servermessage.data.length; index += 4)
            {
                int id = BytesReader.getInt(servermessage.data, index);
//                            int id = Integer.parseInt(new_string);

                if (id >= EntityRegistry.ENTITY_KEY_ARRAY.length)
                {
                    continue;
                }

                boolean result = true;
                for (int i : serverentitiesmemory.entitiesaimemory.skinningentitiesarea.target_list)
                {
                    if (i == id)
                    {
                        result = false;
                        break;
                    }
                }

                if (result)
                {
                    serverentitiesmemory.entitiesaimemory.skinningentitiesarea.target_list.add(id);
                }
            }
        }
    }
}
