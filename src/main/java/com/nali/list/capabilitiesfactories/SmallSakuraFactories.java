package com.nali.list.capabilitiesfactories;

import com.nali.list.capabilitiestypes.SmallSakuraTypes;

public class SmallSakuraFactories implements SmallSakuraTypes
{
    public byte sakura_byte = 0;

    @Override
    public byte get()
    {
        return this.sakura_byte;
    }

    @Override
    public void set(byte i)
    {
        this.sakura_byte = i;
    }

    @Override
    public void add(byte i)
    {
        if (this.sakura_byte + i < 65/*Integer.MAX_VALUE*/)
        {
            this.sakura_byte += i;
        }
    }
}
