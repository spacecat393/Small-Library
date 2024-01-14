package com.nali.small.gui.features;

import com.nali.small.gui.MixGui;

public class GUIFeaturesLoader
{
    public String[] string_array;
    public int[] int_array;
    public MixGui mixgui;
    public boolean have_head;

//    public int tx, ty, txs, tys, l, t;

    public GUIFeaturesLoader(MixGui mixgui, boolean have_head)
    {
        this.mixgui = mixgui;
        this.have_head = have_head;
    }

    public GUIFeaturesLoader(MixGui mixgui)
    {
        this.mixgui = mixgui;
    }

    public void createColor()
    {
        this.int_array = new int[this.string_array.length];
        this.loadColor();
    }

    public void loadColor()
    {
        for (int i = 0; i < this.string_array.length; ++i)
        {
            this.int_array[i] = (i + 1) % 2 == 0 ? 0xFFFFFFFF : 0xFFF85A52;
        }
    }

    public void drawText(int mouseX, int mouseY)
    {
        this.mixgui.drawHoveringText(this.string_array, this.int_array, mouseX, mouseY, this.have_head);
    }

//    public void drawTexture()
//    {
//        this.mixgui.drawTexturedModalRect(this.mixgui.getGuiLeft() + this.l, this.mixgui.getGuiTop() + this.t, this.tx, this.ty, this.txs, this.tys);
//    }
}
