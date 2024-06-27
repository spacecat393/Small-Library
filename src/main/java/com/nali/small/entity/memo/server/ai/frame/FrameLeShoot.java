package com.nali.small.entity.memo.server.ai.frame;

import com.nali.data.IBothDaE;
import com.nali.list.entity.ai.AILeAttack;
import com.nali.list.entity.ai.AILeFindMove;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundLe;
import net.minecraft.entity.EntityLivingBase;

public class FrameLeShoot<SD extends ISoundLe, BD extends IBothDaE, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, S extends ServerLe<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends FrameE<SD, BD, E, I, S, A>
{
    public AILeAttack<SD, BD, E, I, S, A> aileattack;
    public AILeFindMove<SD, BD, E, I, S, A> ailefindmove;
    public byte index1, index2, index3;

    public FrameLeShoot(S s, byte frame, byte index, byte index1, byte index2, byte index3)
    {
        super(s, frame, index);
        this.aileattack = (AILeAttack<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeAttack.ID);
        this.ailefindmove = (AILeFindMove<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeFindMove.ID);
        this.index1 = index1;
        this.index2 = index2;
        this.index3 = index3;
    }

    @Override
    public boolean onUpdate()
    {
        int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
        if (this.aileattack.magic_point <= 0)
        {
            this.step = 1;
            if (this.checkShoot(true))
            {
                return true;
            }
            else
            {
                this.ailefindmove.endGoal();

                if (this.s.frame_int_array[this.frame] < frame_2d_int_array[this.index3][0] || this.s.frame_int_array[this.frame] > frame_2d_int_array[this.index3][1])
                {
                    this.step = 0;
                    this.s.frame_int_array[this.frame] = frame_2d_int_array[this.index3][0];
                    return true;
                }
                else if (this.s.frame_int_array[this.frame] >= frame_2d_int_array[this.index3][0] && this.s.frame_int_array[this.frame] <= frame_2d_int_array[this.index3][1])
                {
                    if (this.s.frame_int_array[this.frame] == frame_2d_int_array[this.index3][1])
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
            if (this.s.frame_int_array[this.frame] >= frame_2d_int_array[this.index1][0] && this.s.frame_int_array[this.frame] <= frame_2d_int_array[this.index1][1])
            {
                this.step();

                for (int attack_frame : this.aileattack.attack_frame_int_array)
                {
                    if (this.s.frame_int_array[this.frame] == attack_frame)
                    {
                        this.aileattack.magic_point -= 1;
                        this.aileattack.flag |= 4;
                        break;
                    }
                }
                return true;
            }
            else if (this.s.frame_int_array[this.frame] >= frame_2d_int_array[this.index][0] && this.s.frame_int_array[this.frame] <= frame_2d_int_array[this.index][1])
            {
                if (this.s.frame_int_array[this.frame] == frame_2d_int_array[this.index][1])
                {
                    this.s.frame_int_array[this.frame] = frame_2d_int_array[this.index1][1];
                    this.step = 0;
                    return true;
                }

                this.step = 1;
                return true;
            }
            else if (this.s.frame_int_array[this.frame] < frame_2d_int_array[this.index][0] || this.s.frame_int_array[this.frame] > frame_2d_int_array[this.index][1])
            {
                this.s.frame_int_array[this.frame] = frame_2d_int_array[this.index][0];
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
        if (this.s.frame_int_array[this.frame] >= frame_2d_int_array[this.index][0] && this.s.frame_int_array[this.frame] <= frame_2d_int_array[this.index][1])
        {
            this.ailefindmove.endGoal();
            if (this.s.frame_int_array[this.frame] == frame_2d_int_array[this.index][1])
            {
                this.s.frame_int_array[this.frame] = frame_2d_int_array[this.index2][0];
                this.step = 0;
            }
            return true;
        }
        else if (this.s.frame_int_array[this.frame] >= frame_2d_int_array[this.index1][0] && this.s.frame_int_array[this.frame] <= frame_2d_int_array[this.index1][1])
        {
            this.ailefindmove.endGoal();
            if (this.s.frame_int_array[this.frame] == frame_2d_int_array[this.index1][1])
            {
                this.s.frame_int_array[this.frame] = frame_2d_int_array[this.index2][0];
                this.step = 0;
            }
            return true;
        }
        else if (!try_reload && this.s.frame_int_array[this.frame] >= frame_2d_int_array[this.index2][0] && this.s.frame_int_array[this.frame] < frame_2d_int_array[this.index2][1])
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
        this.step = 1;
        if (this.s.frame_int_array[this.frame] >= frame_2d_int_array[this.index1][0] && this.s.frame_int_array[this.frame] <= frame_2d_int_array[this.index1][1])
        {
            if (this.s.frame_int_array[this.frame] == frame_2d_int_array[this.index1][1])
            {
                this.s.frame_int_array[this.frame] = frame_2d_int_array[this.index1][0];
                this.step = 0;
            }
        }
    }
}
