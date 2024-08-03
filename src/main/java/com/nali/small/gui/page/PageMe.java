package com.nali.small.gui.page;

import com.nali.small.gui.key.KeyMenuMe;
import com.nali.small.gui.mouse.Mouse;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.nali.list.container.gui.SmallGui.FLAG;
import static com.nali.list.container.gui.SmallGui.PAGE_ARRAY;
import static com.nali.small.gui.key.Key.KEY;
import static com.nali.small.gui.mouse.Mouse.MOUSE;

@SideOnly(Side.CLIENT)
public class PageMe extends Page
{
    public static UUID UUID;

    public static void openPageMe(UUID uuid)
    {
        UUID = uuid;
        PAGE_ARRAY = new Page[]
        {
            new PageBlur(),
            new PageMenu(STRING_ARRAY[14] + ((KeyMenuMe.ME & 1) == 1 ? "|" + STRING_ARRAY[0] : "") + "|" + uuid),
            new PageMe()
        };
        KEY = new KeyMenuMe();
        MOUSE = new Mouse();
        FLAG |= 1;
    }

    @Override
    public void draw()
    {

    }

    @Override
    public void preDraw()
    {

    }

    @Override
    public void detect()
    {

    }

    @Override
    public List<Integer> getArrayBufferIntegerList()
    {
        return Collections.emptyList();
    }

    @Override
    public List<Integer> getTextureIntegerList()
    {
        return Collections.emptyList();
    }
}
