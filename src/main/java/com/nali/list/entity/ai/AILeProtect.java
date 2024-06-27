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

import java.util.ArrayList;
import java.util.List;

import static com.nali.small.entity.EntityMath.getDistanceAABBToAABB;

public class AILeProtect<SD extends ISoundLe, BD extends IBothDaE, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, S extends ServerLe<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends AI<SD, BD, E, I, S, A>
{
    public static byte ID;

    public AIEArea<SD, BD, E, I, S, A> aiearea;
    public AILeFindMove<SD, BD, E, I, S, A> ailefindmove;

    public byte state;//on1 protect2 ani4-8
//    4+8 0
//    4+8 4
//    4+8 8
//    4+8 4+8
    public List<Entity> entity_list = new ArrayList();
    public List<Integer> cooldown_int_list = new ArrayList();
    public double minimum_distance = 1.5D;
//    public byte state = -1, main_state = -1;

    public AILeProtect(S s)
    {
        super(s);
    }

    @Override
    public void init()
    {
        this.aiearea = (AIEArea<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AIEArea.ID);
        this.ailefindmove = (AILeFindMove<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeFindMove.ID);
    }

    @Override
    public void onUpdate()
    {
        if (this.s.isMove())
        {
//            if (this.s.isWork(this.s.bytele.PROTECT()) && !this.aiearea.out_entity_list.isEmpty())
            if ((this.s.a.state & 1) == 1 && (this.state & 1) == 1)
            {
//                this.protect = true;
                this.state |= 2;
                this.ailefindmove.endGoal();
//                if (this.main_state != -1)
//                {
//                    this.state = this.main_state;
//                    this.main_state = -1;
//                }

                for (int i = 0; i < this.cooldown_int_list.size(); ++i)
                {
                    this.cooldown_int_list.set(i, this.cooldown_int_list.get(i) + 1);
                    if (this.cooldown_int_list.get(i) == 1200)
                    {
                        this.entity_list.remove(i);
                        this.cooldown_int_list.remove(i);
                        --i;
                    }
                }

//                if (this.state < 0)
//                {
//                    this.state = 0;
//                }

                for (Entity entity : this.aiearea.out_entity_list)
                {
    //                if (isTooClose(this.skinningentities, entity, this.minimum_distance))
                    if (getDistanceAABBToAABB(this.s.i.getE(), entity) <= this.minimum_distance)
                    {
                        boolean in_list = false;
                        for (Entity in_entity : this.entity_list)
                        {
                            in_list = in_entity.equals(entity);
                            if (in_list)
                            {
                                break;
                            }
                        }

//                        if (!in_list && this.state == 1 || this.state == 2)
                        if (!in_list && ((this.state & 4) == 4 || (this.state & 8) == 8))
                        {
                            this.entity_list.add(entity);
                            this.cooldown_int_list.add(0);
//                            this.state = 2;
                            this.state |= 8;
                            this.state &= 255-4;

                            if (entity instanceof EntityLivingBase)
                            {
    //                            ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 1200, 10));
                                entity.getEntityData().setByte("Nali_protect", (byte)3);
                            }
                        }
                    }
                }
            }
            else
            {
//                if (this.protect)
                if ((this.state & 2) == 2)
                {
//                    this.state = 3;
                    this.state |= 4+8;
//                    this.protect = false;
                    this.state &= 255-2;
                }

//                if (this.state == 3)
//                {
//                    this.ailefindmove.endGoal();
//                }
//                else
//                {
//                    this.s.current_work_byte_array[this.s.bytele.PROTECT() / 8] &= (byte)(255 - Math.pow(2, this.s.bytele.PROTECT() % 8));//0
//                }
            }
        }
    }

    @Override
    public void writeNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setByte("AILeProtect_state", this.state);
    }

    @Override
    public void readNBT(NBTTagCompound nbttagcompound)
    {
        this.state = nbttagcompound.getByte("AILeProtect_state");
    }
}
