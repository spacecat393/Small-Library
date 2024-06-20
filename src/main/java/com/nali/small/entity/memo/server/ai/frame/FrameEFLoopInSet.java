package com.nali.small.entity.memo.server.ai.frame;

import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.MixAIE;
import net.minecraft.entity.Entity;

public class FrameEFLoopInSet<E extends Entity, I extends IMixE<E>, S extends ServerE<E, I, A>, A extends MixAIE<E, I, S>> extends FrameE<E, I, S, A>
{
    public byte index1;

    public FrameEFLoopInSet(S s, byte frame, byte index, byte index1)
    {
        super(s, frame, index);
        this.index1 = index1;
    }

    @Override
    public boolean onUpdate()
    {
        int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
        boolean result = this.s.frame_int_array[this.frame] >= frame_2d_int_array[this.index][1] && this.s.frame_int_array[this.frame] < frame_2d_int_array[this.index1][1];

        if (result)
        {
            this.step = 1;
        }

        return result;
    }
}