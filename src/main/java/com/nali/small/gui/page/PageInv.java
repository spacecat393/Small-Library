package com.nali.small.gui.page;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Collections;
import java.util.List;

@SideOnly(Side.CLIENT)
public class PageInv extends Page
{
    public static ItemStack[] ITEMSTACK_ARRAY;

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
