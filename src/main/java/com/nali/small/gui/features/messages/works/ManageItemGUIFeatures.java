package com.nali.small.gui.features.messages.works;

import com.nali.list.container.InventoryContainer;
import com.nali.small.entities.memory.client.ClientEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
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

    public String b0 = I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".mi0");
    public String b1 = I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".mi1");

    public ManageItemGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".cv") + " : ",
            this.b0 + " : " + I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".mi00"),
            this.b1 + " : " + I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".mi10"),
            I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".mi20") + " <",
            I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".mi21"),
            "1 " + I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".mi22"),
            "2 " + I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".mi23"),
            "X : ",
            "Y : ",
            "Z : ",
            I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".mi24"),
            I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".mi25"),
            I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".mi26"),
            I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".mi27"),
            "1.1 " + I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".mi28") + " : ",
            "1.2 " + I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".mi29") + " : ",
            "1.3 " + I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".mi30") + " : ",
            "2.1 " + I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".mi28") + " : ",
            "2.2 " + I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".mi29") + " : ",
            "2.3 " + I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".mi30") + " : "
        };
        this.loadColor(9);
        GUINETLOADER = new SetManageItemGUINet(mixgui);
    }

    @Override
    public void drawText(int mouseX, int mouseY)
    {
        SkinningEntities skinningentities = ((InventoryContainer)this.mixgui.inventorySlots).skinningentities;
        ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)skinningentities.bothentitiesmemory;

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