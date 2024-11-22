package com.nali.small.entity.memo.server.si.frame.floopfree;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaS;
import com.nali.list.entity.si.SILeEat;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.IMixES;
import com.nali.small.entity.IMixESInv;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;

public class FrameSleFLoopFreeEat
<
	BD extends IBothDaE & IBothDaS,
	E extends EntityLe,
	I extends IMixE<BD, E> & IMixES & IMixESInv,
	S extends ServerLe<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends FrameSFLoopFree<BD, E, I, S, MS>
{
	public SILeEat<BD, E, I, S, MS> sileeat;

	public FrameSleFLoopFreeEat(S s, int index)
	{
		super(s, index);

		this.sileeat = (SILeEat<BD, E, I, S, MS>)this.s.ms.si_map.get(SILeEat.ID);
	}
//
//	@Override
//	public void init()
//	{
//		super.init();
//		this.sileeat = (SILeEat<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILeEat.ID);
//	}

	@Override
	public boolean step()
	{
		return (this.sileeat.state & 1) == 1;
	}

	@Override
	public void free()
	{
		this.sileeat.state &= 255-1;
	}
}
