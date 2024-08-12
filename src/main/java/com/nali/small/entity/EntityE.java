package com.nali.small.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class EntityE extends Entity implements IMixE
{
    public EntityE(World world)
    {
        super(world);
        this.Einit(this, world);
    }

//    @Override
//    public boolean isGlowing()
//    {
//        this.getB().setGlowing(this.world.isRemote && this.getFlag(6));
//        return this.glowing;//this.glowing || this.world.isRemote && this.getFlag(6);
//    }

    @Override
    public double getYOffset()
    {
        return 0.3D;
    }

    @Override
    public BlockPos getPosition()
    {
        return new BlockPos(this.posX, this.posY, this.posZ);
    }

    @Override
    public void entityInit()
    {
        this.EentityInit();
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        this.EwriteEntityToNBT(nbttagcompound);
        this.getB().writeEntityToNBT(nbttagcompound);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        this.EreadEntityFromNBT(nbttagcompound);
        this.getB().readEntityFromNBT(nbttagcompound);
    }

    @Override
    public boolean processInitialInteract(EntityPlayer entityplayer, EnumHand enumhand)
    {
        return this.getB().processInitialInteract(entityplayer, enumhand);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        this.getB().onUpdate();
    }

    @Override
    public Entity getE()
    {
        return this;
    }

    @Override
    public IMixE getI()
    {
        return this;
    }
}
