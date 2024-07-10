package com.nali.small.entity.memo.server.ai.frame.floop;

import com.nali.da.IBothDaNe;
import com.nali.list.entity.ai.AIEPlayWithRSe;
import com.nali.list.entity.ai.AILePlayWithSSe;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundDaLe;
import net.minecraft.entity.Entity;

public class FrameSleFLoopSSeRSePlay<R2 extends AIEPlayWithRSe<S, SD, BD, E, I, A, SD2, BD2, E2, I2, S2, A2>, S2 extends ServerE<SD2, BD2, E2, I2, A2>, SD2, BD2 extends IBothDaNe, E2 extends Entity, I2 extends IMixE<SD2, BD2, E2>, A2 extends MixAIE<SD2, BD2, E2, I2, S2>, SD extends ISoundDaLe, BD extends IBothDaNe, E extends EntityLe, I extends IMixLe<SD, BD, E>, S extends ServerLe<SD, BD, E, I, A> & IServerS, A extends MixAIE<SD, BD, E, I, S>> extends FrameSFLoop<SD, BD, E, I, S, A>
{
    public AILePlayWithSSe<R2, S2, SD2, BD2, E2, I2, A2, SD, BD, E, I, S, A> aileplaywithsse;

    public FrameSleFLoopSSeRSePlay(S s, int index)
    {
        super(s, index);
    }

    @Override
    public void init()
    {
        super.init();
        this.aileplaywithsse = (AILePlayWithSSe<R2, S2, SD2, BD2, E2, I2, A2, SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILePlayWithSSe.ID);
    }

    @Override
    public boolean onUpdate()
    {
        return this.aileplaywithsse.s2 != null && (((R2)this.aileplaywithsse.s2.a.aie_map.get(R2.ID)).state & 2) == 2 && super.onUpdate();
    }
}
