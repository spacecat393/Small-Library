package com.nali.small.gui.features.messages.inventory;

import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.gui.net.messages.inventory.AddTroublemakerGUINet;
import com.nali.small.system.Reference;
import net.minecraft.util.text.translation.I18n;

import static com.nali.small.gui.MixGui.GUINETLOADER;
import static com.nali.small.gui.MixGui.MESSAGE_STRINGBUILDER;

public class AddTroublemakerGUIFeatures extends GUIFeaturesLoader
{
    public AddTroublemakerGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("info." + Reference.MOD_ID + ".bu") + " " + I18n.translateToLocal("info." + Reference.MOD_ID + ".by"),
            I18n.translateToLocal("info." + Reference.MOD_ID + ".e3"),
            I18n.translateToLocal("info." + Reference.MOD_ID + ".eb"),
            I18n.translateToLocal("info." + Reference.MOD_ID + ".c3"),
            I18n.translateToLocal("info." + Reference.MOD_ID + ".c4")
        };
        this.loadColor(6);
//        this.int_array = new int[6];
//        this.loadColor();
//        this.int_array[5] = 0xFFFFFFFF;
        GUINETLOADER = new AddTroublemakerGUINet(mixgui);
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