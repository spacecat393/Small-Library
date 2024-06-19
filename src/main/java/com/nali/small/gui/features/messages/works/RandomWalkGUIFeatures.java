package com.nali.small.gui.features.messages.works;

import com.nali.list.container.InventoryContainer;
import com.nali.small.entity.memo.client.ClientLe;
import com.nali.small.entity.EntityLeInv;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.system.Reference;
import net.minecraft.util.text.translation.I18n;

public class RandomWalkGUIFeatures extends GUIFeaturesLoader
{
    public String b0 = I18n.translateToLocal("info." + Reference.MOD_ID + ".b40");
    public String b1 = I18n.translateToLocal("info." + Reference.MOD_ID + ".b41");

    public RandomWalkGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("info." + Reference.MOD_ID + ".b0") + " : ",
            this.b0 + " : " + I18n.translateToLocal("info." + Reference.MOD_ID + ".b42"),
            this.b1 + " : " + I18n.translateToLocal("info." + Reference.MOD_ID + ".b43")
        };
        this.createColor();
    }

    @Override
    public void drawText(int mouseX, int mouseY)
    {
        EntityLeInv skinningentities = ((InventoryContainer)this.mixgui.inventorySlots).skinningentities;
        ClientLe cliententitiesmemory = (ClientLe)skinningentities.bothentitiesmemory;
        this.mixgui.drawHoveringText(new String[]
        {
            this.string_array[0] + ((cliententitiesmemory.work_byte_array[cliententitiesmemory.workbytes.RANDOM_WALK() / 8] >> cliententitiesmemory.workbytes.RANDOM_WALK() % 8 & 1) == 1 ? this.b1 : this.b0),
            this.string_array[1],
            this.string_array[2],
        }, this.int_array, mouseX, mouseY, this.have_head);
    }
}
