package com.nali.small.entity.memo.server.frame;

import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ai.MixAIE;
import net.minecraft.entity.Entity;

public class FrameEShoot<E extends Entity, I extends IMixE<E>, S extends ServerE<E, I, A>, A extends MixAIE<E, I, S>> extends FrameE<E, I, S, A>
{
    public FrameEShoot(S s, byte frame, byte index)
    {
        super(s, frame, index);
    }

    @Override
    public boolean onUpdate()
    {
        int[][] frame_2d_int_array = this.s.getFrame2DIntArray();
        if (this.s.entitiesaimemory.skinningentitiesattack.magic_point <= 0)
        {
            this.step = 1;
            if (this.checkShoot(id0, id1, id2, true))
            {
                return true;
            }
            else
            {
                this.s.entitiesaimemory.skinningentitiesfindmove.endGoal();

                if (this.s.frame_int_array[this.integer_index] < frame_2d_int_array[id3][0] || this.s.frame_int_array[this.integer_index] > frame_2d_int_array[id3][1])
                {
                    this.step = 0;
                    this.s.frame_int_array[this.integer_index] = frame_2d_int_array[id3][0];
                    return true;
                }
                else if (this.s.frame_int_array[this.integer_index] >= frame_2d_int_array[id3][0] && this.s.frame_int_array[this.integer_index] <= frame_2d_int_array[id3][1])
                {
                    if (this.s.frame_int_array[this.integer_index] == frame_2d_int_array[id3][1])
                    {
                        this.step = 0;
                        this.s.entitiesaimemory.skinningentitiesattack.magic_point = this.s.entitiesaimemory.skinningentitiesattack.max_magic_point;
                        return true;
                    }
                }
                return true;
            }
        }
        else if ((skinningentitiesattack.flag & 2) == 2)
        {
            if (this.s.frame_int_array[this.integer_index] >= frame_2d_int_array[id1][0] && this.s.frame_int_array[this.integer_index] <= frame_2d_int_array[id1][1])
            {
                if (bf)
                {
                    if (this.s.frame_int_array[this.integer_index] == frame_2d_int_array[id1][0])
                    {
                        this.step = 1;
                    }
                    else if (this.s.frame_int_array[this.integer_index] == frame_2d_int_array[id1][1])
                    {
                        this.step = -1;
                    }
                }
                else
                {
                    this.step = 1;
                    if (this.s.frame_int_array[this.integer_index] >= frame_2d_int_array[id1][0] && this.s.frame_int_array[this.integer_index] <= frame_2d_int_array[id1][1])
                    {
                        if (this.s.frame_int_array[this.integer_index] == frame_2d_int_array[id1][1])
                        {
                            this.s.frame_int_array[this.integer_index] = frame_2d_int_array[id1][0];
                            this.step = 0;
                        }
                    }
                }

                for (int attack_frame : skinningentitiesattack.attack_frame_int_array)
                {
                    if (this.s.frame_int_array[this.integer_index] == attack_frame)
                    {
                        this.s.entitiesaimemory.skinningentitiesattack.magic_point -= 1;
                        skinningentitiesattack.flag |= 4;
                        break;
                    }
                }
                return true;
            }
            else if (this.s.frame_int_array[this.integer_index] >= frame_2d_int_array[id0][0] && this.s.frame_int_array[this.integer_index] <= frame_2d_int_array[id0][1])
            {
                if (this.s.frame_int_array[this.integer_index] == frame_2d_int_array[id0][1])
                {
                    this.s.frame_int_array[this.integer_index] = frame_2d_int_array[id1][1];
                    this.step = 0;
                    return true;
                }

                this.step = 1;
                return true;
            }
            else if (this.s.frame_int_array[this.integer_index] < frame_2d_int_array[id0][0] || this.s.frame_int_array[this.integer_index] > frame_2d_int_array[id0][1])
            {
                this.s.frame_int_array[this.integer_index] = frame_2d_int_array[id0][0];
                this.step = 0;
                return true;
            }
        }
        else
        {
            this.step = 1;
            return this.checkShoot(id0, id1, id2, false);
        }

        return false;
    }

    public boolean checkShoot(int id0, int id1, int id2, boolean try_reload)
    {
        if (this.s.frame_int_array[this.integer_index] >= frame_2d_int_array[id0][0] && this.s.frame_int_array[this.integer_index] <= frame_2d_int_array[id0][1])
        {
            this.s.entitiesaimemory.skinningentitiesfindmove.endGoal();
            if (this.s.frame_int_array[this.integer_index] == frame_2d_int_array[id0][1])
            {
                this.s.frame_int_array[this.integer_index] = frame_2d_int_array[id2][0];
                this.step = 0;
            }
            return true;
        }
        else if (this.s.frame_int_array[this.integer_index] >= frame_2d_int_array[id1][0] && this.s.frame_int_array[this.integer_index] <= frame_2d_int_array[id1][1])
        {
            this.s.entitiesaimemory.skinningentitiesfindmove.endGoal();
            if (this.s.frame_int_array[this.integer_index] == frame_2d_int_array[id1][1])
            {
                this.s.frame_int_array[this.integer_index] = frame_2d_int_array[id2][0];
                this.step = 0;
            }
            return true;
        }
        else if (!try_reload && this.s.frame_int_array[this.integer_index] >= frame_2d_int_array[id2][0] && this.s.frame_int_array[this.integer_index] < frame_2d_int_array[id2][1])
        {
            this.s.entitiesaimemory.skinningentitiesfindmove.endGoal();
            return true;
        }
        else
        {
            return false;
        }
    }
}
