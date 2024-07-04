package com.nali.small.entity.memo.server;

import com.nali.data.IBothDaNe;
import com.nali.data.IBothDaSn;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundLe;

public abstract class ServerSle<SD extends ISoundLe, BD extends IBothDaNe & IBothDaSn, E extends EntityLe, I extends IMixLe<SD, BD, E>, A extends MixAIE<SD, BD, E, I, ?>> extends ServerLe<SD, BD, E, I, A> implements IServerS
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
