package com.nali.small.entity.memo.server.ai.frame.shoot;

import com.nali.da.IBothDaNe;
import com.nali.da.IBothDaSn;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerSle;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundDaLe;

public class FrameSleShootReloadPlus<SD extends ISoundDaLe, BD extends IBothDaNe & IBothDaSn, E extends EntityLe, I extends IMixLe<SD, BD, E>, S extends ServerSle<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends FrameSleShoot<SD, BD, E, I, S, A>
{
    public byte
    reload;
//    size;

    public FrameSleShootReloadPlus(S s, int index/*, byte size*/)
    {
        super(s, index);
//        this.size = size;
    }

    @Override
    public boolean onUpdate()
    {
        if (this.aileattack.magic_point > 0)
        {
            this.reload = (byte)(this.s.i.getE().ticksExisted % /*this.size*/this.s.getFrameByteArray()[this.index + 4]);
        }

        return super.onUpdate();
    }

    @Override
    public byte getReload()
    {
        return this.s.getFrameByteArray()[this.index + 4 + 1 + this.reload];
    }
}
