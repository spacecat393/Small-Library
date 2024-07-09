package com.nali.small.entity.memo.server.ai.frame.floopfree;

import com.nali.da.IBothDaNe;
import com.nali.list.entity.ai.AIEPlayWithRSe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.MixAIE;
import net.minecraft.entity.Entity;

public class FrameSFLoopFreePlayWithRSePlay<S2 extends ServerE<SD2, BD2, E2, I2, A2>, SD2, BD2 extends IBothDaNe, E2 extends Entity, I2 extends IMixE<SD2, BD2, E2>, A2 extends MixAIE<SD2, BD2, E2, I2, S2>, SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, A> & IServerS, A extends MixAIE<SD, BD, E, I, S>> extends FrameSFLoopFree<SD, BD, E, I, S, A>
{
    public AIEPlayWithRSe<S2, SD2, BD2, E2, I2, A2, SD, BD, E, I, S, A> aieplaywithrse;
    public S2 s2;

    public FrameSFLoopFreePlayWithRSePlay(S s, int index)
    {
        super(s, index);
    }

    @Override
    public void init()
    {
        super.init();
        this.aieplaywithrse = (AIEPlayWithRSe<S2, SD2, BD2, E2, I2, A2, SD, BD, E, I, S, A>)this.s2.a.aie_map.get(AIEPlayWithRSe.ID);
    }

    @Override
    public boolean step()
    {
        return (this.aieplaywithrse.state & 2) == 2;
    }

    @Override
    public void free()
    {
        this.aieplaywithrse.state &= 255-2;
    }
}
