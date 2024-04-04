package com.nali.small.gui.features.messages.inventory;

import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.gui.features.MessagesFeatures;
import com.nali.small.system.Reference;

public class TroublemakerGUIFeatures extends GUIFeaturesLoader
{
    public static int[] TROUBLEMAKER_INT_ARRAY;

    public TroublemakerGUIFeatures(MixGui mixgui)
    {
        super(mixgui);
        MessagesFeatures.initEntities(TROUBLEMAKER_INT_ARRAY, "gui.info." + Reference.MOD_ID + ".t4", this);
    }
}
