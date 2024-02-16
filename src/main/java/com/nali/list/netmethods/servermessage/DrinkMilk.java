package com.nali.list.netmethods.servermessage;

import com.nali.list.messages.ServerMessage;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class DrinkMilk
{
    public static int ID = 19;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        SkinningEntities skinningentities = SkinningEntities.SERVER_ENTITIES_MAP.get(BytesReader.getUUID(servermessage.data, 1));
        ItemStack itemstack = entityplayermp.getHeldItemMainhand();
        if (skinningentities != null && skinningentities.canEat() && itemstack.getItem() == Items.MILK_BUCKET)
        {
            skinningentities.clearActivePotions();
            entityplayermp.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.BUCKET));

            skinningentities.playSound(SoundEvents.ENTITY_GENERIC_DRINK, skinningentities.getSoundVolume(), skinningentities.getSoundPitch());
        }
    }
}
