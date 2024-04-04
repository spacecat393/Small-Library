package com.nali.small.gui.features.messages.works;

import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.system.Reference;
import net.minecraft.util.text.translation.I18n;

public class CantProtectGUIFeatures extends GUIFeaturesLoader
{
    public CantProtectGUIFeatures(MixGui mixgui)
    {
        super(mixgui);
        this.string_array = new String[]
        {
            I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".c2")
        };
        this.createColor();
    }
}
