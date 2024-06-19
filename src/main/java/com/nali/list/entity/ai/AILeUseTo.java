package com.nali.list.entity.ai;

import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.small.entity.memo.server.ai.AI;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;

public class AILeUseTo<E extends EntityLivingBase, I extends IMixLe<E>, S extends ServerLe<E, I, A>, A extends MixAIE<E, I, S>> extends AI<E, I, S, A>
{
    public static byte ID;

    public byte state;//on

    public AILeUseTo(S s)
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
        nbttagcompound.setByte("AILeUseTo_state", this.state);
    }

    @Override
    public void readNBT(NBTTagCompound nbttagcompound)
    {
        this.state = nbttagcompound.getByte("AILeUseTo_state");
    }
}
