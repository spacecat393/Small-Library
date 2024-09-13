package com.nali.small.gui.net.messages.inventory;

import com.nali.small.Small;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.messages.inventory.AreaGUIFeatures;
import com.nali.small.gui.net.GUINetLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.small.gui.MixGui.MESSAGE_STRINGBUILDER;

@SideOnly(Side.CLIENT)
public class SetAreaGUINet extends GUINetLoader
{
    public SetAreaGUINet(MixGui mixgui)
    {
        super(mixgui);
    }

    @Override
    public void run()
    {
        try
        {
            String[] string_array = MESSAGE_STRINGBUILDER.deleteCharAt(MESSAGE_STRINGBUILDER.length() - 1).toString().split(" ");
            if (string_array.length == 1)
            {
                byte page = Byte.parseByte(string_array[0]);
                if (page > -1 && page < 2)
                {
                    AreaGUIFeatures.PAGE = Byte.parseByte(string_array[0]);
                }
            }
            else
            {
                byte size = (byte)string_array.length;

                boolean pos = size == 4;

                if (pos || size == 2)
                {
                    this.sendUUIDFloatBytes(SSetArea.ID, size, string_array);
                }
            }
        }
        catch (Exception e)
        {
            Small.warn(e);
        }
    }
}