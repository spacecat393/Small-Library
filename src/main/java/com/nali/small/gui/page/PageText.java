package com.nali.small.gui.page;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class PageText extends Page
{
    public static List<Integer>
    TEXTURE_INTEGER_LIST = new ArrayList(),
    ARRAY_BUFFER_INTEGER_LIST = new ArrayList();

    public static byte BYTE = 1;

    public static StringBuffer STRINGBUFFER;

    public float[][] vec2_2d_float_array;
//    public float[][] color_vec4_2d_float_array;

    public PageText()
    {
        this.vec2_2d_float_array = new float[1][2];
//        super.init();
//        this.clear(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST);
    }

    @Override
    public void init()
    {
//        byte b = this.getByte();
//        if ((b & 1) == 1)
        if ((BYTE & 1) == 1)
        {
            this.clear(ARRAY_BUFFER_INTEGER_LIST, TEXTURE_INTEGER_LIST);
//            this.setByte((byte)(b & 255-1));
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

//    @Override
//    public List<Integer> getArrayBufferIntegerList()
//    {
//        return ARRAY_BUFFER_INTEGER_LIST;
//    }
//
//    @Override
//    public List<Integer> getTextureIntegerList()
//    {
//        return TEXTURE_INTEGER_LIST;
//    }
}
