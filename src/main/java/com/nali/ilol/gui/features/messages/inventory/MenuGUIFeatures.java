package com.nali.ilol.gui.features.messages.inventory;

import com.nali.ilol.gui.MixGui;
import com.nali.ilol.gui.features.GUIFeaturesLoader;
import net.minecraft.util.text.translation.I18n;

import static com.nali.list.gui.InventoryGui.PAGE;

public class MenuGUIFeatures extends GUIFeaturesLoader
{
    public String ss = I18n.translateToLocal("gui.info.ss");
    public String sp = I18n.translateToLocal("gui.info.sp");
    public String si = I18n.translateToLocal("gui.info.si");

    public MenuGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("gui.info.cv") + " : ",
            this.sp + " : " + I18n.translateToLocal("gui.info.sp0"),
            this.si + " : " + I18n.translateToLocal("gui.info.si0"),
            this.ss + " : " + I18n.translateToLocal("gui.info.ss0")
        };
        this.createColor();
    }

    @Override
    public void drawText(int mouseX, int mouseY)
    {
        this.mixgui.drawHoveringText(new String[]
        {
            this.string_array[0] + (PAGE == 2 ? this.ss : PAGE == 1 ? this.sp : this.si),
            this.string_array[1],
            this.string_array[2],
            this.string_array[3],
        }, this.int_array, mouseX, mouseY, this.have_head);
    }
}
