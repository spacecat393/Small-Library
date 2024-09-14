package com.nali.list.entity.si;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import com.nali.small.entity.memo.server.si.MixSIE;
import net.minecraft.entity.Entity;

public abstract class SIEPlayWithRSe
<
	S2 extends ServerE<BD2, E2, I2, A2>,
	BD2 extends IBothDaNe,
	E2 extends Entity,
	I2 extends IMixE<BD2, E2>,
	A2 extends MixSIE<BD2, E2, I2, S2>,
	BD extends IBothDaNe,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
> extends SI<BD, E, I, S, MS>
{
	public static byte ID;

//	public AIESit<SD2, BD2, E2, I2, S2, A2> siesit2;

	public S2 s2;

	public byte state;//R se> on play
	//S sle> on should_play -first_playwith-

	public SIEPlayWithRSe(S s)
	{
		super(s);
	}

//	@Override
//	public void init()
//	{
//
//	}
//
//	@Override
//	public void call()
//	{
//
//	}
//
//	@Override
//	public void onUpdate()
//	{
//
//	}

	@Override
	public void call()
	{
		this.state |= 2;
	}

	@Override
	public void writeFile(SIData sidata)
	{
		sidata.byte_array[sidata.index++] = this.state;
	}

	@Override
	public void readFile(SIData sidata)
	{
		this.state = sidata.byte_array[sidata.index++];
	}

	@Override
	public int size()
	{
		return 1;
	}
//	@Override
//	public void onUpdate()
//	{
//		if (this.s2 != null)
//		{
//			this.siesit2 = ((AIESit)this.s2.ms.si_map.get(AIESit.ID));
//		}
//	}
//
//	public void onFree()
//	{
//		this.siesit2 = null;
//	}
}
