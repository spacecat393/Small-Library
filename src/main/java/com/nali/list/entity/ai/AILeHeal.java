package com.nali.list.entity.ai;

import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.AI;
import com.nali.small.entity.memo.server.ai.MixAIE;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;

import static com.nali.small.entity.EntityMath.getDistanceAABBToAABB;
import static com.nali.small.entity.EntityMath.isInArea;

public class AILeHeal<E extends EntityLivingBase, I extends IMixLe<E>, S extends ServerLe<E, I, A>, A extends MixAIE<E, I, S>> extends AI<E, I, S, A>
{
    public static byte ID;

    public AIEArea<E, I, S, A> aiearea;
    public AILeSetLocation<E, I, S, A> ailesetlocation;
    public AILeFindMove<E, I, S, A> ailefindmove;

    public int[] heal_frame_int_array;

    public int cooldown;
    public boolean heal = false;
    public float how_heal = 16.0F;
    public double minimum_distance = 3.0D;
    public byte state;//on1 ani2-4
//    2-4 0
//    2-4 2
//    2-4 4
//    2-4 2-4
//    public byte state = -1;

    public AILeHeal(S s)
    {
        super(s);
    }

    @Override
    public void init()
    {
        this.aiearea = (AIEArea<E, I, S, A>)this.s.a.aie_map.get(AIEArea.ID);
        this.ailesetlocation = (AILeSetLocation<E, I, S, A>)this.s.a.aie_map.get(AILeSetLocation.ID);
        this.ailefindmove = (AILeFindMove<E, I, S, A>)this.s.a.aie_map.get(AILeFindMove.ID);
    }

    @Override
    public void onUpdate()
    {
        if (this.s.isMove())
        {
//            if (this.s.isWork(this.s.bytele.HEAL()) && !this.aiearea.out_entity_list.isEmpty() && ++this.cooldown >= 200)
            if ((this.s.a.state & 1) == 1 && (this.state & 1) == 1)
            {
                E e = this.s.i.getE();
                this.heal = true;
    //            double[] far = new double[this.aiearea.out_entity_list.size()];
    //            boolean should_move = false;
    //            boolean should_move2 = true;

                int index = -1;
                double max_dis = Double.MAX_VALUE;
                for (int i = 0; i < this.aiearea.out_entity_list.size(); ++i)
                {
                    Entity entity = this.aiearea.out_entity_list.get(i);
                    if (e.equals(entity))
                    {
                        continue;
                    }

    //                if (!(entity instanceof EntityLivingBase) || ((EntityLivingBase)entity).getMaxHealth() - ((EntityLivingBase)entity).getHealth() < 1.0F)
                    if (entity instanceof EntityLivingBase && ((EntityLivingBase)entity).getMaxHealth() - ((EntityLivingBase)entity).getHealth() >= 1.0F)
                    {
    //                    far[index] = Double.MAX_VALUE;
    //                }
    //                else
    //                {
    //                    if (e.equals(entity))
    //                    {
    //                        should_move2 = false;
    //                    }

                        double far = e.getDistanceSq(entity);
                        if (far < max_dis)
                        {
                            index = i;
                            max_dis = far;
                        }
    //                    far[index] = e.getDistanceSq(entity);

    //                    if (isTooClose(e, entity, this.minimum_distance))
    //                    {
    //                        if (this.state == -1)
    //                        {
    //                            this.state = 0;
    //                        }
    //
    //                        if (this.state == 1)
    //                        {
    //                            ((EntityLivingBase)entity).heal(this.how_heal);
    //                        }
    //
    //                        this.ailefindmove.endGoal();
    //                    }
    //                    else
    //                    {
    //                        should_move = true;
    //                    }
                    }
    //                ++index;
                }

    //            if (should_move && should_move2)
    //            {
    //                index = 0;

    //                double max_dis = Double.MAX_VALUE;

    //                for (int i = 0; i < far.length; ++i)
    //                {
    //                    if (far[i] < max_dis)
    //                    {
    //                        index = i;
    //                        max_dis = far[i];
    //                    }
    //                }

                if (index != -1)
                {
                    Entity entity = this.aiearea.out_entity_list.get(index);
    //                if (isTooClose(e, entity, this.minimum_distance))
                    if (getDistanceAABBToAABB(e, entity) <= this.minimum_distance)
                    {
//                        if (this.state == -1)
                        if ((this.state & (2+4)) == 0)
                        {
//                            this.state = 0;
                            this.state |= 2;
                            this.state &= 255-4;
                        }

//                        if (this.state == 1)
                        if ((this.state & (2+4)) == 4)
                        {
                            ((EntityLivingBase)entity).heal(this.how_heal);
                        }

                        this.ailefindmove.endGoal();
                    }
                    else
                    {
                        if (this.ailesetlocation.far == 0 || this.ailesetlocation.blockpos == null || isInArea(entity, this.ailesetlocation.blockpos, this.ailesetlocation.far))
                        {
                            this.ailefindmove.setGoal(entity.posX, entity.posY, entity.posZ);
                        }
                    }
                }
//                else
//                {
//                    this.s.current_work_byte_array[this.s.bytele.HEAL() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.HEAL() % 8));//0
//                }

    //            /*if (this.state == 0 || this.state == 1)
    //            {
    ////                this.s.entitiesaimemory.skinningentitieslook.set(entity.posX, entity.posY, entity.posZ, 20.0F);
    //            }
    //            else */if (!this.ailefindmove.try_move)
    //            {
    //                this.state = 0;
    ////                this.s.entitiesaimemory.skinningentitieslook.set(entity.posX, entity.posY, entity.posZ, 20.0F);
    //            }

    //                if (!isTooClose(e, entity, this.minimum_distance))
    //                {
    //                this.ailefindmove.setGoal(entity.posX, entity.posY, entity.posZ);
    //                this.state = -1;
    //                }
    //            }

//                if (this.state == 1)
                if ((this.state & (2+4)) == 4)
                {
                    this.cooldown = 0;
//                    this.state = -1;
                    this.state &= 255-(2+4);
                }
    //            if (!should_move && should_move2)
    //            {
    //                this.s.current_work_byte_array[this.s.bytele.HEAL() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.HEAL() % 8));//0
    //            }
            }
            else
            {
                if (this.heal)
                {
                    this.cooldown = 0;
//                    this.state = -1;
                    this.state &= 255-(2+4);
                    this.ailefindmove.endGoal();
                    this.heal = false;
                }

//                this.s.current_work_byte_array[this.s.bytele.HEAL() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.HEAL() % 8));//0
            }
        }
    }

    @Override
    public void writeNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setByte("AILeHeal_state", this.state);
    }

    @Override
    public void readNBT(NBTTagCompound nbttagcompound)
    {
        this.state = nbttagcompound.getByte("AILeHeal_state");
    }
}
