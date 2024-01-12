package com.nali.ilol.gui.features.messages.player;

import com.nali.ilol.gui.MixGui;
import com.nali.ilol.gui.features.GUIFeaturesLoader;
import net.minecraft.util.text.translation.I18n;

public class MimiTalkGUIFeatures extends GUIFeaturesLoader
{
    public MimiTalkGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("gui.info.a2"),
            I18n.translateToLocal("gui.info.a20")
        };
        this.createColor();
    }
}
