package com.nali.ilol.gui.features.messages.inventory;

import com.nali.ilol.entities.EntitiesRegistryHelper;
import com.nali.ilol.gui.MixGui;
import com.nali.ilol.gui.features.GUIFeaturesLoader;
import net.minecraft.util.text.translation.I18n;

import java.util.ArrayList;
import java.util.Objects;

import static com.nali.ilol.gui.MixGui.GUIFEATURESLOADER;
import static com.nali.ilol.gui.MixGui.MESSAGE_STRINGBUILDER;

public class EntityListGUIFeatures extends GUIFeaturesLoader
{
    public String string;

    public EntityListGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);

        this.string = MESSAGE_STRINGBUILDER.toString();
        this.string = this.string.substring(0, this.string.length() - 1);
        ArrayList<String> string_arraylist = new ArrayList<String>();

        int index = 1;
        for (Object o : EntitiesRegistryHelper.ENTITY_KEY_ARRAY)
        {
            String name = ((Class)o).getName();
            if (name.contains(this.string))
            {
                string_arraylist.add((index - 1) + " " + name);
            }
            ++index;
//            this.string_array[index] = (index++ - 1) + " " + ((Class)o).getName();
        }

        this.string_array = new String[string_arraylist.size() + 1];
        this.string_array[0] = I18n.translateToLocal("gui.info.t3");

        for (int i = 1; i < this.string_array.length; ++i)
        {
            this.string_array[i] = string_arraylist.get(i - 1);
        }

        index = this.string_array.length + 1;
        this.int_array = new int[index];
        this.loadColor();
        this.int_array[this.string_array.length] = (index) % 2 == 0 ? 0xFFFFFFFF : 0xFFF85A52;
    }

    @Override
    public void drawText(int mouseX, int mouseY)
    {
        String string = MESSAGE_STRINGBUILDER.toString();
        string = string.substring(0, string.length() - 1);
        if (!Objects.equals(this.string, string))
        {
            GUIFEATURESLOADER = new EntityListGUIFeatures(this.mixgui);
        }
        else
        {
            String[] string_array = new String[this.int_array.length];
            string_array[0] = this.string_array[0];
            string_array[1] = MESSAGE_STRINGBUILDER.toString();
            System.arraycopy(this.string_array, 1, string_array, 2, this.string_array.length - 1);
            this.mixgui.drawHoveringText(string_array, this.int_array, mouseX, mouseY, this.have_head);
//            super.drawText(mouseX, mouseY);
        }
    }
}