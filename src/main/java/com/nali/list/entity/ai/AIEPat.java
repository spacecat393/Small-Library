package com.nali.list.entity.ai;

import com.nali.data.IBothDaE;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.AI;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundN;
import com.nali.system.bytes.ByteReader;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;

public class AIEPat<SD extends ISoundN, BD extends IBothDaE, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends AI<SD, BD, E, I, S, A>
{
    public static byte ID;

    public byte state;//t-pat

    public AIEPat(S s)
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
        this.state |= 1;
        E e = this.s.i.getE();
        this.s.worldserver.spawnParticle(EnumParticleTypes.HEART, e.posX, ByteReader.getFloat(this.s.a.byte_array, 1 + 16), e.posZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public void onUpdate()
    {
    }

    @Override
    public void writeNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setByte("AIEPat_state", this.state);
    }

    @Override
    public void readNBT(NBTTagCompound nbttagcompound)
    {
        this.state = nbttagcompound.getByte("AIEPat_state");
    }
}
