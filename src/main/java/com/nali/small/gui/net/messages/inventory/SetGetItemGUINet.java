package com.nali.small.gui.net.messages.inventory;

import com.nali.list.netmethods.servermessage.SetGetItem;
import com.nali.small.Small;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.messages.works.GetItemGUIFeatures;
import com.nali.small.gui.net.GUINetLoader;

import static com.nali.small.gui.MixGui.MESSAGE_STRINGBUILDER;

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
                this.sendUUIDFloatBytes(SetGetItem.ID, (byte)2, string_array);
            }
        }
        catch (Exception e)
        {
            Small.warn(e);
        }
    }
}