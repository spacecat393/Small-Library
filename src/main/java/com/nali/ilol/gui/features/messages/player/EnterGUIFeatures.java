package com.nali.ilol.gui.features.messages.player;

import com.nali.ilol.gui.MixGui;
import com.nali.ilol.gui.features.GUIFeaturesLoader;
import net.minecraft.util.text.translation.I18n;

public class EnterGUIFeatures extends GUIFeaturesLoader
{
    public EnterGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("gui.info.a7"),
            I18n.translateToLocal("gui.info.a70")
        };
        this.createColor();
    }
}
