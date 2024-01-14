package com.nali.small.gui.features.messages.player;

import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import net.minecraft.util.text.translation.I18n;

public class RandomBGUIFeatures extends GUIFeaturesLoader
{
    public RandomBGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("gui.info.a1"),
            I18n.translateToLocal("gui.info.a10")
        };
        this.createColor();
    }
}
