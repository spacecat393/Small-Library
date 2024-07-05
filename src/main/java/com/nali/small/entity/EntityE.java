package com.nali.small.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class EntityE extends Entity implements IMixE
{
    public EntityE(World world)
    {
        super(world);
        this.Einit(this, world);
    }

    @Override
    public BlockPos getPosition()
    {
        return new BlockPos(this.posX, this.posY, this.posZ);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        this.EwriteEntityToNBT(nbttagcompound);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        this.EreadEntityFromNBT(nbttagcompound);
    }

    @Override
    public Entity getE()
    {
        return this;
    }
}
