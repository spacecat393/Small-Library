package com.nali.small.entity.memo.server.si.frame.floopfree;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaS;
import com.nali.da.IBothDaSe;
import com.nali.list.entity.si.SIEPat;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import net.minecraft.entity.Entity;

public class KeySFLoopFreePat
<
	BD extends IBothDaE & IBothDaS & IBothDaSe,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends KeySFLoopFree<BD, E, I, S, MS>
{
	public SIEPat<BD, E, I, S, MS> siepat;

	public KeySFLoopFreePat(S s, byte key_data_index)
	{
		super(s, key_data_index);

		this.siepat = (SIEPat<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEPat.ID);
	}
//
//	@Override
//	public void init()
//	{
//		super.init();
//		this.siepat = (SIEPat<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEPat.ID);
//	}

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
