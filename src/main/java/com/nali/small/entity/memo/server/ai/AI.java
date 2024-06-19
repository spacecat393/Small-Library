package com.nali.small.entity.memo.server.ai;

import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public abstract class AI<E extends Entity, I extends IMixE<E>, S extends ServerE<E, I, A>, A extends MixAIE<E, I, S>>
{
    public S s;
//    public byte num;

    public AI(S s)
    {
        this.s = s;
    }

    public abstract void init();
    public abstract void onUpdate();
    public abstract void writeNBT(NBTTagCompound nbttagcompound);
    public abstract void readNBT(NBTTagCompound nbttagcompound);
}
