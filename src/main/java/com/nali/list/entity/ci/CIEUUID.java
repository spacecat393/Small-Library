//package com.nali.list.entity.ci;
//
//import com.nali.da.IBothDaNe;
//import com.nali.da.IBothDaSn;
//import com.nali.da.client.IClientDaS;
//import com.nali.render.RenderS;
//import com.nali.small.entity.IMixE;
//import com.nali.small.entity.memo.client.ClientE;
//import com.nali.small.entity.memo.client.IClientSound;
//import com.nali.small.entity.memo.client.box.mix.MixBoxE;
//import com.nali.small.entity.memo.client.ci.CI;
//import com.nali.small.entity.memo.client.ci.MixCIE;
//import com.nali.small.entity.memo.client.render.mix.MixRenderSe;
//import com.nali.system.bytes.ByteReader;
//import net.minecraft.entity.Entity;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//@SideOnly(Side.CLIENT)
//public class CIEUUID<RC extends IClientDaS, R extends RenderS<BD, RC>, SD, BD extends IBothDaNe & IBothDaSn, E extends Entity, I extends IMixE<BD, E>, MC extends MixCIE<RC, R, SD, BD, E, I, MB, MR, C>, MB extends MixBoxE<RC, R, SD, BD, E, I, MC, MR, C>, MR extends MixRenderSe<RC, R, SD, BD, E, I, MC, MB, C>, C extends ClientE<RC, R, SD, BD, E, I, MC, MB, MR> & IClientSound> extends CI<RC, R, SD, BD, E, I, MC, MB, MR, C>
//{
//	public static byte ID;
//
//	public CIEUUID(C c)
//	{
//		super(c);
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
//		this.c.uuid = ByteReader.getUUID(this.c.mc.byte_array, 1 + 4 + 1);
//	}
//
//	@Override
//	public void onUpdate()
//	{
//	}
//
//	@Override
//	public void onReadNBT()
//	{
//
//	}
//}
