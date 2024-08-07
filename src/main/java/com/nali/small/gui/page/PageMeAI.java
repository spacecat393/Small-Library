package com.nali.small.gui.page;

import com.nali.small.gui.key.KeyMenuMe;
import com.nali.small.gui.mouse.Mouse;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.list.container.gui.SmallGui.PAGE_ARRAY;
import static com.nali.list.container.gui.SmallGui.addSet;
import static com.nali.small.gui.key.Key.KEY;
import static com.nali.small.gui.mouse.Mouse.MOUSE;

@SideOnly(Side.CLIENT)
public class PageMeAI extends PageMe
{
    public static byte BYTE = 1;

    public static void openPageMeAI(java.util.UUID uuid)
    {
        UUID = uuid;
        PageMenu.BYTE |= 1;
        PAGE_ARRAY = new Page[]
        {
            new PageBlur(),
            new PageMenu(STRING_ARRAY[14] + ((KeyMenuMe.ME & 1) == 1 ? "|" + STRING_ARRAY[0] : "") + "|" + uuid),
            new PageSakura(),
            new PageKey(),
            new PageMe()
        };
        KEY = new KeyMenuMe();
        MOUSE = new Mouse();
//        FLAG |= 1;
        addSet();
    }

//    @Override
//    public byte getByte()
//    {
//        return BYTE;
//    }
//
//    @Override
//    public void setByte(byte b)
//    {
//        BYTE = b;
//    }
}
