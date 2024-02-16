package com.nali.list.netmethods.servermessage;

import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;

import static com.nali.list.handlers.ServerHandler.canPass;

public class SetLocation
{
    public static int ID = 25;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        SkinningEntities skinningentities = SkinningEntities.SERVER_ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            int id = (int)BytesReader.getFloat(servermessage.data, 1 + 16);
            if (id == 1)
            {
                skinningentities.skinningentitiessetlocation.far = BytesReader.getFloat(servermessage.data, 1 + 16 + 4);
            }
            else
            {
                skinningentities.skinningentitiessetlocation.blockpos_long = new BlockPos(BytesReader.getFloat(servermessage.data, 1 + 16 + 4), BytesReader.getFloat(servermessage.data, 1 + 16 + 4 + 4), BytesReader.getFloat(servermessage.data, 1 + 16 + 4 + 4 + 4)).toLong();
            }
        }
    }
}
