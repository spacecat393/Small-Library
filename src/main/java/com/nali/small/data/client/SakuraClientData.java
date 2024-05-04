package com.nali.small.data.client;

import com.nali.data.client.ClientData;

public class SakuraClientData implements ClientData
{
    @Override
    public int StartPart()
    {
        return 3;
    }

    @Override
    public int EndPart()
    {
        return 5;
    }
}
