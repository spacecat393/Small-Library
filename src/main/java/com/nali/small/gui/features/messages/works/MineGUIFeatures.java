package com.nali.small.gui.features.messages.works;

import com.nali.list.container.InventoryContainer;
import com.nali.small.entities.memory.client.ClientEntitiesMemory;
import com.nali.small.entities.skinning.SkinningEntities;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;

import static com.nali.small.gui.MixGui.MESSAGE_STRINGBUILDER;

public class MineGUIFeatures extends GUIFeaturesLoader
{
    public static byte STATE;//remote1 x2-4 y8-16 z32-64 +-
    public static BlockPos START_BLOCKPOS, END_BLOCKPOS;
    public static byte PAGE;//p0-4

    public String b0 = I18n.translateToLocal("gui.info.m0");
    public String b1 = I18n.translateToLocal("gui.info.m1");

    public MineGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            I18n.translateToLocal("gui.info.cv") + " : ",
            this.b0 + " : " + I18n.translateToLocal("gui.info.m00"),
            this.b1 + " : " + I18n.translateToLocal("gui.info.m10"),
            I18n.translateToLocal("gui.info.mi20") + " <",
        };
        this.loadColor(9);
    }

    @Override
    public void drawText(int mouseX, int mouseY)
    {
        if (PAGE == 2)
        {

        }
        else if (PAGE == 1)
        {

        }
        else
        {
            SkinningEntities skinningentities = ((InventoryContainer)this.mixgui.inventorySlots).skinningentities;
            ClientEntitiesMemory cliententitiesmemory = (ClientEntitiesMemory)skinningentities.bothentitiesmemory;

            this.mixgui.drawHoveringText(new String[]
            {
                MESSAGE_STRINGBUILDER.toString(),
                this.string_array[0] + ((cliententitiesmemory.work_byte_array[cliententitiesmemory.workbytes.MINE() / 8] >> cliententitiesmemory.workbytes.MINE() % 8 & 1) == 1 ? this.b1 : this.b0),
                this.string_array[1],
                this.string_array[2],
                this.string_array[3] + PAGE + "/2>"
            }, this.int_array, mouseX, mouseY, this.have_head);
        }
    }
}
