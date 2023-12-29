package com.nali.ilol.entities.skinning.ai;

import com.nali.ilol.entities.skinning.SkinningEntities;

public abstract class SkinningEntitiesAI
{
    public SkinningEntities skinningentities;

    public SkinningEntitiesAI(SkinningEntities skinningentities)
    {
        this.skinningentities = skinningentities;
    }

    public abstract void onUpdate();
}
