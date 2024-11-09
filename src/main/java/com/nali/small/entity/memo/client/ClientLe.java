package com.nali.small.entity.memo.client;

import com.nali.da.IBothDaNe;
import com.nali.da.client.IClientDaO;
import com.nali.render.RenderO;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.IBothLe;
import com.nali.small.entity.memo.client.box.mix.MixBoxE;
import com.nali.small.entity.memo.client.ci.MixCIE;
import com.nali.small.entity.memo.client.render.mix.MixRenderE;
import com.nali.small.entity.memo.work.WorkEBodyYaw;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class ClientLe
<
	RC extends IClientDaO,
	R extends RenderO<RC>,
//	SD extends ISoundDaLe,
	BD extends IBothDaNe,
	E extends EntityLivingBase,
	I extends IMixE<BD, E>/* & IMixESoundDa<SD>*/,
	MC extends MixCIE<RC, R, BD, E, I, MB, MR, ?>,
	MB extends MixBoxE<RC, R, BD, E, I, MC, MR, ?>,
	MR extends MixRenderE<RC, R, BD, E, I, MC, MB, ?>
> extends ClientE<RC, R, BD, E, I, MC, MB, MR> implements IBothLe<E>
{
	public WorkEBodyYaw workebodyyaw;

//	public byte[] work_byte_array;

	public ClientLe(I i, R r)
	{
		super(i, r);
		this.workebodyyaw = new WorkEBodyYaw();
//		this.work_byte_array = new byte[workbytes.MAX_WORKS()];
	}

	public ClientLe(R r)
	{
		super(r);
	}

//	@Override
//	public void getHurtSound(DamageSource damagesource)
//	{
//		this.getSound().play(this.i.getSD().HURT());
//	}
//
//	@Override
//	public void getDeathSound()
//	{
//		this.getSound().play(this.i.getSD().DEATH());
//	}

	@Override
	public boolean attackEntityAsMob(Entity entity)
	{
		return false;
	}

	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float v)
	{
		return false;
	}

	@Override
	public WorkEBodyYaw getWorkEBodyYaw()
	{
		return this.workebodyyaw;
	}
}
