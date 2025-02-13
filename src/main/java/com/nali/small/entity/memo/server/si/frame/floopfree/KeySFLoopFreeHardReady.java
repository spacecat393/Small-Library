package com.nali.small.entity.memo.server.si.frame.floopfree;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaS;
import com.nali.da.IBothDaSe;
import com.nali.list.entity.si.SIESit;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import net.minecraft.entity.Entity;

public class KeySFLoopFreeHardReady
<
	BD extends IBothDaE & IBothDaS & IBothDaSe,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends KeySFLoopFree<BD, E, I, S, MS>
{
	public SIESit<BD, E, I, S, MS> siesit;

	public KeySFLoopFreeHardReady(S s, byte key_data_index)
	{
		super(s, key_data_index);

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
		return (this.siesit.flag & SIESit.B_ANIMATE_HARD_READY) == SIESit.B_ANIMATE_HARD_READY;
	}

	@Override
	public void free()
	{
		this.siesit.flag &= 255 - SIESit.B_ANIMATE_HARD_READY;
	}
}
