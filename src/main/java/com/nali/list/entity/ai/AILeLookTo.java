package com.nali.list.entity.ai;

import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.ai.AI;
import com.nali.small.entity.memo.server.ai.MixAIE;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

public class AILeLookTo<E extends EntityLivingBase, I extends IMixLe<E>, S extends ServerLe<E, I, A>, A extends MixAIE<E, I, S>> extends AI<E, I, S, A>
{
    public static byte ID;

    public AIEArea<E, I, S, A> aiearea;
    public AILeLook<E, I, S, A> ailelook;

    public byte state;//on
    public Entity entity;
    public BlockPos blockpos;
    public int tick;
//    public byte[] bypass_int_array = {this.skinningentities.bothentitiesmemory.workbytes.SIT(), this.skinningentities.bothentitiesmemory.workbytes.PROTECT()};

    public AILeLookTo(S s)
    {
        super(s);
    }

    @Override
    public void init()
    {
        this.aiearea = (AIEArea<E, I, S, A>)this.s.a.aie_map.get(AIEArea.ID);
        this.ailelook = (AILeLook<E, I, S, A>)this.s.a.aie_map.get(AILeLook.ID);
    }

    @Override
    public void onUpdate()
    {
//        if (serverentitiesmemory.isWorkBypass(serverentitiesmemory.workbytes.LOOK_TO(), this.bypass_int_array))
        if (this.s.isMove())
        {
            if ((this.s.a.state & 2) == 2 && (this.state & 1) == 1)
            {
                E e = this.s.i.getE();
                double max_far = Double.MAX_VALUE;
    //            Entity current_entity = null;
                if (this.entity == null)
                {
                    for (Entity entity : this.aiearea.entity_collection)
                    {
                        if (entity.equals(e))
                        {
                            continue;
                        }

                        double far = e.getDistanceSq(entity);
                        if (far < max_far)
                        {
                            this.entity = entity;
                            max_far = far;
                        }
                    }
                }

                if (this.entity != null)
                {
                    if (this.tick <= 0)
                    {
                        this.tick = this.entity.world.rand.nextInt(700) + 200;
                    }
                }

    //            Entity owner_entity = serverentitiesmemory.getOwner();
    //            if (owner_entity != null)
    //            {
    //                if (this.skinningentities.getDistanceSq(owner_entity) < this.far)
    //                {
    //                    this.entity = owner_entity;
    //                    this.setTime(current_entity);
    //                }
    //            }

                if (--this.tick > 300)
                {
                    if (this.blockpos != null)
                    {
                        this.ailelook.set(this.blockpos.getX(), this.blockpos.getY(), this.blockpos.getZ(), 5.0F);
                    }
                    else if (this.entity != null)
                    {
                        this.ailelook.set(this.entity.posX, this.entity.posY + this.entity.getEyeHeight(), this.entity.posZ, 5.0F);
                    }
                }
                else
                {
                    if (this.tick <= 0)
                    {
                        this.entity = null;
                        this.blockpos = null;
                    }
    //                serverentitiesmemory.current_work_byte_array[serverentitiesmemory.workbytes.LOOK_TO() / 8] &= (byte)(255 - Math.pow(2, serverentitiesmemory.workbytes.LOOK_TO() % 8));//0
                }
            }
        }
    }

    @Override
    public void writeNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setByte("AILeLookTo_state", this.state);
    }

    @Override
    public void readNBT(NBTTagCompound nbttagcompound)
    {
        this.state = nbttagcompound.getByte("AILeLookTo_state");
    }
}
