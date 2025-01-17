package com.nali.small.entity.memo.server.si.frame.floopinset;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaS;
import com.nali.da.IBothDaSe;
import com.nali.list.entity.si.SIEFindMove;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;

public class KeySleFLoopInSetEndMove
<
	BD extends IBothDaE & IBothDaS & IBothDaSe,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends KeySFLoopInSet<BD, E, I, S, MS>
{
	public SIEFindMove<BD, E, I, S, MS> siefindmove;

	public KeySleFLoopInSetEndMove(S s, byte key_data_index)
	{
		super(s, key_data_index);

		this.siefindmove = (SIEFindMove<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEFindMove.ID);
	}
//
//	@Override
//	public void init()
//	{
//		super.init();
//		this.siefindmove = (SILeFindMove<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILeFindMove.ID);
//	}

	@Override
	public boolean onUpdate()
	{
		return super.onUpdate() && this.siefindmove.endGoalT();
	}
}
