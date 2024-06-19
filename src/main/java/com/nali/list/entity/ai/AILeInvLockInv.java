package com.nali.list.entity.ai;

import com.nali.small.entity.EntityLeInv;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerLeInv;
import com.nali.small.entity.memo.server.ai.AI;
import com.nali.small.entity.memo.server.ai.MixAIE;
import net.minecraft.nbt.NBTTagCompound;

public class AILeInvLockInv<E extends EntityLeInv<?>, I extends IMixLe<E>, S extends ServerLeInv<E, I, A>, A extends MixAIE<E, I, S>> extends AI<E, I, S, A>
{
    public static byte ID;

    public byte state;//on

    public AILeInvLockInv(S s)
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
        nbttagcompound.setByte("AILeInvLockInv_state", this.state);
    }

    @Override
    public void readNBT(NBTTagCompound nbttagcompound)
    {
        this.state = nbttagcompound.getByte("AILeInvLockInv_state");
    }
}
