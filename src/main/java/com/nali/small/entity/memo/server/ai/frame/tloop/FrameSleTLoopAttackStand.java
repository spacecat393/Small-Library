package com.nali.small.entity.memo.server.ai.frame.tloop;

import com.nali.data.IBothDaNe;
import com.nali.list.entity.ai.AILeAttack;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundLe;

public class FrameSleTLoopAttackStand<SD extends ISoundLe, BD extends IBothDaNe, E extends EntityLe, I extends IMixLe<SD, BD, E>, S extends ServerLe<SD, BD, E, I, A> & IServerS, A extends MixAIE<SD, BD, E, I, S>> extends FrameSTLoop<SD, BD, E, I, S, A>
{
    public FrameSleTLoopAttackStand(S s, byte frame, byte index)
    {
        super(s, frame, index);
    }

    public AILeAttack<SD, BD, E, I, S, A> aileattack;

    @Override
    public void init()
    {
        this.aileattack = (AILeAttack<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeAttack.ID);
    }

    @Override
    public boolean onUpdate()
    {
        return (this.aileattack.flag & 16) == 16 && super.onUpdate();
    }
}
