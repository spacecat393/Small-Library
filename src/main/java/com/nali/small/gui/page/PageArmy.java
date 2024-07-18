package com.nali.small.gui.page;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Collections;
import java.util.List;

import static com.nali.list.container.gui.SmallGui.SMALLGUI;

@SideOnly(Side.CLIENT)
public class PageArmy extends Page
{
    @Override
    public void preDraw()
    {
//        super.preDraw();
    }

    @Override
    public void draw()
    {
        if ((SMALLGUI.state & 4) == 4)
        {
            SMALLGUI.state &= 255-4;
            SMALLGUI.defaultPage();
        }
    }

    @Override
    public void detect()
    {

    }

//    @Override
//    public void change()
//    {
//
//    }

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
