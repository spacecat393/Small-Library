package com.nali.small.gui.features.messages.player;

import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.system.Reference;
import net.minecraft.util.text.translation.I18n;

public class MimiTalkGUIFeatures extends GUIFeaturesLoader
{
    public MimiTalkGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("info." + Reference.MOD_ID + ".bk4"),
            I18n.translateToLocal("info." + Reference.MOD_ID + ".bk5")
        };
        this.createColor();
    }
}
