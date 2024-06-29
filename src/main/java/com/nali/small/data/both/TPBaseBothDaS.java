package com.nali.small.data.both;

import com.nali.data.IBothDaSn;
import com.nali.sound.ISoundN;

public class TPBaseBothDaS<SD extends ISoundN> implements IBothDaSn<SD>
{
    public static byte MAX_FRAME = 1;

    @Override
    public byte MaxFrame()
    {
        return MAX_FRAME;
    }

    @Override
    public SD getSD()
    {
        return null;
    }
}
