package com.nali.list.entity.si;

import com.nali.da.IBothDaE;
import com.nali.list.entity.ci.CIESound;
import com.nali.list.network.message.ClientMessage;
import com.nali.network.NetworkRegistry;
import com.nali.small.entity.IMixE;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.small.entity.memo.server.si.SI;
import com.nali.small.entity.memo.server.si.SIData;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class SIESound
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
	public byte flag = B_ON;//on

	public SIESound(S s)
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
			byte[] new_byte_array = new byte[1 + 8 + 1 + 4];
//			byte_array[0] = CCI.ID;
//			ByteWriter.set(byte_array, ByteReader.getInt(ms_byte_array, 1 + 8 + 1), 1);
//			byte_array[1 + 8] = CIESound.ID;
//			ByteWriter.set(byte_array, this.s.i.getE().getEntityId(), 1 + 8 + 1);
//			NetworkRegistry.I.sendTo(new ClientMessage(byte_array), this.s.ms.entityplayermp);
			this.s.setCCI(new_byte_array, CIESound.ID);
			ByteWriter.set(new_byte_array, ByteReader.getInt(byte_array, 1 + 8 + 1), 1 + 8 + 1);
			NetworkRegistry.I.sendToAll(new ClientMessage(new_byte_array));
		}
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
