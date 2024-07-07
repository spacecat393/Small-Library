package com.nali.small.entity.memo.server.ai.frame.floopfree;

import com.nali.da.IBothDaNe;
import com.nali.da.IBothDaSn;
import com.nali.list.entity.ai.AIEPat;
import com.nali.list.entity.ai.AILeEat;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerSle;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundDaLe;

public class FrameSleFLoopFreePE<SD extends ISoundDaLe, BD extends IBothDaNe & IBothDaSn, E extends EntityLe, I extends IMixLe<SD, BD, E>, S extends ServerSle<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends FrameSFLoopFree<SD, BD, E, I, S, A>
{
    public AIEPat<SD, BD, E, I, S, A> aiepat;
    public AILeEat<SD, BD, E, I, S, A> aileeat;

    public FrameSleFLoopFreePE(S s, int index)
    {
        super(s, index);
    }

    @Override
    public void init()
    {
        super.init();
        this.aiepat = (AIEPat<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AIEPat.ID);
        this.aileeat = (AILeEat<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeEat.ID);
    }

    @Override
    public boolean step()
    {
        return (this.aiepat.state & 1) == 1 || (this.aileeat.state & 1) == 1;
    }

    @Override
    public void free()
    {
        this.aiepat.state &= 255-1;
        this.aileeat.state &= 255-1;
    }
}
