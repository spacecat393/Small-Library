package com.nali.small.entity.memo;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.IMixE;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public interface IBothEInv<SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>> extends IBothE<SD, BD, E, I>, IBothInv
{
    @Override
    default void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        this.writeInvToNBT(nbttagcompound);
    }

    @Override
    default void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        this.readInvFromNBT(nbttagcompound);
    }
}
