package com.nali.small.entity.memo.server.ai.frame;

import com.nali.data.IBothDaNe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.MixAIE;
import net.minecraft.entity.Entity;

public class FrameEFLoopInSet<SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends FrameE<SD, BD, E, I, S, A>
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
        int[][] frame_2d_int_array = this.s.a.getFrame2DIntArray();
        boolean result = this.s.frame_int_array[this.frame] >= frame_2d_int_array[this.index][1] && this.s.frame_int_array[this.frame] < frame_2d_int_array[this.index1][1];

        if (result)
        {
            this.step = 1;
        }

        return result;
    }
}
