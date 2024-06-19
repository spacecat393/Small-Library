package com.nali.small.gui.features.messages.works;

import com.nali.list.container.InventoryContainer;
import com.nali.small.entity.memo.client.ClientLe;
import com.nali.small.entity.EntityLeInv;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.gui.net.messages.inventory.SetAttackGUINet;
import com.nali.small.system.Reference;
import net.minecraft.util.text.translation.I18n;

import static com.nali.small.gui.MixGui.GUINETLOADER;
import static com.nali.small.gui.MixGui.MESSAGE_STRINGBUILDER;

public class AttackGUIFeatures extends GUIFeaturesLoader
{
    public static float MINIMUM_DISTANCE;
    public static int MAX_MAGIC_POINT;
    public static byte FLAG;//move_to prepare hit | remote walk_to
    public static byte PAGE;//p0-1

    public String b0 = I18n.translateToLocal("info." + Reference.MOD_ID + ".b60");
    public String b1 = I18n.translateToLocal("info." + Reference.MOD_ID + ".b61");

    public AttackGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("info." + Reference.MOD_ID + ".b0") + " : ",
            this.b0 + " : " + I18n.translateToLocal("info." + Reference.MOD_ID + ".b62"),
            this.b1 + " : " + I18n.translateToLocal("info." + Reference.MOD_ID + ".b63"),
            I18n.translateToLocal("info." + Reference.MOD_ID + ".e0") + " <",
            I18n.translateToLocal("info." + Reference.MOD_ID + ".e2"),
            I18n.translateToLocal("info." + Reference.MOD_ID + ".c0"),
            "1.1 " + I18n.translateToLocal("info." + Reference.MOD_ID + ".b3") + " : ",
            "1.2 " + I18n.translateToLocal("info." + Reference.MOD_ID + ".b6") + " : ",
            "1.3 " + I18n.translateToLocal("info." + Reference.MOD_ID + ".bt") + " : ",
            "1.4 " + I18n.translateToLocal("info." + Reference.MOD_ID + ".bc") + " " + I18n.translateToLocal("info." + Reference.MOD_ID + ".b_3") + " : "
        };
        this.loadColor(8);
        GUINETLOADER = new SetAttackGUINet(mixgui);
    }

    @Override
    public void drawText(int mouseX, int mouseY)
    {
        if (PAGE == 1)
        {
            this.mixgui.drawHoveringText(new String[]
            {
                MESSAGE_STRINGBUILDER.toString(),
                this.string_array[6] + ((FLAG & 16) == 16 ? "ON" : "OFF"),
                this.string_array[7] + ((FLAG & 8) == 8 ? "ON" : "OFF"),
                this.string_array[8] + MINIMUM_DISTANCE,
                this.string_array[9] + MAX_MAGIC_POINT,
                this.string_array[4],
                this.string_array[5],
                this.string_array[3] + PAGE + "/1>"
            }, this.int_array, mouseX, mouseY, this.have_head);
        }
        else
        {
            EntityLeInv skinningentities = ((InventoryContainer)this.mixgui.inventorySlots).skinningentities;
            ClientLe cliententitiesmemory = (ClientLe)skinningentities.bothentitiesmemory;
            this.mixgui.drawHoveringText(new String[]
            {
                MESSAGE_STRINGBUILDER.toString(),
                this.string_array[0] + ((cliententitiesmemory.work_byte_array[cliententitiesmemory.workbytes.ATTACK() / 8] >> cliententitiesmemory.workbytes.ATTACK() % 8 & 1) == 1 ? this.b1 : this.b0),
                this.string_array[1],
                this.string_array[2],
                this.string_array[3] + PAGE + "/1>"
            }, this.int_array, mouseX, mouseY, this.have_head);
        }
    }
}
