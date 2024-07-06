package com.nali.small.entity.memo.server.ai.frame.tloopinset;

import com.nali.data.IBothDaNe;
import com.nali.list.entity.ai.AIESit;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.MixAIE;
import net.minecraft.entity.Entity;

public class FrameSeTLoopInSetSit<SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, A> & IServerS, A extends MixAIE<SD, BD, E, I, S>> extends FrameSTLoopInSet<SD, BD, E, I, S, A>
{
    public AIESit<SD, BD, E, I, S, A> aiesit;

    public FrameSeTLoopInSetSit(S s, int index)
    {
        super(s, index);
    }

    @Override
    public void init()
    {
        super.init();
        this.aiesit = (AIESit<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AIESit.ID);
    }

    @Override
    public boolean onUpdate()
    {
        return (this.aiesit.state & 1) == 1 && super.onUpdate();
    }
}
