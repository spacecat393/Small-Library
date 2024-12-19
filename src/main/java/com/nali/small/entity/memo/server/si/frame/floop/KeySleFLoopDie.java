package com.nali.small.entity.memo.server.si.frame.floop;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaS;
import com.nali.da.IBothDaSe;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;

public class KeySleFLoopDie
<
	BD extends IBothDaE & IBothDaS & IBothDaSe,
	E extends EntityLe,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends KeySFLoop<BD, E, I, S, MS>
{
	public KeySleFLoopDie(S s, byte key_data_index)
	{
		super(s, key_data_index);
	}

	@Override
	public boolean onUpdate()
	{
		return this.step() && super.onUpdate();
	}

	public boolean step()
	{
		return this.s.isZeroMove(this.s.i.getE());
	}
}
