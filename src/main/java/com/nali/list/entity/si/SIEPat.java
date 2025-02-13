package com.nali.list.entity.si;

import com.nali.da.IBothDaE;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import com.nali.system.bytes.ByteReader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumParticleTypes;

public class SIEPat
<
	BD extends IBothDaE,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
> extends SI<BD, E, I, S, MS>
{
	public static byte ID;

	public final static byte B_T_PAT = 1;
	public byte flag;//t-pat

	public SIEPat(S s)
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
		this.flag |= B_T_PAT;
		E e = this.s.i.getE();
		this.s.worldserver.spawnParticle(EnumParticleTypes.HEART, e.posX, ByteReader.getFloat(byte_array, 1 + 8 + 1), e.posZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
	}

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
}
