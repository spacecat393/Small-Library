package com.nali.small.entity.memo.server.ai.frame;

import com.nali.da.IBothDaNe;
import com.nali.da.IBothDaSn;
import com.nali.list.entity.ai.AILeHeal;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerSle;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundDaLe;

public class FrameSleHeal<SD extends ISoundDaLe, BD extends IBothDaNe & IBothDaSn, E extends EntityLe, I extends IMixLe<SD, BD, E>, S extends ServerSle<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends FrameS<SD, BD, E, I, S, A>
{
    public AILeHeal<SD, BD, E, I, S, A> aileheal;

//    public byte index;

    public FrameSleHeal(S s, int index)
    {
        super(s, index);
//        index = index;
    }

    @Override
    public void init()
    {
        super.init();
        this.aileheal = (AILeHeal<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeHeal.ID);
    }

    @Override
    public boolean onUpdate()
    {
        this.step = 1;
        if (this.aileheal.state == 0 || this.aileheal.state == 1)
        {
            int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
            byte[] frame_byte_array = this.s.getFrameByteArray();
            byte frame = frame_byte_array[this.index];
            for (int heal_frame : this.aileheal.heal_frame_int_array)
            {
                if (this.s.frame_int_array[frame] == heal_frame)
                {
                    this.aileheal.state = 1;
                    break;
                }
            }

            byte index = frame_byte_array[this.index + 1];
            if (this.s.frame_int_array[frame] < frame_2d_int_array[index][0] || this.s.frame_int_array[frame] > frame_2d_int_array[index][1])
            {
                this.s.frame_int_array[frame] = frame_2d_int_array[index][0];
                this.step = 0;
            }
            else if (this.s.frame_int_array[frame] == frame_2d_int_array[index][1])
            {
                this.s.frame_int_array[frame] = frame_2d_int_array[index][0];
                this.step = 0;
            }

            return true;
        }

        return false;
    }
}
