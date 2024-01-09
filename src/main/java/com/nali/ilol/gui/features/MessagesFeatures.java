//package com.nali.ilol.gui.features;
//
//import com.nali.ilol.entities.skinning.SkinningEntities;
//import com.nali.list.container.InventoryContainer;
//import com.nali.list.gui.InventoryGui;
//import net.minecraft.util.text.translation.I18n;
//
//public class MessagesFeatures
//{
//    public static void drawOnOff2(String[] main_key_string_array, String[] sub_key_string_array, int id, InventoryGui inventorygui, int mouseX, int mouseY)
//    {
//        SkinningEntities skinningentities = ((InventoryContainer)inventorygui.inventorySlots).skinningentities;
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
//        String[] string_array = new String[sub_key_string_array.length + 1];
//        string_array[0] = I18n.translateToLocal("gui.info.cv") + " : " + (skinningentities.client_work_byte_array[id] == 1 ? main_key_string_array[0] : main_key_string_array[1]);
//
//        for (int i = 0; i < sub_key_string_array.length; ++i)
//        {
//            string_array[i + 1] = main_key_string_array[i] + " : " + sub_key_string_array[i];
//        }
//
//        inventorygui.drawHoveringText(string_array, mouseX, mouseY, true);
//    }
//}
