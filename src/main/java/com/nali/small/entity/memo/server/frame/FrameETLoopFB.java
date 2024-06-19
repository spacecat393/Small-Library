package com.nali.small.entity.memo.server.frame;

import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.MixAIE;
import net.minecraft.entity.Entity;

public class FrameETLoopFB<E extends Entity, I extends IMixE<E>, S extends ServerE<E, I, A>, A extends MixAIE<E, I, S>> extends FrameE<E, I, S, A>
{
    public FrameETLoopFB(S s, byte frame, byte index)
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
