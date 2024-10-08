package com.nali.small.gui.features.messages.inventory;

import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.system.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.translation.I18n;

public class TPGUIFeatures extends GUIFeaturesLoader
{
    public TPGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("info." + Small.ID + ".bl4"),
            I18n.translateToLocal("info." + Small.ID + ".bl5"),
            I18n.translateToLocal("info." + Small.ID + ".br") + " : ",
        };
        this.createColor();
    }

    @Override
    public void drawText(int mouseX, int mouseY)
    {
        this.mixgui.drawHoveringText(new String[]
        {
            this.string_array[0],
            this.string_array[1],
//                this.string_array[2] + CapabilitiesRegistryHelper.CLIENT_CAPABILITY_OBJECT_LIST.get(0)
            this.string_array[2] + Minecraft.getMinecraft().player.getEntityData().getInteger("sakura_nali")
        }, this.int_array, mouseX, mouseY, this.have_head);
    }
}