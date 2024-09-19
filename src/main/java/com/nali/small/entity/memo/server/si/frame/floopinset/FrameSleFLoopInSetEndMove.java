package com.nali.small.entity.memo.server.si.frame.floopinset;

import com.nali.da.IBothDaNe;
import com.nali.list.entity.si.SILeFindMove;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.IMixESoundDa;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.sound.ISoundDaLe;

public class FrameSleFLoopInSetEndMove
<
	SD extends ISoundDaLe,
	BD extends IBothDaNe,
	E extends EntityLe,
	I extends IMixE<BD, E> & IMixESoundDa<SD>,
	S extends ServerLe<SD, BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends FrameSFLoopInSet<BD, E, I, S, MS>
{
	public SILeFindMove<SD, BD, E, I, S, MS> silefindmove;

	public FrameSleFLoopInSetEndMove(S s, int index)
	{
		super(s, index);

		this.silefindmove = (SILeFindMove<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILeFindMove.ID);
	}
//
//	@Override
//	public void init()
//	{
//		super.init();
//		this.silefindmove = (SILeFindMove<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILeFindMove.ID);
//	}

	@Override
	public boolean onUpdate()
	{
		return super.onUpdate() && this.silefindmove.endGoalT();
	}
}
