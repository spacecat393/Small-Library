package com.nali.small.gui.features.messages.works;

import com.nali.list.container.InventoryContainer;
import com.nali.small.entities.memory.client.ClientEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.system.Reference;
import net.minecraft.util.text.translation.I18n;

public class WalkToGUIFeatures extends GUIFeaturesLoader
{
    public String b0 = I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".wt0");
    public String b1 = I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".wt1");

    public WalkToGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".cv") + " : ",
            this.b0 + " : " + I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".wt00"),
            this.b1 + " : " + I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".wt10")
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
            this.string_array[0] + ((cliententitiesmemory.work_byte_array[cliententitiesmemory.workbytes.WALK_TO() / 8] >> cliententitiesmemory.workbytes.WALK_TO() % 8 & 1) == 1 ? this.b1 : this.b0),
            this.string_array[1],
            this.string_array[2],
        }, this.int_array, mouseX, mouseY, this.have_head);
    }
}
