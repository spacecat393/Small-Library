package com.nali.ilol.entities.skinning;

import com.nali.ilol.networks.NetworksMessage;

public class SkinningEntitiesClientMessage extends NetworksMessage
{
    public SkinningEntitiesClientMessage()
    {

    }

    public SkinningEntitiesClientMessage(byte[] data)
    {
        this.data = data;
    }
}
