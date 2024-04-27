package com.nali.small.gui.features.messages.inventory;

import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.system.Reference;
import net.minecraft.util.text.translation.I18n;

import static com.nali.list.gui.InventoryGui.PAGE;

public class MenuGUIFeatures extends GUIFeaturesLoader
{
    public String ss = I18n.translateToLocal("info." + Reference.MOD_ID + ".bj4");
    public String sp = I18n.translateToLocal("info." + Reference.MOD_ID + ".bj0");
    public String si = I18n.translateToLocal("info." + Reference.MOD_ID + ".bj2");
    public String sa = I18n.translateToLocal("info." + Reference.MOD_ID + ".bj6");

    public MenuGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("info." + Reference.MOD_ID + ".b0") + " : ",
            this.sp + " : " + I18n.translateToLocal("info." + Reference.MOD_ID + ".bj1"),
            this.si + " : " + I18n.translateToLocal("info." + Reference.MOD_ID + ".bj3"),
            this.ss + " : " + I18n.translateToLocal("info." + Reference.MOD_ID + ".bj5"),
            this.sa + " : " + I18n.translateToLocal("info." + Reference.MOD_ID + ".bj7"),
        };
        this.createColor();
    }

    @Override
    public void drawText(int mouseX, int mouseY)
    {
        this.mixgui.drawHoveringText(new String[]
        {
            this.string_array[0] + (PAGE == 3 ? this.sa : PAGE == 2 ? this.ss : PAGE == 1 ? this.sp : this.si),
            this.string_array[1],
            this.string_array[2],
            this.string_array[3],
            this.string_array[4]
        }, this.int_array, mouseX, mouseY, this.have_head);
    }
}
