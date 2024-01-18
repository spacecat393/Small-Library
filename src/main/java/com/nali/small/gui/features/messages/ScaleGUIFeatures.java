package com.nali.small.gui.features.messages;

import com.nali.list.container.InventoryContainer;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import net.minecraft.util.text.translation.I18n;

public class ScaleGUIFeatures extends GUIFeaturesLoader
{
    public ScaleGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("gui.info.s0") + " : ",
            I18n.translateToLocal("gui.info.ss00"),
            I18n.translateToLocal("gui.info.ss01"),
        };
        this.createColor();
    }

    @Override
    public void drawText(int mouseX, int mouseY)
    {
        SkinningEntities skinningentities = ((InventoryContainer)this.mixgui.inventorySlots).skinningentities;
        this.mixgui.drawHoveringText(new String[]
        {
            this.string_array[0] + skinningentities.getDataManager().get(skinningentities.getFloatDataParameterArray()[0]),
            this.string_array[1],
            this.string_array[2]
        }, this.int_array, mouseX, mouseY, this.have_head);
    }
}
