package com.nali.small.gui.page;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class PageEquipment extends Page
{
    public static List<Integer>
    TEXTURE_INTEGER_LIST = new ArrayList(),
    ARRAY_BUFFER_INTEGER_LIST = new ArrayList();

    public float[][] vec2_2d_float_array;
    public float[][] color_vec4_2d_float_array;

    public static byte BYTE = 1;
    public static ItemStack[] ITEMSTACK_ARRAY;

    @Override
    public void init()
    {
        if ((BYTE & 1) == 1)
        {
            this.clear(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST);

            BYTE &= 255-1;
        }
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
//
//    @Override
//    public List<Integer> getArrayBufferIntegerList()
//    {
//        return Collections.emptyList();
//    }
//
//    @Override
//    public List<Integer> getTextureIntegerList()
//    {
//        return Collections.emptyList();
//    }
}
