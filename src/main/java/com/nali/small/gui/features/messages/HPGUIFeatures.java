package com.nali.small.gui.features.messages;

import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.list.container.InventoryContainer;
import net.minecraft.util.text.translation.I18n;

public class HPGUIFeatures extends GUIFeaturesLoader
{
    public HPGUIFeatures(MixGui mixgui)
    {
        super(mixgui);
        this.string_array = new String[]
        {
            I18n.translateToLocal("gui.info.hp") + " : ",
            I18n.translateToLocal("gui.info.mhp") + " : "
        };
        this.createColor();
    }

    @Override
    public void drawText(int mouseX, int mouseY)
    {
        SkinningEntities skinningentities = ((InventoryContainer)this.mixgui.inventorySlots).skinningentities;
        this.mixgui.drawHoveringText(new String[]
        {
            this.string_array[0] + skinningentities.getHealth(),
            this.string_array[1] + skinningentities.getMaxHealth(),
        }, this.int_array, mouseX, mouseY, this.have_head);
    }
}
