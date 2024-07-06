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
    public FrameSleProtect(S s, int index)
    {
        super(s, index);
    }

    @Override
    public void init()
    {
        super.init();
        this.aileprotect = (AILeProtect<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeProtect.ID);
    }

    @Override
    public boolean onUpdate()
    {
        int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
        byte[] frame_byte_array = this.s.getFrameByteArray();
        this.step = 1;
        byte frame = frame_byte_array[this.index];
        byte index = frame_byte_array[this.index + 1];
        byte index1 = frame_byte_array[this.index + 2];
        switch (this.aileprotect.state & (4+8))
        {
            case 0:
            {
                if (this.s.frame_int_array[frame] < frame_2d_int_array[index][0] || this.s.frame_int_array[frame] > frame_2d_int_array[index][1])
                {
                    this.s.frame_int_array[frame] = frame_2d_int_array[index][0];
                    this.step = 0;
                }
                else if (this.s.frame_int_array[frame] == frame_2d_int_array[index][1])
                {
                    this.s.frame_int_array[frame] = frame_2d_int_array[index1][0];
                    this.step = 0;
//                    this.aileprotect.main_state = 1;
                    this.aileprotect.state |= 4;
                }

                break;
            }
            case 4:
            {
                if (this.s.frame_int_array[frame] < frame_2d_int_array[index1][0] || this.s.frame_int_array[frame] > frame_2d_int_array[index1][1])
                {
                    this.s.frame_int_array[frame] = frame_2d_int_array[index1][0];
                    this.step = 0;
                }
                else if (this.s.frame_int_array[frame] == frame_2d_int_array[index1][1])
                {
                    this.s.frame_int_array[frame] = frame_2d_int_array[index1][0];
                    this.step = 0;
                }

                break;
            }
            case 8:
            {
                byte index2 = frame_byte_array[this.index + 3];
                if (this.s.frame_int_array[frame] < frame_2d_int_array[index2][0] || this.s.frame_int_array[frame] > frame_2d_int_array[index2][1])
                {
                    this.s.frame_int_array[frame] = frame_2d_int_array[index2][0];
                    this.step = 0;
                }
                else if (this.s.frame_int_array[frame] == frame_2d_int_array[index2][1])
                {
                    this.s.frame_int_array[frame] = frame_2d_int_array[index2][0];
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
                byte index3 = frame_byte_array[this.index + 4];
                if (this.s.frame_int_array[frame] < frame_2d_int_array[index3][0] || this.s.frame_int_array[frame] > frame_2d_int_array[index3][1])
                {
                    this.s.frame_int_array[frame] = frame_2d_int_array[index3][0];
                    this.step = 0;
                }
                else if (this.s.frame_int_array[frame] == frame_2d_int_array[index3][1])
                {
//                    this.s.frame_int_array[frame] = frame_2d_int_array[index3][0];
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
