package com.nali.small.data.both;

import com.nali.data.BothDataS;

public class TPBaseBothDataS implements BothDataS
{
    public static byte MAX_FRAME = 1;

    @Override
    public byte MaxFrame()
    {
        return MAX_FRAME;
    }
}
