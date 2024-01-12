package com.nali.ilol.gui.features.messages.player;

import com.nali.ilol.gui.MixGui;
import com.nali.ilol.gui.features.GUIFeaturesLoader;
import net.minecraft.util.text.translation.I18n;

import static com.nali.ilol.gui.MixGui.MESSAGE_STRINGBUILDER;

public class DropGUIFeatures extends GUIFeaturesLoader
{
    public DropGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("gui.info.a3"),
            I18n.translateToLocal("gui.info.a30"),
            I18n.translateToLocal("gui.info.a31")
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
            this.string_array[2]
        }, this.int_array, mouseX, mouseY, this.have_head);
    }
}
