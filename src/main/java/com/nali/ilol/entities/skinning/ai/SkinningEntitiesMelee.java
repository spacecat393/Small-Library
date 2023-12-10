package com.nali.ilol.entities.skinning.ai;

import com.nali.ilol.entities.skinning.SkinningEntities;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHand;

public class SkinningEntitiesMelee
{
    public SkinningEntities skinningentities;

    public SkinningEntitiesMelee(SkinningEntities skinningentities)
    {
        this.skinningentities = skinningentities;
    }

    public void onUpdate()
    {
        for (Entity entity : this.skinningentities.skinningentitiesarea.all_entity_arraylist)
        {
            this.skinningentities.swingArm(EnumHand.MAIN_HAND);
            this.skinningentities.attackEntityAsMob(entity);
        }
    }
}
