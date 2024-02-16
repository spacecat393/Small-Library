package com.nali.list.netmethods.servermessage;

import com.nali.list.messages.ClientMessage;
import com.nali.list.messages.ServerMessage;
import com.nali.small.Small;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.networks.NetworksRegistry;
import com.nali.system.bytes.BytesReader;
import com.nali.system.bytes.BytesWriter;
import net.minecraft.entity.player.EntityPlayerMP;

import static com.nali.list.handlers.ServerHandler.canPass;

public class OpenInvGUI
{
    public static int ID = 6;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        SkinningEntities skinningentities = SkinningEntities.SERVER_ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            entityplayermp.openGui(Small.I, 0, skinningentities.world, skinningentities.getEntityId(), 0, 0);

            byte[] byte_array = new byte[1 + 4 + skinningentities.main_server_work_byte_array.length];
            byte_array[0] = 6;
            BytesWriter.set(byte_array, skinningentities.getEntityId(), 1);
            System.arraycopy(skinningentities.main_server_work_byte_array, 0, byte_array, 1 + 4, skinningentities.main_server_work_byte_array.length);
            NetworksRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
//                        EntitiesContainerHelper.setContainer(skinningentities, messagecontext.getServerHandler().player, InventoryContainer.ID);
        }
    }
}
