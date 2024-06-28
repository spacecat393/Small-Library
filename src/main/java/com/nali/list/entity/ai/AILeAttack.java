package com.nali.list.entity.ai;

import com.nali.data.IBothDaE;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.AI;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundLe;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;

import java.util.List;

import static com.nali.small.entity.EntityMath.getDistanceAABBToAABB;
import static com.nali.small.entity.EntityMath.isInArea;

public class AILeAttack<SD extends ISoundLe, BD extends IBothDaE, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, S extends ServerLe<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends AI<SD, BD, E, I, S, A>
{
    public static byte ID;

    public AIEArea<SD, BD, E, I, S, A> aiearea;
    public AILeSetLocation<SD, BD, E, I, S, A> ailesetlocation;
    public AILeFindMove<SD, BD, E, I, S, A> ailefindmove;
    public AILeLook<SD, BD, E, I, S, A> ailelook;
    public AILeCareOwner<SD, BD, E, I, S, A> ailecareowner;

    public int[] attack_frame_int_array;

    public byte flag = 16;//move_to prepare hit | remote walk_to
    public float minimum_distance = 3.0F;
    public int magic_point,
    max_magic_point = 16;

    public AILeAttack(S s)
    {
        super(s);
    }

    @Override
    public void init()
    {
        this.aiearea = (AIEArea<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AIEArea.ID);
        this.ailesetlocation = (AILeSetLocation<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeSetLocation.ID);
        this.ailefindmove = (AILeFindMove<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeFindMove.ID);
        this.ailelook = (AILeLook<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeLook.ID);
        this.ailecareowner = (AILeCareOwner<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeCareOwner.ID);
    }

    @Override
    public void call()
    {

    }

    @Override
    public void onUpdate()
    {
        E e = this.s.i.getE();
//        boolean should_work = false;
//        if ((this.s.main_work_byte_array[this.s.bytele.CARE_OWNER() / 8] >> this.s.bytele.CARE_OWNER() % 8 & 1) == 1)
//        {
//            this.s.current_work_byte_array[this.s.bytele.CARE_OWNER() / 8] |= (byte)Math.pow(2, this.s.bytele.CARE_OWNER() % 8);
//            should_work = this.s.isWork(this.s.bytele.CARE_OWNER());
//            this.s.current_work_byte_array[this.s.bytele.CARE_OWNER() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.CARE_OWNER() % 8));
//        }

//        boolean work = this.s.isWork(this.s.bytele.ATTACK());
//        if ((!work && should_work && !this.ailecareowner.target_entity_list.isEmpty()) || (work && !this.aiearea.all_entity_list.isEmpty()))
        if ((this.s.a.state & 1) == 1)
        {
            if (((this.ailecareowner.state & 1) == 1 && !this.ailecareowner.target_entity_list.isEmpty()) || ((this.flag & (8+16)) != 0 && !this.aiearea.all_entity_list.isEmpty()))
            {
    //            if ((this.s.main_work_byte_array[this.s.bytele.MINE() / 8] >> this.s.bytele.MINE() % 8 & 1) == 1 && this.s.entitiesaimemory.skinningentitiesmine.blockpos != null)
    //            {
    //                this.s.entitiesaimemory.skinningentitiesmine.breakWork();
    //            }

                Entity target_entity;
                if (!this.ailecareowner.target_entity_list.isEmpty())
                {
                    target_entity = this.attackAndFind(this.ailecareowner.target_entity_list);
                }
                else
                {
                    target_entity = this.attackAndFind(this.aiearea.all_entity_list);
                }

                if (this.ailesetlocation.far == 0 || this.ailesetlocation.blockpos == null || isInArea(target_entity, this.ailesetlocation.blockpos, this.ailesetlocation.far))
                {
                    this.flag |= 2;

                    if (!this.ailefindmove.try_move)
                    {
                        this.ailelook.set(target_entity.posX, target_entity.posY, target_entity.posZ, 20.0F);
                    }

                    if ((this.flag & 16+8) == 16 && !(e.canEntityBeSeen(target_entity) && getDistanceAABBToAABB(e, target_entity) <= this.minimum_distance/*isTooClose(e, target_entity, this.minimum_distance)*/))
                    {
    //                    this.ailefindmove.setBreakGoal(target_entity.posX, target_entity.posY, target_entity.posZ);
                        this.ailefindmove.setGoal(target_entity.posX, target_entity.posY, target_entity.posZ);
                        this.flag |= 1;
                        this.flag &= 255-2;
                    }
                }

                this.flag &= 255-4;
            }
            else
            {
                if ((this.flag & 1) == 1)
                {
                    this.ailefindmove.endGoal();
    //                this.flag &= 255-(1+2+4);
                }
                this.flag &= 255-(1+2+4);

    //            this.s.current_work_byte_array[this.s.bytele.ATTACK() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.ATTACK() % 8));
            }
        }
    }

    @Override
    public void writeNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setByte("AILeAttack_flag", this.flag);
        nbttagcompound.setFloat("AILeAttack_minimum_distance", this.minimum_distance);
        nbttagcompound.setInteger("AILeAttack_magic_point", this.magic_point);
        nbttagcompound.setInteger("AILeAttack_max_magic_point", this.max_magic_point);
    }

    @Override
    public void readNBT(NBTTagCompound nbttagcompound)
    {
        this.flag = nbttagcompound.getByte("AILeAttack_flag");
        this.minimum_distance = nbttagcompound.getFloat("AILeAttack_minimum_distance");
        this.magic_point = nbttagcompound.getInteger("AILeAttack_magic_point");
        this.max_magic_point = nbttagcompound.getInteger("AILeAttack_max_magic_point");
    }

    public Entity attackAndFind(List<Entity> entity_list)
    {
        E e = this.s.i.getE();
//        double[] far = new double[entity_list.size()];
//        int index = 0;
        int index = -1;
        double max_dis = Double.MAX_VALUE;
        for (int i = 0; i < entity_list.size(); ++i)
        {
            Entity entity = entity_list.get(i);
            double far = e.getDistanceSq(entity);
            if (far < max_dis)
            {
                index = i;
                max_dis = far;
            }
//            far[index++] = e.getDistanceSq(entity);
            if (this.ailesetlocation.far == 0 || this.ailesetlocation.blockpos == null ||
                isInArea(entity, this.ailesetlocation.blockpos, this.ailesetlocation.far))
            {
//                if ((this.flag & 8) == 8 || (e.canEntityBeSeen(entity) && isTooClose(e, entity, this.minimum_distance)))
                if ((this.flag & 8) == 8 || (e.canEntityBeSeen(entity) && getDistanceAABBToAABB(e, entity) <= this.minimum_distance))
                {
                    if ((this.flag & 4) == 4)
                    {
                        e.swingArm(EnumHand.MAIN_HAND);
                        e.attackEntityAsMob(entity);
                    }

                    this.ailefindmove.endGoal();
                }
            }
        }

//        index = 0;

//        double max_dis = Double.MAX_VALUE;

//        for (int i = 0; i < far.length; ++i)
//        {
//            if (far[i] < max_dis)
//            {
//                index = i;
//                max_dis = far[i];
//            }
//        }

        if (index == -1)
        {
            return null;
        }
        else
        {
            return entity_list.get(index);
        }
    }
}
