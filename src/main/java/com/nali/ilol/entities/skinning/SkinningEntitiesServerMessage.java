package com.nali.ilol.entities.skinning;

import com.nali.ilol.networks.NetworksMessage;

public class SkinningEntitiesServerMessage extends NetworksMessage
{
    public SkinningEntitiesServerMessage()
    {

    }

    public SkinningEntitiesServerMessage(byte[] data)
    {
        this.data = data;
    }
}
