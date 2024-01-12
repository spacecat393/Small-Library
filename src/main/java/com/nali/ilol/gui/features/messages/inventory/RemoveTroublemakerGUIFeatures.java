package com.nali.ilol.gui.features.messages.inventory;

import com.nali.ilol.gui.MixGui;
import com.nali.ilol.gui.features.GUIFeaturesLoader;
import net.minecraft.util.text.translation.I18n;

import static com.nali.ilol.gui.MixGui.MESSAGE_STRINGBUILDER;

public class RemoveTroublemakerGUIFeatures extends GUIFeaturesLoader
{
    public RemoveTroublemakerGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("gui.info.t6"),
            I18n.translateToLocal("gui.info.st0"),
            I18n.translateToLocal("gui.info.st1")
        };
        this.int_array = new int[4];
        this.loadColor();
        this.int_array[3] = 0xFFFFFFFF;
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
        }, this.int_array, mouseX, mouseY, this.have_head);
    }
}
