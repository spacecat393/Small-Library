package com.nali.small.entity.memo.server.frame;

import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.MixAIE;
import net.minecraft.entity.Entity;

public class FrameEFLoopFree<E extends Entity, I extends IMixE<E>, S extends ServerE<E, I, A>, A extends MixAIE<E, I, S>> extends FrameE<E, I, S, A>
{
    public byte id_pack;

    public FrameEFLoopFree(S s, byte frame, byte index, byte id_pack)
    {
        super(s, frame, index);
        this.id_pack = id_pack;
    }

    @Override
    public boolean onUpdate()
    {
        int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
        if (this.s.frame_int_array[this.frame] == frame_2d_int_array[this.index][1] - 1)
        {
            this.s.statentitiesmemory.stat &= 255 - this.id_pack;

            this.s.frame_int_array[this.frame] = frame_2d_int_array[this.index][1] - 1;
            this.step = 0;
            return true;
        }
        else if (this.s.frame_int_array[this.frame] < frame_2d_int_array[this.index][0] || this.s.frame_int_array[this.frame] > frame_2d_int_array[this.index][1])
        {
            this.s.frame_int_array[this.frame] = frame_2d_int_array[this.index][0];
            this.step = 0;
            return true;
        }

        this.step = 1;
        return this.s.frame_int_array[this.frame] < frame_2d_int_array[this.index][1];
    }
}
