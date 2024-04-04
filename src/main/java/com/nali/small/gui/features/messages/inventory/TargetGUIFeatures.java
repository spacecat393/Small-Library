package com.nali.small.gui.features.messages.inventory;

import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.gui.features.MessagesFeatures;
import com.nali.small.system.Reference;

public class TargetGUIFeatures extends GUIFeaturesLoader
{
    public static int[] TARGET_INT_ARRAY;

    public TargetGUIFeatures(MixGui mixgui)
    {
        super(mixgui);
        MessagesFeatures.initEntities(TARGET_INT_ARRAY, "gui.info." + Reference.MOD_ID + ".t2", this);
    }
}
