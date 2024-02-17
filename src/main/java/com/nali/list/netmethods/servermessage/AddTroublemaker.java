package com.nali.list.netmethods.servermessage;

import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.EntitiesRegistryHelper;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.handlers.ServerHandler.canPass;

public class AddTroublemaker
{
    public static byte ID = 13;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        SkinningEntities skinningentities = SkinningEntities.SERVER_ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
//                        String string = new String(servermessage.data, 1 + 16, servermessage.data.length - (1 + 16));
//                        String[] string_array = string.split(" ");

//                        for (String new_string : string_array)
            for (int index = 1 + 16; index < servermessage.data.length; index += 4)
            {
                int id = BytesReader.getInt(servermessage.data, index);
//                            int id = Integer.parseInt(new_string);

                if (id >= EntitiesRegistryHelper.ENTITY_KEY_ARRAY.length)
                {
                    continue;
                }

                boolean result = true;
                for (int i : skinningentities.skinningentitiesarea.troublemaker_arraylist)
                {
                    if (i == id)
                    {
                        result = false;
                        break;
                    }
                }

                if (result)
                {
                    skinningentities.skinningentitiesarea.troublemaker_arraylist.add(id);
                }
            }
        }
    }
}
