package com.nali.list.entity.si;

import com.nali.da.IBothDaNe;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import com.nali.small.entity.memo.server.si.MixSIEInv;
import net.minecraft.entity.Entity;

public class SIEInvLockInv<SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, MS>, MS extends MixSIEInv<SD, BD, E, I, S>> extends SI<SD, BD, E, I, S, MS>
{
	public static byte ID;

	public SIEOwner<SD, BD, E, I, S, MS> sieowner;

	public byte state;//on

	public SIEInvLockInv(S s)
	{
		super(s);
	}

	@Override
	public void init()
	{
		this.sieowner = (SIEOwner<SD, BD, E, I, S, MS>)this.s.ms.si_map.get(SIEOwner.ID);
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

	public boolean canPass(Entity entity)
	{
		if ((this.state & 1) == 1)
		{
			return this.sieowner.uuid == null || entity.getUniqueID().equals(this.sieowner.uuid);
		}

		return true;
	}
}
