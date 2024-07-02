package com.nali.list.entity.ai;

import com.nali.data.IBothDaNe;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.AI;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundLe;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;

public class AILeLockDMG<SD extends ISoundLe, BD extends IBothDaNe, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, S extends ServerE<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends AI<SD, BD, E, I, S, A>
{
    public static byte ID;

    public byte state;//on

    public AILeLockDMG(S s)
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
        nbttagcompound.setByte("AILeLockDMG_state", this.state);
    }

    @Override
    public void readNBT(NBTTagCompound nbttagcompound)
    {
        this.state = nbttagcompound.getByte("AILeLockDMG_state");
    }
}
