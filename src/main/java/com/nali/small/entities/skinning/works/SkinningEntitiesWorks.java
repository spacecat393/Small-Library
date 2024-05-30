package com.nali.small.entities.skinning.works;

import com.nali.small.entities.skinning.SkinningEntities;

public abstract class SkinningEntitiesWorks
{
    public SkinningEntities skinningentities;

    public SkinningEntitiesWorks(SkinningEntities skinningentities)
    {
        this.skinningentities = skinningentities;
    }

    public abstract void run();
}
