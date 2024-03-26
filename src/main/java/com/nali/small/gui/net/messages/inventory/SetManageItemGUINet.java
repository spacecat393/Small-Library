package com.nali.small.gui.net.messages.inventory;

import com.nali.list.netmethods.servermessage.SetManageItem;
import com.nali.small.Small;
import com.nali.small.gui.MixGui;
import com.nali.small.gui.features.messages.works.ManageItemGUIFeatures;
import com.nali.small.gui.net.GUINetLoader;

import static com.nali.small.gui.MixGui.MESSAGE_STRINGBUILDER;

public class SetManageItemGUINet extends GUINetLoader
{
    public SetManageItemGUINet(MixGui mixgui)
    {
        super(mixgui);
    }

    @Override
    public void run()
    {
        try
        {
            ManageItemGUIFeatures.PAGE = Byte.parseByte(MESSAGE_STRINGBUILDER.deleteCharAt(MESSAGE_STRINGBUILDER.length() - 1).toString());

            String[] string_array = MESSAGE_STRINGBUILDER.deleteCharAt(MESSAGE_STRINGBUILDER.length() - 1).toString().split(" ");
            byte size = (byte)string_array.length;

            boolean pos = size == 4;

            if (pos || size == 2)
            {
                this.sendUUIDFloatBytes(SetManageItem.ID, size, string_array);
            }
        }
        catch (Exception e)
        {
            Small.warn(e);
        }
    }
}
