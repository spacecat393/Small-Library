package com.nali.small.entity.memo;

import com.nali.small.entity.IMixLe;
import com.nali.small.entity.Inventory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;

public interface IBothLeInv<E extends EntityLivingBase, I extends IMixLe<E>> extends IBothLe<E, I>
{
    @Override
    default void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        this.getInventory().writeNBT(nbttagcompound);
    }

    @Override
    default void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        this.getInventory().readNBT(nbttagcompound);
    }

    Inventory getInventory();
}
