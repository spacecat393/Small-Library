package com.nali.list.entity.si;

import com.nali.da.IBothDaNe;
import com.nali.list.container.SmallContainer;
import com.nali.small.Small;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIEInv;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;

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
		E e = this.s.i.getE();
		this.s.ms.entityplayermp.openGui(Small.I, SmallContainer.ID, this.s.ms.entityplayermp.world, e.getEntityId(), this.s.worldserver.provider.getDimension(), EntityList.getID(e.getClass()));
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
