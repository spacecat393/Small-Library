package com.nali.small.entity.memo.work;

import com.nali.data.IBothDaSe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.IBothE;
import net.minecraft.entity.Entity;

public abstract class WorkE<BD extends IBothDaSe, E extends Entity, I extends IMixE<BD, E>, B extends IBothE<BD, E, I>>
{
    public B b;

    public WorkE(B b)
    {
        this.b = b;
    }

    public abstract void run();
}
