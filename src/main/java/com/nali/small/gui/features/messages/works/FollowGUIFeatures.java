package com.nali.small.gui.features.messages.works;

import com.nali.small.entities.memory.ClientEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.list.container.InventoryContainer;
import net.minecraft.util.text.translation.I18n;

public class FollowGUIFeatures extends GUIFeaturesLoader
{
    public static byte STATE;//remote1 walk_to2
    public static byte PAGE;//p0-1

    public String b0 = I18n.translateToLocal("gui.info.bf0");
    public String b1 = I18n.translateToLocal("gui.info.bf1");

    public FollowGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("gui.info.cv") + " : ",
            this.b0 + " : " + I18n.translateToLocal("gui.info.bf00"),
            this.b1 + " : " + I18n.translateToLocal("gui.info.bf10"),
//            I18n.translateToLocal("gui.info.bf000")
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
            this.string_array[0] + ((cliententitiesmemory.work_byte_array[cliententitiesmemory.workbytes.FOLLOW() / 8] >> cliententitiesmemory.workbytes.FOLLOW() % 8 & 1) == 1 ? this.b1 : this.b0),
            this.string_array[1],
            this.string_array[2],
//            this.string_array[3],
        }, this.int_array, mouseX, mouseY, this.have_head);
    }
}
