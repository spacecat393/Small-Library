package com.nali.small.gui.features.messages.works;

import com.nali.list.container.InventoryContainer;
import com.nali.small.entities.memory.client.ClientEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.gui.net.messages.inventory.SetGetItemGUINet;
import com.nali.small.system.Reference;
import net.minecraft.util.text.translation.I18n;

import static com.nali.small.gui.MixGui.GUINETLOADER;
import static com.nali.small.gui.MixGui.MESSAGE_STRINGBUILDER;

public class GetItemGUIFeatures extends GUIFeaturesLoader
{
    public static byte STATE;//move_to | remote_xp remote_item can_take_xp can_take_item walk_to_xp walk_to_item
    public static byte PAGE;//p0-2

    public String b0 = I18n.translateToLocal("info." + Reference.MOD_ID + ".b30");
    public String b1 = I18n.translateToLocal("info." + Reference.MOD_ID + ".b31");

    public GetItemGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("info." + Reference.MOD_ID + ".b0") + " : ",
            this.b0 + " : " + I18n.translateToLocal("info." + Reference.MOD_ID + ".b32"),
            this.b1 + " : " + I18n.translateToLocal("info." + Reference.MOD_ID + ".b33"),
            I18n.translateToLocal("info." + Reference.MOD_ID + ".e0") + " <",
            I18n.translateToLocal("info." + Reference.MOD_ID + ".b1"),
            "1.1 " + I18n.translateToLocal("info." + Reference.MOD_ID + ".b6") + " : ",
            "1.2 " + I18n.translateToLocal("info." + Reference.MOD_ID + ".b3") + " : ",
            "1.3 " + I18n.translateToLocal("info." + Reference.MOD_ID + ".b4") + " : ",
            I18n.translateToLocal("info." + Reference.MOD_ID + ".b2"),
            "2.1 " + I18n.translateToLocal("info." + Reference.MOD_ID + ".b6") + " : ",
            "2.2 " + I18n.translateToLocal("info." + Reference.MOD_ID + ".b3") + " : ",
            "2.3 " + I18n.translateToLocal("info." + Reference.MOD_ID + ".b4") + " : ",
            I18n.translateToLocal("info." + Reference.MOD_ID + ".e2"),
            I18n.translateToLocal("info." + Reference.MOD_ID + ".c0")
        };
        this.loadColor(8);
        GUINETLOADER = new SetGetItemGUINet(mixgui);
    }

    @Override
    public void drawText(int mouseX, int mouseY)
    {
        if (PAGE == 2)
        {
            this.mixgui.drawHoveringText(new String[]
            {
                MESSAGE_STRINGBUILDER.toString(),
                this.string_array[8],
                this.string_array[9] + ((STATE & 4) == 4 ? "ON" : "OFF"),
                this.string_array[10] + ((STATE & 64) == 64 ? "ON" : "OFF"),
                this.string_array[11] + ((STATE & 16) == 16 ? "ON" : "OFF"),
                this.string_array[12],
                this.string_array[13],
                this.string_array[3] + PAGE + "/2>"
            }, this.int_array, mouseX, mouseY, this.have_head);
        }
        else if (PAGE == 1)
        {
            this.mixgui.drawHoveringText(new String[]
            {
                MESSAGE_STRINGBUILDER.toString(),
                this.string_array[4],
                this.string_array[5] + ((STATE & 2) == 2 ? "ON" : "OFF"),
                this.string_array[6] + ((STATE & 32) == 32 ? "ON" : "OFF"),
                this.string_array[7] + ((STATE & 8) == 8 ? "ON" : "OFF"),
                this.string_array[12],
                this.string_array[13],
                this.string_array[3] + PAGE + "/2>"
            }, this.int_array, mouseX, mouseY, this.have_head);
        }
        else
        {
            SkinningEntities skinningentities = ((InventoryContainer)this.mixgui.inventorySlots).skinningentities;
            ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)skinningentities.bothentitiesmemory;

            this.mixgui.drawHoveringText(new String[]
            {
                MESSAGE_STRINGBUILDER.toString(),
                this.string_array[0] + ((cliententitiesmemory.work_byte_array[cliententitiesmemory.workbytes.GET_ITEM() / 8] >> cliententitiesmemory.workbytes.GET_ITEM() % 8 & 1) == 1 ? this.b1 : this.b0),
                this.string_array[1],
                this.string_array[2],
                this.string_array[3] + PAGE + "/2>"
            }, this.int_array, mouseX, mouseY, this.have_head);
        }
    }
}
