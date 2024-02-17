package com.nali.list.netmethods.servermessage;

import com.nali.list.capabilitiesserializations.SmallSakuraSerializations;
import com.nali.list.messages.ServerMessage;
import com.nali.system.bytes.BytesReader;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

import java.util.Arrays;

public class DropToSakura
{
    public static byte ID = 7;

    public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
    {
        InventoryPlayer inventoryplayer = entityplayermp.inventory;
//                    String string = new String(servermessage.data, 1, servermessage.data.length - 1);
//                    String[] string_array = string.split(" ");

        int max_count = 0, index = 0;
        int[] index_int_array = new int[(servermessage.data.length - 1) / 4];
        Arrays.fill(index_int_array, -1);
        int hand_index = inventoryplayer.mainInventory.size() + inventoryplayer.armorInventory.size();
        int armor_index = inventoryplayer.mainInventory.size();
//                    for (String new_string : string_array)
        for (int x = 1; x < servermessage.data.length; x += 4)
        {
            int i = BytesReader.getInt(servermessage.data, x);
//                        int i = Integer.parseInt(new_string);

            boolean result = true;
            for (int y : index_int_array)
            {
                if (i == y)
                {
                    result = false;
                    break;
                }
            }

            if (result)
            {
                if (i < inventoryplayer.mainInventory.size() + inventoryplayer.offHandInventory.size() + inventoryplayer.armorInventory.size())
                {
                    index_int_array[index++] = i;

                    if (i >= inventoryplayer.mainInventory.size() + inventoryplayer.armorInventory.size())
                    {
                        max_count += inventoryplayer.offHandInventory.get(i - hand_index).getCount();
                    }
                    else if (i >= inventoryplayer.mainInventory.size())
                    {
                        max_count += inventoryplayer.armorInventory.get(i - armor_index).getCount();
                    }
                    else
                    {
                        max_count += inventoryplayer.mainInventory.get(i).getCount();
                    }
                }
            }
        }

        if (max_count % 64 == 0)
        {
            for (int i : index_int_array)
            {
                if (i != -1)
                {
                    if (i >= inventoryplayer.mainInventory.size() + inventoryplayer.armorInventory.size())
                    {
                        inventoryplayer.offHandInventory.set(i - hand_index, ItemStack.EMPTY);
                    }
                    else if (i >= inventoryplayer.mainInventory.size())
                    {
                        inventoryplayer.armorInventory.set(i - armor_index, ItemStack.EMPTY);
                    }
                    else
                    {
                        inventoryplayer.mainInventory.set(i, ItemStack.EMPTY);
                    }
                }
            }

            entityplayermp.getCapability(SmallSakuraSerializations.SMALLSAKURATYPES_CAPABILITY, null).add(max_count / 64);
        }

//    //                Object[] key_array = new HashSet<>(((MixinEntityRegistry)EntityRegistry.instance()).entityClassEntries().keySet()).toArray();
//    //                for (Object object : key_array)
//    //                {
//    //                    ((Class)object).getName();
//    //                }
//                    SMALL.LOGGER.info("Name : " + string);
//                    SMALL.LOGGER.info("Have : " + ForgeRegistries.ENTITIES.containsKey(new ResourceLocation(string)));
    }
}
