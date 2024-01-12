package com.nali.ilol.gui.features.messages.player;

import com.nali.ilol.gui.MixGui;
import com.nali.ilol.gui.features.GUIFeaturesLoader;
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
