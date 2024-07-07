package com.nali.list.entity.ai;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.AI;
import com.nali.small.entity.memo.server.ai.MixAIEInv;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public class AIEInvLockInv<SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, A>, A extends MixAIEInv<SD, BD, E, I, S>> extends AI<SD, BD, E, I, S, A>
{
    public static byte ID;

    public AIEOwner<SD, BD, E, I, S, A> aieowner;

    public byte state;//on

    public AIEInvLockInv(S s)
    {
        super(s);
    }

    @Override
    public void init()
    {
        this.aieowner = (AIEOwner<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AIEOwner.ID);
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
        nbttagcompound.setByte("AILeInvLockInv_state", this.state);
    }

    @Override
    public void readNBT(NBTTagCompound nbttagcompound)
    {
        this.state = nbttagcompound.getByte("AILeInvLockInv_state");
    }

    public boolean canPass(Entity entity)
    {
        if ((this.state & 1) == 1)
        {
            return this.aieowner.uuid == null || entity.getUniqueID().equals(this.aieowner.uuid);
        }

        return true;
    }
}
