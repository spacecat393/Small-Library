package com.nali.small.entity.memo.server.ai.frame.floopoffset;

import com.nali.da.IBothDaNe;
import com.nali.list.entity.ai.AILeAttack;
import com.nali.list.entity.ai.AILeFindMove;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundDaLe;

public class FrameSleFLoopOffSetAttackPrepare<SD extends ISoundDaLe, BD extends IBothDaNe, E extends EntityLe, I extends IMixLe<SD, BD, E>, S extends ServerLe<SD, BD, E, I, A> & IServerS, A extends MixAIE<SD, BD, E, I, S>> extends FrameSFLoopOffSet<SD, BD, E, I, S, A>
{
    public AILeAttack<SD, BD, E, I, S, A> aileattack;
    public AILeFindMove<SD, BD, E, I, S, A> ailefindmove;

    public FrameSleFLoopOffSetAttackPrepare(S s, int index)
    {
        super(s, index);
    }

    @Override
    public void init()
    {
        super.init();
        this.aileattack = (AILeAttack<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeAttack.ID);
        this.ailefindmove = (AILeFindMove<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeFindMove.ID);
    }

    @Override
    public boolean onUpdate()
    {
        return (this.aileattack.flag & 2) == 0 && super.onUpdate() && this.ailefindmove.endGoalT();
    }
}
