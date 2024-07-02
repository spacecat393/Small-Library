package com.nali.list.entity.ai;

import com.nali.data.IBothDaNe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.AI;
import com.nali.small.entity.memo.server.ai.MixAIE;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public class AIESit<SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends AI<SD, BD, E, I, S, A>
{
    public static byte ID;

    public byte state;//on t-ready f-ready

    public AIESit(S s)
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
        if ((this.state & 1) == 1)
        {
            this.state |= 4;
            this.state &= 255-(2+1);
        }
        else
        {
            this.state |= 2+1;
            this.state &= 255-4;
        }
//        this.state ^= 1;
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
