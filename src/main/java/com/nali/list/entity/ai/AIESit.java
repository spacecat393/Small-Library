package com.nali.list.entity.ai;

import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.AI;
import com.nali.small.entity.memo.server.ai.MixAIE;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public class AIESit<E extends Entity, I extends IMixE<E>, S extends ServerE<E, I, A>, A extends MixAIE<E, I, S>> extends AI<E, I, S, A>
{
    public static byte ID;

    public byte state;//on

    public AIESit(S s)
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
        if ((this.state & 1) == 1)
        {
            this.state |= 1;
            this.s.a.state &= 255-1;
        }
    }

    @Override
    public void writeNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setByte("AIESit_state", this.state);
    }

    @Override
    public void readNBT(NBTTagCompound nbttagcompound)
    {
        this.state = nbttagcompound.getByte("AIESit_state");
    }
}
