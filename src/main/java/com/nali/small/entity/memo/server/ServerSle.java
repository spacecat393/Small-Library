package com.nali.small.entity.memo.server;

import com.nali.da.IBothDaNe;
import com.nali.da.IBothDaSn;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundDaLe;

public abstract class ServerSle<SD extends ISoundDaLe, BD extends IBothDaNe & IBothDaSn, E extends EntityLe, I extends IMixLe<SD, BD, E>, A extends MixAIE<SD, BD, E, I, ?>> extends ServerLe<SD, BD, E, I, A> implements IServerS
{
    public ServerSle(I i)
    {
        super(i);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        this.updateFrame();
    }
}
