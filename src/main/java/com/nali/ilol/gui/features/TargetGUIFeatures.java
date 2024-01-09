package com.nali.ilol.gui.features;

import com.nali.ilol.entities.EntitiesRegistryHelper;
import com.nali.list.gui.InventoryGui;
import net.minecraft.util.text.translation.I18n;

public class TargetGUIFeatures
{
    public static int[] TROUBLEMAKER_INT_ARRAY;
    public static int[] TARGET_INT_ARRAY;

    public static void draw(int[] id_int_array, String key, InventoryGui inventorygui, int mouseX, int mouseY)
    {
        String[] string_array;

        if (id_int_array != null && id_int_array.length != 0)
        {
            string_array = new String[id_int_array.length + 2];

            int index = 1;
            for (int i : id_int_array)
            {
                if (i < EntitiesRegistryHelper.ENTITY_KEY_ARRAY.length)
                {
                    string_array[index++] = i + " " + ((Class)EntitiesRegistryHelper.ENTITY_KEY_ARRAY[i]).getName();
                }
                else
                {
                    string_array[index++] = i + " OUT_ARRAY";
                }
            }
        }
        else
        {
            string_array = new String[2];
        }

        string_array[0] = I18n.translateToLocal(key);
        string_array[string_array.length - 1] = I18n.translateToLocal("gui.info.st2");

        inventorygui.drawHoveringText(string_array, mouseX, mouseY, false);
    }
}
