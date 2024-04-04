package com.nali.small.gui.features.messages.inventory;

import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import com.nali.small.gui.net.messages.inventory.SetLocationGUINet;
import com.nali.small.system.Reference;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;

import static com.nali.small.gui.MixGui.GUINETLOADER;
import static com.nali.small.gui.MixGui.MESSAGE_STRINGBUILDER;

public class SetLocationGUIFeatures extends GUIFeaturesLoader
{
    public static BlockPos BLOCKPOS = BlockPos.ORIGIN;
    public static float FAR;

    public SetLocationGUIFeatures(MixGui mixgui)
    {
        super(mixgui, true);
        this.string_array = new String[]
        {
            "0 " + I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".slm0"),
            "X : ",
            "Y : ",
            "Z : ",
            "1 " + I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".slm1") + " : ",
            I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".slm2"),
            I18n.translateToLocal("gui.info." + Reference.MOD_ID + ".st2")
        };
        this.loadColor(8);
//        this.int_array = new int[8];
//        this.loadColor();
//        this.int_array[7] = 0xFFFFFFFF;
        GUINETLOADER = new SetLocationGUINet(mixgui);
    }

    @Override
    public void drawText(int mouseX, int mouseY)
    {
        this.mixgui.drawHoveringText(new String[]
        {
            MESSAGE_STRINGBUILDER.toString(),
            this.string_array[0],
            this.string_array[1] + BLOCKPOS.getX(),
            this.string_array[2] + BLOCKPOS.getY(),
            this.string_array[3] + BLOCKPOS.getZ(),
            this.string_array[4] + FAR,
            this.string_array[5],
            this.string_array[6]
        }, this.int_array, mouseX, mouseY, this.have_head);
    }
}