package com.nali.small.entity.memo;

import com.nali.data.IBothDaE;
import com.nali.small.entity.IMixE;
import com.nali.sound.ISoundN;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public interface IBothEInv<SD extends ISoundN, BD extends IBothDaE, E extends Entity, I extends IMixE<SD, BD, E>> extends IBothE<SD, BD, E, I>, IBothInv
{
    @Override
    default void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        IBothInv.super.writeEntityToNBT(nbttagcompound);
    }

    @Override
    default void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        IBothInv.super.readEntityFromNBT(nbttagcompound);
    }
}
