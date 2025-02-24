package com.nali.list.entity.si;

import com.nali.da.IBothDaE;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.UUID;

public class SIEOwner
<
	BD extends IBothDaE,
	E extends Entity,
	I extends IMixE<BD, E>,
	S extends ServerE<BD, E, I, MS>,
	MS extends MixSIE<BD, E, I, S>
> extends SI<BD, E, I, S, MS>
{
	public static byte ID;

	public UUID uuid;

	public SIEOwner(S s)
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
//		this.uuid = this.s.ms.entityplayermp.getUniqueID();
	}

	@Override
	public void onUpdate()
	{

	}

	@Override
	public void writeFile(SIData sidata)
	{
		sidata.byte_array[sidata.index++] = (byte)(this.uuid == null ? 0 : 1);

		if (this.uuid != null)
		{
			ByteWriter.set(sidata.byte_array, this.uuid, sidata.index);
			sidata.index += 16;
		}
	}

	@Override
	public void readFile(SIData sidata)
	{
		byte uuid_state = sidata.byte_array[sidata.index++];

		if ((uuid_state & 1) == 1)
		{
			this.uuid = ByteReader.getUUID(sidata.byte_array, sidata.index);
			sidata.index += 16;
		}
	}

	@Override
	public int size()
	{
		return this.uuid == null ? 1 : 1 + 16;
	}

	public Entity getOwner()
	{
		if (this.uuid == null)
		{
			return null;
		}

		return this.s.worldserver.getEntityFromUuid(this.uuid);
	}
}