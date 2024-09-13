package com.nali.list.entity.si;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import com.nali.small.entity.memo.server.si.MixSIE;
import net.minecraft.entity.Entity;

public class SIESit<SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, MS>, MS extends MixSIE<SD, BD, E, I, S>> extends SI<SD, BD, E, I, S, MS>
{
	public static byte ID;

	public byte state;//on t-ready/hard_ready f-ready/soft_ready

	public SIESit(S s)
	{
		super(s);
	}

	@Override
	public void init()
	{

	}

	@Override
	public void call()
	{
		if ((this.state & 1) == 1)
		{
			this.state |= 4;
			this.state &= 255-(2+1);
		}
		else
		{
			this.state |= 2+1;
			this.state &= 255-4;
		}
//		this.state ^= 1;
	}

	@Override
	public void onUpdate()
	{
		if ((this.state & 1) == 1)
		{
			this.state |= 1;
			this.s.ms.state &= 255-1;
		}
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
}