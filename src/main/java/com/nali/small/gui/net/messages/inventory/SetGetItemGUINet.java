package com.nali.small.gui.net.messages.inventory;

import com.nali.list.network.method.server.SSetGetItem;
import com.nali.small.Small;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.messages.works.GetItemGUIFeatures;
import com.nali.small.gui.net.GUINetLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.small.gui.MixGui.MESSAGE_STRINGBUILDER;

@SideOnly(Side.CLIENT)
public class SetGetItemGUINet extends GUINetLoader
{
    public SetGetItemGUINet(MixGui mixgui)
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
                if (page > -1 && page < 3)
                {
                    GetItemGUIFeatures.PAGE = Byte.parseByte(string_array[0]);
                }
            }
            else
            {
                this.sendUUIDFloatBytes(SSetGetItem.ID, (byte)2, string_array);
            }
        }
        catch (Exception e)
        {
            Small.warn(e);
        }
    }
}