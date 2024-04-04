package com.nali.small.gui.features.messages;

import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.system.Reference;
import net.minecraft.util.text.translation.I18n;

import static com.nali.small.gui.MixGui.MESSAGE_STRINGBUILDER;

public class AttributeStatGUIFeatures extends GUIFeaturesLoader
{
    public AttributeStatGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".s1"),
            I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".s10"),
            I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".s11"),
        };
        this.loadColor(4);
//        this.int_array = new int[4];
//        this.loadColor();
//        this.int_array[3] = 0xFFFFFFFF;
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
