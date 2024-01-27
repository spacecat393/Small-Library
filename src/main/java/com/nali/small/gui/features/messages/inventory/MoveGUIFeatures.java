package com.nali.small.gui.features.messages.inventory;

import com.nali.list.container.InventoryContainer;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import net.minecraft.util.text.translation.I18n;

import static com.nali.small.gui.MixGui.MESSAGE_STRINGBUILDER;

public class MoveGUIFeatures extends GUIFeaturesLoader
{
    public MoveGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("gui.info.sm0"),
            "X : ",
            "Y : ",
            "Z : ",
            I18n.translateToLocal("gui.info.sm1")
        };
        this.int_array = new int[6];
        this.loadColor();
        this.int_array[5] = 0xFFFFFFFF;
    }

    @Override
    public void drawText(int mouseX, int mouseY)
    {
        SkinningEntities skinningentities = ((InventoryContainer)this.mixgui.inventorySlots).skinningentities;
        this.mixgui.drawHoveringText(new String[]
        {
            MESSAGE_STRINGBUILDER.toString(),
            this.string_array[0],
            this.string_array[1] + skinningentities.posX,
            this.string_array[2] + skinningentities.posY,
            this.string_array[3] + skinningentities.posZ,
            this.string_array[4]
        }, this.int_array, mouseX, mouseY, this.have_head);
    }
}