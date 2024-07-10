package com.nali.list.entity.ai;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.MixAIE;
import com.nali.sound.ISoundDaLe;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

import static com.nali.small.entity.EntityMath.getDistanceAABBToAABB;
import static com.nali.small.entity.EntityMath.isInArea;

public abstract class AILePlayWithSSe<R2 extends AIEPlayWithRSe<S, SD, BD, E, I, A, SD2, BD2, E2, I2, S2, A2>, S2 extends ServerE<SD2, BD2, E2, I2, A2>, SD2, BD2 extends IBothDaNe, E2 extends Entity, I2 extends IMixE<SD2, BD2, E2>, A2 extends MixAIE<SD2, BD2, E2, I2, S2>, SD extends ISoundDaLe, BD extends IBothDaNe, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>, S extends ServerLe<SD, BD, E, I, A>, A extends MixAIE<SD, BD, E, I, S>> extends AIEPlayWithRSe<S2, SD2, BD2, E2, I2, A2, SD, BD, E, I, S, A>
{
    public static byte ID;

    public AIEArea<SD, BD, E, I, S, A> aiearea;
    public AILeFindMove<SD, BD, E, I, S, A> ailefindmove;
    public AILeSetLocation<SD, BD, E, I, S, A> ailesetlocation;

//    public AIESit<SD2, BD2, E2, I2, S2, A2> aiesit2;
    public R2 r2;

    public Class e2_class;
    public int tick;

    public AILePlayWithSSe(S s)
    {
        super(s);
    }

    @Override
    public void init()
    {
        this.aiearea = (AIEArea<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AIEArea.ID);
        this.ailefindmove = (AILeFindMove<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeFindMove.ID);
        this.ailesetlocation = (AILeSetLocation<SD, BD, E, I, S, A>)this.s.a.aie_map.get(AILeSetLocation.ID);
    }

    @Override
    public void onUpdate()
    {
        if (this.s.isMove())
        {
            if ((this.s.a.state & 1) == 1 && (this.state & 1) == 1)
            {
                E2 e2 = this.s2.i.getE();
                if (this.s2 != null && this.s.worldserver.getEntityFromUuid(e2.getUniqueID()) == null)
                {
                    this.onFree();
                }
                else if (--this.tick <= 0)
                {
                    E e = this.s.i.getE();
                    if (this.s2 != null)
                    {
                        this.onFree();
                    }
                    else if (e.ticksExisted % 200 == 0)
                    {
//                        int index = -1;
                        double max = Double.MAX_VALUE;
                        for (int i = 0; i < this.aiearea.out_entity_list.size(); ++i)
                        {
                            Entity entity = this.aiearea.out_entity_list.get(i);
//                            for (Class clasz : this.class_array)
//                            {
//                            if (entity.getClass() == clasz)
                            if (entity.getClass() == this.e2_class)
                            {
                                double new_max = getDistanceAABBToAABB(e, entity);
                                if (new_max < max && this.shouldAdd(i))
                                {
//                                        index = i;
                                    max = new_max;
                                }
                                else
                                {
                                    this.s2 = null;
                                    this.r2 = null;
                                }
                            }
//                            }
                        }

//                        super.onUpdate();
//                        if (this.o2 != null)
//                        {
//                            this.aiesit2 = ((AIESit)this.o2.a.aie_map.get(AIESit.ID));
//                        }
//                        if (index != -1)
//                        {
////                            this.onFind(index);
//                        }

                        this.tick = 1200 + e.world.rand.nextInt(5000);
                    }
                }
                else
                {
                    if (this.s2 != null && (this.ailesetlocation.far == 0 || this.ailesetlocation.blockpos == null || isInArea(e2, this.ailesetlocation.blockpos, this.ailesetlocation.far)))
                    {
                        if ((this.state & 2) == 0)
                        {
                            this.onAdd();
                        }
                        else
                        {
                            this.onPlay();
                        }
                    }
                }
            }
        }
        else if (this.s2 != null)
        {
            this.onFree();
        }
    }

    public void onPlay()
    {
        E e = this.s.i.getE();
        E2 e2 = this.s2.i.getE();
        e.setPositionAndUpdate(e2.posX, e2.posY, e2.posZ);
        e.rotationYaw = e2.rotationYaw;
        e.rotationPitch = e2.rotationPitch;
        e.fallDistance = 0;
//        e.renderYawOffset = e2.rotationYaw;
    }

    public boolean shouldAdd(int index)
    {
        Entity entity = this.aiearea.out_entity_list.get(index);
        this.s2 = (S2)ServerE.S_MAP.get(entity.getUniqueID());
        this.r2 = ((R2)this.s2.a.aie_map.get(AIEPlayWithRSe.ID));
        return this.r2.s2 == null && (this.r2.state & 1) == 1 && (this.ailesetlocation.far == 0 || this.ailesetlocation.blockpos == null || isInArea(entity, this.ailesetlocation.blockpos, this.ailesetlocation.far));
    }

//    public void onFind(int index)
//    {
//        if (s2 != null)
//        {
//            if ( &&
////                    e.getDataManager().get(this.s.i.getFloatDataParameterArray()[0]) <= playwith_skinningentities.getDataManager().get(playwith_skinningentities.getFloatDataParameterArray()[0]) &&
//                    )
//            {
//                break;
//            }
//            else
//            {
//                playwith_skinningentities = null;
//            }
//        }
//    }

    public void onAdd()
    {
        E e = this.s.i.getE();
        E2 e2 = this.s2.i.getE();
        if (e.getEntityBoundingBox().intersects(e2.getEntityBoundingBox()))
        {
            if ((this.r2.state & 1) == 1 && this.r2.s2 == null)
            {
                this.r2.s2 = this.s;
                this.state |= 2;
//                this.aieplaywith2.state |= 2+4;
            }
            else
            {
                this.onFree();
//                this.o2 = null;
//                this.tick = 0;
            }
        }
        else
        {
            if (this.ailesetlocation.far == 0 || this.ailesetlocation.blockpos == null || isInArea(e2, this.ailesetlocation.blockpos, this.ailesetlocation.far))
            {
                this.ailefindmove.setGoal(e2.posX, e2.posY, e2.posZ);
            }
        }
    }

//    @Override
    public void onFree()
    {
//        super.onFree();
//        this.aiesit2 = null;
        this.r2.s2 = null;
//        this.aieplaywith2.state &= 255-(2+4);
        this.r2.state &= 255-2;
        this.r2 = null;

        this.s2 = null;
//        this.state &= 255-(2+4);
        this.state &= 255-2;

        this.tick = 1200 + this.s.i.getE().world.rand.nextInt(1200);
    }

    @Override
    public void call()
    {
    }
}
