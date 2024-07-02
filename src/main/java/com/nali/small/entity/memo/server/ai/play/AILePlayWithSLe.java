package com.nali.small.entity.memo.server.ai.play;

import com.nali.data.IBothDaNe;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundLe;
import net.minecraft.entity.EntityLivingBase;

public abstract class AILePlayWithSLe<E2 extends EntityLivingBase, SD extends ISoundLe, BD extends IBothDaNe, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, S extends ServerLe<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends AILePlayWithSE<E2, SD, BD, E, I, S, A>
{
//    public static byte ID;

    public AILePlayWithSLe(S s)
    {
        super(s);
    }

    @Override
    public void onPlay()
    {
        super.onPlay();
        this.s.i.getE().renderYawOffset = this.e2.renderYawOffset;
    }
}
