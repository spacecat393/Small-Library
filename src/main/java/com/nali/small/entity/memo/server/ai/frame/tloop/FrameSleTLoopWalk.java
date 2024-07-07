package com.nali.small.entity.memo.server.ai.frame.tloop;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundDaLe;

public class FrameSleTLoopWalk<SD extends ISoundDaLe, BD extends IBothDaNe, E extends EntityLe, I extends IMixLe<SD, BD, E>, S extends ServerLe<SD, BD, E, I, A> & IServerS, A extends MixAIE<SD, BD, E, I, S>> extends FrameSTLoop<SD, BD, E, I, S, A>
{
    public FrameSleTLoopWalk(S s, int index)
    {
        super(s, index);
    }

    @Override
    public boolean onUpdate()
    {
        return this.s.i.getE().moveForward != 0 && super.onUpdate();
    }
}
