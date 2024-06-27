package com.nali.small.entity.memo;

import com.nali.small.entity.Inventory;
import net.minecraft.nbt.NBTTagCompound;

public interface IBothInv
{
    default void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        this.getInventory().writeNBT(nbttagcompound);
    }

    default void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        this.getInventory().readNBT(nbttagcompound);
    }

    Inventory getInventory();
}
