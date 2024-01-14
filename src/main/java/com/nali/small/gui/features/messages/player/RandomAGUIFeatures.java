package com.nali.small.gui.features.messages.player;

import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import net.minecraft.util.text.translation.I18n;

public class RandomAGUIFeatures extends GUIFeaturesLoader
{
    public RandomAGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("gui.info.a0"),
            I18n.translateToLocal("gui.info.a00")
        };
        this.createColor();
    }
}
