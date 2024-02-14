package com.nali.small.gui.features.messages.player;

import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;

import static com.nali.small.gui.MixGui.MESSAGE_STRINGBUILDER;

public class DebugGUIFeatures extends GUIFeaturesLoader
{
    public DebugGUIFeatures(MixGui mixgui)
    {
        super(mixgui);
        this.int_array = new int[1];
        this.int_array[0] = 0xFFFFFFFF;
    }

    @Override
    public void drawText(int mouseX, int mouseY)
    {
        this.mixgui.drawHoveringText(new String[]
        {
            MESSAGE_STRINGBUILDER.toString(),
        }, this.int_array, mouseX, mouseY, this.have_head);
    }
}
