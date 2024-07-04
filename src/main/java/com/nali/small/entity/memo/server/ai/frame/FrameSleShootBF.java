package com.nali.small.entity.memo.server.ai.frame;

import com.nali.data.IBothDaNe;
import com.nali.data.IBothDaSn;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerSle;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundLe;

public class FrameSleShootBF<SD extends ISoundLe, BD extends IBothDaNe & IBothDaSn, E extends EntityLe, I extends IMixLe<SD, BD, E>, S extends ServerSle<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends FrameSleShoot<SD, BD, E, I, S, A>
{
    public FrameSleShootBF(S s, byte frame, byte index, byte index1, byte index2, byte index3)
    {
        super(s, frame, index, index1, index2, index3);
    }

    @Override
    public void step()
    {
        int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
        if (this.s.frame_int_array[this.frame] == frame_2d_int_array[this.index1][0])
        {
            this.step = 1;
        }
        else if (this.s.frame_int_array[this.frame] == frame_2d_int_array[this.index1][1])
        {
            this.step = -1;
        }
    }
}
