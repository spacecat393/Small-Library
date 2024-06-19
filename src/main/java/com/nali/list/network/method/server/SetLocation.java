package com.nali.list.network.method.server;

import com.nali.list.network.message.ServerMessage;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.EntityLeInv;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;

import static com.nali.list.network.handler.ServerHandler.canPass;
import static com.nali.small.entity.memo.server.ServerE.ENTITIES_MAP;

public class SetLocation
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        EntityLeInv skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            ServerE serverentitiesmemory = (ServerE)skinningentities.bothentitiesmemory;
            int id = (int)BytesReader.getFloat(servermessage.data, 1 + 16);
            if (id == 1)
            {
                serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.far = BytesReader.getFloat(servermessage.data, 1 + 16 + 4);
            }
            else
            {
                serverentitiesmemory.entitiesaimemory.skinningentitiessetlocation.blockpos_long = new BlockPos(BytesReader.getFloat(servermessage.data, 1 + 16 + 4), BytesReader.getFloat(servermessage.data, 1 + 16 + 4 + 4), BytesReader.getFloat(servermessage.data, 1 + 16 + 4 + 4 + 4)).toLong();
            }
        }
    }
}
