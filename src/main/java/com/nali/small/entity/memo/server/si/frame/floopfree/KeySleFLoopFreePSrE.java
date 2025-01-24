package com.nali.small.entity.memo.server.si.frame.floopfree;

import com.nali.da.IBothDaE;
import com.nali.da.IBothDaO;
import com.nali.da.IBothDaS;
import com.nali.da.IBothDaSe;
import com.nali.list.entity.si.SIEPat;
import com.nali.list.entity.si.SIESit;
import com.nali.list.entity.si.SILeEat;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.IMixES;
import com.nali.small.entity.IMixESInv;
import com.nali.small.entity.memo.server.IServerS;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;

public class KeySleFLoopFreePSrE
<
	BD extends IBothDaE & IBothDaO & IBothDaS & IBothDaSe,
	E extends EntityLe,
	I extends IMixE<BD, E> & IMixES & IMixESInv,
	S extends ServerLe<BD, E, I, MS> & IServerS,
	MS extends MixSIE<BD, E, I, S>
> extends KeySFLoopFree<BD, E, I, S, MS>
{
	public SIEPat<BD, E, I, S, MS> siepat;
	public SIESit<BD, E, I, S, MS> siesit;
	public SILeEat<BD, E, I, S, MS> sileeat;

	public KeySleFLoopFreePSrE(S s, byte key_data_index)
	{
		super(s, key_data_index);

		this.siepat = (SIEPat<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEPat.ID);
		this.siesit = (SIESit<BD, E, I, S, MS>)this.s.ms.si_map.get(SIESit.ID);
		this.sileeat = (SILeEat<BD, E, I, S, MS>)this.s.ms.si_map.get(SILeEat.ID);
	}
//
//	@Override
//	public void init()
//	{
//		super.init();
//		this.siepat = (SIEPat<BD, E, I, S, MS>)this.s.ms.si_map.get(SIEPat.ID);
//		this.siesit = (SIESit<BD, E, I, S, MS>)this.s.ms.si_map.get(SIESit.ID);
//		this.sileeat = (SILeEat<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SILeEat.ID);
//	}

	@Override
	public boolean step()
	{
		return (this.siepat.state & 1) == 1 || (this.siesit.state & 4) == 4 || (this.sileeat.state & 1+2) != 0;
	}

	@Override
	public void free()
	{
		this.siepat.state &= 255-1;
		this.siesit.state &= 255-4;
		this.sileeat.state &= 255-(1+2);
	}
}
