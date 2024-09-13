//package com.nali.list.entity.si;
//
//import com.nali.da.IBothDaNe;
//import com.nali.list.entity.ci.CIEUUID;
//import com.nali.list.network.message.ClientMessage;
//import com.nali.list.network.method.client.CCI;
//import com.nali.network.NetworkRegistry;
//import com.nali.small.entity.IMixE;
//import com.nali.small.entity.memo.server.ServerE;
//import com.nali.small.entity.memo.server.si.MixSIE;
//import com.nali.small.entity.memo.server.si.SI;
//import com.nali.small.entity.memo.server.si.SIData;
//import com.nali.system.bytes.ByteWriter;
//import net.minecraft.entity.Entity;
//
//public class SIEUUID<SD, BD extends IBothDaNe, E extends Entity, I extends IMixE<SD, BD, E>, S extends ServerE<SD, BD, E, I, MS>, MS extends MixSIE<SD, BD, E, I, S>> extends SI<SD, BD, E, I, S, MS>
//{
//	public static byte ID;
//
//	public SIEUUID(S s)
//	{
//		super(s);
//	}
//
//	@Override
//	public void init()
//	{
//
//	}
//
//	@Override
//	public void call()
//	{
//		E e = this.s.i.getE();
//
//		byte[] byte_array = new byte[1 + 4 + 1 + 16];
//		byte_array[0] = CCI.ID;
//		ByteWriter.set(byte_array, e.getEntityId(), 1);
//		byte_array[1 + 4] = CIEUUID.ID;
//		ByteWriter.set(byte_array, e.getUniqueID(), 1 + 4 + 1);
//		NetworkRegistry.I.sendTo(new ClientMessage(byte_array), this.s.ms.entityplayermp);
//	}
//
//	@Override
//	public void onUpdate()
//	{
//	}
//
//	@Override
//	public void writeFile(SIData sidata)
//	{
//	}
//
//	@Override
//	public void readFile(SIData sidata)
//	{
//	}
//
//	@Override
//	public int size()
//	{
//		return 0;
//	}
//}
