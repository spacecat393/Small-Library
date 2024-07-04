package com.nali.small.entity.memo.server.ai.frame;

import com.nali.data.IBothDaNe;
import com.nali.data.IBothDaSn;
import com.nali.list.entity.ai.AILeProtect;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerSle;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundLe;

public class FrameSleProtect<SD extends ISoundLe, BD extends IBothDaNe & IBothDaSn, E extends EntityLe, I extends IMixLe<SD, BD, E>, S extends ServerSle<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends FrameS<SD, BD, E, I, S, A>
{
    public AILeProtect<SD, BD, E, I, S, A> aileprotect;
    public byte index1, index2, index3;
    public FrameSleProtect(S s, byte frame, byte index, byte index1, byte index2, byte index3)
    {
        super(s, frame, index);
        this.index1 = index1;
        this.index2 = index2;
        this.index3 = index3;
    }

    @Override
    public void init()
    {
        this.aileprotect = (AILeProtect<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeProtect.ID);
    }

    @Override
    public boolean onUpdate()
    {
        int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
        this.step = 1;
        switch (this.aileprotect.state & (4+8))
        {
            case 0:
            {
                if (this.s.frame_int_array[this.frame] < frame_2d_int_array[this.index][0] || this.s.frame_int_array[this.frame] > frame_2d_int_array[this.index][1])
                {
                    this.s.frame_int_array[this.frame] = frame_2d_int_array[this.index][0];
                    this.step = 0;
                }
                else if (this.s.frame_int_array[this.frame] == frame_2d_int_array[this.index][1])
                {
                    this.s.frame_int_array[this.frame] = frame_2d_int_array[this.index1][0];
                    this.step = 0;
//                    this.aileprotect.main_state = 1;
                    this.aileprotect.state |= 4;
                }

                break;
            }
            case 4:
            {
                if (this.s.frame_int_array[this.frame] < frame_2d_int_array[this.index1][0] || this.s.frame_int_array[this.frame] > frame_2d_int_array[this.index1][1])
                {
                    this.s.frame_int_array[this.frame] = frame_2d_int_array[this.index1][0];
                    this.step = 0;
                }
                else if (this.s.frame_int_array[this.frame] == frame_2d_int_array[this.index1][1])
                {
                    this.s.frame_int_array[this.frame] = frame_2d_int_array[this.index1][0];
                    this.step = 0;
                }

                break;
            }
            case 8:
            {
                if (this.s.frame_int_array[this.frame] < frame_2d_int_array[this.index2][0] || this.s.frame_int_array[this.frame] > frame_2d_int_array[this.index2][1])
                {
                    this.s.frame_int_array[this.frame] = frame_2d_int_array[this.index2][0];
                    this.step = 0;
                }
                else if (this.s.frame_int_array[this.frame] == frame_2d_int_array[this.index2][1])
                {
                    this.s.frame_int_array[this.frame] = frame_2d_int_array[this.index2][0];
                    this.step = 0;
//                    this.aileprotect.main_state = 1;
//                    this.aileprotect.state |= 4;
//                    this.aileprotect.state &= 255-(8);
                    this.aileprotect.state ^= 4+8;
                }

                break;
            }
            case 4+8:
            {
                if (this.s.frame_int_array[this.frame] < frame_2d_int_array[this.index3][0] || this.s.frame_int_array[this.frame] > frame_2d_int_array[this.index3][1])
                {
                    this.s.frame_int_array[this.frame] = frame_2d_int_array[this.index3][0];
                    this.step = 0;
                }
                else if (this.s.frame_int_array[this.frame] == frame_2d_int_array[this.index3][1])
                {
//                    this.s.frame_int_array[this.frame] = frame_2d_int_array[this.index3][0];
                    this.step = 0;
//                    this.aileprotect.main_state = -2;
                    this.aileprotect.state &= 255-(4+8);
                    return true;
                }
                break;
            }
            default:
            {
                break;
            }
        }

        return this.aileprotect.state > -1;
    }
}
