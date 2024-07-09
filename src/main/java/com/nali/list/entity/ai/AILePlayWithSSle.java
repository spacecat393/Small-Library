package com.nali.list.entity.ai;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundDaLe;
import net.minecraft.entity.EntityLivingBase;

public abstract class AILePlayWithSSle<S2 extends ServerLe<SD2, BD2, E2, I2, A2>, SD2 extends ISoundDaLe, BD2 extends IBothDaNe, E2 extends EntityLivingBase, I2 extends IMixLe<SD2, BD2, E2>, A2 extends MixAIE<SD2, BD2, E2, I2, S2>, SD extends ISoundDaLe, BD extends IBothDaNe, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, S extends ServerLe<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends AILePlayWithSSe<S2, SD2, BD2, E2, I2, A2, SD, BD, E, I, S, A>
{
    public static byte ID;

    public AILePlayWithSSle(S s)
    {
        super(s);
    }

    public void onPlay()
    {
        E e = this.s.i.getE();
        E2 e2 = this.s2.i.getE();
        super.onPlay();
        e.renderYawOffset = e2.renderYawOffset;
    }
}
