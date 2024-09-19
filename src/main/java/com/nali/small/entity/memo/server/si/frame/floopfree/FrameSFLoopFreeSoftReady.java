package com.nali.small.entity.memo.server.si.frame.floopfree;

import com.nali.da.IBothDaNe;
import com.nali.list.entity.si.SIESit;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import net.minecraft.entity.Entity;

public class FrameSFLoopFreeSoftReady
<
	BD extends IBothDaNe,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends FrameSFLoopFree<BD, E, I, S, MS>
{
	public SIESit<BD, E, I, S, MS> siesit;

	public FrameSFLoopFreeSoftReady(S s, int index)
	{
		super(s, index);

		this.siesit = (SIESit<BD, E, I, S, MS>)this.s.ms.si_map.get(SIESit.ID);
	}
//
//	@Override
//	public void init()
//	{
//		super.init();
//		this.siesit = (SIESit<BD, E, I, S, MS>)this.s.ms.si_map.get(SIESit.ID);
//	}

	@Override
	public boolean step()
	{
		return (this.siesit.state & 4) == 4;
	}

	@Override
	public void free()
	{
		this.siesit.state &= 255-4;
	}
}
