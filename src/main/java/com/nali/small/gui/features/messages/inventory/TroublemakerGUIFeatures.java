package com.nali.small.gui.features.messages.inventory;

import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.gui.features.MessagesFeatures;

public class TroublemakerGUIFeatures extends GUIFeaturesLoader
{
    public static int[] TROUBLEMAKER_INT_ARRAY;

    public TroublemakerGUIFeatures(MixGui mixgui)
    {
        super(mixgui);
        MessagesFeatures.initEntities(TROUBLEMAKER_INT_ARRAY, "gui.info.t4", this);
    }
}
