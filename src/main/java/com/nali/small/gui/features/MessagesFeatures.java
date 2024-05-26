package com.nali.small.gui.features;

import com.nali.small.entities.EntitiesRegistry;
import com.nali.small.system.Reference;
import net.minecraft.util.text.translation.I18n;

public class MessagesFeatures
{
//    public static void initOnOff2(String[] main_key_string_array, String[] sub_key_string_array, int id, GUIFeaturesLoader guifeaturesloader)
//    {
//        SkinningEntities skinningentities = ((InventoryContainer)guifeaturesloader.mixgui.inventorySlots).skinningentities;
//
//        for (int i = 0; i < main_key_string_array.length; ++i)
//        {
//            main_key_string_array[i] = I18n.translateToLocal(main_key_string_array[i]);
//        }
//
//        for (int i = 0; i < sub_key_string_array.length; ++i)
//        {
//            sub_key_string_array[i] = I18n.translateToLocal(sub_key_string_array[i]);
//        }
//
//        guifeaturesloader.string_array = new String[sub_key_string_array.length + 1];
//        guifeaturesloader.string_array[0] = I18n.translateToLocal("info." + Reference.MOD_ID + ".b0") + " : " + (skinningentities.work_byte_array[id] == 1 ? main_key_string_array[0] : main_key_string_array[1]);
//
//        for (int i = 0; i < sub_key_string_array.length; ++i)
//        {
//            guifeaturesloader.string_array[i + 1] = main_key_string_array[i] + " : " + sub_key_string_array[i];
//        }
//
//        guifeaturesloader.createColor();
//    }

    public static void initEntities(int[] int_array, String key, GUIFeaturesLoader guifeaturesloader)
    {
        if (int_array != null && int_array.length != 0)
        {
            guifeaturesloader.string_array = new String[int_array.length + 3];

            int index = 1;
            for (int i : int_array)
            {
                if (i < EntitiesRegistry.ENTITY_KEY_ARRAY.length)
                {
                    guifeaturesloader.string_array[index++] = i + " " + ((Class) EntitiesRegistry.ENTITY_KEY_ARRAY[i]).getName();
                }
                else
                {
                    guifeaturesloader.string_array[index++] = i + " OUT_ARRAY";
                }
            }
        }
        else
        {
            guifeaturesloader.string_array = new String[2];
        }

        guifeaturesloader.string_array[0] = key;
        guifeaturesloader.string_array[guifeaturesloader.string_array.length - 2] = I18n.translateToLocal("info." + Reference.MOD_ID + ".c1");
        guifeaturesloader.string_array[guifeaturesloader.string_array.length - 1] = I18n.translateToLocal("info." + Reference.MOD_ID + ".c2");

        guifeaturesloader.createColor();
    }
}
