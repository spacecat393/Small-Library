package com.nali.small.gui.features.messages.inventory;

import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import net.minecraft.util.text.translation.I18n;

import static com.nali.small.gui.MixGui.MESSAGE_STRINGBUILDER;

public class AddTargetGUIFeatures extends GUIFeaturesLoader
{
    public AddTargetGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("gui.info.t0"),
            I18n.translateToLocal("gui.info.st0"),
            I18n.translateToLocal("gui.info.st1"),
            I18n.translateToLocal("gui.info.st4"),
            I18n.translateToLocal("gui.info.st5")
        };
        this.int_array = new int[6];
        this.loadColor();
        this.int_array[5] = 0xFFFFFFFF;
    }

    @Override
    public void drawText(int mouseX, int mouseY)
    {
        this.mixgui.drawHoveringText(new String[]
        {
            MESSAGE_STRINGBUILDER.toString(),
            this.string_array[0],
            this.string_array[1],
            this.string_array[2],
            this.string_array[3],
            this.string_array[4]
        }, this.int_array, mouseX, mouseY, this.have_head);
    }
}