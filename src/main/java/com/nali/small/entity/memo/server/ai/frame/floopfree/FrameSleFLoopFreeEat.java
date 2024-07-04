package com.nali.small.entity.memo.server.ai.frame.floopfree;

import com.nali.data.IBothDaNe;
import com.nali.data.IBothDaSn;
import com.nali.list.entity.ai.AILeEat;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerSle;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundLe;

public class FrameSleFLoopFreeEat<SD extends ISoundLe, BD extends IBothDaNe & IBothDaSn, E extends EntityLe, I extends IMixLe<SD, BD, E>, S extends ServerSle<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends FrameSFLoopFree<SD, BD, E, I, S, A>
{
    public AILeEat<SD, BD, E, I, S, A> aileeat;

    public FrameSleFLoopFreeEat(S s, byte frame, byte index/*, byte bit*/)
    {
        super(s, frame, index/*, (byte)(255-1)*/);
    }

    @Override
    public void init()
    {
        this.aileeat = (AILeEat<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeEat.ID);
    }

    @Override
    public boolean step()
    {
        return (this.aileeat.state & 1) == 1;
    }

    @Override
    public void free()
    {
        this.aileeat.state &= 255-1;
    }
}
