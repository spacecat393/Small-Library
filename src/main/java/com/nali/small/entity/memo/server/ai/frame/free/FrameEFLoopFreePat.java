package com.nali.small.entity.memo.server.ai.frame.free;

import com.nali.data.IBothDaE;
import com.nali.list.entity.ai.AIEPat;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundN;
import net.minecraft.entity.Entity;

public class FrameEFLoopFreePat<SD extends ISoundN, BD extends IBothDaE, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends FrameEFLoopFree<SD, BD, E, I, S, A>
{
    public AIEPat<SD, BD, E, I, S, A> aiepat;

    public FrameEFLoopFreePat(S s, byte frame, byte index, byte id_pack)
    {
        super(s, frame, index, id_pack);
        this.aiepat = (AIEPat<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AIEPat.ID);
    }

    @Override
    public void free()
    {
        this.aiepat.state &= 255-1;
    }
}
