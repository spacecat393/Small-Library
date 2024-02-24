package com.nali.small.entities.skinning.works;

import com.nali.small.entities.skinning.SkinningEntities;

public abstract class SkinningEntitiesWork
{
    public SkinningEntities skinningentities;

    public SkinningEntitiesWork(SkinningEntities skinningentities)
    {
        this.skinningentities = skinningentities;
    }

    public abstract void run();
}
