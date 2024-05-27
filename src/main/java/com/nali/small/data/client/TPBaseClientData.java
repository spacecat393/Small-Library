package com.nali.small.data.client;

import com.nali.data.client.SkinningClientData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.list.data.SmallData.MODEL_STEP;

@SideOnly(Side.CLIENT)
public class TPBaseClientData implements SkinningClientData
{
    @Override
    public int StartPart()
    {
        return MODEL_STEP + 6;
    }

    @Override
    public int EndPart()
    {
        return MODEL_STEP + 9;
    }

    @Override
    public int AnimationID()
    {
        return MODEL_STEP + 5;
    }
}
