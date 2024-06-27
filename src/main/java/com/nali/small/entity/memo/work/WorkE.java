package com.nali.small.entity.memo.work;

import com.nali.data.IBothDaE;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.IBothE;
import com.nali.sound.ISoundN;
import net.minecraft.entity.Entity;

public abstract class WorkE<SD extends ISoundN, BD extends IBothDaE, E extends Entity, I extends IMixE<SD, BD, E>, B extends IBothE<SD, BD, E, I>>
{
    public B b;

    public WorkE(B b)
    {
        this.b = b;
    }

    public abstract void run();
}
