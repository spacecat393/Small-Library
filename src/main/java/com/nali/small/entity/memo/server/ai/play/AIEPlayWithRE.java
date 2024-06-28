package com.nali.small.entity.memo.server.ai.play;

import com.nali.data.IBothDaE;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.small.entity.memo.server.ai.AI;
import com.nali.sound.ISoundN;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public abstract class AIEPlayWithRE<E2 extends Entity, SD extends ISoundN, BD extends IBothDaE, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends AI<SD, BD, E, I, S, A>
{
//    public static byte ID;

    public E2 e2;

    public AIEPlayWithRE(S s)
    {
        super(s);
    }

    @Override
    public void init()
    {

    }

    @Override
    public void call()
    {

    }

    @Override
    public void onUpdate()
    {

    }

    @Override
    public void writeNBT(NBTTagCompound nbttagcompound)
    {

    }

    @Override
    public void readNBT(NBTTagCompound nbttagcompound)
    {

    }
}
