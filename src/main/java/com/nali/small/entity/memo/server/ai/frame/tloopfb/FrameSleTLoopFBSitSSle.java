package com.nali.small.entity.memo.server.ai.frame.tloopfb;

import com.nali.da.IBothDaNe;
import com.nali.list.entity.ai.AIEPlayWithRSe;
import com.nali.list.entity.ai.AIESit;
import com.nali.list.entity.ai.AILePlayWithSSle;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundDaLe;
import net.minecraft.entity.EntityLivingBase;

public class FrameSleTLoopFBSitSSle<R2 extends AIEPlayWithRSe<S, SD, BD, E, I, A, SD2, BD2, E2, I2, S2, A2>, S2 extends ServerLe<SD2, BD2, E2, I2, A2>, SD2 extends ISoundDaLe, BD2 extends IBothDaNe, E2 extends EntityLivingBase, I2 extends IMixLe<SD2, BD2, E2>, A2 extends MixAIE<SD2, BD2, E2, I2, S2>, SD extends ISoundDaLe, BD extends IBothDaNe, E extends EntityLe, I extends IMixLe<SD, BD, E>, S extends ServerLe<SD, BD, E, I, A> & IServerS, A extends MixAIE<SD, BD, E, I, S>> extends FrameSTLoopFB<SD, BD, E, I, S, A>
{
    public AILePlayWithSSle<R2, S2, SD2, BD2, E2, I2, A2, SD, BD, E, I, S, A> aileplaywithssle;

    public FrameSleTLoopFBSitSSle(S s, int index)
    {
        super(s, index);
    }

    @Override
    public void init()
    {
        super.init();
        this.aileplaywithssle = (AILePlayWithSSle<R2, S2, SD2, BD2, E2, I2, A2, SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILePlayWithSSle.ID);
    }

    @Override
    public boolean onUpdate()
    {
        return this.aileplaywithssle.s2 != null && (((AIESit<SD2, BD2, E2, I2, S2, A2>)this.aileplaywithssle.s2.a.aie_map.get(AIESit.ID)).state & 1) == 1 && super.onUpdate();
    }
}
