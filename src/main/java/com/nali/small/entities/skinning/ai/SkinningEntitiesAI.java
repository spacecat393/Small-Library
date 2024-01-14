package com.nali.small.entities.skinning.ai;

import com.nali.small.entities.skinning.SkinningEntities;

public abstract class SkinningEntitiesAI
{
    public SkinningEntities skinningentities;

    public SkinningEntitiesAI(SkinningEntities skinningentities)
    {
        this.skinningentities = skinningentities;
    }

    public abstract void onUpdate();
}
