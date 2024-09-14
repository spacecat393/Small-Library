package com.nali.small.entity.memo.server;

import com.nali.da.IBothDaNe;
import com.nali.list.entity.si.SIESound;
import com.nali.list.entity.si.SILeLockDMG;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.IMixESoundDa;
import com.nali.small.entity.memo.IBothLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.work.WorkEBodyYaw;
import com.nali.sound.ISoundDaLe;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public abstract class ServerLe
<
	SD extends ISoundDaLe,
	BD extends IBothDaNe,
	E extends EntityLivingBase,
	I extends IMixE<BD, E> & IMixESoundDa<SD>,
	MS extends MixSIE<BD, E, I, ?>
> extends ServerE<BD, E, I, MS> implements IBothLe<E>
{
	public WorkEBodyYaw workebodyyaw;
	public byte serverle_state;//attackEntityAsMob attackEntityFrom attackEntityAsMob_trigger attackEntityFrom_trigger

	public Entity attack_entity_as_mob_entity;
	public DamageSource attack_entity_from_damagesource;
//	public AIEOwner<SD, BD, E, I, ?, MS> sieowner;
//	public AILeLockDMG<SD, BD, E, I, ?, MS> ailelockdmg;

	public ServerLe(I i)
	{
		super(i);
		this.workebodyyaw = new WorkEBodyYaw();
//		this.sieowner = (AIEOwner<SD, BD, E, I, ?, MS>)this.ms.si_map.get(AIEOwner.ID);
//		this.ailelockdmg = (AILeLockDMG<SD, BD, E, I, ?, MS>)this.ms.si_map.get(AILeLockDMG.ID);
	}

	@Override
	public boolean attackEntityFrom(DamageSource damagesource, float amount)
	{
		this.attack_entity_from_damagesource = damagesource;
		this.serverle_state |= 8;
		this.ms.call(SILeLockDMG.ID);
		return true;
	}

	@Override
	public boolean attackEntityAsMob(Entity entity)//both?
	{
		this.attack_entity_as_mob_entity = entity;
		this.serverle_state |= 4;
		this.ms.call(SILeLockDMG.ID);
		return (this.serverle_state & 1) == 1;
	}

	@Override
	public WorkEBodyYaw getWorkEBodyYaw()
	{
		return this.workebodyyaw;
	}

	@Override
	public void getHurtSound(DamageSource damageSource)
	{
		this.ms.byte_array = new byte[1 + 8 + 1 + 4];
		ByteWriter.set(this.ms.byte_array, this.i.getSD().HURT(), 1 + 8 + 1);
		this.ms.call(SIESound.ID);
	}

	@Override
	public void getDeathSound()
	{
		this.ms.byte_array = new byte[1 + 8 + 1 + 4];
		ByteWriter.set(this.ms.byte_array, this.i.getSD().DEATH(), 1 + 8 + 1);
		this.ms.call(SIESound.ID);
	}
}
