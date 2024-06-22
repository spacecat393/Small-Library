package com.nali.small.data.client;

import com.nali.data.client.ClientDataS;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.list.data.SmallData.MODEL_S_STEP;

@SideOnly(Side.CLIENT)
public class TPBaseClient implements ClientDataS
{
    @Override
    public int StartPart()
    {
        return MODEL_S_STEP + 5;
    }

    @Override
    public int EndPart()
    {
        return MODEL_S_STEP + 8;
    }

    @Override
    public int AnimationID()
    {
        return MODEL_S_STEP + 4;
    }
}
