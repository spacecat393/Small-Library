package com.nali.small.entity.memo.server.si.frame.floopfree;

import com.nali.da.IBothDaNe;
import com.nali.list.entity.si.SIEPat;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import net.minecraft.entity.Entity;

public class FrameSFLoopFreePat
<
	BD extends IBothDaNe,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends FrameSFLoopFree<BD, E, I, S, MS>
{
	public SIEPat<BD, E, I, S, MS> siepat;

	public FrameSFLoopFreePat(S s, int index)
	{
		super(s, index);
	}

	@Override
	public void init()
	{
		super.init();
		this.siepat = (SIEPat<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEPat.ID);
	}

	@Override
	public boolean step()
	{
		return (this.siepat.state & 1) == 1;
	}

	@Override
	public void free()
	{
		this.siepat.state &= 255-1;
	}
}
