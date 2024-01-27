package com.nali.small.gui;

import com.nali.list.container.PlayerContainer;
import com.nali.list.gui.PlayerGui;
import net.minecraft.client.Minecraft;

import java.util.List;

public class OpenGUIHelper
{
    public static List<Class> GUI_CLASS_LIST;

    public static void callPlayerGUI()
    {
        Minecraft minecraft = Minecraft.getMinecraft();
        minecraft.addScheduledTask(() ->
        {
            minecraft.displayGuiScreen(new PlayerGui(new PlayerContainer()));
        });
    }
}
