package com.nali.list.entity.ai;

import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.small.entity.memo.server.ai.AI;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public class AIEPlayWithRE<E2 extends Entity, E extends Entity, I extends IMixE<E>, S extends ServerE<E, I, A>, A extends MixAIE<E, I, S>> extends AI<E, I, S, A>
{
    public static byte ID;

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
