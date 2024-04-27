package com.nali.small.gui.features.messages.inventory;

import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.system.Reference;
import net.minecraft.util.text.translation.I18n;

public class WorkGUIFeatures extends GUIFeaturesLoader
{
    public WorkGUIFeatures(MixGui mixgui)
    {
        super(mixgui);
        this.string_array = new String[]
        {
            I18n.translateToLocal("info." + Reference.MOD_ID + ".c8")
        };
        this.createColor();
    }
}