package com.nali.small.entity;

import com.nali.small.entity.memo.IBothE;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public abstract class EntityE extends Entity implements IMixE
{
    public IBothE ibothe;

    public EntityE(World world)
    {
        super(world);
        this.Einit(this, world);
    }
}
