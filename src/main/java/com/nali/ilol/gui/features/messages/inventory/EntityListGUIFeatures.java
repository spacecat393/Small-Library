package com.nali.ilol.gui.features.messages.inventory;

import com.nali.ilol.entities.EntitiesRegistryHelper;
import com.nali.ilol.gui.MixGui;
import com.nali.ilol.gui.features.GUIFeaturesLoader;
import net.minecraft.util.text.translation.I18n;

public class EntityListGUIFeatures extends GUIFeaturesLoader
{
    public EntityListGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[EntitiesRegistryHelper.ENTITY_KEY_ARRAY.length + 1];
        this.string_array[0] = I18n.translateToLocal("gui.info.t3");
        int index = 1;
        for (Object o : EntitiesRegistryHelper.ENTITY_KEY_ARRAY)
        {
            this.string_array[index] = (index++ - 1) + " " + ((Class)o).getName();
        }
        this.createColor();
    }
}