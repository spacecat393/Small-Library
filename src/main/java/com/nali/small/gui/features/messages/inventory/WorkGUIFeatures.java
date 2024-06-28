package com.nali.small.gui.features.messages.inventory;

import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.gui.net.messages.inventory.SetWorkGUINet;
import com.nali.small.system.Reference;
import net.minecraft.util.text.translation.I18n;

import static com.nali.small.gui.MixGui.GUINETLOADER;
import static com.nali.small.gui.MixGui.MESSAGE_STRINGBUILDER;

public class WorkGUIFeatures extends GUIFeaturesLoader
{
    public static byte PAGE;//p0-1

    public WorkGUIFeatures(MixGui mixgui)
    {
        super(mixgui);
        this.string_array = new String[]
        {
            I18n.translateToLocal("info." + Small.ID + ".c8"),
            I18n.translateToLocal("info." + Small.ID + ".e0") + " <"
        };
        this.loadColor(3);
        GUINETLOADER = new SetWorkGUINet(mixgui);
    }

    @Override
    public void drawText(int mouseX, int mouseY)
    {
        if (PAGE == 1)
        {
//            this.mixgui.drawHoveringText(new String[]
//            {
//                MESSAGE_STRINGBUILDER.toString(),
//                this.string_array[6] + ((FLAG & 16) == 16 ? "ON" : "OFF"),
//                this.string_array[7] + ((FLAG & 32) == 32 ? "ON" : "OFF"),
//                this.string_array[8] + ((FLAG & 64) == 64 ? "ON" : "OFF"),
//                this.string_array[1],
//                this.string_array[2],
//                this.string_array[0] + PAGE + "/1>"
//            }, this.int_array, mouseX, mouseY, this.have_head);
        }
        else
        {
            this.mixgui.drawHoveringText(new String[]
            {
                MESSAGE_STRINGBUILDER.toString(),
                this.string_array[0],
                this.string_array[1] + PAGE + "/1>"
            }, this.int_array, mouseX, mouseY, this.have_head);
        }
    }
}