package com.nali.small.entity.memo;

import com.nali.data.IBothDaSe;
import com.nali.small.entity.Inventory;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public interface INNeInv<BD extends IBothDaSe, E extends Entity/*, I extends IMixE<BD, E>*/>/* extends IBothLe<BD, E, I>*/
{
//    @Override
    default void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        this.getInventory().writeNBT(nbttagcompound);
    }

//    @Override
    default void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        this.getInventory().readNBT(nbttagcompound);
    }

    Inventory getInventory();
}
