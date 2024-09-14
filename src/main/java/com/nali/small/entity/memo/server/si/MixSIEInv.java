package com.nali.small.entity.memo.server.si;

import com.nali.da.IBothDaNe;
import com.nali.list.entity.si.SIEInvLockInv;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import net.minecraft.entity.Entity;

public abstract class MixSIEInv
<
	BD extends IBothDaNe,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, ?>
> extends MixSIE<BD, E, I, S>
{
	public SIEInvLockInv<BD, E, I, S, ?> aileinvlockinv;
	public MixSIEInv(S s)
	{
		super(s);
		this.aileinvlockinv = (SIEInvLockInv<BD, E, I, S, ?>)this.si_map.get(SIEInvLockInv.ID);
	}

	public void call(byte id)
	{
		if (this.aileinvlockinv.canPass(this.s.ms.entityplayermp))
		{
			super.call(id);
		}
	}
}
