package com.nali.small.gui.features.messages.works;

import com.nali.list.container.InventoryContainer;
import com.nali.small.entities.memory.client.ClientEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.gui.net.messages.inventory.SetGetItemGUINet;
import net.minecraft.util.text.translation.I18n;

import static com.nali.small.gui.MixGui.GUINETLOADER;
import static com.nali.small.gui.MixGui.MESSAGE_STRINGBUILDER;

public class GetItemGUIFeatures extends GUIFeaturesLoader
{
    public static byte STATE;//remote_xp remote_item can_take_xp can_take_item walk_to_xp walk_to_item
    public static byte PAGE;//p0-2

    public String b0 = I18n.translateToLocal("gui.info.bfi0");
    public String b1 = I18n.translateToLocal("gui.info.bfi1");

    public GetItemGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("gui.info.cv") + " : ",
            this.b0 + " : " + I18n.translateToLocal("gui.info.bfi00"),
            this.b1 + " : " + I18n.translateToLocal("gui.info.bfi10"),
            I18n.translateToLocal("gui.info.mi20") + " <",
            I18n.translateToLocal("gui.info.bfi20"),
            "1.1 " + I18n.translateToLocal("gui.info.mi29") + " : ",
            "1.2 " + I18n.translateToLocal("gui.info.bfi22") + " : ",
            "1.3 " + I18n.translateToLocal("gui.info.bfi23") + " : ",
            I18n.translateToLocal("gui.info.bfi21"),
            "2.1 " + I18n.translateToLocal("gui.info.mi29") + " : ",
            "2.2 " + I18n.translateToLocal("gui.info.bfi22") + " : ",
            "2.3 " + I18n.translateToLocal("gui.info.bfi23") + " : ",
            I18n.translateToLocal("gui.info.mi26"),
            I18n.translateToLocal("gui.info.mi27")
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
                this.string_array[9] + ((STATE & 2) == 2 ? "ON" : "OFF"),
                this.string_array[10] + ((STATE & 32) == 32 ? "ON" : "OFF"),
                this.string_array[11] + ((STATE & 8) == 8 ? "ON" : "OFF"),
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
                this.string_array[5] + ((STATE & 1) == 1 ? "ON" : "OFF"),
                this.string_array[6] + ((STATE & 16) == 16 ? "ON" : "OFF"),
                this.string_array[7] + ((STATE & 4) == 4 ? "ON" : "OFF"),
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
