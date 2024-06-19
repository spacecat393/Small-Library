package com.nali.small.entity.memo.work;

import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.IBothE;
import net.minecraft.entity.Entity;

public abstract class WorkE<E extends Entity, I extends IMixE<E>, B extends IBothE<E, I>>
{
    public B b;

    public WorkE(B b)
    {
        this.b = b;
    }

    public abstract void run();
}
