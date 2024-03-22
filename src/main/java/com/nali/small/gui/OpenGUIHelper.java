package com.nali.small.gui;

import com.nali.system.Reflect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Comparator;
import java.util.List;

@SideOnly(Side.CLIENT)
public class OpenGUIHelper
{
    public static List<Class> GUI_CLASS_LIST;

    public static void set()
    {
        GUI_CLASS_LIST = Reflect.getClasses("com.nali.list.gui");
        GUI_CLASS_LIST.sort(Comparator.comparing(Class::getName));
    }

//    public static void callPlayerGUI()
//    {
//        Minecraft minecraft = Minecraft.getMinecraft();
//        minecraft.addScheduledTask(() ->
//        {
//            minecraft.displayGuiScreen(new PlayerGui(new PlayerContainer()));
//        });
//    }
}
