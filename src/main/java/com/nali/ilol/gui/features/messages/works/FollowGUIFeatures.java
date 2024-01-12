package com.nali.ilol.gui.features.messages.works;

import com.nali.ilol.entities.skinning.SkinningEntities;
import com.nali.ilol.gui.MixGui;
import com.nali.ilol.gui.features.GUIFeaturesLoader;
import com.nali.list.container.InventoryContainer;
import net.minecraft.util.text.translation.I18n;

public class FollowGUIFeatures extends GUIFeaturesLoader
{
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
            I18n.translateToLocal("gui.info.bf000")
        };
        this.createColor();
    }

    @Override
    public void drawText(int mouseX, int mouseY)
    {
        SkinningEntities skinningentities = ((InventoryContainer)this.mixgui.inventorySlots).skinningentities;
        this.mixgui.drawHoveringText(new String[]
        {
            this.string_array[0] + (skinningentities.client_work_byte_array[skinningentities.skinningentitiesbytes.FOLLOW()] == 1 ? this.b1 : this.b0),
            this.string_array[1],
            this.string_array[2],
            this.string_array[3],
        }, this.int_array, mouseX, mouseY, this.have_head);
    }
}
