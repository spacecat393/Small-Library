package com.nali.list.entity.si;

import com.nali.da.IBothDaE;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class SIEPlayWithRSe
<
	S2 extends ServerE<BD2, E2, I2, A2>,
	BD2 extends IBothDaE,
	E2 extends Entity,
	I2 extends IMixE<BD2, E2>,
	A2 extends MixSIE<BD2, E2, I2, S2>,
	BD extends IBothDaE,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
> extends SI<BD, E, I, S, MS>
{
	public static byte ID;

//	public AIESit<SD2, BD2, E2, I2, S2, A2> siesit2;

	public S2 s2;

	public final static byte B_ON = 1;
	public final static byte B_PLAY = 2;
	public byte flag = B_ON;//R se> on play
	//S sle> on should_play -first_playwith-

	public SIEPlayWithRSe(S s)
	{
		super(s);
	}

	@Override
	public void init()
	{

	}

	@Override
	public void call(EntityPlayerMP entityplayermp, byte[] byte_array)
	{
//		this.state |= 2;
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
	public void onUpdate()
	{

	}

	@Override
	public void writeFile(SIData sidata)
	{
		sidata.byte_array[sidata.index++] = this.flag;
	}

	@Override
	public void readFile(SIData sidata)
	{
		this.flag = sidata.byte_array[sidata.index++];
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
