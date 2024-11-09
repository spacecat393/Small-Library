package com.nali.list.entity.si;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import net.minecraft.entity.EntityLivingBase;

public class SILeUseTo
<
	BD extends IBothDaNe,
	E extends EntityLivingBase,
	I extends IMixE<BD, E>,
	S extends ServerLe<BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
> extends SI<BD, E, I, S, MS>
{
	public static byte ID;

	public byte state;//on

	public SILeUseTo(S s)
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

	}

	@Override
	public void onUpdate()
	{

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
