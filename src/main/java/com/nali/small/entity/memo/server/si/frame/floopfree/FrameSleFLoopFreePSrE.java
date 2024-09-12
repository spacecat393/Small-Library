package com.nali.small.entity.memo.server.si.frame.floopfree;

import com.nali.da.IBothDaNe;
import com.nali.da.IBothDaSn;
import com.nali.list.entity.si.SIEPat;
import com.nali.list.entity.si.SIESit;
import com.nali.list.entity.si.SILeEat;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.sound.ISoundDaLe;

public class FrameSleFLoopFreePSrE<SD extends ISoundDaLe, BD extends IBothDaNe & IBothDaSn, E extends EntityLe, I extends IMixE<SD, BD, E>, S extends ServerLe<SD, BD, E, I, MS> & IServerS, MS extends MixSIE<SD, BD, E, I, S>> extends FrameSFLoopFree<SD, BD, E, I, S, MS>
{
	public SIEPat<SD, BD, E, I, S, MS> siepat;
	public SIESit<SD, BD, E, I, S, MS> siesit;
	public SILeEat<SD, BD, E, I, S, MS> sileeat;

	public FrameSleFLoopFreePSrE(S s, int index)
	{
		super(s, index);
	}

	@Override
	public void init()
	{
		super.init();
		this.siepat = (SIEPat<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SIEPat.ID);
		this.siesit = (SIESit<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SIESit.ID);
		this.sileeat = (SILeEat<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILeEat.ID);
	}

	@Override
	public boolean step()
	{
		return (this.siepat.state & 1) == 1 || (this.siesit.state & 4) == 4 || (this.sileeat.state & 1) == 1;
	}

	@Override
	public void free()
	{
		this.siepat.state &= 255-1;
		this.siesit.state &= 255-4;
		this.sileeat.state &= 255-1;
	}
}
