package com.nali.small.entity.memo.server.ai.frame.tloopfb;

import com.nali.data.IBothDaNe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.small.entity.memo.server.ai.frame.FrameS;
import net.minecraft.entity.Entity;

public class FrameSTLoopFB<SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, A> & IServerS, A extends MixAIE<SD, BD, E, I, S>> extends FrameS<SD, BD, E, I, S, A>
{
    public FrameSTLoopFB(S s, byte frame, byte index)
    {
        super(s, frame, index);
    }

    @Override
    public boolean onUpdate()
    {
        int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
        if (this.s.frame_int_array[this.frame] < frame_2d_int_array[this.index][0] || this.s.frame_int_array[this.frame] > frame_2d_int_array[this.index][1])
        {
            this.step = 0;
            this.s.frame_int_array[this.frame] = frame_2d_int_array[this.index][0];
        }
        else if (this.s.frame_int_array[this.frame] == frame_2d_int_array[this.index][0])
        {
            this.step = 1;
        }
        else if (this.s.frame_int_array[this.frame] == frame_2d_int_array[this.index][1])
        {
            this.step = -1;
        }

        return true;
    }
}
