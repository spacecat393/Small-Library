package com.nali.small.gui.features.messages.works;

import com.nali.small.entities.memory.ClientEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
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
        ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)skinningentities.bothentitiesmemory;
        this.mixgui.drawHoveringText(new String[]
        {
            this.string_array[0] + (cliententitiesmemory.work_byte_array[cliententitiesmemory.workbytes.LOCK_INVENTORY()] == 1 ? this.lock : this.unlock),
            this.string_array[1],
            this.string_array[2],
        }, this.int_array, mouseX, mouseY, this.have_head);
    }
}
