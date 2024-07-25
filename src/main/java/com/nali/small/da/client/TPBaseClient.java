package com.nali.small.da.client;

import com.nali.da.client.IClientDaS;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.list.data.SmallData.*;

@SideOnly(Side.CLIENT)
public class TPBaseClient implements IClientDaS
{
    @Override
    public int StartPart()
    {
        return MODEL_STEP + 21;
    }

    @Override
    public int EndPart()
    {
        return MODEL_STEP + 24;
    }

    @Override
    public int FrameID()
    {
        return FRAME_STEP/* + 0*/;
    }
}
