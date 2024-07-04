package com.nali.small.entity;

import com.nali.small.entity.memo.IBothEInv;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public abstract class EntityEInv extends EntityE
{
    public IBothEInv ibotheinv;

    public EntityEInv(World world)
    {
        super(world);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        this.ibotheinv.writeEntityToNBT(nbttagcompound);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
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

    @Override
    public IBothEInv getB()
    {
        return ibotheinv;
    }
}
