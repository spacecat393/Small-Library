package com.nali.small.entity.memo.server.ai.frame.floop;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundDaLe;

public class FrameSleFLoopDiePlus<SD extends ISoundDaLe, BD extends IBothDaNe, E extends EntityLe, I extends IMixLe<SD, BD, E>, S extends ServerLe<SD, BD, E, I, A> & IServerS, A extends MixAIE<SD, BD, E, I, S>> extends FrameSleFLoopDie<SD, BD, E, I, S, A>
{
    public byte die;

    public FrameSleFLoopDiePlus(S s, int index)
    {
        super(s, index);
    }

    @Override
    public boolean step()
    {
        boolean result = super.step();
        if (!result)
        {
            this.die = (byte)(this.s.i.getE().ticksExisted % this.s.getFrameByteArray()[this.index + 1]);
        }

        return super.step();
    }

    @Override
    public byte getIndex()
    {
        return this.s.getFrameByteArray()[this.index + 1 + 1 + this.die];
    }
}
