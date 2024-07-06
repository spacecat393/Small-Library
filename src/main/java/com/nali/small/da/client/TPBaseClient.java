package com.nali.small.da.client;

import com.nali.data.client.IClientDaS;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.list.data.SmallData.ANIMATION_STEP;
import static com.nali.list.data.SmallData.MODEL_S_STEP;

@SideOnly(Side.CLIENT)
public class TPBaseClient implements IClientDaS
{
    @Override
    public int StartPart()
    {
        return MODEL_S_STEP/* + 0*/;
    }

    @Override
    public int EndPart()
    {
        return MODEL_S_STEP + 3;
    }

    @Override
    public int AnimationID()
    {
        return ANIMATION_STEP/* + 0*/;
    }
}
