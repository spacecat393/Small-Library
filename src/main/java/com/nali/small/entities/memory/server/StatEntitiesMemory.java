package com.nali.small.entities.memory.server;

import net.minecraft.nbt.NBTTagCompound;

public class StatEntitiesMemory
{
    /*1 pat
    * 2 soft_ready
    * 4 hard_ready
    * 8 eat*/
    public byte stat;
    public byte life_stack;
    public byte magic_point, max_magic_point = 16;

    public void writeNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setByte("stat", this.stat);
        nbttagcompound.setByte("life_stack", this.life_stack);
        nbttagcompound.setByte("magic_point", this.magic_point);
    }

    public void readNBT(NBTTagCompound nbttagcompound)
    {
        this.stat = nbttagcompound.getByte("stat");
        this.life_stack = nbttagcompound.getByte("life_stack");
        this.magic_point = nbttagcompound.getByte("magic_point");
    }
}
