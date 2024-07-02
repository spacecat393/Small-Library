package com.nali.small.entity.memo.server.ai;

import com.nali.data.IBothDaNe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public abstract class AI<SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>>
{
    public S s;
//    public byte num;

    public AI(S s)
    {
        this.s = s;
    }

    public abstract void init();
    public abstract void call();
//    public abstract void flip();
    public abstract void onUpdate();
    public abstract void writeNBT(NBTTagCompound nbttagcompound);
    public abstract void readNBT(NBTTagCompound nbttagcompound);
}
