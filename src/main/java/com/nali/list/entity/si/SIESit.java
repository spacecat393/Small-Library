package com.nali.list.entity.si;

import com.nali.da.IBothDaE;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class SIESit
<
	BD extends IBothDaE,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
> extends SI<BD, E, I, S, MS>
{
	public static byte ID;

	public final static byte B_ON = 1;
	public final static byte B_ANIMATE_HARD_READY = 2;
	public final static byte B_ANIMATE_SOFT_READY = 4;
	public byte flag;//on t-ready/hard_ready f-ready/soft_ready

	public SIESit(S s)
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
		if ((this.flag & B_ON) == B_ON)
		{
			this.flag |= B_ANIMATE_SOFT_READY;
			this.flag &= 255 - (B_ANIMATE_HARD_READY + B_ON);
		}
		else
		{
			this.flag |= B_ANIMATE_HARD_READY + B_ON;
			this.flag &= 255 - B_ANIMATE_SOFT_READY;
		}
//		this.state ^= 1;
	}

	@Override
	public void onUpdate()
	{
		if ((this.flag & B_ON) == B_ON)
		{
			this.flag |= B_ON;
			this.s.ms.flag &= 255 - MixSIE.B_MAIN_WORK;
		}
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
}
