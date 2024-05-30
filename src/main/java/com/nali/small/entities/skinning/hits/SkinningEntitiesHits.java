package com.nali.small.entities.skinning.hits;

import com.nali.small.entities.skinning.SkinningEntities;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;

public abstract class SkinningEntitiesHits
{
    public SkinningEntities skinningentities;

    public SkinningEntitiesHits(SkinningEntities skinningentities)
    {
        this.skinningentities = skinningentities;
    }

    public abstract void run(Entity player_entity, AxisAlignedBB axisalignedbb);
    public abstract boolean should(Entity player_entity, AxisAlignedBB axisalignedbb);
}
