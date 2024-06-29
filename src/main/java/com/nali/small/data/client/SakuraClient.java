package com.nali.small.data.client;

import com.nali.data.client.IClientDaO;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.list.data.SmallData.MODEL_O_STEP;

@SideOnly(Side.CLIENT)
public class SakuraClient implements IClientDaO
{
    @Override
    public int StartPart()
    {
        return MODEL_O_STEP + 3;
    }

    @Override
    public int EndPart()
    {
        return MODEL_O_STEP + 4;
    }
}
