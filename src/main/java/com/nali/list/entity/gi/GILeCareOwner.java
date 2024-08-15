package com.nali.list.entity.gi;

import com.nali.small.gui.key.Key;
import com.nali.small.gui.key.KeyMenuGI;
import com.nali.small.gui.mouse.Mouse;
import com.nali.small.gui.page.Page;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GILeCareOwner extends Page
{
    @Override
    public void init()
    {

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

    public static Key getKey()
    {
        return new KeyMenuGI();
    }

    public static Mouse getMouse()
    {
        return new MouseGI();
    }

    public static class MouseGI extends Mouse
    {

    }
}
