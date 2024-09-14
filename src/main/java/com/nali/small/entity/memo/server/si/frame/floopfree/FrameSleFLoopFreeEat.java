package com.nali.small.entity.memo.server.si.frame.floopfree;

import com.nali.da.IBothDaNe;
import com.nali.da.IBothDaSn;
import com.nali.list.entity.si.SILeEat;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.IMixESoundDa;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.sound.ISoundDaLe;

public class FrameSleFLoopFreeEat
<
	SD extends ISoundDaLe,
	BD extends IBothDaNe & IBothDaSn,
	E extends EntityLe,
	I extends IMixE<BD, E> & IMixESoundDa<SD>,
	S extends ServerLe<SD, BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends FrameSFLoopFree<BD, E, I, S, MS>
{
	public SILeEat<SD, BD, E, I, S, MS> sileeat;

	public FrameSleFLoopFreeEat(S s, int index)
	{
		super(s, index);
	}

	@Override
	public void init()
	{
		super.init();
		this.sileeat = (SILeEat<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILeEat.ID);
	}

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
