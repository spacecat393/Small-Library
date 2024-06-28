package com.nali.small.gui.features.messages.inventory;

import com.nali.small.entity.EntityRegistry;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.system.Reference;
import net.minecraft.util.text.translation.I18n;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.nali.small.gui.MixGui.*;

public class EntityListGUIFeatures extends GUIFeaturesLoader
{
    public String string;

    public EntityListGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);

        this.string = MESSAGE_STRINGBUILDER.toString();
        this.string = this.string.substring(0, this.string.length() - 1);
        List<String> string_list = new ArrayList();

        int index = 1;
        for (Object o : EntityRegistry.ENTITY_KEY_ARRAY)
        {
            String name = ((Class)o).getName();
            if (name.contains(this.string))
            {
                string_list.add((index - 1) + " " + name);
            }
            ++index;
//            this.string_array[index] = (index++ - 1) + " " + ((Class)o).getName();
        }

        this.string_array = new String[string_list.size() + 3];
        this.string_array[0] = I18n.translateToLocal("info." + Small.ID + ".bz");

        for (int i = 1; i < this.string_array.length - 2; ++i)
        {
            this.string_array[i] = string_list.get(i - 1);
        }

        this.string_array[this.string_array.length - 2] = I18n.translateToLocal("info." + Small.ID + ".c3");
        this.string_array[this.string_array.length - 1] = I18n.translateToLocal("info." + Small.ID + ".c4");

        index = this.string_array.length + 1;
        this.int_array = new int[index];
        this.loadColor();
        this.int_array[this.string_array.length] = (index) % 2 == 0 ? 0xFFFFFFFF : 0xFFF85A52;
        GUINETLOADER = null;
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
            string_array[0] = MESSAGE_STRINGBUILDER.toString();
            string_array[1] = this.string_array[0];
            System.arraycopy(this.string_array, 1, string_array, 2, this.string_array.length - 1);
            this.mixgui.drawHoveringText(string_array, this.int_array, mouseX, mouseY, this.have_head);
//            super.drawText(mouseX, mouseY);
        }
    }
}