package com.nali.small.data.both;

import com.nali.data.BothData;

public class TPBaseBothData implements BothData
{
    public static int MAX_FRAME = 1;
//    public static int MAX_SYNC = 1;

    @Override
    public float Width()
    {
        return 1.0F;
    }

    @Override
    public float Height()
    {
        return 1.0F;
    }

    @Override
    public float Scale()
    {
        return 1.0F;
    }

    @Override
    public int MaxFrame()
    {
        return MAX_FRAME;
    }

    @Override
    public int MaxSync()
    {
//        return MAX_SYNC;
        return -1;
    }
}
