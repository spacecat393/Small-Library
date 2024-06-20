package com.nali.small.gui.features.messages.player;

import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.system.Reference;
import net.minecraft.util.text.translation.I18n;

import static com.nali.list.container.gui.PlayerGui.MAX_NEXT;
import static com.nali.list.container.gui.PlayerGui.NEXT;

public class BackGUIFeatures extends GUIFeaturesLoader
{
    public BackGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("info." + Reference.MOD_ID + ".bk")
        };
        this.loadColor(2);
//        this.int_array = new int[2];
//        this.loadColor();
//        this.int_array[1] = 0xFFFFFFFF;
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
