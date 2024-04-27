package com.nali.small.gui.features.messages.inventory;

import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.gui.features.MessagesFeatures;
import com.nali.small.system.Reference;
import net.minecraft.util.text.translation.I18n;

public class TroublemakerGUIFeatures extends GUIFeaturesLoader
{
    public static int[] TROUBLEMAKER_INT_ARRAY;

    public TroublemakerGUIFeatures(MixGui mixgui)
    {
        super(mixgui);
        MessagesFeatures.initEntities
        (
            TROUBLEMAKER_INT_ARRAY,
            I18n.translateToLocal("info." + Reference.MOD_ID + ".bw") + " " + I18n.translateToLocal("info." + Reference.MOD_ID + ".by"),
            this
        );
    }
}
