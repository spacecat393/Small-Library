package com.nali.small.entity.memo.server.ai.frame.tloop;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.small.entity.memo.server.ai.frame.FrameS;
import net.minecraft.entity.Entity;

public class FrameSTLoop<SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, A> & IServerS, A extends MixAIE<SD, BD, E, I, S>> extends FrameS<SD, BD, E, I, S, A>
{
    public FrameSTLoop(S s, int index)
    {
        super(s, index);
    }

    @Override
    public boolean onUpdate()
    {
        int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
        byte[] frame_byte_array = this.s.getFrameByteArray();
        byte frame = frame_byte_array[this.index];
        byte index = frame_byte_array[this.index + 1];
        this.step = 1;
        if (this.s.frame_int_array[frame] < frame_2d_int_array[index][0] || this.s.frame_int_array[frame] > frame_2d_int_array[index][1] - 1)
        {
            this.s.frame_int_array[frame] = frame_2d_int_array[index][0];
            this.step = 0;
            return true;
        }

        return this.s.frame_int_array[frame] < frame_2d_int_array[index][1];
    }
}
