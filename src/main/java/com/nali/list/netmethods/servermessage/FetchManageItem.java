package com.nali.list.netmethods.servermessage;

import com.nali.list.messages.ClientMessage;
import com.nali.list.messages.ServerMessage;
import com.nali.list.netmethods.clientmessage.SetManageItems;
import com.nali.small.entities.memory.server.ServerEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.entities.skinning.ai.SkinningEntitiesManageItem;
import com.nali.small.networks.NetworksRegistry;
import com.nali.system.bytes.BytesReader;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.handlers.ServerHandler.canPass;
import static com.nali.small.entities.memory.server.ServerEntitiesMemory.ENTITIES_MAP;

public class FetchManageItem
{
    public static byte ID = 32;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        SkinningEntities skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            fetch(entityplayermp, ((ServerEntitiesMemory)skinningentities.bothentitiesmemory).entitiesaimemory.skinningentitiesmanageitem);
        }
    }

    public static void fetch(EntityPlayerMP entityplayermp, SkinningEntitiesManageItem skinningentitiesmanageitem)
    {
        byte[] byte_array = new byte[1 + 1 + 8 + 8 + 4 + 4];
        byte_array[0] = SetManageItems.ID;
        byte_array[1] = skinningentitiesmanageitem.state;
        if (skinningentitiesmanageitem.in_blockpos != null)
        {
            BytesWriter.set(byte_array, skinningentitiesmanageitem.in_blockpos.toLong(), 1 + 1);
        }
        else
        {
            byte_array[1 + 1] = -1;
        }
        if (skinningentitiesmanageitem.out_blockpos != null)
        {
            BytesWriter.set(byte_array, skinningentitiesmanageitem.out_blockpos.toLong(), 1 + 1 + 8);
        }
        else
        {
            byte_array[1 + 1 + 8] = -1;
        }
        BytesWriter.set(byte_array, skinningentitiesmanageitem.random_area_in, 1 + 1 + 8 + 8);
        BytesWriter.set(byte_array, skinningentitiesmanageitem.random_area_out, 1 + 1 + 8 + 8 + 4);
        NetworksRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
    }
}
