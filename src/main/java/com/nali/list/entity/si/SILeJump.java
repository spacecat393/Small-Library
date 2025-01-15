//package com.nali.list.entity.si;
//
//import com.nali.da.IBothDaE;
//import com.nali.small.entity.EntityLe;
//import com.nali.small.entity.IMixE;
//import com.nali.small.entity.memo.server.ServerE;
//import com.nali.small.entity.memo.server.si.MixSIE;
//import com.nali.small.entity.memo.server.si.SI;
//import com.nali.small.entity.memo.server.si.SIData;
//
//public class SILeJump
//<
//	BD extends IBothDaE,
//	E extends EntityLe,
//	I extends IMixE<BD, E>,
//	S extends ServerE<BD, E, I, MS>,
//	MS extends MixSIE<BD, E, I, S>
//> extends SI<BD, E, I, S, MS>
//{
//	public static byte ID;
//
//	public boolean is_jumping;
//
//	public SILeJump(S s)
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
//
//	}
//
//	public void setJumping()
//	{
//		this.is_jumping = true;
//	}
//
//	@Override
//	public void onUpdate()
//	{
//		this.s.i.getE().setJumping(this.is_jumping);
//		this.is_jumping = false;
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
