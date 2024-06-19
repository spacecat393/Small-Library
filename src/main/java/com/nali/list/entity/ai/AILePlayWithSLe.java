package com.nali.list.entity.ai;

import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.MixAIE;
import net.minecraft.entity.EntityLivingBase;

public class AILePlayWithSLe<E2 extends EntityLivingBase, E extends EntityLivingBase, I extends IMixLe<E>, S extends ServerLe<E, I, A>, A extends MixAIE<E, I, S>> extends AILePlayWithSE<E2, E, I, S, A>
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
