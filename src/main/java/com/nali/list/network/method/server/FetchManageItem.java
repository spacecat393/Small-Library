package com.nali.list.network.method.server;

import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.SetManageItems;
import com.nali.list.network.handler.ServerHandler;
import com.nali.networks.NetworksRegistry;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.EntityLeInv;
import com.nali.list.entity.ai.AILeInvManageItem;
import com.nali.system.bytes.BytesReader;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.small.entity.memo.server.ServerE.ENTITIES_MAP;

public class FetchManageItem
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        EntityLeInv skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && ServerHandler.canPass(skinningentities, entityplayermp))
        {
            fetch(entityplayermp, ((ServerE)skinningentities.bothentitiesmemory).entitiesaimemory.skinningentitiesmanageitem);
        }
    }

    public static void fetch(EntityPlayerMP entityplayermp, AILeInvManageItem skinningentitiesmanageitem)
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
