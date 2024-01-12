package com.nali.ilol.gui.features.messages.inventory;

import com.nali.ilol.gui.MixGui;
import com.nali.ilol.gui.features.GUIFeaturesLoader;
import com.nali.ilol.gui.features.MessagesFeatures;

public class TroublemakerGUIFeatures extends GUIFeaturesLoader
{
    public static int[] TROUBLEMAKER_INT_ARRAY;

    public TroublemakerGUIFeatures(MixGui mixgui)
    {
        super(mixgui);
        MessagesFeatures.initEntities(TROUBLEMAKER_INT_ARRAY, "gui.info.t4", this);
    }
}
