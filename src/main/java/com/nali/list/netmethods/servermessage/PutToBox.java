package com.nali.list.netmethods.servermessage;

import com.nali.list.items.SmallBox;
import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

import static com.nali.list.handlers.ServerHandler.canPass;
import static com.nali.small.entities.memory.server.ServerEntitiesMemory.ENTITIES_MAP;
import static com.nali.small.items.ItemsRegistry.ITEM_ARRAY;

public class PutToBox
{
    public static byte ID;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        SkinningEntities skinningentities = ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        if (skinningentities != null && canPass(skinningentities, entityplayermp))
        {
            ItemStack itemstack = entityplayermp.getHeldItemMainhand();

            if (itemstack.getItem() == ITEM_ARRAY[SmallBox.ID] && itemstack.getTagCompound() == null)
            {
                SmallBox.putToBox(skinningentities, itemstack);
                entityplayermp.closeScreen();
            }
        }
    }
}