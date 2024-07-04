package com.nali.small.entity.memo.server.ai.frame.floopfree;

import com.nali.data.IBothDaNe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.small.entity.memo.server.ai.frame.FrameS;
import net.minecraft.entity.Entity;

public abstract class FrameSFLoopFree<SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, A> & IServerS, A extends MixAIE<SD, BD, E, I, S>> extends FrameS<SD, BD, E, I, S, A>
{
//    public byte bit;
    public FrameSFLoopFree(S s, byte frame, byte index/*, byte bit*/)
    {
        super(s, frame, index);
//        this.bit = bit;
    }

    @Override
    public boolean onUpdate()
    {
        if (this.step())
        {
            int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
            if (this.s.frame_int_array[this.frame] == frame_2d_int_array[this.index][1] - 1)
            {
                this.free();
    //            this.s.statentitiesmemory.stat &= 255 - this.id_pack;

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

        return false;
    }

    public abstract boolean step();
    public abstract void free();
}
