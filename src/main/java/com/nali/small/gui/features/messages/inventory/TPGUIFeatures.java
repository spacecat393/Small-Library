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
            I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".tp0"),
            I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".tp1"),
            I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".tp2") + " : ",
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
//                this.string_array[2] + CapabilitiesRegistryHelper.CLIENT_CAPABILITY_OBJECT_ARRAYLIST.get(0)
            this.string_array[2] + Minecraft.getMinecraft().player.getEntityData().getInteger("sakura_nali")
        }, this.int_array, mouseX, mouseY, this.have_head);
    }
}