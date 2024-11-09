package com.nali.small.entity.memo.server.si.frame.shoot;

import com.nali.da.IBothDaNe;
import com.nali.da.IBothDaSn;
import com.nali.list.entity.si.SIEPlayWithRSe;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import net.minecraft.entity.EntityLivingBase;

public class FrameSleShootRSe
<
	S2 extends ServerLe<BD2, E2, I2, A2>,
	BD2 extends IBothDaNe,
	E2 extends EntityLivingBase,
	I2 extends IMixE<BD2, E2>,
	A2 extends MixSIE<BD2, E2, I2, S2>,
	BD extends IBothDaNe & IBothDaSn,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends FrameSleShoot<BD, E, I, S, MS>
{
	public SIEPlayWithRSe<S2, BD2, E2, I2, A2, BD, E, I, S, MS> sieplaywithrse;

	public FrameSleShootRSe(S s, int index)
	{
		super(s, index);
	}

	@Override
	public void init()
	{
		super.init();
		this.sieplaywithrse = (SIEPlayWithRSe<S2, BD2, E2, I2, A2, BD, E, I, S, MS>)this.s.ms.si_map.get(SIEPlayWithRSe.ID);
	}

	@Override
	public boolean onUpdate()
	{
		return this.sieplaywithrse.s2 != null && super.onUpdate();
	}
}
