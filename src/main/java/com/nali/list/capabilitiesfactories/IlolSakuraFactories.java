package com.nali.list.capabilitiesfactories;

import com.nali.list.capabilitiestypes.IlolSakuraTypes;

public class IlolSakuraFactories implements IlolSakuraTypes
{
    public int sakura_int = 0;

    @Override
    public int get()
    {
        return this.sakura_int;
    }

    @Override
    public void set(int i)
    {
        this.sakura_int = i;
    }

    @Override
    public void add(int i)
    {
        if (this.sakura_int + i < Integer.MAX_VALUE)
        {
            this.sakura_int += i;
        }
    }
}
