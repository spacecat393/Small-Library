package com.nali.ilol.entities.skinning.ai;

import com.nali.ilol.entities.skinning.SkinningEntities;
import net.minecraft.entity.Entity;

public class SkinningEntitiesRange
{
    public SkinningEntities skinningentities;

    public SkinningEntitiesRange(SkinningEntities skinningentities)
    {
        this.skinningentities = skinningentities;
    }

    public void onUpdate()
    {
        for (Entity entity : this.skinningentities.skinningentitiesarea.all_entity_arraylist)
        {

        }
    }
}
