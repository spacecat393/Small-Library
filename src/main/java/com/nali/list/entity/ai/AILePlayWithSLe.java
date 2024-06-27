package com.nali.list.entity.ai;

import com.nali.data.IBothDaE;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundLe;
import net.minecraft.entity.EntityLivingBase;

public class AILePlayWithSLe<E2 extends EntityLivingBase, SD extends ISoundLe, BD extends IBothDaE, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, S extends ServerLe<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends AILePlayWithSE<E2, SD, BD, E, I, S, A>
{
    public static byte ID;

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
