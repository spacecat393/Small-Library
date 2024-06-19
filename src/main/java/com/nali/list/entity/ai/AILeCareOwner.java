package com.nali.list.entity.ai;

import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.AI;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.small.mixin.IMixinEntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.List;

public class AILeCareOwner<E extends EntityLivingBase, I extends IMixLe<E>, S extends ServerLe<E, I, A>, A extends MixAIE<E, I, S>> extends AI<E, I, S, A>
{
    public static byte ID;

    public AIEArea<E, I, S, A> aiearea;

    public byte state;

    public List<Entity> target_entity_list = new ArrayList();
    public List<Double> far_double_list = new ArrayList();

    public AILeCareOwner(S s)
    {
        super(s);
    }

    @Override
    public void init()
    {
        this.aiearea = (AIEArea<E, I, S, A>)this.s.a.aie_map.get(AIEArea.ID);
    }

    @Override
    public void onUpdate()
    {
        if (this.s.isMove())
        {
            E e = this.s.i.getE();
            Entity owner_entity = this.s.getOwner();
//            if (this.s.isWork(this.s.bytele.CARE_OWNER()) && owner_entity != null && !this.aiearea.all_entity_list.isEmpty())
            if ((this.s.a.state & 1) == 1 && (this.state & 1) == 1 && owner_entity != null && !this.aiearea.all_entity_list.isEmpty())
            {
                List<Entity> target_entity_list = new ArrayList(this.target_entity_list);
                this.target_entity_list.clear();
                this.far_double_list.clear();
                for (Entity entity : this.aiearea.all_entity_list)
                {
                    boolean should_attack = false;
                    for (Entity old_entity : target_entity_list)
                    {
                        if (old_entity.equals(entity))
                        {
                            should_attack = true;
                            break;
                        }
                    }

                    if (should_attack || ourTarget(entity, owner_entity) || ourTarget(entity, e))
                    {
                        this.target_entity_list.add(entity);
                        this.far_double_list.add(e.getDistanceSq(entity));
                    }
                }
    //            if (!this.far_double_list.isEmpty())
    //            {
    //                int index = 0;
    //                double max_dis = Double.MAX_VALUE;
    //                for (int i = 0; i < this.far_double_list.size(); ++i)
    //                {
    //                    double far = this.far_double_list.get(i);
    //                    if (far < max_dis)
    //                    {
    //                        index = i;
    //                        max_dis = far;
    //                    }
    //                }
    //
    //                this.target_entity = this.aiearea.all_entity_list.get(index);
    //            }
    //            else
    //            {
    //                this.target_entity = null;
    //            }
            }
            else
            {
                this.target_entity_list.clear();
                this.far_double_list.clear();
            }

//            this.s.current_work_byte_array[this.s.bytele.CARE_OWNER() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.CARE_OWNER() % 8));//0
        }
    }

    @Override
    public void writeNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setByte("AILeCareOwner_state", this.state);
    }

    @Override
    public void readNBT(NBTTagCompound nbttagcompound)
    {
        this.state = nbttagcompound.getByte("AILeCareOwner_state");
    }

    public static boolean ourTarget(Entity entity, Entity us_entity)
    {
        return (entity instanceof EntityLivingBase &&
        (
            us_entity.equals(((EntityLivingBase)entity).getCombatTracker().getBestAttacker()) ||
            us_entity.equals(((IMixinEntityLivingBase)entity).attackingPlayer()) ||
            us_entity.equals(((EntityLivingBase)entity).getRevengeTarget()) ||
            us_entity.equals(((EntityLivingBase)entity).getLastAttackedEntity()) ||
            ((EntityLivingBase)entity).getLastDamageSource() != null && us_entity.equals(((EntityLivingBase)entity).getLastDamageSource().getTrueSource())
        ) || (entity instanceof EntityLiving && us_entity.equals(((EntityLiving)entity).getAttackTarget())))
        && /*!us_entity.equals(entity) && */!(entity instanceof EntityPlayer);// && fastCheck(entity);
    }
}
