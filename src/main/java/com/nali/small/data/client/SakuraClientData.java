package com.nali.small.data.client;

import com.nali.data.client.ClientData;

import static com.nali.list.data.SmallData.MODEL_STEP;

public class SakuraClientData implements ClientData
{
    @Override
    public int StartPart()
    {
        return MODEL_STEP + 3;
    }

    @Override
    public int EndPart()
    {
        return MODEL_STEP + 5;
    }
}
