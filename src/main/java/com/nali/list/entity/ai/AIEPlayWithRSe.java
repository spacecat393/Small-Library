package com.nali.list.entity.ai;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.AI;
import com.nali.small.entity.memo.server.ai.MixAIE;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public abstract class AIEPlayWithRSe<S2 extends ServerE<SD2, BD2, E2, I2, A2>, SD2, BD2 extends IBothDaNe, E2 extends Entity, I2 extends IMixE<SD2, BD2, E2>, A2 extends MixAIE<SD2, BD2, E2, I2, S2>, SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends AI<SD, BD, E, I, S, A>
{
    public static byte ID;

//    public AIESit<SD2, BD2, E2, I2, S2, A2> aiesit2;

    public S2 s2;

    public byte state;//R se> on play
    //S sle> on should_play -first_playwith-

    public AIEPlayWithRSe(S s)
    {
        super(s);
    }

//    @Override
//    public void init()
//    {
//
//    }
//
//    @Override
//    public void call()
//    {
//
//    }
//
//    @Override
//    public void onUpdate()
//    {
//
//    }

    @Override
    public void call()
    {
        this.state |= 2;
    }

    @Override
    public void writeNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setByte("AIEPlayWith_state", this.state);
    }

    @Override
    public void readNBT(NBTTagCompound nbttagcompound)
    {
        this.state = nbttagcompound.getByte("AIEPlayWith_state");
    }
//    @Override
//    public void onUpdate()
//    {
//        if (this.s2 != null)
//        {
//            this.aiesit2 = ((AIESit)this.s2.a.aie_map.get(AIESit.ID));
//        }
//    }
//
//    public void onFree()
//    {
//        this.aiesit2 = null;
//    }
}
