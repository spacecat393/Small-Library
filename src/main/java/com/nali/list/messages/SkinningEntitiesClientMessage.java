package com.nali.list.messages;

import com.nali.ilol.networks.NetworksMessage;
import net.minecraftforge.fml.relauncher.Side;

public class SkinningEntitiesClientMessage extends NetworksMessage
{
    public static Side SIDE = Side.CLIENT;
    public SkinningEntitiesClientMessage()
    {

    }

    public SkinningEntitiesClientMessage(byte[] data)
    {
        this.data = data;
    }
}
