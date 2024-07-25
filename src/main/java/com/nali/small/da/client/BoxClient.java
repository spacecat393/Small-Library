package com.nali.small.da.client;

import com.nali.da.client.IClientDaO;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.list.data.SmallData.MODEL_STEP;

@SideOnly(Side.CLIENT)
public class BoxClient implements IClientDaO
{
    @Override
    public int StartPart()
    {
        return MODEL_STEP/* + 0*/;
    }

    @Override
    public int EndPart()
    {
        return MODEL_STEP + 3;
    }
}