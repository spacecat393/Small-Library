package com.nali.small.entity.memo.server.frame;

import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.MixAIE;
import net.minecraft.entity.Entity;

public class FrameEProtect<E extends Entity, I extends IMixE<E>, S extends ServerE<E, I, A>, A extends MixAIE<E, I, S>> extends FrameE<E, I, S, A>
{
    public FrameEProtect(S s, byte frame, byte index)
    {
        super(s, frame, index);
    }

    @Override
    public boolean onUpdate()
    {
        int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
        this.step = 1;
        switch (skinningentitiesprotect.state)
        {
            case 0:
            {
                if (this.s.frame_int_array[this.frame] < frame_2d_int_array[id0][0] || this.s.frame_int_array[this.frame] > frame_2d_int_array[id0][1])
                {
                    this.s.frame_int_array[this.frame] = frame_2d_int_array[id0][0];
                    this.step = 0;
                }
                else if (this.s.frame_int_array[this.frame] == frame_2d_int_array[id0][1])
                {
                    this.s.frame_int_array[this.frame] = frame_2d_int_array[id1][0];
                    this.step = 0;
                    skinningentitiesprotect.main_state = 1;
                }

                break;
            }
            case 1:
            {
                if (this.s.frame_int_array[this.frame] < frame_2d_int_array[id1][0] || this.s.frame_int_array[this.frame] > frame_2d_int_array[id1][1])
                {
                    this.s.frame_int_array[this.frame] = frame_2d_int_array[id1][0];
                    this.step = 0;
                }
                else if (this.s.frame_int_array[this.frame] == frame_2d_int_array[id1][1])
                {
                    this.s.frame_int_array[this.frame] = frame_2d_int_array[id1][0];
                    this.step = 0;
                }

                break;
            }
            case 2:
            {
                if (this.s.frame_int_array[this.frame] < frame_2d_int_array[id2][0] || this.s.frame_int_array[this.frame] > frame_2d_int_array[id2][1])
                {
                    this.s.frame_int_array[this.frame] = frame_2d_int_array[id2][0];
                    this.step = 0;
                }
                else if (this.s.frame_int_array[this.frame] == frame_2d_int_array[id2][1])
                {
                    this.s.frame_int_array[this.frame] = frame_2d_int_array[id2][0];
                    this.step = 0;
                    skinningentitiesprotect.main_state = 1;
                }

                break;
            }
            case 3:
            {
                if (this.s.frame_int_array[this.frame] < frame_2d_int_array[id3][0] || this.s.frame_int_array[this.frame] > frame_2d_int_array[id3][1])
                {
                    this.s.frame_int_array[this.frame] = frame_2d_int_array[id3][0];
                    this.step = 0;
                }
                else if (this.s.frame_int_array[this.frame] == frame_2d_int_array[id3][1])
                {
//                    this.s.frame_int_array[this.frame] = frame_2d_int_array[id3][0];
                    this.step = 0;
                    skinningentitiesprotect.main_state = -2;
                    return true;
                }
                break;
            }
            default:
            {
                break;
            }
        }

        return skinningentitiesprotect.state > -1;
    }
}
