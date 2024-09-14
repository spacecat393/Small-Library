package com.nali.small.entity.memo.server.si.frame.tloop;

import com.nali.da.IBothDaNe;
import com.nali.list.entity.si.SIEPlayWithRSe;
import com.nali.list.entity.si.SILePlayWithSSe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.IMixESoundDa;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.sound.ISoundDaLe;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class FrameSleTLoopSSeRSeStand
<
	MA extends SILePlayWithSSe<R2, S2, BD2, E2, I2, A2, SD, BD, E, I, S, MS>,
	R2 extends SIEPlayWithRSe<S, BD, E, I, MS, BD2, E2, I2, S2, A2>,
	S2 extends ServerE<BD2, E2, I2, A2>,
	BD2 extends IBothDaNe,
	E2 extends Entity,
	I2 extends IMixE<BD2, E2>,
	A2 extends MixSIE<BD2, E2, I2, S2>,
	SD extends ISoundDaLe,
	BD extends IBothDaNe,
	E extends EntityLivingBase,
	I extends IMixE<BD, E> & IMixESoundDa<SD>,
	S extends ServerLe<SD, BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends FrameSTLoop<BD, E, I, S, MS>
{
	public MA ma;

	public FrameSleTLoopSSeRSeStand(S s, int index)
	{
		super(s, index);
	}

	@Override
	public void init()
	{
		super.init();
		this.ma = (MA)this.s.ms.si_map.get(MA.ID);
	}

	@Override
	public boolean onUpdate()
	{
		return this.ma.s2 != null && ((R2)this.ma.s2.ms.si_map.get(R2.ID)).s2 == this.s && super.onUpdate();
	}
}
