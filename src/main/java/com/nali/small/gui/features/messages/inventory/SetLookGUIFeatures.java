package com.nali.small.gui.features.messages.inventory;

import com.nali.list.container.InventoryContainer;
import com.nali.small.entity.EntityLeInv;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.gui.net.messages.inventory.SetLookGUINet;
import com.nali.small.system.Reference;
import net.minecraft.util.text.translation.I18n;

import static com.nali.small.gui.MixGui.GUINETLOADER;
import static com.nali.small.gui.MixGui.MESSAGE_STRINGBUILDER;

public class SetLookGUIFeatures extends GUIFeaturesLoader
{
    public SetLookGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            "0 " + I18n.translateToLocal("info." + Reference.MOD_ID + ".bo") + " : ",
            "1 " + I18n.translateToLocal("info." + Reference.MOD_ID + ".bp") + " : ",
            I18n.translateToLocal("info." + Reference.MOD_ID + ".e8"),
        };
        this.loadColor(4);
//        this.int_array = new int[4];
//        this.loadColor();
//        this.int_array[3] = 0xFFFFFFFF;
        GUINETLOADER = new SetLookGUINet(mixgui);
    }

    @Override
    public void drawText(int mouseX, int mouseY)
    {
        EntityLeInv skinningentities = ((InventoryContainer)this.mixgui.inventorySlots).skinningentities;
        this.mixgui.drawHoveringText(new String[]
        {
            MESSAGE_STRINGBUILDER.toString(),
            this.string_array[0] + skinningentities.rotationYaw,
            this.string_array[1] + skinningentities.rotationPitch,
            this.string_array[2]
        }, this.int_array, mouseX, mouseY, this.have_head);
    }
}