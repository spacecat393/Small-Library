package com.nali.small.da.client;

import com.nali.da.client.IClientDaO;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.list.data.SmallData.MODEL_O_STEP;

@SideOnly(Side.CLIENT)
public class StorageClient implements IClientDaO
{
    @Override
    public int StartPart()
    {
        return MODEL_O_STEP + 4;
    }

    @Override
    public int EndPart()
    {
        return MODEL_O_STEP + 6;
    }
}