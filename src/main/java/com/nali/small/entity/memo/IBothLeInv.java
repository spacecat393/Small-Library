package com.nali.small.entity.memo;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.IMixLe;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;

public interface IBothLeInv<SD, BD extends IBothDaNe, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>> extends IBothLe<SD, BD, E, I>, IBothInv
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
