package com.nali.small.entity.memo.server.ai.frame.floopfree;

import com.nali.data.IBothDaNe;
import com.nali.data.IBothDaSn;
import com.nali.list.entity.ai.AIEPat;
import com.nali.list.entity.ai.AIESit;
import com.nali.list.entity.ai.AILeEat;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerSle;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundLe;

public class FrameSleFLoopFreePSE<SD extends ISoundLe, BD extends IBothDaNe & IBothDaSn, E extends EntityLe, I extends IMixLe<SD, BD, E>, S extends ServerSle<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends FrameSFLoopFree<SD, BD, E, I, S, A>
{
    public AIEPat<SD, BD, E, I, S, A> aiepat;
    public AIESit<SD, BD, E, I, S, A> aiesit;
    public AILeEat<SD, BD, E, I, S, A> aileeat;

    public FrameSleFLoopFreePSE(S s, byte frame, byte index)
    {
        super(s, frame, index);
    }

    @Override
    public void init()
    {
        this.aiepat = (AIEPat<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AIEPat.ID);
        this.aiesit = (AIESit<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AIESit.ID);
        this.aileeat = (AILeEat<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeEat.ID);
    }

    @Override
    public boolean step()
    {
        return (this.aiepat.state & 1) == 1 || (this.aiesit.state & 2) == 2 || (this.aileeat.state & 1) == 1;
    }

    @Override
    public void free()
    {
        this.aiepat.state &= 255-1;
        this.aiesit.state &= 255-2;
        this.aileeat.state &= 255-1;
    }
}
