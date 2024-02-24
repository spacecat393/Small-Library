package com.nali.small.entities.memory;

import com.nali.data.BothData;
import com.nali.small.entities.bytes.WorkBytes;
import com.nali.small.entities.skinning.SkinningInventory;
import com.nali.small.entities.skinning.works.SkinningEntitiesBodyYaw;

public abstract class BothEntitiesMemory
{
    public WorkBytes workbytes;
    public BothData bothdata;
    public SkinningInventory skinninginventory = new SkinningInventory();
    public SkinningEntitiesBodyYaw skinningentitiesbody;

    public BothEntitiesMemory(BothData bothdata, WorkBytes workbytes)
    {
        this.bothdata = bothdata;
        this.workbytes = workbytes;
    }
}
