package com.nali.small.gui.net.messages.inventory;

import com.nali.small.gui.MixGui;
import com.nali.small.gui.net.GUINetLoader;

import static com.nali.small.gui.MixGui.MESSAGE_STRINGBUILDER;

public class SetWorkGUINet extends GUINetLoader
{
    public SetWorkGUINet(MixGui mixgui)
    {
        super(mixgui);
    }

    @Override
    public void run()
    {
        String[] string_array = MESSAGE_STRINGBUILDER.deleteCharAt(MESSAGE_STRINGBUILDER.length() - 1).toString().split(" ");
        if (string_array.length == 1)
        {
            if (string_array.equals("reset"))
            {

            }
        }
    }
}