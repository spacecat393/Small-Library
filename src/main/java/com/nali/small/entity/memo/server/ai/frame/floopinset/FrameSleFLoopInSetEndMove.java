package com.nali.small.entity.memo.server.ai.frame.floopinset;

import com.nali.data.IBothDaNe;
import com.nali.list.entity.ai.AILeFindMove;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundLe;

public class FrameSleFLoopInSetEndMove<SD extends ISoundLe, BD extends IBothDaNe, E extends EntityLe, I extends IMixLe<SD, BD, E>, S extends ServerLe<SD, BD, E, I, A> & IServerS, A extends MixAIE<SD, BD, E, I, S>> extends FrameSFLoopInSet<SD, BD, E, I, S, A>
{
    public AILeFindMove<SD, BD, E, I, S, A> ailefindmove;

    public FrameSleFLoopInSetEndMove(S s, int index)
    {
        super(s, index);
    }

    @Override
    public void init()
    {
        super.init();
        this.ailefindmove = (AILeFindMove<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeFindMove.ID);
    }

    @Override
    public boolean onUpdate()
    {
        return super.onUpdate() && this.ailefindmove.endGoalT();
    }
}
