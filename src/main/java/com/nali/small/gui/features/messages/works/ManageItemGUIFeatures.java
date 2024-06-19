package com.nali.small.gui.features.messages.works;

import com.nali.list.container.InventoryContainer;
import com.nali.small.entity.memo.client.ClientLe;
import com.nali.small.entity.EntityLeInv;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.gui.net.messages.inventory.SetManageItemGUINet;
import com.nali.small.system.Reference;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;

import static com.nali.small.gui.MixGui.GUINETLOADER;
import static com.nali.small.gui.MixGui.MESSAGE_STRINGBUILDER;

public class ManageItemGUIFeatures extends GUIFeaturesLoader
{
    public static BlockPos IN_BLOCKPOS, OUT_BLOCKPOS;
    public static int RANDOM_AREA_IN = 1, RANDOM_AREA_OUT = 1;
    public static byte STATE;//remote_in remote_out random_in random_out in out
    public static byte PAGE;//p0-4

    public String b0 = I18n.translateToLocal("info." + Reference.MOD_ID + ".bi0");
    public String b1 = I18n.translateToLocal("info." + Reference.MOD_ID + ".bi1");

    public ManageItemGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        String in = I18n.translateToLocal("info." + Reference.MOD_ID + ".bd"),
        out = I18n.translateToLocal("info." + Reference.MOD_ID + ".be"),
        blockpos = I18n.translateToLocal("info." + Reference.MOD_ID + ".bf"),
        state = I18n.translateToLocal("info." + Reference.MOD_ID + ".b5"),
        in_blockpos = in + " " + blockpos,
        out_blockpos = out + " " + blockpos;

        this.string_array = new String[]
        {
            I18n.translateToLocal("info." + Reference.MOD_ID + ".b0") + " : ",
            this.b0 + " : " + I18n.translateToLocal("info." + Reference.MOD_ID + ".bi2"),
            this.b1 + " : " + I18n.translateToLocal("info." + Reference.MOD_ID + ".bi3"),
            I18n.translateToLocal("info." + Reference.MOD_ID + ".e0") + " <",
            I18n.translateToLocal("info." + Reference.MOD_ID + ".e1"),
            "1 " + in_blockpos,
            "2 " + out_blockpos,
            "X : ",
            "Y : ",
            "Z : ",
            in_blockpos + " " + state,
            out_blockpos + " " + state,
            I18n.translateToLocal("info." + Reference.MOD_ID + ".e2"),
            I18n.translateToLocal("info." + Reference.MOD_ID + ".c0"),
            "1.1 " + I18n.translateToLocal("info." + Reference.MOD_ID + ".b5") + " : ",
            "1.2 " + I18n.translateToLocal("info." + Reference.MOD_ID + ".b6") + " : ",
            "1.3 " + I18n.translateToLocal("info." + Reference.MOD_ID + ".bg") + " : ",
            "2.1 " + I18n.translateToLocal("info." + Reference.MOD_ID + ".b5") + " : ",
            "2.2 " + I18n.translateToLocal("info." + Reference.MOD_ID + ".b6") + " : ",
            "2.3 " + I18n.translateToLocal("info." + Reference.MOD_ID + ".bg") + " : "
        };
        this.loadColor(9);
        GUINETLOADER = new SetManageItemGUINet(mixgui);
    }

    @Override
    public void drawText(int mouseX, int mouseY)
    {
        EntityLeInv skinningentities = ((InventoryContainer)this.mixgui.inventorySlots).skinningentities;
        ClientLe cliententitiesmemory = (ClientLe)skinningentities.bothentitiesmemory;

        if (PAGE == 4)
        {
            this.mixgui.drawHoveringText(new String[]
            {
                MESSAGE_STRINGBUILDER.toString(),
                this.string_array[11],
                this.string_array[17] + ((STATE & 32) == 32 ? (STATE & 8) == 8 ? "Random" : "ON" : "OFF"),
                this.string_array[18] + ((STATE & 2) == 2 ? "ON" : "OFF"),
                this.string_array[19] + RANDOM_AREA_OUT,
                this.string_array[12],
                this.string_array[13],
                this.string_array[3] + PAGE + "/4>"
            }, this.int_array, mouseX, mouseY, this.have_head);
        }
        else if (PAGE == 3)
        {
            this.mixgui.drawHoveringText(new String[]
            {
                MESSAGE_STRINGBUILDER.toString(),
                this.string_array[10],
                this.string_array[14] + ((STATE & 16) == 16 ? (STATE & 4) == 4 ? "Random" : "ON" : "OFF"),
                this.string_array[15] + ((STATE & 1) == 1 ? "ON" : "OFF"),
                this.string_array[16] + RANDOM_AREA_IN,
                this.string_array[12],
                this.string_array[13],
                this.string_array[3] + PAGE + "/4>"
            }, this.int_array, mouseX, mouseY, this.have_head);
        }
        else if (PAGE == 2)
        {
            this.mixgui.drawHoveringText(new String[]
            {
                MESSAGE_STRINGBUILDER.toString(),
                this.string_array[6],
                this.string_array[7] + (OUT_BLOCKPOS == null ? "Null" : OUT_BLOCKPOS.getX()),
                this.string_array[8] + (OUT_BLOCKPOS == null ? "Null" : OUT_BLOCKPOS.getY()),
                this.string_array[9] + (OUT_BLOCKPOS == null ? "Null" : OUT_BLOCKPOS.getZ()),
                this.string_array[4],
                this.string_array[13],
                this.string_array[3] + PAGE + "/4>"
            }, this.int_array, mouseX, mouseY, this.have_head);
        }
        else if (PAGE == 1)
        {
            this.mixgui.drawHoveringText(new String[]
            {
                MESSAGE_STRINGBUILDER.toString(),
                this.string_array[5],
                this.string_array[7] + (IN_BLOCKPOS == null ? "Null" : IN_BLOCKPOS.getX()),
                this.string_array[8] + (IN_BLOCKPOS == null ? "Null" : IN_BLOCKPOS.getY()),
                this.string_array[9] + (IN_BLOCKPOS == null ? "Null" : IN_BLOCKPOS.getZ()),
                this.string_array[4],
                this.string_array[13],
                this.string_array[3] + PAGE + "/4>"
            }, this.int_array, mouseX, mouseY, this.have_head);
        }
        else
        {
            this.mixgui.drawHoveringText(new String[]
            {
                MESSAGE_STRINGBUILDER.toString(),
                this.string_array[0] + ((cliententitiesmemory.work_byte_array[cliententitiesmemory.workbytes.MANAGE_ITEM() / 8] >> cliententitiesmemory.workbytes.MANAGE_ITEM() % 8 & 1) == 1 ? this.b1 : this.b0),
                this.string_array[1],
                this.string_array[2],
                this.string_array[3] + PAGE + "/4>"
            }, this.int_array, mouseX, mouseY, this.have_head);
        }
    }
}