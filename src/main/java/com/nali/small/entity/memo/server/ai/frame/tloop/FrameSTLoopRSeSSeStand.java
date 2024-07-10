package com.nali.small.entity.memo.server.ai.frame.tloop;

import com.nali.da.IBothDaNe;
import com.nali.list.entity.ai.AIEPlayWithRSe;
import com.nali.list.entity.ai.AILePlayWithSSe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundDaLe;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class FrameSTLoopRSeSSeStand<MA extends AIEPlayWithRSe<S2, SD2, BD2, E2, I2, A2, SD, BD, E, I, S, A>, R2 extends AILePlayWithSSe<MA, S, SD, BD, E, I, A, SD2, BD2, E2, I2, S2, A2>, S2 extends ServerLe<SD2, BD2, E2, I2, A2>, SD2 extends ISoundDaLe, BD2 extends IBothDaNe, E2 extends EntityLivingBase, I2 extends IMixLe<SD2, BD2, E2>, A2 extends MixAIE<SD2, BD2, E2, I2, S2>, SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, A> & IServerS, A extends MixAIE<SD, BD, E, I, S>> extends FrameSTLoop<SD, BD, E, I, S, A>
{
    public MA ma;

    public FrameSTLoopRSeSSeStand(S s, int index)
    {
        super(s, index);
    }

    @Override
    public void init()
    {
        super.init();
        this.ma = (MA)this.s.a.aie_map.get(MA.ID);
    }

    @Override
    public boolean onUpdate()
    {
        return this.ma.s2 != null && ((R2)this.ma.s2.a.aie_map.get(R2.ID)).s2 == this.s && super.onUpdate();
    }
}
