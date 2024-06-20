package com.nali.small.entity.memo.server.ai.frame;

import com.nali.list.entity.ai.AILeHeal;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.MixAIE;
import net.minecraft.entity.EntityLivingBase;

public class FrameLeHeal<E extends EntityLivingBase, I extends IMixLe<E>, S extends ServerLe<E, I, A>, A extends MixAIE<E, I, S>> extends FrameE<E, I, S, A>
{
    public AILeHeal<E, I, S, A> aileheal;
    public FrameLeHeal(S s, byte frame, byte index)
    {
        super(s, frame, index);
        this.aileheal = (AILeHeal<E, I, S, A>)this.s.a.aie_map.get(AILeHeal.ID);
    }

    @Override
    public boolean onUpdate()
    {
        this.step = 1;
        if (this.aileheal.state == 0 || this.aileheal.state == 1)
        {
            int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
            for (int heal_frame : this.aileheal.heal_frame_int_array)
            {
                if (this.s.frame_int_array[this.frame] == heal_frame)
                {
                    this.aileheal.state = 1;
                    break;
                }
            }

            if (this.s.frame_int_array[this.frame] < frame_2d_int_array[this.index][0] || this.s.frame_int_array[this.frame] > frame_2d_int_array[this.index][1])
            {
                this.s.frame_int_array[this.frame] = frame_2d_int_array[this.index][0];
                this.step = 0;
            }
            else if (this.s.frame_int_array[this.frame] == frame_2d_int_array[this.index][1])
            {
                this.s.frame_int_array[this.frame] = frame_2d_int_array[this.index][0];
                this.step = 0;
            }

            return true;
        }

        return false;
    }
}
