package com.nali.small.entity.memo.server.ai.frame.attack;

import com.nali.da.IBothDaNe;
import com.nali.da.IBothDaSn;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerSle;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundDaLe;

public class FrameSleAttackPlus<SD extends ISoundDaLe, BD extends IBothDaNe & IBothDaSn, E extends EntityLe, I extends IMixLe<SD, BD, E>, S extends ServerSle<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends FrameSleAttack<SD, BD, E, I, S, A>
{
    public byte
    attack;
//    size;

    public FrameSleAttackPlus(S s, int index/*, byte size*/)
    {
        super(s, index);
//        this.size = size;
    }

    @Override
    public boolean step()
    {
        boolean result = super.step();
        if (!result)
        {
            this.attack = (byte)(this.s.i.getE().ticksExisted % /*this.size*/this.s.getFrameByteArray()[this.index + 1]);
        }

        return result;
    }

    @Override
    public byte getIndex()
    {
        return this.s.getFrameByteArray()[this.index + 1 + 1 + this.attack];
    }
}
