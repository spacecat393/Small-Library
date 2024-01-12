package com.nali.ilol.gui.features.messages;

import com.nali.ilol.gui.MixGui;
import com.nali.ilol.gui.features.GUIFeaturesLoader;
import com.nali.ilol.gui.features.MessagesFeatures;

public class TargetGUIFeatures extends GUIFeaturesLoader
{
    public static int[] TARGET_INT_ARRAY;

    public TargetGUIFeatures(MixGui mixgui)
    {
        super(mixgui);
        MessagesFeatures.initEntities(TARGET_INT_ARRAY, "gui.info.t2", this);
    }
}
