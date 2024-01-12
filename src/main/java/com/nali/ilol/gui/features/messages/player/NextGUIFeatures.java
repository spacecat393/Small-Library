package com.nali.ilol.gui.features.messages.player;

import com.nali.ilol.gui.MixGui;
import com.nali.ilol.gui.features.GUIFeaturesLoader;
import net.minecraft.util.text.translation.I18n;

import static com.nali.list.gui.PlayerGui.MAX_NEXT;
import static com.nali.list.gui.PlayerGui.NEXT;

public class NextGUIFeatures extends GUIFeaturesLoader
{
    public NextGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("gui.info.a6")
        };
        this.int_array = new int[2];
        this.loadColor();
        this.int_array[1] = 0xFFFFFFFF;
    }

    @Override
    public void drawText(int mouseX, int mouseY)
    {
        this.mixgui.drawHoveringText(new String[]
        {
            this.string_array[0],
            NEXT + " / " + MAX_NEXT
        }, this.int_array, mouseX, mouseY, this.have_head);
    }
}
