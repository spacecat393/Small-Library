package com.nali.small.entity.memo.server.ai.frame.floopfree;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.small.entity.memo.server.ai.frame.FrameS;
import net.minecraft.entity.Entity;

public abstract class FrameSFLoopFree<SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, A> & IServerS, A extends MixAIE<SD, BD, E, I, S>> extends FrameS<SD, BD, E, I, S, A>
{
//    public byte bit;
    public FrameSFLoopFree(S s, int index)
    {
        super(s, index);
//        this.bit = bit;
    }

    @Override
    public boolean onUpdate()
    {
        if (this.step())
        {
            int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
            byte[] frame_byte_array = this.s.getFrameByteArray();
            byte frame = frame_byte_array[this.index];
            byte index = this.getIndex();
            int[] frame_int_array = this.s.getFrameIntArray();
            if (frame_int_array[frame] == frame_2d_int_array[index][1] - 1)
            {
                this.free();
    //            this.s.statentitiesmemory.stat &= 255 - this.id_pack;

                frame_int_array[frame] = frame_2d_int_array[index][1] - 1;
                this.step = 0;
                return true;
            }
            else if (frame_int_array[frame] < frame_2d_int_array[index][0] || frame_int_array[frame] > frame_2d_int_array[index][1])
            {
                frame_int_array[frame] = frame_2d_int_array[index][0];
                this.step = 0;
                return true;
            }

            this.step = 1;
            return frame_int_array[frame] < frame_2d_int_array[index][1];
        }

        return false;
    }

    public byte getIndex()
    {
        return this.s.getFrameByteArray()[this.index + 1];
    }

    public abstract boolean step();
    public abstract void free();
}
