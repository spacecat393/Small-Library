package com.nali.small.entity.memo.server.si.frame.floop;

import com.nali.da.IBothDaNe;
import com.nali.list.entity.si.SIEPlayWithRSe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import net.minecraft.entity.Entity;

public class FrameSFLoopRSeStand<S2 extends ServerE<SD2, BD2, E2, I2, A2>, SD2, BD2 extends IBothDaNe, E2 extends Entity, I2 extends IMixE<SD2, BD2, E2>, A2 extends MixSIE<SD2, BD2, E2, I2, S2>, SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, MS> & IServerS, MS extends MixSIE<SD, BD, E, I, S>> extends FrameSFLoop<SD, BD, E, I, S, MS>
{
	public SIEPlayWithRSe<S2, SD2, BD2, E2, I2, A2, SD, BD, E, I, S, MS> sieplaywithrse;

	public FrameSFLoopRSeStand(S s, int index)
	{
		super(s, index);
	}

	@Override
	public void init()
	{
		super.init();
		this.sieplaywithrse = (SIEPlayWithRSe<S2, SD2, BD2, E2, I2, A2, SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SIEPlayWithRSe.ID);
	}

	@Override
	public boolean onUpdate()
	{
		return this.sieplaywithrse.s2 != null && super.onUpdate();
	}
}
