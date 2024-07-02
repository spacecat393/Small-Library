package com.nali.small.entity.memo;

import com.nali.data.IBothDaNe;
import com.nali.small.entity.IMixLe;
import com.nali.small.entity.memo.work.WorkEBodyYaw;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public interface IBothLe<SD, BD extends IBothDaNe, E extends EntityLivingBase, I extends IMixLe<SD, BD, E>> extends IBothE<SD, BD, E, I>
{
    @Override
    default boolean isMove()
    {
        return IBothE.super.isMove() && this.getI().getE().getHealth() > 0.0F;
    }

    default boolean isZeroMove()
    {
        return this.getI().getE().getHealth() <= 0.0F;
    }

    //both
    WorkEBodyYaw getWorkEBodyYaw();

    //server
    boolean attackEntityAsMob(Entity entity);
    boolean attackEntityFrom(DamageSource damagesource, float amount);

    //client
    void getHurtSound(DamageSource damagesource);
    void getDeathSound();
}
