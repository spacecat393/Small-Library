package com.nali.ilol.gui.features.messages.player;

import com.nali.ilol.gui.MixGui;
import com.nali.ilol.gui.features.GUIFeaturesLoader;
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
