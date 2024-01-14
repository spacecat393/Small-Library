package com.nali.small.data;

import com.nali.data.BothData;

public class BoxData implements BothData
{
    public static int MAX_PART = 3;

    @Override
    public int MaxPart()
    {
        return MAX_PART;
    }

    @Override
    public int StepModels()
    {
        return 0;
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
}