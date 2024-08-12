package com.nali.small.gui.features.messages.player;

import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.system.Reference;
import net.minecraft.util.text.translation.I18n;

public class FetchGUIFeatures extends GUIFeaturesLoader
{
    public FetchGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("info." + Small.ID + ".bl"),
            I18n.translateToLocal("info." + Small.ID + ".c5"),
            I18n.translateToLocal("info." + Small.ID + ".c6")
        };
        this.createColor();
    }
}
