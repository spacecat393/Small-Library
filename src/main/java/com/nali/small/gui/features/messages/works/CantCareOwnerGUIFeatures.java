package com.nali.small.gui.features.messages.works;

import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.system.Reference;
import net.minecraft.util.text.translation.I18n;

public class CantCareOwnerGUIFeatures extends GUIFeaturesLoader
{
    public CantCareOwnerGUIFeatures(MixGui mixgui)
    {
        super(mixgui);
        this.string_array = new String[]
        {
            I18n.translateToLocal("info." + Reference.MOD_ID + ".z5")
        };
        this.createColor();
    }
}
