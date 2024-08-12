package com.nali.small.gui.features.messages.inventory;

import com.nali.small.Small;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.gui.net.messages.inventory.SetAreaGUINet;
import net.minecraft.util.text.translation.I18n;

import static com.nali.small.gui.MixGui.GUINETLOADER;
import static com.nali.small.gui.MixGui.MESSAGE_STRINGBUILDER;

public class AreaGUIFeatures extends GUIFeaturesLoader
{
    public static byte FLAG;//check_tameable is_tameable | put_player put_owner put_other_tameable put_owner_tameable put_all_tameable put_object
    public static byte PAGE;//p0-1

    public AreaGUIFeatures(MixGui mixgui)
    {
        super(mixgui);
        this.string_array = new String[]
        {
            I18n.translateToLocal("info." + Small.ID + ".e0") + " <",
            I18n.translateToLocal("info." + Small.ID + ".ec"),
            I18n.translateToLocal("info." + Small.ID + ".c0"),
            "0.1 " + I18n.translateToLocal("info." + Small.ID + ".b_4") + " : ",
            "0.2 " + I18n.translateToLocal("info." + Small.ID + ".b_5") + " : ",
            "0.3 " + I18n.translateToLocal("info." + Small.ID + ".b_9") + " : ",
            "1.1 " + I18n.translateToLocal("info." + Small.ID + ".b_6") + " : ",
            "1.2 " + I18n.translateToLocal("info." + Small.ID + ".b_7") + " : ",
            "1.3 " + I18n.translateToLocal("info." + Small.ID + ".b_8") + " : "
        };
        this.loadColor(7);
        GUINETLOADER = new SetAreaGUINet(mixgui);
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
                this.string_array[7] + ((FLAG & 32) == 32 ? "ON" : "OFF"),
                this.string_array[8] + ((FLAG & 64) == 64 ? "ON" : "OFF"),
                this.string_array[1],
                this.string_array[2],
                this.string_array[0] + PAGE + "/1>"
            }, this.int_array, mouseX, mouseY, this.have_head);
        }
        else
        {
            this.mixgui.drawHoveringText(new String[]
            {
                MESSAGE_STRINGBUILDER.toString(),
                this.string_array[3] + ((FLAG & 4) == 4 ? "ON" : "OFF"),
                this.string_array[4] + ((FLAG & 8) == 8 ? "ON" : "OFF"),
                this.string_array[5] + ((FLAG & 128) == 128 ? "ON" : "OFF"),
                this.string_array[1],
                this.string_array[2],
                this.string_array[0] + PAGE + "/1>"
            }, this.int_array, mouseX, mouseY, this.have_head);
        }
    }
}