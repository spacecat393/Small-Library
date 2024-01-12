package com.nali.ilol.gui.features.messages.inventory;

import com.nali.ilol.gui.MixGui;
import com.nali.ilol.gui.features.GUIFeaturesLoader;
import net.minecraft.util.text.translation.I18n;

public class BoxGUIFeatures extends GUIFeaturesLoader
{
    public BoxGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("gui.info.i0"),
            I18n.translateToLocal("gui.info.i00")
        };
        this.createColor();
    }
}
