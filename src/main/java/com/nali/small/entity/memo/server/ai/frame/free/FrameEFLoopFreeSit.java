package com.nali.small.entity.memo.server.ai.frame.free;

import com.nali.data.IBothDaE;
import com.nali.list.entity.ai.AIESit;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundN;
import net.minecraft.entity.Entity;

public class FrameEFLoopFreeSit<SD extends ISoundN, BD extends IBothDaE, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends FrameEFLoopFree<SD, BD, E, I, S, A>
{
    public AIESit<SD, BD, E, I, S, A> aiesit;

    public byte bit;

    public FrameEFLoopFreeSit(S s, byte frame, byte index, byte id_pack, byte bit)
    {
        super(s, frame, index, id_pack);
        this.aiesit = (AIESit<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AIESit.ID);
        this.bit = bit;
    }

    @Override
    public void free()
    {
        this.aiesit.state &= /*255 - */this.bit;
    }
}
