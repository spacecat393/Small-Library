package com.nali.small.gui.features.messages.inventory;

import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.system.Reference;
import net.minecraft.util.text.translation.I18n;

public class BoxGUIFeatures extends GUIFeaturesLoader
{
    public BoxGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("info." + Small.ID + ".bk6"),
            I18n.translateToLocal("info." + Small.ID + ".bk7")
        };
        this.createColor();
    }
}
