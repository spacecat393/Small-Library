package com.nali.small.gui.features.messages.works;

import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import net.minecraft.util.text.translation.I18n;

public class CantCareOwnerGUIFeatures extends GUIFeaturesLoader
{
    public CantCareOwnerGUIFeatures(MixGui mixgui)
    {
        super(mixgui);
        this.string_array = new String[]
        {
            I18n.translateToLocal("gui.info.c9")
        };
        this.createColor();
    }
}
