package com.nali.small.gui.features.messages;

import com.nali.list.container.InventoryContainer;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.gui.net.messages.inventory.ScaleGUINet;
import net.minecraft.util.text.translation.I18n;

import static com.nali.small.gui.MixGui.GUINETLOADER;
import static com.nali.small.gui.MixGui.MESSAGE_STRINGBUILDER;

public class ScaleGUIFeatures extends GUIFeaturesLoader
{
    public ScaleGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("gui.info.s0") + " : ",
            I18n.translateToLocal("gui.info.s00"),
            I18n.translateToLocal("gui.info.s01"),
        };
        this.loadColor(4);
//        this.int_array = new int[4];
//        this.loadColor();
//        this.int_array[3] = 0xFFFFFFFF;
        GUINETLOADER = new ScaleGUINet(mixgui);
    }

    @Override
    public void drawText(int mouseX, int mouseY)
    {
        SkinningEntities skinningentities = ((InventoryContainer)this.mixgui.inventorySlots).skinningentities;
        this.mixgui.drawHoveringText(new String[]
        {
            MESSAGE_STRINGBUILDER.toString(),
            this.string_array[0] + skinningentities.getDataManager().get(skinningentities.getFloatDataParameterArray()[0]),
            this.string_array[1],
            this.string_array[2]
        }, this.int_array, mouseX, mouseY, this.have_head);
    }
}
