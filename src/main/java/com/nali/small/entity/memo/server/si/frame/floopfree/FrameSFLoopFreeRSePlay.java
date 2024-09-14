package com.nali.small.entity.memo.server.si.frame.floopfree;

import com.nali.da.IBothDaNe;
import com.nali.list.entity.si.SIEPlayWithRSe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import net.minecraft.entity.Entity;

public class FrameSFLoopFreeRSePlay
<
	S2 extends ServerE<BD2, E2, I2, A2>,
	BD2 extends IBothDaNe,
	E2 extends Entity,
	I2 extends IMixE<BD2, E2>,
	A2 extends MixSIE<BD2, E2, I2, S2>,
	BD extends IBothDaNe,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends FrameSFLoopFree<BD, E, I, S, MS>
{
	public SIEPlayWithRSe<S2, BD2, E2, I2, A2, BD, E, I, S, MS> sieplaywithrse;
	public S2 s2;

	public FrameSFLoopFreeRSePlay(S s, int index)
	{
		super(s, index);
	}

	@Override
	public void init()
	{
		super.init();
		this.sieplaywithrse = (SIEPlayWithRSe<S2, BD2, E2, I2, A2, BD, E, I, S, MS>)this.s2.ms.si_map.get(SIEPlayWithRSe.ID);
	}

	@Override
	public boolean step()
	{
		return (this.sieplaywithrse.state & 2) == 2;
	}

	@Override
	public void free()
	{
		this.sieplaywithrse.state &= 255-2;
	}
}
