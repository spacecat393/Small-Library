package com.nali.small.entity.memo.server.ai.frame.floopfree;

import com.nali.da.IBothDaNe;
import com.nali.list.entity.ai.AIESit;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.MixAIE;
import net.minecraft.entity.Entity;

public class FrameSFLoopFreeHardReady<SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, A> & IServerS, A extends MixAIE<SD, BD, E, I, S>> extends FrameSFLoopFree<SD, BD, E, I, S, A>
{
    public AIESit<SD, BD, E, I, S, A> aiesit;

    public FrameSFLoopFreeHardReady(S s, int index)
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
    public boolean step()
    {
        return (this.aiesit.state & 2) == 2;
    }

    @Override
    public void free()
    {
        this.aiesit.state &= 255-2;
    }
}
