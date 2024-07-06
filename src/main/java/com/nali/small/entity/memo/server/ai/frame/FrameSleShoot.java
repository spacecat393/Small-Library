package com.nali.small.entity.memo.server.ai.frame;

import com.nali.data.IBothDaNe;
import com.nali.data.IBothDaSn;
import com.nali.list.entity.ai.AILeAttack;
import com.nali.list.entity.ai.AILeFindMove;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerSle;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundLe;

public class FrameSleShoot<SD extends ISoundLe, BD extends IBothDaNe & IBothDaSn, E extends EntityLe, I extends IMixLe<SD, BD, E>, S extends ServerSle<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends FrameS<SD, BD, E, I, S, A>
{
    public AILeAttack<SD, BD, E, I, S, A> aileattack;
    public AILeFindMove<SD, BD, E, I, S, A> ailefindmove;

    public FrameSleShoot(S s, int index)
    {
        super(s, index);
    }

    @Override
    public void init()
    {
        super.init();
        this.aileattack = (AILeAttack<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeAttack.ID);
        this.ailefindmove = (AILeFindMove<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeFindMove.ID);
    }

    @Override
    public boolean onUpdate()
    {
        int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
        byte[] frame_byte_array = this.s.getFrameByteArray();
        byte frame = frame_byte_array[this.index];
        if (this.aileattack.magic_point <= 0)
        {
            this.step = 1;
            if (this.checkShoot(true))
            {
                return true;
            }
            else
            {
                byte index3 = frame_byte_array[this.index + 4];
                this.ailefindmove.endGoal();

                if (this.s.frame_int_array[frame] < frame_2d_int_array[index3][0] || this.s.frame_int_array[frame] > frame_2d_int_array[index3][1])
                {
                    this.step = 0;
                    this.s.frame_int_array[frame] = frame_2d_int_array[index3][0];
                    return true;
                }
                else if (this.s.frame_int_array[frame] >= frame_2d_int_array[index3][0] && this.s.frame_int_array[frame] <= frame_2d_int_array[index3][1])
                {
                    if (this.s.frame_int_array[frame] == frame_2d_int_array[index3][1])
                    {
                        this.step = 0;
                        this.aileattack.magic_point = this.aileattack.max_magic_point;
                        return true;
                    }
                }
                return true;
            }
        }
        else if ((this.aileattack.flag & 2) == 2)
        {
            byte index = frame_byte_array[this.index + 1];
            byte index1 = frame_byte_array[this.index + 2];
            if (this.s.frame_int_array[frame] >= frame_2d_int_array[index1][0] && this.s.frame_int_array[frame] <= frame_2d_int_array[index1][1])
            {
                this.step();

                for (int attack_frame : this.aileattack.attack_frame_int_array)
                {
                    if (this.s.frame_int_array[frame] == attack_frame)
                    {
                        this.aileattack.magic_point -= 1;
                        this.aileattack.flag |= 4;
                        break;
                    }
                }
                return true;
            }
            else if (this.s.frame_int_array[frame] >= frame_2d_int_array[index][0] && this.s.frame_int_array[frame] <= frame_2d_int_array[index][1])
            {
                if (this.s.frame_int_array[frame] == frame_2d_int_array[index][1])
                {
                    this.s.frame_int_array[frame] = frame_2d_int_array[index1][1];
                    this.step = 0;
                    return true;
                }

                this.step = 1;
                return true;
            }
            else if (this.s.frame_int_array[frame] < frame_2d_int_array[index][0] || this.s.frame_int_array[frame] > frame_2d_int_array[index][1])
            {
                this.s.frame_int_array[frame] = frame_2d_int_array[index][0];
                this.step = 0;
                return true;
            }
        }
        else
        {
            this.step = 1;
            return this.checkShoot(false);
        }

        return false;
    }

    public boolean checkShoot(boolean try_reload)
    {
        int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
        byte[] frame_byte_array = this.s.getFrameByteArray();
        byte frame = frame_byte_array[this.index];
        byte index = frame_byte_array[this.index + 1];
        byte index1 = frame_byte_array[this.index + 2];
        byte index2 = frame_byte_array[this.index + 3];
        if (this.s.frame_int_array[frame] >= frame_2d_int_array[index][0] && this.s.frame_int_array[frame] <= frame_2d_int_array[index][1])
        {
            this.ailefindmove.endGoal();
            if (this.s.frame_int_array[frame] == frame_2d_int_array[index][1])
            {
                this.s.frame_int_array[frame] = frame_2d_int_array[index2][0];
                this.step = 0;
            }
            return true;
        }
        else if (this.s.frame_int_array[frame] >= frame_2d_int_array[index1][0] && this.s.frame_int_array[frame] <= frame_2d_int_array[index1][1])
        {
            this.ailefindmove.endGoal();
            if (this.s.frame_int_array[frame] == frame_2d_int_array[index1][1])
            {
                this.s.frame_int_array[frame] = frame_2d_int_array[index2][0];
                this.step = 0;
            }
            return true;
        }
        else if (!try_reload && this.s.frame_int_array[frame] >= frame_2d_int_array[index2][0] && this.s.frame_int_array[frame] < frame_2d_int_array[index2][1])
        {
            this.ailefindmove.endGoal();
            return true;
        }
        else
        {
            return false;
        }
    }

    public void step()
    {
        int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
        byte[] frame_byte_array = this.s.getFrameByteArray();
        byte frame = frame_byte_array[this.index];
        byte index1 = frame_byte_array[this.index + 2];
        this.step = 1;
        if (this.s.frame_int_array[frame] >= frame_2d_int_array[index1][0] && this.s.frame_int_array[frame] <= frame_2d_int_array[index1][1])
        {
            if (this.s.frame_int_array[frame] == frame_2d_int_array[index1][1])
            {
                this.s.frame_int_array[frame] = frame_2d_int_array[index1][0];
                this.step = 0;
            }
        }
    }
}
