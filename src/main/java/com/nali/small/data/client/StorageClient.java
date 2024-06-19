package com.nali.small.data.client;

import com.nali.data.client.ClientData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.list.data.SmallData.MODEL_STEP;

@SideOnly(Side.CLIENT)
public class StorageClient implements ClientData
{
    @Override
    public int StartPart()
    {
        return MODEL_STEP + 8;
    }

    @Override
    public int EndPart()
    {
        return MODEL_STEP + 10;
    }
}
