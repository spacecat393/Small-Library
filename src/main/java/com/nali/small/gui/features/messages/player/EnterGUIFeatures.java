package com.nali.small.gui.features.messages.player;

import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.system.Reference;
import net.minecraft.util.text.translation.I18n;

public class EnterGUIFeatures extends GUIFeaturesLoader
{
    public EnterGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("info." + Reference.MOD_ID + ".e5"),
            I18n.translateToLocal("info." + Reference.MOD_ID + ".c7")
        };
        this.createColor();
    }
}
