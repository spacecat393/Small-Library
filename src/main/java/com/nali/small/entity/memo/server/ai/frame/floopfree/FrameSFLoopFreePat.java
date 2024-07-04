package com.nali.small.entity.memo.server.ai.frame.floopfree;

import com.nali.data.IBothDaNe;
import com.nali.list.entity.ai.AIEPat;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.MixAIE;
import net.minecraft.entity.Entity;

public class FrameSFLoopFreePat<SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, A> & IServerS, A extends MixAIE<SD, BD, E, I, S>> extends FrameSFLoopFree<SD, BD, E, I, S, A>
{
    public AIEPat<SD, BD, E, I, S, A> aiepat;

    public FrameSFLoopFreePat(S s, byte frame, byte index/*, byte bit*/)
    {
        super(s, frame, index/*, (byte)(255-1)*/);
    }

    @Override
    public void init()
    {
        this.aiepat = (AIEPat<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AIEPat.ID);
    }

    @Override
    public boolean step()
    {
        return (this.aiepat.state & 1) == 1;
    }

    @Override
    public void free()
    {
        this.aiepat.state &= 255-1;
    }
}
