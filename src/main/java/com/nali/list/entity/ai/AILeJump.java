package com.nali.list.entity.ai;

import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.small.entity.memo.server.ai.AI;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;

public class AILeJump<E extends EntityLivingBase, I extends IMixLe<E>, S extends ServerE<E, I, A>, A extends MixAIE<E, I, S>> extends AI<E, I, S, A>
{
    public static byte ID;

    public boolean isJumping;

    public AILeJump(S s)
    {
        super(s);
    }

    @Override
    public void init()
    {

    }

    public void setJumping()
    {
        this.isJumping = true;
    }

    @Override
    public void onUpdate()
    {
        this.s.i.getE().setJumping(this.isJumping);
        this.isJumping = false;
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
