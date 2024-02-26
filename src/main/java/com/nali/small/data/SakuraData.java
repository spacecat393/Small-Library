package com.nali.small.data;

import com.nali.data.BothData;

public class SakuraData implements BothData
{
    public static int MAX_PART = 2;

    @Override
    public int MaxPart()
    {
        return MAX_PART;
    }

    @Override
    public int StepModels()
    {
        return 3;
    }

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
        return -1;
    }

    @Override
    public int MaxSync()
    {
        return -1;
    }
}
