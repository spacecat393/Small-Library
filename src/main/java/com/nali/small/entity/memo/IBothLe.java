package com.nali.small.entity.memo;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.work.WorkEBodyYaw;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public interface IBothLe<SD, BD extends IBothDaNe, E extends EntityLivingBase, I extends IMixE<SD, BD, E>> extends IBothE<SD, BD, E, I>
{
	@Override
	default boolean isMove(E e)
	{
		return IBothE.super.isMove(e) && e.getHealth() > 0.0F;
	}

	default boolean isZeroMove(E e)
	{
		return e.getHealth() <= 0.0F;
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
