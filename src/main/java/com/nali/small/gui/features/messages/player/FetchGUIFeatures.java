package com.nali.small.gui.features.messages.player;

import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import net.minecraft.util.text.translation.I18n;

public class FetchGUIFeatures extends GUIFeaturesLoader
{
    public FetchGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("gui.info.a5"),
            I18n.translateToLocal("gui.info.a50"),
            I18n.translateToLocal("gui.info.a51")
        };
        this.createColor();
    }
}
