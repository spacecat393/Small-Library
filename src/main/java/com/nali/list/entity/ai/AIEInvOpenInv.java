package com.nali.list.entity.ai;

import com.nali.data.IBothDaE;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.AI;
import com.nali.small.entity.memo.server.ai.MixAIEInv;
import com.nali.sound.ISoundN;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public class AIEInvOpenInv<SD extends ISoundN, BD extends IBothDaE, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, A>, A extends MixAIEInv<SD, BD, E, I, S>> extends AI<SD, BD, E, I, S, A>
{
    public static byte ID;

    public AIEInvOpenInv(S s)
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
//        this.s.a.entityplayermp.openGui(Small.I, InventoryContainer.ID, this.s.a.entityplayermp.world, this.s.i.getE().getEntityId(), 0, 0);
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