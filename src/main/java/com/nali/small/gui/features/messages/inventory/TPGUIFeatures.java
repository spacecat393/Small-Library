package com.nali.small.gui.features.messages.inventory;

import com.nali.small.capabilities.CapabilitiesRegistryHelper;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import net.minecraft.util.text.translation.I18n;

public class TPGUIFeatures extends GUIFeaturesLoader
{
    public TPGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("gui.info.tp0"),
            I18n.translateToLocal("gui.info.tp1"),
            I18n.translateToLocal("gui.info.tp2") + " : ",
        };
        this.createColor();
    }

    @Override
    public void drawText(int mouseX, int mouseY)
    {
        this.mixgui.drawHoveringText(new String[]
        {
            this.string_array[0],
            this.string_array[1],
            this.string_array[2] + CapabilitiesRegistryHelper.CLIENT_CAPABILITY_OBJECT_ARRAYLIST.get(0),
        }, this.int_array, mouseX, mouseY, this.have_head);
    }
}