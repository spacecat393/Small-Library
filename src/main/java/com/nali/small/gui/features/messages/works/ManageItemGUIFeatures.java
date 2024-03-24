package com.nali.small.gui.features.messages.works;

import com.nali.list.container.InventoryContainer;
import com.nali.small.entities.memory.ClientEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;

import static com.nali.small.gui.MixGui.MESSAGE_STRINGBUILDER;

public class ManageItemGUIFeatures extends GUIFeaturesLoader
{
    public static BlockPos IN_BLOCKPOS, OUT_BLOCKPOS;
    public static byte PAGE;//p0-2 is_random4

    public String b0 = I18n.translateToLocal("gui.info.mi0");
    public String b1 = I18n.translateToLocal("gui.info.mi1");

    public ManageItemGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("gui.info.cv") + " : ",
            this.b0 + " : " + I18n.translateToLocal("gui.info.mi00"),
            this.b1 + " : " + I18n.translateToLocal("gui.info.mi10"),
            I18n.translateToLocal("gui.info.mi20"),
            I18n.translateToLocal("gui.info.mi21"),
            I18n.translateToLocal("gui.info.mi22"),
            I18n.translateToLocal("gui.info.mi23"),
            "X : ",
            "Y : ",
            "Z : ",
            I18n.translateToLocal("gui.info.mi24"),
            I18n.translateToLocal("gui.info.mi25"),
            I18n.translateToLocal("gui.info.mi26"),
            I18n.translateToLocal("gui.info.mi27"),
            I18n.translateToLocal("gui.info.mi28") + " : ",
            I18n.translateToLocal("gui.info.mi29") + " : "
        };
        this.createColor();
    }

    @Override
    public void drawText(int mouseX, int mouseY)
    {
        SkinningEntities skinningentities = ((InventoryContainer)this.mixgui.inventorySlots).skinningentities;
        ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)skinningentities.bothentitiesmemory;

        if ((PAGE & 1) == 1)
        {
            this.mixgui.drawHoveringText(new String[]
            {
                MESSAGE_STRINGBUILDER.toString(),
                this.string_array[5],
                this.string_array[7] + (IN_BLOCKPOS == null ? "Null" : IN_BLOCKPOS.getX()),
                this.string_array[8] + (IN_BLOCKPOS == null ? "Null" : IN_BLOCKPOS.getY()),
                this.string_array[9] + (IN_BLOCKPOS == null ? "Null" : IN_BLOCKPOS.getZ()),
                this.string_array[4],
                this.string_array[3]
            }, this.int_array, mouseX, mouseY, this.have_head);
        }
        else if ((PAGE & 2) == 2)
        {
            this.mixgui.drawHoveringText(new String[]
            {
                MESSAGE_STRINGBUILDER.toString(),
                this.string_array[6],
                this.string_array[7] + ((PAGE & 4) == 4 ? "Random" : OUT_BLOCKPOS == null ? "Null" : OUT_BLOCKPOS.getX()),
                this.string_array[8] + ((PAGE & 4) == 4 ? "Random" : OUT_BLOCKPOS == null ? "Null" : OUT_BLOCKPOS.getY()),
                this.string_array[9] + ((PAGE & 4) == 4 ? "Random" : OUT_BLOCKPOS == null ? "Null" : OUT_BLOCKPOS.getZ()),
                this.string_array[4],
                this.string_array[3]
            }, this.int_array, mouseX, mouseY, this.have_head);
        }
        else if ((PAGE & 3) == 3)
        {

        }
        else
        {
            this.mixgui.drawHoveringText(new String[]
            {
                MESSAGE_STRINGBUILDER.toString(),
                this.string_array[0] + ((cliententitiesmemory.work_byte_array[cliententitiesmemory.workbytes.MANAGE_ITEM() / 8] >> cliententitiesmemory.workbytes.MANAGE_ITEM() % 8 & 1) == 1 ? this.b1 : this.b0),
                this.string_array[1],
                this.string_array[2],
                this.string_array[3]
            }, this.int_array, mouseX, mouseY, this.have_head);
        }
    }
}