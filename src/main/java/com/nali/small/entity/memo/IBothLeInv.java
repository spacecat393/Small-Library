package com.nali.small.entity.memo;

import com.nali.data.IBothDaE;
import com.nali.small.entity.IMixLe;
import com.nali.sound.ISoundN;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;

public interface IBothLeInv<SD extends ISoundN, BD extends IBothDaE, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>> extends IBothLe<SD, BD, E, I>, IBothInv
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
