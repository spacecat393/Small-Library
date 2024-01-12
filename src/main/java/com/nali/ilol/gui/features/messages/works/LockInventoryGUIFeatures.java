package com.nali.ilol.gui.features.messages.works;

import com.nali.ilol.entities.skinning.SkinningEntities;
import com.nali.ilol.gui.MixGui;
import com.nali.ilol.gui.features.GUIFeaturesLoader;
import com.nali.list.container.InventoryContainer;
import net.minecraft.util.text.translation.I18n;

public class LockInventoryGUIFeatures extends GUIFeaturesLoader
{
    public String lock = I18n.translateToLocal("gui.info.l");
    public String unlock = I18n.translateToLocal("gui.info.ul");

    public LockInventoryGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("gui.info.cv") + " : ",
            this.lock + " : " + I18n.translateToLocal("gui.info.l0"),
            this.unlock + " : " + I18n.translateToLocal("gui.info.ul0")
        };
        this.createColor();
    }

    @Override
    public void drawText(int mouseX, int mouseY)
    {
        SkinningEntities skinningentities = ((InventoryContainer)this.mixgui.inventorySlots).skinningentities;
        this.mixgui.drawHoveringText(new String[]
        {
            this.string_array[0] + (skinningentities.client_work_byte_array[skinningentities.skinningentitiesbytes.LOCK_INVENTORY()] == 1 ? this.lock : this.unlock),
            this.string_array[1],
            this.string_array[2],
        }, this.int_array, mouseX, mouseY, this.have_head);
    }
}
