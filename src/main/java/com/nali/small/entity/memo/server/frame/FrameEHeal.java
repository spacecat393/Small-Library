package com.nali.small.entity.memo.server.frame;

import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.MixAIE;
import net.minecraft.entity.Entity;

public class FrameEHeal<E extends Entity, I extends IMixE<E>, S extends ServerE<E, I, A>, A extends MixAIE<E, I, S>> extends FrameE<E, I, S, A>
{
    public FrameEHeal(S s, byte frame, byte index)
    {
        super(s, frame, index);
    }

    @Override
    public boolean onUpdate()
    {
        this.step = 1;
        if (skinningentitiesheal.state == 0 || skinningentitiesheal.state == 1)
        {
            int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
            for (int heal_frame : skinningentitiesheal.heal_frame_int_array)
            {
                if (this.s.frame_int_array[this.integer_index] == heal_frame)
                {
                    skinningentitiesheal.state = 1;
                    break;
                }
            }

            if (this.s.frame_int_array[this.integer_index] < frame_2d_int_array[id0][0] || this.s.frame_int_array[this.integer_index] > frame_2d_int_array[id0][1])
            {
                this.s.frame_int_array[this.integer_index] = frame_2d_int_array[id0][0];
                this.step = 0;
            }
            else if (this.s.frame_int_array[this.integer_index] == frame_2d_int_array[id0][1])
            {
                this.s.frame_int_array[this.integer_index] = frame_2d_int_array[id0][0];
                this.step = 0;
            }

            return true;
        }

        return false;
    }
}
