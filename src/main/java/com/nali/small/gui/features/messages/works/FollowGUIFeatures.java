package com.nali.small.gui.features.messages.works;

import com.nali.list.container.InventoryContainer;
import com.nali.small.entity.memo.client.ClientLe;
import com.nali.small.entity.EntityLeInv;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.gui.net.messages.inventory.SetFollowGUINet;
import com.nali.small.system.Reference;
import net.minecraft.util.text.translation.I18n;

import static com.nali.small.gui.MixGui.GUINETLOADER;
import static com.nali.small.gui.MixGui.MESSAGE_STRINGBUILDER;

public class FollowGUIFeatures extends GUIFeaturesLoader
{
    public static float MAX_DISTANCE;
    public static float MIN_DISTANCE;
    public static byte FLAG;//move_to | tp_to walk_to
    public static byte PAGE;//p0-2

    public String b0 = I18n.translateToLocal("info." + Reference.MOD_ID + ".b20");
    public String b1 = I18n.translateToLocal("info." + Reference.MOD_ID + ".b21");

    public FollowGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("info." + Reference.MOD_ID + ".b0") + " : ",
            this.b0 + " : " + I18n.translateToLocal("info." + Reference.MOD_ID + ".b22"),
            this.b1 + " : " + I18n.translateToLocal("info." + Reference.MOD_ID + ".b23"),
            I18n.translateToLocal("info." + Reference.MOD_ID + ".e0") + " <",
            I18n.translateToLocal("info." + Reference.MOD_ID + ".e2"),
            I18n.translateToLocal("info." + Reference.MOD_ID + ".c0"),
            "1.1 " + I18n.translateToLocal("info." + Reference.MOD_ID + ".b3") + " : ",
            "1.2 " + I18n.translateToLocal("info." + Reference.MOD_ID + ".b_0") + " : ",
            "2.1 " + I18n.translateToLocal("info." + Reference.MOD_ID + ".b_1") + " : ",
            "2.2 " + I18n.translateToLocal("info." + Reference.MOD_ID + ".b_2") + " : ",
            I18n.translateToLocal("info." + Reference.MOD_ID + ".e7")
        };
        this.loadColor(6);
        GUINETLOADER = new SetFollowGUINet(mixgui);
    }

    @Override
    public void drawText(int mouseX, int mouseY)
    {
        if (PAGE == 2)
        {
            this.mixgui.drawHoveringText(new String[]
            {
                MESSAGE_STRINGBUILDER.toString(),
                this.string_array[8] + MAX_DISTANCE,
                this.string_array[9] + MIN_DISTANCE,
                this.string_array[10],
                this.string_array[5],
                this.string_array[3] + PAGE + "/2>"
            }, this.int_array, mouseX, mouseY, this.have_head);
        }
        else if (PAGE == 1)
        {
            this.mixgui.drawHoveringText(new String[]
            {
                MESSAGE_STRINGBUILDER.toString(),
                this.string_array[6] + ((FLAG & 4) == 4 ? "ON" : "OFF"),
                this.string_array[7] + ((FLAG & 2) == 2 ? "ON" : "OFF"),
                this.string_array[4],
                this.string_array[5],
                this.string_array[3] + PAGE + "/2>"
            }, this.int_array, mouseX, mouseY, this.have_head);
        }
        else
        {
            EntityLeInv skinningentities = ((InventoryContainer)this.mixgui.inventorySlots).skinningentities;
            ClientLe cliententitiesmemory = (ClientLe)skinningentities.bothentitiesmemory;
            this.mixgui.drawHoveringText(new String[]
            {
                MESSAGE_STRINGBUILDER.toString(),
                this.string_array[0] + ((cliententitiesmemory.work_byte_array[cliententitiesmemory.workbytes.FOLLOW() / 8] >> cliententitiesmemory.workbytes.FOLLOW() % 8 & 1) == 1 ? this.b1 : this.b0),
                this.string_array[1],
                this.string_array[2],
                this.string_array[3] + PAGE + "/2>"
            }, this.int_array, mouseX, mouseY, this.have_head);
        }
    }
}
