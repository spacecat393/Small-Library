package com.nali.small.data.client;

import com.nali.data.client.ClientData;

public class BoxClientData implements ClientData
{
    @Override
    public int StartPart()
    {
        return 0;
    }

    @Override
    public int EndPart()
    {
        return 3;
    }
}