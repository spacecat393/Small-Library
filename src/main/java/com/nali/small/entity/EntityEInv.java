package com.nali.small.entity;

import com.nali.small.entity.memo.IBothEInv;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class EntityEInv extends Entity implements IMixE
{
    public IBothEInv ibotheinv;

    public EntityEInv(World world)
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
        this.ibotheinv.writeEntityToNBT(nbttagcompound);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        this.EreadEntityFromNBT(nbttagcompound);
        this.ibotheinv.readEntityFromNBT(nbttagcompound);
    }

    @Override
    public boolean processInitialInteract(EntityPlayer entityplayer, EnumHand enumhand)
    {
        return this.ibotheinv.processInitialInteract(entityplayer, enumhand);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        this.ibotheinv.onUpdate();
    }
}
