package com.nali.small.da.both;

import com.nali.data.IBothDaSn;

public class TPBaseBothDaS implements IBothDaSn
{
    public static byte MAX_FRAME = 1;

    @Override
    public byte MaxFrame()
    {
        return MAX_FRAME;
    }
}
