package com.nali.list.entity.si;

import com.nali.da.IBothDaNe;
import com.nali.list.container.SmallContainer;
import com.nali.small.Small;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import com.nali.small.entity.memo.server.si.MixSIEInv;
import net.minecraft.entity.Entity;

public class SIEInvOpenInv
<
	BD extends IBothDaNe,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS>,
	MS extends MixSIEInv<BD, E, I, S>
> extends SI<BD, E, I, S, MS>
{
	public static byte ID;

	public SIEInvOpenInv(S s)
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
		this.s.ms.entityplayermp.openGui(Small.I, SmallContainer.ID, this.s.ms.entityplayermp.world, this.s.i.getE().getEntityId(), this.s.worldserver.provider.getDimension(), 0);
	}

	@Override
	public void onUpdate()
	{

	}

	@Override
	public void writeFile(SIData sidata)
	{

	}

	@Override
	public void readFile(SIData sidata)
	{

	}

	@Override
	public int size()
	{
		return 0;
	}
}
