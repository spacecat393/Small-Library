package com.nali.small.entity.memo.server.ai.frame.floop;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundDaLe;

public class FrameSleFLoopDie<SD extends ISoundDaLe, BD extends IBothDaNe, E extends EntityLe, I extends IMixLe<SD, BD, E>, S extends ServerLe<SD, BD, E, I, A> & IServerS, A extends MixAIE<SD, BD, E, I, S>> extends FrameSFLoop<SD, BD, E, I, S, A>
{
    public FrameSleFLoopDie(S s, int index)
    {
        super(s, index);
    }

    @Override
    public boolean onUpdate()
    {
        return this.step() && super.onUpdate();
    }

    public boolean step()
    {
        return this.s.isZeroMove();
    }
}
