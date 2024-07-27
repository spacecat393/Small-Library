package com.nali.small.gui.page;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@SideOnly(Side.CLIENT)
public class PageMe extends Page
{
    public static UUID UUID;

    @Override
    public void draw()
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
