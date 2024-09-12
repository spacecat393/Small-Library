package com.nali.small.entity.memo.server.si.frame.tloopfb;

import com.nali.da.IBothDaNe;
import com.nali.list.entity.si.SIESit;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import net.minecraft.entity.Entity;

public class FrameSTLoopFBSit<SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, MS> & IServerS, MS extends MixSIE<SD, BD, E, I, S>> extends FrameSTLoopFB<SD, BD, E, I, S, MS>
{
	public SIESit<SD, BD, E, I, S, MS> siesit;

	public FrameSTLoopFBSit(S s, int index)
	{
		super(s, index);
	}

	@Override
	public void init()
	{
		super.init();
		this.siesit = (SIESit<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SIESit.ID);
	}

	@Override
	public boolean onUpdate()
	{
		return (this.siesit.state & 1) == 1 && super.onUpdate();
	}
}
