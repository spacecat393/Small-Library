package com.nali.small.gui.features.messages;

import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.GUIFeaturesLoader;
import net.minecraft.potion.Potion;
import net.minecraft.util.text.translation.I18n;

public class EffectsGUIFeatures extends GUIFeaturesLoader
{
    public static int[] EFFECTS_INT_ARRAY;

    public String ed = I18n.translateToLocal("gui.info.ed");
    public String ea = I18n.translateToLocal("gui.info.ea");

    public EffectsGUIFeatures(MixGui mixgui)
    {
        super(mixgui);
        if (EFFECTS_INT_ARRAY != null && EFFECTS_INT_ARRAY.length != 0)
        {
            this.string_array = new String[EFFECTS_INT_ARRAY.length + 2];

            int index = 1;
            for (int i = 0; i < EFFECTS_INT_ARRAY.length; ++i)
            {
                int id = EFFECTS_INT_ARRAY[i++];
                Potion potion = Potion.getPotionById(id);
                if (potion == null)
                {
                    i += 2;
                    this.string_array[index++] = i + " NULL";
                    this.string_array[index++] = this.ed + " -";
                    this.string_array[index++] = this.ea + " -";
                }
                else
                {
                    int ticks = EFFECTS_INT_ARRAY[i++];
                    int seconds = ticks / 20;  // 1 second = 20 ticks
                    int minutes = seconds / 60;
                    int hours = minutes / 60;

                    // Calculate remaining minutes and seconds after hours
                    minutes %= 60;
                    seconds %= 60;

                    this.string_array[index++] = id + " " + potion.getName();
                    this.string_array[index++] = this.ed + " " + String.format("%02d:%02d:%02d", hours, minutes, seconds);
                    this.string_array[index++] = this.ea + " " + EFFECTS_INT_ARRAY[i];//(EFFECTS_INT_ARRAY[i] == 0 ? 1 : EFFECTS_INT_ARRAY[i]);
                }
            }
        }
        else
        {
            this.string_array = new String[2];
        }

        this.string_array[0] = I18n.translateToLocal("gui.info.e");
        this.string_array[this.string_array.length - 1] = I18n.translateToLocal("gui.info.st2");

        this.createColor();
    }
}
