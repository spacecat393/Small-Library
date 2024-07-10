package com.nali.small.entity.memo.server.ai.frame.shoot;

import com.nali.da.IBothDaNe;
import com.nali.da.IBothDaSn;
import com.nali.list.entity.ai.*;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerSle;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundDaLe;

public class FrameSleShootSSle<R2 extends AIEPlayWithRSe<S, SD, BD, E, I, A, SD2, BD2, E2, I2, S2, A2>, S2 extends ServerSle<SD2, BD2, E2, I2, A2>, SD2 extends ISoundDaLe, BD2 extends IBothDaNe & IBothDaSn, E2 extends EntityLe, I2 extends IMixLe<SD2, BD2, E2>, A2 extends MixAIE<SD2, BD2, E2, I2, S2>, SD extends ISoundDaLe, BD extends IBothDaNe & IBothDaSn, E extends EntityLe, I extends IMixLe<SD, BD, E>, S extends ServerSle<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends FrameSleShoot<SD, BD, E, I, S, A>
{
    public AILePlayWithSSle<R2, S2, SD2, BD2, E2, I2, A2, SD, BD, E, I, S, A> aileplaywithssle;

    public FrameSleShootSSle(S s, int index)
    {
        super(s, index);
    }

    @Override
    public void init()
    {
        this.aileplaywithssle = (AILePlayWithSSle<R2, S2, SD2, BD2, E2, I2, A2, SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILePlayWithSSle.ID);
    }

    @Override
    public boolean onUpdate()
    {
        boolean result = this.aileplaywithssle.s2 != null;
        if (result)
        {
            this.aileattack = (AILeAttack/*<SD2, BD2, E2, I2, S2, A2>*/)this.aileplaywithssle.s2.a.aie_map.get(AILeAttack.ID);
            this.ailefindmove = (AILeFindMove/*<SD2, BD2, E2, I2, S2, A2>*/)this.aileplaywithssle.s2.a.aie_map.get(AILeFindMove.ID);
        }
        return result && super.onUpdate();
    }
}
