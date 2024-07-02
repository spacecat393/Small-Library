package com.nali.small.entity.memo.server.ai;

import com.nali.data.IBothDaNe;
import com.nali.list.entity.ai.AIEInvLockInv;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import net.minecraft.entity.Entity;

public abstract class MixAIEInv<SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, ?>> extends MixAIE<SD, BD, E, I, S>
{
    public AIEInvLockInv<SD, BD, E, I, S, ?> aileinvlockinv;
    public MixAIEInv(S s)
    {
        super(s);
        this.aileinvlockinv = (AIEInvLockInv<SD, BD, E, I, S, ?>)this.aie_map.get(AIEInvLockInv.ID);
    }

    public void call(byte id)
    {
        if (this.aileinvlockinv.canPass(this.s.a.entityplayermp))
        {
            super.call(id);
        }
    }
}
